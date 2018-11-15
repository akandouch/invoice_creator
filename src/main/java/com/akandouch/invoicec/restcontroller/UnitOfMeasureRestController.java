package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uom")
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@Slf4j
public class UnitOfMeasureRestController extends CrudRestController<UnitOfMeasure> {

}
