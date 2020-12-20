package com.finastra.intercash.payments.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vipin.Mangalkar
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "paymentMethodId" })
@Table(name = "bank_payment_method", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "bank_id", "payment_mode" }) })
public class BankPaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pay_method_id", columnDefinition = "SERIAL")
	private Long paymentMethodId;

	@Column(name = "bank_id", nullable = false, length = 25)
	private String bankId;

	@Column(name = "payment_mode", nullable = false, length = 25)
	private String paymentMode;

	@Column(name = "channel_type", nullable = false, length = 10)
	private String channelType;

	@Column(name = "api_url", nullable = true, length = 255)
	private String apiUrl;

	@Column(name = "api_user_id", nullable = true, length = 50)
	private String apiUserId;

	@Column(name = "api_pwd", nullable = true, length = 50)
	private String apiPassword;

	@Column(name = "ftp_type", nullable = true, length = 1)
	private String ftpType;

	@Column(name = "ftp_server", nullable = true, length = 25)
	private String ftpServer;

	@Column(name = "ftp_user", nullable = true, length = 25)
	private String ftpUser;

	@Column(name = "ftp_pwd", nullable = true, length = 25)
	private String ftpPwd;

	@Column(name = "ftp_port", nullable = true, length = 10)
	private String ftpPort;

	@Column(name = "ftp_path", nullable = true, length = 255)
	private String ftpPath;

	@Column(name = "file_temp_dir", nullable = true, length = 255)
	private String fileTempDir;

	@Column(name = "mq_id", nullable = true, length = 50)
	private String mqId;

	@Column(name = "mq_session_name", nullable = true, length = 50)
	private String mqSessionName;

	@Column(name = "mq_req_q_name", nullable = true, length = 50)
	private String mqReqQName;

	@Column(name = "mq_res_q_name", nullable = true, length = 50)
	private String mqResQName;

	@Column(name = "mq_conn_name", nullable = true, length = 50)
	private String mqConnectionName;

	@Column(name = "mq_user", nullable = true, length = 50)
	private String mqUser;

	@Column(name = "mq_pwd", nullable = true, length = 50)
	private String mqPassword;

	@Column(name = "msg_correlated_flag", nullable = true, length = 1)
	private String msgCorrelatedFlag;

	@Column(name = "seller_code", nullable = false)
	private String sellerCode;

	@Column(name = "default_package_name", nullable = false)
	private String defaultPackageCode;

	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

}
