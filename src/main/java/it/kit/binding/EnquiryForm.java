package it.kit.binding;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryForm {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String message;
	private String subject;
	
}
