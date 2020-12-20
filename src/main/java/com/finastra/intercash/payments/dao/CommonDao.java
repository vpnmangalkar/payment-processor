/**
 * 
 */
package com.finastra.intercash.payments.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.finastra.intercash.payments.utils.DateFormatUtility;
import com.finastra.intercash.payments.utils.IdGenerator;

/**
 * @author u722954
 *
 */
@Repository
public class CommonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param downloadFileId
	 * @return
	 */
	public List<String> getFileData(String downloadFileId) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT file_data FROM dnld_file_data ");
		query.append(" WHERE dnld_id = ? ");
		query.append(" ORDER BY seq_nmbr  ");

		return jdbcTemplate.queryForList(query.toString(), String.class, new Object[] { downloadFileId });
	}

	public void insertFileData(String payId, String downloadFileId, String requestType, String fileName) {

		StringBuilder query = new StringBuilder();

		query.append(" select p.recipient_entity_code,  ");
		query.append(" m.default_package_name,  ");
		query.append(" p.inter_seller_txn_ref_nmbr,  ");
		query.append(" p.nostr_dr_amount,  ");
		query.append(" p.bene_cr_amount,  ");
		query.append(" p.bene_code,  ");
		query.append(" p.bene_description,  ");
		query.append(" p.bene_mobile, ");
		query.append(" p.bene_email, ");
		query.append(" p.bene_bank_code, ");
		query.append(" p.bene_branch_code, ");
		query.append(" p.bene_bank_bic,  ");
		query.append(" p.bene_bank_swift, ");
		query.append(" p.bene_addr, ");
		query.append(" p.bene_account_nmbr, ");
		query.append(" p.bene_ccy, ");
		query.append(" p.bene_account_iban, ");
		query.append(" p.bene_bank_addr ");
		query.append(" from bank_payment_method m, nostr_account_details a, pay_initiation p ");
		query.append(" 		where m.bank_id = p.recipient_entity_bank_id ");
		query.append(" 		and a.partner_bank_id = m.bank_id ");
		query.append(" 		and p.pay_id = ? ");

		StringBuilder h = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder t = new StringBuilder();

		jdbcTemplate.query(query.toString(), new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					// file h
					h.append("H," + rs.getString("recipient_entity_code")).append("," + rs.getString("default_package_name")).append(", ,").append(DateFormatUtility.getCurrentDate("yyyyMMdd")).append(",")
							.append(rs.getString("inter_seller_txn_ref_nmbr"));

					// file d
					d.append("D,").append(rs.getString("inter_seller_txn_ref_nmbr")).append(",").append(DateFormatUtility.getCurrentDate("yyyyMMdd")).append(",").append(rs.getString("nostr_dr_amount")).append(
							",").append(rs.getString("bene_cr_amount")).append(",").append(rs.getString("bene_code")).append(", ,").append(rs.getString("bene_description")).append(",").append(rs
									.getString("bene_mobile")).append(",").append(rs.getString("bene_email")).append(",").append(rs.getString("bene_bank_code")).append(",").append(rs.getString(
											"bene_branch_code")).append(",").append(rs.getString("bene_bank_bic")).append(",").append(rs.getString("bene_bank_swift")).append(",").append(rs.getString(
													"bene_addr")).append(",").append(rs.getString("bene_account_nmbr")).append(",").append(rs.getString("bene_ccy")).append(",").append(rs.getString(
															"bene_account_iban")).append(",").append(rs.getString("bene_bank_addr"));

					// file t
					t.append("T,1,").append(rs.getDouble("nostr_dr_amount")).append(",").append(rs.getDouble("bene_cr_amount"));

				}
			}
		}, new Object[] { payId });

		StringBuilder insertFileName = new StringBuilder("INSERT INTO dnld_file_name(dnld_id, file_name, request_type, pay_id, date_time)");
		insertFileName.append("values (?, ?, ?, ?, now())");

		jdbcTemplate.update(insertFileName.toString(), downloadFileId, fileName, requestType, payId);

		StringBuilder insertFileData = new StringBuilder("INSERT INTO dnld_file_data(dnld_id, seq_nmbr, file_data, date_time)");
		insertFileData.append("values (?, ?, ?, now())");

		jdbcTemplate.update(insertFileData.toString(), downloadFileId, 1, h);
		jdbcTemplate.update(insertFileData.toString(), downloadFileId, 2, d);
		jdbcTemplate.update(insertFileData.toString(), downloadFileId, 3, t);
	}

	/**
	 * @return
	 */
	public String getDownloadFileId() {
		
		try(Connection conn = jdbcTemplate.getDataSource().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select nextval('file_download_id_sequence') as nextval");) {

			Integer sequenceNumber = 0;
			while(rs.next())
			{
				sequenceNumber = rs.getInt("nextval");
			}
			return IdGenerator.generateId(sequenceNumber);
		} catch (SQLException e) {
			return null;
		}
	}
	
}
