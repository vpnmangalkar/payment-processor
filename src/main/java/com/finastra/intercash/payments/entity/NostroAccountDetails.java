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

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "accountId" })
@Table(name = "nostr_account_details")
public class NostroAccountDetails {

	@Id
	@GenericGenerator(name = "account_id", strategy = "com.finastra.intercash.payments.entity.NostroAccountIdGenerator")
	@GeneratedValue(generator = "account_id")
	@Column(name = "account_id", length = 25)
	private String accountId;

	@Column(name = "owner_bank_id", nullable = false, length = 25)
	private String ownerBankId;

	@Column(name = "partner_bank_id", nullable = false, length = 25)
	private String partnerBankId;

	@Column(name = "owner_swift_code", nullable = true, length = 25)
	private String ownerSwiftCode;

	@Column(name = "owner_bic_code", nullable = true, length = 25)
	private String ownerBicCode;

	@Column(name = "partner_swift_code", nullable = true, length = 25)
	private String partnerSwiftCode;

	@Column(name = "partner_bic_code", nullable = true, length = 25)
	private String partnerBicCode;

	@Column(name = "gl_id", nullable = false, length = 50)
	private String glId;

	@Column(name = "ccy_code", nullable = false, length = 5)
	private String ccyCode;

	@Column(name = "branch_id", nullable = false, length = 25)
	private String branchId;

	@Column(name = "branch_name", nullable = false, length = 25)
	private String branchName;

	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

}
