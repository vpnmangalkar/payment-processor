/**
 * 
 */
package com.finastra.intercash.payments.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Vipin.Mangalkar
 *
 */
public class IdGenerator {

	public static String generateId(int sequenceNumber) {

		String padStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String tempStr = "";
		String id = "";
		int sequenceNumberLength = 0;
		int mod = 0;

		try {
			sequenceNumberLength = String.valueOf(sequenceNumber).length();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			String todayDateStr = dtf.format(LocalDate.now());
			id = todayDateStr;

			while (sequenceNumber != 0) {
				mod = sequenceNumber % 36;
				tempStr = padStr.substring(mod, mod + 1) + tempStr;
				sequenceNumber = Math.abs(sequenceNumber / 36);
			}

			id = id.concat(String.format("%-" + sequenceNumberLength + "s", tempStr).replace(" ", "0"));
		} catch (Exception e) {
			id = null;
			e.printStackTrace();
		}

		return id;
	}
}
