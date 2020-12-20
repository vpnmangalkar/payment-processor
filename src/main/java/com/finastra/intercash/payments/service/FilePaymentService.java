package com.finastra.intercash.payments.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.finastra.intercash.payments.constant.PaymentConstant;
import com.finastra.intercash.payments.dao.BankFileConfigRepository;
import com.finastra.intercash.payments.dao.CommonDao;
import com.finastra.intercash.payments.dao.PaymentInitiationRepository;
import com.finastra.intercash.payments.dao.PaymentStatusRepository;
import com.finastra.intercash.payments.entity.BankFileConfig;
import com.finastra.intercash.payments.entity.BankPaymentMethod;
import com.finastra.intercash.payments.entity.PaymentInitiation;
import com.finastra.intercash.payments.entity.PaymentStatus;
import com.finastra.intercash.payments.utils.DateFormatUtility;
import com.finastra.intercash.payments.utils.FTPUploader;
import com.finastra.intercash.payments.utils.SFTPUploader;

import lombok.extern.log4j.Log4j2;

/**
 * @author Vipin.Mangalkar Class to process file based payment transaction
 */
@Service
@Log4j2
public class FilePaymentService {

	@Autowired
	private PaymentInitiationRepository paymentInitiationRepository;

	@Autowired
	private PaymentStatusRepository paymentStatusRepository;

	@Autowired
	private BankFileConfigRepository bankFileConfigRepository;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private CreateFileData createFileData;

	/**
	 * Method Process file based payment
	 * 
	 * 1. Set Payment Status to INPROCESS 2. Create File Name 3. Create and Save
	 * File Data -> Get File Download Id 4. Create File to Transfer (Upload to
	 * FTP/SFTP) 5. Upload File 6. Update Payment Status to SENTTOHOST 7. If failed
	 * to Process File Payment then Update Payment Status to FAILED and rollback the
	 * changes.
	 * 
	 * @param paymentToProcess
	 * @param bankPaymentMethod
	 * @return
	 */
	public void processPayment(PaymentInitiation paymentToProcess, BankPaymentMethod ftpDetails) {

		log.info("Started proccessing payment for payment id = {}", paymentToProcess.getPayId());

		try {

			updatePaymentStatus(paymentToProcess.getPayId(), PaymentConstant.INPROCESS);

			String fileName = createFileName(paymentToProcess.getFromBankId());

			String downloadFileId = createFileData.createFileData(paymentToProcess.getPayId(), ftpDetails, fileName);

			List<String> fileDataList = commonDao.getFileData(downloadFileId);

			createAndUploadFile(downloadFileId, fileName, ftpDetails, fileDataList);

			updatePaymentStatus(paymentToProcess.getPayId(), PaymentConstant.SENTTOHOST);
		} catch (Exception e) {
			log.error("Error while proccessing payment for payment id = {}, exception = {}", paymentToProcess.getPayId(), e);
			updatePaymentStatus(paymentToProcess.getPayId(), PaymentConstant.FAILED);
		}

		log.info("Payment proccessing completed for payment id = {}", paymentToProcess.getPayId());
	}

	/**
	 * @param fromBankId
	 * @return
	 * @throws Exception
	 */
	private String createFileName(String bankId) throws Exception {

		String fileName = "";

		Optional<BankFileConfig> optional = bankFileConfigRepository.findByBankId(bankId);

		if (!optional.isPresent()) {
			throw new Exception("Bank file details not found for bankId " + bankId);
		}

		BankFileConfig bankFileConfig = optional.get();

		if ("Y".equals(bankFileConfig.getAttachEntityCode())) {
			fileName += "ENTITY_CODE";
		}

		if (!ObjectUtils.isEmpty(bankFileConfig.getFilePrefix())) {
			fileName += bankFileConfig.getFilePrefix();
		}

		if (!ObjectUtils.isEmpty(bankFileConfig.getFileMiddle())) {
			fileName += bankFileConfig.getFileMiddle();
		}

		if ("Y".equals(bankFileConfig.getAttachDate())) {
			fileName += DateFormatUtility.getCurrentDate("ddMMyyyyHHmmss");
		}

		return fileName.concat(bankFileConfig.getFile_ext());
	}

	/**
	 * @param payid
	 * @param status
	 */
	@Transactional(rollbackOn = Exception.class)
	private void updatePaymentStatus(String payId, String status) {

		List<PaymentStatus> statusList = paymentStatusRepository.findByPayId(payId);

		PaymentStatus paymentStatus = statusList.get(statusList.size()-1);

		paymentStatus.setStatusId(null);
		paymentStatus.setStatus(status);
		paymentStatus.setSerialId(paymentStatus.getSerialId() + 1);
		paymentStatusRepository.saveAndFlush(paymentStatus);

		Optional<PaymentInitiation> paymentOptional = paymentInitiationRepository.findById(payId);

		PaymentInitiation paymentInitiation = paymentOptional.get();
		paymentInitiation.setStatus(status);
		paymentInitiationRepository.saveAndFlush(paymentInitiation);
	}

