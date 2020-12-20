/**
 * 
 */
package com.finastra.intercash.payments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author u722954
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "dnldId", "seqNmbr" })
@Embeddable
public class DownloadFileDataId implements Serializable {

	private static final long serialVersionUID = 5545798131992941513L;

	@Column(name = "dnld_id", nullable = false, length = 25)
	private String dnldId;

	@Column(name = "seq_nmbr", nullable = false, length = 25)
	private Integer seqNmbr;

}
