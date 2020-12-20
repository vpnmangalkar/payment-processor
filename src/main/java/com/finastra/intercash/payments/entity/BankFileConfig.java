package com.finastra.intercash.payments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@EqualsAndHashCode(of = { "id" })
@Table(name = "bank_file_config", uniqueConstraints = { @UniqueConstraint(columnNames = { "bank_id" }) })
public class BankFileConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "SERIAL")
	private Long id;

	@Column(name = "bank_id", length = 25, nullable = false)
	private String bankId;

	@Column(name = "attach_entity_code", nullable = false, length = 1)
	private String attachEntityCode = "Y";

	@Column(name = "file_prefix", length = 25, nullable = true)
	private String filePrefix;

	@Column(name = "file_postfix", length = 25, nullable = true)
	private String filePostfix;

	@Column(name = "file_middle", length = 25, nullable = true)
	private String fileMiddle;

	@Column(name = "file_ext", length = 10, nullable = true)
	private String file_ext;

	@Column(name = "attach_date", length = 1, nullable = true)
	private String attachDate = "Y";
}
