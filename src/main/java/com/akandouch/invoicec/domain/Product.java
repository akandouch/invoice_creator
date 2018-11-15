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

    private String id;
    private String name;
    private String description;

    private Float unitPrice;
    private Float quantity;
    private Float vat;

    private Integer type;
    private UnitOfMeasure unitOfMeasure;

    private List<Upload> uploads;

    enum ProductType{
        SERVICE,
        ITEM
    }
    /*
    enum UnitOfMeasure{
        DAYS,
        UNIT
    }*/
}
