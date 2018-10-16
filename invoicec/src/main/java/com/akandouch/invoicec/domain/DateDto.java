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
public class DateDto {
    @NotBlank
    @Size(min = 0, max = 7)
    private int day;
    @NotBlank
    @Size(min = 0, max = 12)
    private int month;
    @NotBlank
    @Size(min = 1970, max = 2100)
    private int year;
}
