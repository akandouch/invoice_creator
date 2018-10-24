package com.akandouch.invoicec.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<List<Float>> getRatePerMonthForYear(int year);
}
