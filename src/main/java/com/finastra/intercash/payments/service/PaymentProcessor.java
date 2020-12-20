package com.finastra.intercash.payments.service;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.finastra.intercash.payments.constant.PaymentConstant;
import com.finastra.intercash.payments.dao.PaymentInitiationRepository;
import com.finastra.intercash.payments.entity.PaymentInitiation;

/**
 * @author Vipin.Mangalkar
 *
 */
@Service
public class PaymentProcessor {

	private BlockingQueue<PaymentInitiation> paymentsQueue = new ArrayBlockingQueue<>(1000);

	@Autowired
	private PaymentInitiationRepository paymentInitiationRepository;

	@Autowired
	private PaymentService paymentService;

	/**
	 * Method performs
	 * <ul>
	 * <li>1. Read Payment which is in Initiated Status.</li>
	 * <li>2. Add that payments to payment queue for processing</li>
	 * </ul>
	 */
	@Scheduled(fixedDelay = 10000)
	public void readInitiatedPayment() {

		// Get payments which is in INITIATED Status
		List<PaymentInitiation> paymentList = paymentInitiationRepository.findAllByStatus(PaymentConstant.INITIATED);

		// add payments in queue if that payment is not exist in queue
		for (PaymentInitiation paymentItem : paymentList) {

			if (!paymentsQueue.contains(paymentItem))
				paymentsQueue.offer(paymentItem);
		}

	}

	/**
	 * Method to process payments which is in payment queue
	 */
	@Scheduled(fixedDelay = 10000)
	private void processPayment() {

		while (!paymentsQueue.isEmpty()) {

			paymentService.process(paymentsQueue.poll());
		}
	}

}
