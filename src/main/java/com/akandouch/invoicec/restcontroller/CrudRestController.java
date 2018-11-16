package com.akandouch.invoicec.restcontroller;


import com.akandouch.invoicec.domain.DomainEntity;
import com.akandouch.invoicec.domain.Product;
import com.akandouch.invoicec.service.CrudService;
import com.akandouch.invoicec.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CrudRestController<T extends DomainEntity> {
    @Autowired
    protected CrudService<T> crudService;


    @GetMapping
    public List<T> get(){
       return this.crudService.findAll();
    }
    /*
    @GetMapping
    public T get(@RequestParam String id){
        return this.crudService.findOne(id);
    }
*/
    @GetMapping(value = "page")
    public Page<T> getAllByPage(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam String orderColumn, @RequestParam String direction){
        return crudService.findAllByPage(pageSize, pageNumber,orderColumn, direction);
    }

    @PostMapping
    public T post(@RequestBody T t){
        System.out.println(this.crudService.getClass().getName());
        return this.crudService.save(t);
    }

    @DeleteMapping
    public void delete(String id) {
        this.crudService.delete(id);
    }

}
