package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Settings {
    private String id;

    @Min(1)
    private int currentInvoiceNumber = 1;
}
