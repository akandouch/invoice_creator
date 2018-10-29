package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product implements DomainEntity{
    private String name;
    private String description;

    private Float unitPrice;
    private Float quantity;
    private Float vat;

    private ProductType productType;
    private UnitOfMeasurement unitOfMeasurement;

    private List<Upload> uploads;

    enum ProductType{
        SERVICE,
        ITEM
    }
    enum UnitOfMeasurement{
        DAYS,
        UNIT
    }
}
