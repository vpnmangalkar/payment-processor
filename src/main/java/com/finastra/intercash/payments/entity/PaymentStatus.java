package com.finastra.intercash.payments.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@EqualsAndHashCode(of = { "statusId" })
@Table(name = "payment_status")
public class PaymentStatus {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id", columnDefinition = "SERIAL")
	private Long statusId;

	@Column(name = "pay_id", length = 25, nullable = false)
	private String payId;

	@Column(name = "serial_id", length = 2, nullable = false)
	private Integer serialId = 1;

	@Column(name = "status", length = 20, nullable = false)
	private String status;

	@Column(name = "error_code", length = 50, nullable = true)
	private String errorCode;

	@Column(name = "error_reason", length = 500, nullable = true)
	private String errorReason;

	@Column(name = "req_res_id", length = 25, nullable = true)
	private String reqResId;

	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

	/**
	 * @param payId
	 * @param status
	 */
	public PaymentStatus(String payId, String status) {
		super();
		this.payId = payId;
		this.status = status;
	}
	
	
}
