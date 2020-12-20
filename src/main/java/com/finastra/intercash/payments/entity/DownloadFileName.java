package com.finastra.intercash.payments.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
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
@Table(name = "dnld_file_name")
public class DownloadFileName {

	@EmbeddedId
	private DownloadFileNameId downloadFileNameId;

	@Column(name = "file_name", nullable = false, length = 255)
	private String fileName;

	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;
}
