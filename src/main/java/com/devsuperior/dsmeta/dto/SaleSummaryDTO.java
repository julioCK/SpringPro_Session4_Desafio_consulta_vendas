package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SalesSummaryProjection;

public class SaleSummaryDTO {
    private String sellerName;
    private Double total;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(SalesSummaryProjection projection) {
        sellerName = projection.getName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
