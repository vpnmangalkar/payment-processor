package com.finastra.intercash.payments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vipin.Mangalkar
 *
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "dnldId", "requestType", "payId" })
@Embeddable
public class DownloadFileNameId implements Serializable {

	private static final long serialVersionUID = -8123935953338785924L;

	@Column(name = "dnld_id", nullable = false, length = 25)
	private String dnldId;

	@Column(name = "request_type", nullable = false, length = 25)
	private String requestType;

	@Column(name = "pay_id", nullable = false, length = 25)
	private String payId;

}
