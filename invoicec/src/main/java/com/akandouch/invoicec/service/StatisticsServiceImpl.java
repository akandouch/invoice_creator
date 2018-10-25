package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.repository.InvoiceRepository;
import com.akandouch.invoicec.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public List < List < Float > > getRatePerMonthForYear(int year){
        Map<Integer,Float> datas = new HashMap<Integer, Float>();
        for (int i=1; i<=12; i++){
            datas.put(i,0.0f);
        }

        List<Float> rates = new ArrayList<Float>(12);
        List<Float> days = new ArrayList<Float>(12);

        for (int i=0; i<12; i++){
            rates.add(0.0f);
            days.add(0.0f);
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

            //LocalDate.of()
            if( (year <= toYear && year >= fromYear) || (year <= fromYear && year >= toYear ) ) {
                for (int idx = from; idx <= to; idx++) {
                    rates.set(idx - 1, rates.get(idx - 1) + x.getRate());
                    days.set(idx - 1, days.get(idx - 1) + x.getDays());
                }
            }
        }));

        return Arrays.asList(rates,days);
    }
    @Override
    public Map<String, Float> getTotalInvoicedPerCustomer(){
        Map<String, Float> datas = new HashMap<String, Float>();

        List<Invoice> invoices = this.invoiceRepository.findAll();

        invoices.forEach(i->{
            datas.put(i.getInvoiced().getFirstname(), datas.getOrDefault(i.getInvoiced().getFirstname(),Float.valueOf(0.0f)) + i.getTotal());
        });
        return datas;
    }
}
