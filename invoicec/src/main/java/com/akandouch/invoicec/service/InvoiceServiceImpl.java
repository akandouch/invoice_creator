package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.InvoiceProfile;
import com.akandouch.invoicec.repository.InvoiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepo;

    public Invoice save(Invoice invoice) {
        InvoiceProfile invoiced = invoice.getInvoiced();
        InvoiceProfile invoicer = invoice.getInvoicer();

        Invoice copy = Invoice.newBuilder(invoice)
                .invoiced(Optional.ofNullable(invoiced).orElseGet(InvoiceProfile::new))
                .invoicer(Optional.ofNullable(invoicer).orElseGet(InvoiceProfile::new))
                .items(Optional.ofNullable(invoice.getItems()).orElseGet(Collections::emptyList))
                .title(StringUtils.isEmpty(invoice.getTitle()) ? "No title" : invoice.getTitle())
                .build();

        return this.invoiceRepo.save(copy);
    }

    public Invoice findOne(String id) {
        return this.invoiceRepo.findById(id).get();
    }

    public List<Invoice> findAll() {
        return this.invoiceRepo.findAll();
    }

    public void delete(String id) {
        this.invoiceRepo.deleteById(id);
    }
}
