package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.Item;
import com.akandouch.invoicec.repository.InvoiceRepository;
import com.akandouch.invoicec.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private InvoiceRepository invoiceRepository;
    private ItemRepository itemRepository;

    @Autowired
    StatisticsServiceImpl(InvoiceRepository invoiceRepository, ItemRepository itemRepository){
        this.invoiceRepository = invoiceRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List <Float> getRatePerMonthForYear(int year){
        Map<Integer,Float> datas = new HashMap<Integer, Float>();
        for (int i=1; i<=12; i++){
            datas.put(i,0.0f);
        }

        List<Float> l = new ArrayList<Float>(12);

        for (int i=0; i<12; i++){
            l.add(0.0f);
        }

        List<Invoice> invoices = this.invoiceRepository.findInvoiceByItemsPeriodFromYearLessThanEqualAndItemsPeriodToYearGreaterThanEqual(year, year);

        System.out.println(invoices.size());
        invoices.forEach(i->i.getItems().forEach(x->{
            int from = x.getPeriod().getFrom().getMonth();
            int fromYear = x.getPeriod().getFrom().getYear();
            int to = x.getPeriod().getTo().getMonth();
            int toYear = x.getPeriod().getTo().getYear();
            if ( fromYear < year ){
                from = 1;
            }

            if ( toYear > year ){
                to = 12;
            }

            if( (year <= toYear && year >= fromYear) || (year <= fromYear && year >= toYear ) ) {
                for (int idx = from; idx <= to; idx++) {
                    l.set(idx - 1, l.get(idx - 1) + x.getRate());
                }
            }
        }));

        return l;
    }
}
