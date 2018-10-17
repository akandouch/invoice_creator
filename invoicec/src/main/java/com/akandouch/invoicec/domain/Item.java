package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Item {
	
	private Long id;
	@Size(min = 1, max = 1000)
	private String description;
	@Min(0)
	private int unit;
	@Min(0)
	private double amount;
	@NotBlank
	private String project;
	@NotNull
    private Period period;
	@NotBlank
	private String nature;
	@Min(1)
	private Float days;
	@NotNull
	private Float rate;

}
