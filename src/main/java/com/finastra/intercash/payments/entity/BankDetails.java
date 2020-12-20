package com.finastra.intercash.payments.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
@EqualsAndHashCode(of = { "bankId" })
@Table(name = "bank_details_mst")
public class BankDetails {

	@Id
	@GenericGenerator(name = "bank_id", strategy = "com.finastra.intercash.payments.entity.BankIdGenerator")
	@GeneratedValue(generator = "bank_id")
	@Column(name = "bank_id", length = 25)
	private String bankId;

	@Column(name = "bank_name", nullable = false, length = 100)
	private String bankName;

	@Column(name = "sys_controlling_branch", nullable = false, length = 5)
	private String sysControllingBranch;

	@Column(name = "swift_code", nullable = false, length = 25)
	private String swiftCode;

	@Column(name = "bic_code", nullable = false, length = 25)
	private String bicCode;

	@Column(name = "micr_code", nullable = false, length = 25)
	private String micrCode;

	@Column(name = "country_code", nullable = false, length = 5)
	private String countryCode;

	@Column(name = "state", nullable = true, length = 10)
	private String state;

	@Column(name = "city", nullable = true, length = 20)
	private String city;

	@Column(name = "address_1", nullable = false, length = 40)
	private String address1;

	@Column(name = "address_2", nullable = true, length = 40)
	private String address2;

	@Column(name = "address_3", nullable = true, length = 40)
	private String address3;

	@Column(name = "address_4", nullable = true, length = 40)
	private String address4;

	@Column(name = "address_5", nullable = true, length = 40)
	private String address5;

	@Column(name = "dummy_client_code", nullable = false, length = 20)
	private String dummyClientCode;

	@Column(name = "dummy_pck_code", nullable = false, length = 20)
	private String dummyPackageCode;

	@Column(name = "is_active", nullable = false, length = 1)
	private String isActive = "Y";
	
	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

}
