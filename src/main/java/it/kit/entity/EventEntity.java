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
@Table(name = "Event_dtls")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventid;
	private String name;
	
	 @CreationTimestamp
		@Column(insertable = true, updatable = false)
		private LocalDateTime creationDate;
		@UpdateTimestamp
		@Column(insertable = false, updatable = true)
		private LocalDateTime updationDate;
	
}
