package com.finastra.intercash.payments.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "auditId" })
@Table(name = "bank_details_audit")
public class BankDetailsAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id", columnDefinition = "SERIAL")
	private Long auditId;

	@Column(name = "bank_id", nullable = false)
	private String bankId;

	@Column(name = "bank_name", nullable = false)
	private String bankName;

	@Column(name = "sys_controlling_branch", nullable = false)
	private String sysControllingBranch;

	@Column(name = "swift_code", nullable = false)
	private String swiftCode;

	@Column(name = "bic_code", nullable = false)
	private String bicCode;

	@Column(name = "micr_code", nullable = false)
	private String micrCode;

	@Column(name = "country_code", nullable = false)
	private String countryCode;
	
	@Column(name = "state", nullable = true)
	private String state;
	
	@Column(name = "city", nullable = true)
	private String city;

	@Column(name = "address_1", nullable = false)
	private String address1;

	@Column(name = "address_2", nullable = true)
	private String address2;

	@Column(name = "address_3", nullable = true)
	private String address3;

	@Column(name = "address_4", nullable = true)
	private String address4;

	@Column(name = "address_5", nullable = true)
	private String address5;
	
	@Column(name = "dummy_client_code", nullable = false)
	private String dummyClientCode;
	
	@Column(name = "dummy_package_code", nullable = false)
	private String dummyPackageCode;

	@Column(name = "action", nullable = false)
	private String action;

	@Column(name = "action_by", nullable = false)
	private String actionBy;

	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

	/**
	 * @param bankDetails
	 * @param created
	 */
	public BankDetailsAudit(BankDetails bankDetails, String action, String user) {
		this.bankId = bankDetails.getBankId();
		this.bankName = bankDetails.getBankName();
		this.sysControllingBranch = bankDetails.getSysControllingBranch();
		this.swiftCode = bankDetails.getSwiftCode();
		this.micrCode = bankDetails.getMicrCode();
		this.bicCode = bankDetails.getBicCode();
		this.countryCode = bankDetails.getCountryCode();
		this.address1 = bankDetails.getAddress1();
		this.address2 = bankDetails.getAddress2();
		this.address3 = bankDetails.getAddress3();
		this.address4 = bankDetails.getAddress4();
		this.address5 = bankDetails.getAddress5();
		this.dummyClientCode = bankDetails.getDummyClientCode();
		this.dummyPackageCode = bankDetails.getDummyPackageCode();
		this.action = action;
		this.actionBy = user;
	}
}
