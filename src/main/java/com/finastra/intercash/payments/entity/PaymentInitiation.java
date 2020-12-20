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
@EqualsAndHashCode(of = { "payId" })
@Table(name = "pay_initiation")
public class PaymentInitiation {

	@Id
	@GenericGenerator(name = "pay_id", strategy = "com.finastra.intercash.payments.entity.PaymentInitiationIdGenerator")
	@GeneratedValue(generator = "pay_id")
	@Column(name = "pay_id", length = 25)
	private String payId;

	@Column(name = "from_bank_id", nullable = false, length = 25)
	private String fromBankId;

	@UpdateTimestamp
	@Column(name = "maker_time", nullable = false)
	private LocalDateTime makerTime;

	@Column(name = "originating_txn_nmbr", nullable = false, length = 25)
	private String originatingTxnNmbr;

	@Column(name = "inter_seller_txn_ref_nmbr", nullable = false, length = 25)
	private String interSellerTxnRefNmbr;

	@Column(name = "appcode", nullable = false, length = 30)
	private String appcode;

	@Column(name = "client_code", nullable = false, length = 20)
	private String clientCode;

	@Column(name = "bene_code", nullable = false, length = 20)
	private String beneCode;

	@Column(name = "bene_description", nullable = false, length = 800)
	private String beneDescription;

	@Column(name = "bene_account_nmbr", nullable = false, length = 35)
	private String beneAccountNmbr;
	
	@Column(name = "bene_account_iban", nullable = false, length = 35)
	private String beneAccountIban;

	@Column(name = "bene_bank_code", nullable = false, length = 10)
	private String beneBankCode;

	@Column(name = "bene_branch_code", nullable = false, length = 10)
	private String beneBranchCode;
	
	@Column(name = "bene_bank_swift", nullable = false, length = 40)
	private String beneBankSwift;
	
	@Column(name = "bene_bank_bic", nullable = false, length = 40)
	private String beneBankBic;

	@Column(name = "bene_addr", nullable = false, length = 800)
	private String beneAddr;

	@Column(name = "bene_bank_addr", nullable = true, length = 800)
	private String beneBankAddr;

	@Column(name = "bene_mobile", nullable = true, length = 20)
	private String beneMobile;

	@Column(name = "bene_phone_nmbr", nullable = true, length = 20)
	private String benePhoneNmbr;

	@Column(name = "bene_pin_code", nullable = true, length = 30)
	private String benePinCode;

	@Column(name = "bene_business_regs", nullable = true, length = 20)
	private String beneBusinessRegs;

	@Column(name = "bene_ccy", nullable = false, length = 6)
	private String beneCcy;

	@Column(name = "bene_email", nullable = true, length = 255)
	private String beneEmail;

	@Column(name = "bene_fax", nullable = true, length = 80)
	private String beneFax;

	@Column(name = "bene_id", nullable = true, length = 15)
	private String beneId;

	@Column(name = "bene_ivr_code", nullable = true, length = 80)
	private String beneIvrCode;

	@Column(name = "bene_non_resi_indicator", nullable = true, length = 1)
	private String beneNonResiIndicator;

	@Column(name = "bene_passport_no", nullable = true, length = 20)
	private String benePassportNo;

	@Column(name = "bene_payment_details", nullable = false, length = 255)
	private String benePaymentDetails;

	@Column(name = "by_order_of", nullable = true, length = 40)
	private String byOrderOf;

	@Column(name = "co_advice_layout", nullable = true, length = 40)
	private String coAdviceLayout;

	@Column(name = "co_auth_person_ic", nullable = true, length = 25)
	private String coAuthPersonIc;

	@Column(name = "co_auth_person_name", nullable = true, length = 45)
	private String coAuthPersonName;

	@Column(name = "co_coll_br", nullable = true, length = 20)
	private String coCollBr;

	@Column(name = "co_drawee_branch", nullable = true, length = 10)
	private String coDraweeBranch;

	@Column(name = "co_mailing_city", nullable = true, length = 60)
	private String co_mailing_city;

	@Column(name = "co_mailing_country", nullable = true, length = 60)
	private String coMailingCountry;

	@Column(name = "co_mailing_postcode", nullable = true, length = 10)
	private Integer coMailingPostcode;

	@Column(name = "co_mailing_state", nullable = true, length = 60)
	private String coMailingState;

	@Column(name = "originating_entity_type", nullable = true, length = 25)
	private String originatingEntityType;

	@Column(name = "originating_entity_code", nullable = true, length = 25)
	private String originatingEntityCode;

	@Column(name = "originating_entity_acct_nmbr", nullable = true, length = 25)
	private String originatingEntityAcctNmbr;

	@Column(name = "originating_entity_ccy", nullable = true, length = 25)
	private String originatingEntityCcy;

	@Column(name = "originating_entity_bank_id", nullable = true, length = 25)
	private String originatingEntityBankId;

	@Column(name = "originating_entity_bank_name", nullable = true, length = 25)
	private String originatingEntityBankName;

	@Column(name = "originating_entity_branch_id", nullable = true, length = 25)
	private String originatingEntityBranchId;

	@Column(name = "originating_entity_branch_name", nullable = true, length = 25)
	private String originatingEntityBranchName;

	@Column(name = "originating_entity_bic", nullable = true, length = 25)
	private String originatingEntityBic;

	@Column(name = "originating_entity_swift", nullable = true, length = 25)
	private String originatingEntitySwift;

	@Column(name = "originating_entity_dr_amnt", nullable = true, length = 25)
	private String originatingEntityDrAmnt;

	@Column(name = "originating_txn_pay_ccy", nullable = true, length = 25)
	private String originatingTxnPayCcy;

	@Column(name = "originating_txn_pay_country", nullable = true, length = 25)
	private String originatingTxnPayCountry;

	@Column(name = "originating_txn_pay_seller", nullable = true, length = 25)
	private String originatingTxnPaySeller;

	@Column(name = "recipient_entity_type", nullable = true, length = 25)
	private String recipientEntityType;

	@Column(name = "recipient_entity_code", nullable = true, length = 25)
	private String recipientEntityCode;

	@Column(name = "recipient_entity_acct_nmbr", nullable = true, length = 25)
	private String recipientEntityAcctNmbr;

	@Column(name = "recipient_entity_ccy", nullable = true, length = 25)
	private String recipientEntityCcy;

	@Column(name = "recipient_entity_bank_id", nullable = true, length = 25)
	private String recipientEntityBankId;

	@Column(name = "recipient_entity_bank_name", nullable = true, length = 25)
	private String recipientEntityBankName;

	@Column(name = "recipient_entity_branch_id", nullable = true, length = 25)
	private String recipientEntityBranchId;

	@Column(name = "recipient_entity_branch_name", nullable = true, length = 25)
	private String recipientEntityBranchName;

	@Column(name = "recipient_entity_dr_amnt", nullable = true, length = 25)
	private String recipientEntityDrAmnt;

	@Column(name = "recipient_txn_pay_ccy", nullable = true, length = 25)
	private String recipientTxnPayCcy;

	@Column(name = "recipient_entity_bic", nullable = true, length = 25)
	private String recipientEntityBic;

	@Column(name = "recipient_entity_swift", nullable = true, length = 25)
	private String recipientEntitySwift;

	@Column(name = "nostr_dr_amount", nullable = false, precision = 25, scale = 4)
	private Double nostrDrAmount;

	@Column(name = "bene_cr_amount", nullable = false, precision = 25, scale = 4)
	private Double beneCrAmount;

	@Column(name = "status", nullable = true, length = 16)
	private String status;
}
