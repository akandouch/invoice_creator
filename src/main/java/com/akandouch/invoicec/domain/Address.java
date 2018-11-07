package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Address {
    @NotBlank
    @Size(min = 1, max = 255)
    private String street;
    @NotBlank
    @Size(min = 1, max = 10)
    private String streetNumber;
    @NotBlank
    @Size(min = 1, max = 50)
    private String city;
    @NotBlank
    @Size(min = 1, max = 10)
    private String postcode;
    @NotBlank
    @Size(min = 1, max = 50)
    private String country;

}
