package com.finastra.intercash.payments.entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
/**
 * @author Vipin.Mangalkar
 * Class to Generate AccountId for NostroAccountDetails
 *  
 */
public class NostroAccountIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String padStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String tempStr = "";
		String bankId = "";
		int sequenceNumber = 0;
		int sequenceNumberLength = 0;
		int mod = 0;

		try {
			sequenceNumber = getSequence(session);
			sequenceNumberLength = String.valueOf(sequenceNumber).length();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			String todayDateStr = dtf.format(LocalDate.now());
			bankId = todayDateStr;
			
			while (sequenceNumber != 0) {
				mod = sequenceNumber % 36;
				tempStr = padStr.substring(mod, mod + 1) + tempStr;
				sequenceNumber = Math.abs(sequenceNumber / 36);
			}

			bankId = bankId.concat(String.format("%-" + sequenceNumberLength + "s", tempStr).replace(" ", "0"));
		} catch (Exception e) {
			bankId = null;
			e.printStackTrace();
		}

		return bankId;
	}

	private int getSequence(SharedSessionContractImplementor session) throws SQLException {

		Connection connection = session.connection();

		Statement statement = connection.createStatement();

		ResultSet rs = statement.executeQuery( " select nextval ('settlement_account_id_sequence') ");

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}
}