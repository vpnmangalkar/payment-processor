package com.finastra.intercash.payments.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finastra.intercash.payments.dao.CommonDao;
import com.finastra.intercash.payments.entity.BankPaymentMethod;
import com.finastra.intercash.payments.entity.PaymentInitiation;

/**
 * @author Vipin.Mangalkar
 *
 */
@Component
public class CreateFileData {

	@Autowired
	private CommonDao commonDao;
	
	@Transactional
	public String createFileData(String payId, BankPaymentMethod ftpDetails, String fileName) {
		
		String downloadFileId = commonDao.getDownloadFileId();
		
		commonDao.insertFileData(payId, downloadFileId, ftpDetails.getPaymentMode(), fileName);
		
		return downloadFileId;
	}
}
