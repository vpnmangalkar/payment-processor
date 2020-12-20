/**
 * 
 */
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
 * @author u722954
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "dnld_file_data")
public class DownloadFileData {

	@EmbeddedId
	private DownloadFileDataId id;

	@Column(name = "file_data", nullable = false, length = 4000)
	private String fileData;

	@UpdateTimestamp
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;
}
