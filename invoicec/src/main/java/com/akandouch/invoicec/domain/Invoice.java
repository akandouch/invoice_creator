package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Invoice {
	
	private String id;
	@NotBlank
	@Size(min = 5, max = 255)
	private String title;
	@Valid
	private List<Item> items;
	@Valid
	private InvoiceProfile invoicer;
	@Valid
	private InvoiceProfile invoiced;
	@Valid
	private Integer status;

	private String invoiceNumber;

	private List<Upload> attachments;

	private float total;

	public Float getTotal(){
		this.items.forEach(i->{
			this.total += i.getRate() * i.getDays();
		});
		return this.total;
	}

}
