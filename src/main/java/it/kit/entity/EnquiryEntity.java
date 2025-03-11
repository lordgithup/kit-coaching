package it.kit.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Enquiry_dtls")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnquiryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquaryid;
	private String name;
	private String email;
	private String phone;
	private String message;
	private String subject;
	
	
	    @CreationTimestamp
		@Column(insertable = true, updatable = false)
		private LocalDateTime creationDate;
		@UpdateTimestamp
		@Column(insertable = false, updatable = true)
		private LocalDateTime updationDate;
	
}
