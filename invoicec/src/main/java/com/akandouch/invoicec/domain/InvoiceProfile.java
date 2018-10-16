package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class InvoiceProfile {
	
	private String id;
	@NotBlank
	@Size(min = 1, max = 255)
	private String firstname;
	@NotBlank
	@Size(min = 1, max = 255)
	private String lastname;
	private Boolean active;
	@NotBlank
	@Size(min = 1, max = 20)
	private String vat;
	@Valid
	private Address address;

}
