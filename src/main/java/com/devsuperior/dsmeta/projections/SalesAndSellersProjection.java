package com.devsuperior.dsmeta.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SalesAndSellersProjection {
    Long getId();
    Double getAmount();
    LocalDate getDate();
    String getName();
}
