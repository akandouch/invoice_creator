package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/statistics")
public class StatisticsRestController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    public List<Float> getRatePerMonthForYear(int year){
        return this.statisticsService.getRatePerMonthForYear(year);
    }
}
