/**
 * 
 */
package com.finastra.intercash.payments.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author u722954
 *
 */
public class DateFormatUtility {

	public static String getCurrentDate(String format) {
		
		LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return now.format(formatter);
	}
}
