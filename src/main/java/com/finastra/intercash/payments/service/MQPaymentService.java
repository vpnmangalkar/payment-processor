/**
 * 
 */
package com.finastra.intercash.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author u722954
 *
 */
@Service
public class MQPaymentService {

	private static final String QUEUE_NAME = "DEV.QUEUE.1";
	
	@Autowired
	private JmsTemplate jmsTemplate;

	
	
	
	private String sendPaymentMessage() {
		try {
			jmsTemplate.convertAndSend(QUEUE_NAME, "Message Body");
			return "OK";
			
			//jmsTemplate.receive("");
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}
	
	
	

}