	/**
	 * @param downloadFileId
	 * @param fileName
	 * @param ftpDetails
	 * @param fileDataList
	 * @return
	 */
	private void createAndUploadFile(String downloadFileId, String fileName, BankPaymentMethod ftpDetails, List<String> fileDataList) throws Exception {

		String ftpTempDir = ftpDetails.getFileTempDir();
		String ftpPath = ftpDetails.getFtpPath();
		String ftpType = ftpDetails.getFtpType();

		String compileFilePath = "D:/dev/files/";
		String remoteFilePath = "";

		if (ftpTempDir.substring(ftpTempDir.length() - 1).equals("/") || ftpTempDir.substring(ftpTempDir.length() - 1).equals("/")) {
			compileFilePath += ftpTempDir + fileName;
		} else {
			compileFilePath += ftpTempDir + "/" + fileName;
		}

		if (ftpPath.substring(ftpPath.length() - 1).equals("/") || ftpPath.substring(ftpPath.length() - 1).equals("/")) {
			remoteFilePath = ftpPath + fileName;
		} else {
			remoteFilePath = ftpTempDir + "/" + fileName;
		}

		createFile(downloadFileId, compileFilePath, fileDataList);

		if ("F".equalsIgnoreCase(ftpType)) // FTP
		{
			sendFileFTP(compileFilePath, remoteFilePath, ftpDetails);
		} else if ("S".equalsIgnoreCase(ftpType)) // SFTP
		{
			sendFileSFTP(compileFilePath, remoteFilePath, ftpDetails);
		}
	}

	/**
	 * @param downloadFileId
	 * @param compileFilePath
	 * @param ftpDetails
	 * @param fileDataList
	 * @return
	 */
	private void createFile(String downloadFileId, String compileFilePath, List<String> fileDataList) throws Exception {

		log.info("Started Creating File for downloadFileId = {}, compileFilePath = {}", downloadFileId, compileFilePath);

		File file = new File(compileFilePath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();

		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

			for (String line : fileDataList) {

				line = String.format("%s", line);

				fileWriter.newLine();
				fileWriter.write(line);
			}

		} catch (Exception e) {
			log.error("Exception while creating file for downloadFileId = {}, exception = {} ", downloadFileId, e);
			throw e;
		}
		log.info("File Created Successfully for downloadFileId = {}, compileFilePath = {}", downloadFileId, compileFilePath);
	}

	/**
	 * @param compileFilePath
	 * @param remoteFilePath
	 * @param ftpDetails
	 * @return
	 */
	private void sendFileFTP(String localFileFullName, String remoteFileFullName, BankPaymentMethod ftpDetails) throws Exception {

		log.info("Uploading file (FTP) Started for file remoteFileFullName = {}", remoteFileFullName);

		try {
			FTPUploader ftpUploader = new FTPUploader(ftpDetails.getFtpServer(), ftpDetails.getFtpPort(), ftpDetails.getFtpUser(), ftpDetails.getFtpPwd());
			ftpUploader.uploadFile(localFileFullName, remoteFileFullName);
			ftpUploader.disconnect();
		} catch (Exception e) {
			log.error("Error while uplading file to ftp server, remoteFilePath = {}, {}", remoteFileFullName, e);
			throw e;
		}
		log.info("Uploading file (FTP) Completed for file remoteFilePath = {}", remoteFileFullName);
	}

	/**
	 * @param compileFilePath
	 * @param remoteFilePath
	 * @param ftpDetails
	 */
	private void sendFileSFTP(String localFileFullName, String remoteFileFullName, BankPaymentMethod ftpDetails) throws Exception {

		log.info("Uploading file (SFTP) Started for file remoteFilePath = {}", remoteFileFullName);

		try {
			SFTPUploader sftpUploader = new SFTPUploader();
			sftpUploader.uploadFile(ftpDetails.getFtpServer(), ftpDetails.getFtpPort(), ftpDetails.getFtpUser(), ftpDetails.getFtpPwd(), localFileFullName, remoteFileFullName);
		} catch (Exception e) {
			log.error("Error while uplading file to ftp server, remoteFilePath = {}, e = {}", remoteFileFullName, e);
			throw e;
		}
		log.info("Uploading file (SFTP) Completed for file remoteFilePath = {}", remoteFileFullName);
	}

}
