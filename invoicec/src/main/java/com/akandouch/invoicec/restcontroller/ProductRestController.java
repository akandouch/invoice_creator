package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Product;
import com.akandouch.invoicec.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@RequestMapping("/product")
public class ProductRestController extends CrudRestController<Product> {
}
