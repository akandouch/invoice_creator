package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.repository.InvoiceRepository;
import com.akandouch.invoicec.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
            int fromDays = x.getPeriod().getFrom().getDay();
            int to = x.getPeriod().getTo().getMonth();
            int toYear = x.getPeriod().getTo().getYear();
            int toDays = x.getPeriod().getTo().getDay();
            if ( fromYear < year ){
                from = 1;
            }

            if ( toYear > year ){
                to = 12;
            }

            if( (year <= toYear && year >= fromYear) || (year <= fromYear && year >= toYear ) ) {
                //iteration par mois
                for (int idx = from; idx <= to; idx++) {
                    int nbOfDays = 0;
                    if (idx == to){
                        if( from == to ) {
                            nbOfDays = toDays - fromDays;
                        }else{
                            nbOfDays = LocalDate.of(fromYear,to,1).lengthOfMonth() - toDays;
                        }
                    }else if( idx < to ){
                        if( idx > from ){
                            nbOfDays = LocalDate.of(fromYear,idx,1).lengthOfMonth();
                        }else{
                            nbOfDays = LocalDate.of(fromYear,idx,1).lengthOfMonth() - fromDays;
                        }
                    }
                    rates.set(idx - 1, rates.get(idx - 1) + x.getRate());
                    days.set(idx - 1, days.get(idx - 1) + nbOfDays);
                }
            }
        }));

        return Arrays.asList(rates,days);
    }
    @Override
    public List<Map<String, Float>> getTotalInvoicedPerCustomer(){
        Map<String, Float> open = new HashMap<String, Float>();
        Map<String, Float> closed = new HashMap<String, Float>();
        List<Invoice> invoices = this.invoiceRepository.findAll();

        invoices.forEach(i->{
            if(i.getStatus() == 0){
                open.put(i.getInvoiced().getFirstname(), open.getOrDefault(i.getInvoiced().getFirstname(), Float.valueOf(0.0f)) + i.getTotal());
            }else{
                closed.put(i.getInvoiced().getFirstname(), open.getOrDefault(i.getInvoiced().getFirstname(), Float.valueOf(0.0f)) + i.getTotal());
            }
        });
        return Arrays.asList(open,closed);
    }
}
