package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Product;
import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.service.ProductService;
import com.akandouch.invoicec.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@RequestMapping("/product")
@Slf4j
public class ProductRestController extends CrudRestController<Product> {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload-csv")
    public void uploadCsv(@RequestBody Upload upload) {
        ProductService productService = (ProductService) this.crudService;
        productService.saveFromCSV(upload.getNewUpload());
        log.info("csv to product success");
    }

}
