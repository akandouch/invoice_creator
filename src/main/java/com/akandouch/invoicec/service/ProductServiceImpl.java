package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Product;
import com.akandouch.invoicec.repository.ProductRepository;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Service
@Slf4j
public class ProductServiceImpl implements CrudService<Product>, ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product findOne(String id) {
        return this.productRepository.findById(id).orElse(new Product());
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Page<Product> findAllByPage(Integer pageSize, Integer pageNumber) {
        return this.productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Async
    @Override
    public void saveFromCSV(byte[] csv) {
        try (
                Reader reader = new InputStreamReader(IOUtils.toInputStream(new String(csv).replace(';', ','))); // todo better workaround for ; excel separator
                CSVReader csvReader = new CSVReader(reader);
        ) {
            csvReader.readAll()
                    .stream()
                    .skip(1L) // skip header
                    .map(p -> Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name(p[0].trim())
                            .description(p[1].trim())
                            .unitPrice(parseFloat(p[2].trim()))
                            .quantity(parseFloat(p[3].trim()))
                            .vat(parseFloat(p[4].trim()))
                            .type(parseInt(p[5].trim()))
                            //.unitOfMeasure(p[6].trim()))
                            .build()
                    ).map(productRepository::save)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("error: ", e);
        }
    }
}
