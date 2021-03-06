package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@RequestMapping("/statistics")
public class StatisticsRestController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/ratePerMonthForYear")
    public List<List<Float>> getRatePerMonthForYear(int year){
        return this.statisticsService.getRatePerMonthForYear(year);
    }

    @GetMapping("/totalPerCustomer")
    public List<Map<String, Float>> getTotalPerCustomer(){
        return this.statisticsService.getTotalInvoicedPerCustomer();
    }
}
