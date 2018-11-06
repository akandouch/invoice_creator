package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Product;
import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.service.ProductService;
import com.akandouch.invoicec.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/template-csv")
    @Cacheable("procuctTemplateCSV")
    public ResponseEntity<Resource> templateCSV() {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "template-product.csv" + "\"")
                .body(new ClassPathResource("csv/template-product.csv"));
    }

}
