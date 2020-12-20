package com.finastra.intercash.payments.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finastra.intercash.payments.constant.PaymentConstant;
import com.finastra.intercash.payments.dao.BankPaymentMethodChannelRepository;
import com.finastra.intercash.payments.entity.BankPaymentMethod;
import com.finastra.intercash.payments.entity.PaymentInitiation;

/**
 * @author Vipin.Mangalkar
 *
 */
@Service
public class PaymentService {

	@Autowired
	private FilePaymentService filePaymentService;

	@Autowired
	private BankPaymentMethodChannelRepository bankPaymentMethodChannelRepository;

	private List<String> fileTypeList = Arrays.asList("FTP", "SFTP", "HTH", "FILE");

	/**
	 * Method to process payments 
	 * <ul>
	 * <li>1. Poll Payment From Payment Queue.</li> 
	 * <li>2. Read Payment Method Type from BankId </li>
	 * <li>3. Assign Payment Processing Task to respective payment handler service (MQ/FILE/API)</li> 
	 * <li>4. Update the Payment Status</li>
	 * 
	 * @param paymentToProcess
	 */
	public void process(PaymentInitiation paymentToProcess) {

		BankPaymentMethod bankPaymentMethod = bankPaymentMethodChannelRepository.findByBankIdAndPaymentMode(paymentToProcess.getRecipientEntityBankId(), PaymentConstant.PAY_INIT);

		if (fileTypeList.contains(bankPaymentMethod.getChannelType())) {
			filePaymentService.processPayment(paymentToProcess, bankPaymentMethod);
		}

		// MQ

		// API
	}

}
