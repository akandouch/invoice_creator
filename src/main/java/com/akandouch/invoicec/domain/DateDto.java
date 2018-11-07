package com.akandouch.invoicec.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DateDto {
    @Min(1)
    @Max(31)
    private int day;
    @Min(1)
    @Max(12)
    private int month;
    @Min(1900)
    @Max(2100)
    private int year;
}
