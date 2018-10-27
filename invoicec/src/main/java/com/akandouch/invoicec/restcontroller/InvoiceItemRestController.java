package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.Item;
import com.akandouch.invoicec.service.InvoiceService;
import com.akandouch.invoicec.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoice/{id}/item")
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@Slf4j
public class InvoiceItemRestController {


    @Autowired
     private ItemService itemService;

    @Autowired
     private InvoiceService invoiceService;

    @GetMapping
    public List<Item> findAll() {
        return this.itemService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Item save(@Valid @RequestBody Item item, @PathVariable("id") String id) {
        System.out.println("-------- " + id + " ---------");
        Invoice invoice = this.invoiceService.findOne(id);
        double amount = (double) item.getDays() * item.getRate();
        Item copyItem = item.toBuilder()
                .amount(amount)
                .vatAmount(amount/item.getVatRate())
                .id(item.getId() == null ? UUID.randomUUID().toString() : item.getId())
                .build();
        List<Item> newListItems = invoice.getItems()
                .stream()
                .filter(i -> !i.getId().equals(copyItem.getId()))
                .collect(Collectors.toList());

        newListItems.add(copyItem);
        this.invoiceService.save(invoice.toBuilder().items(newListItems).build());
        return item;
    }
}
