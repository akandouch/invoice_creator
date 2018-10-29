package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Product;
import com.akandouch.invoicec.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements CrudService<Product>{

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
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }
}
