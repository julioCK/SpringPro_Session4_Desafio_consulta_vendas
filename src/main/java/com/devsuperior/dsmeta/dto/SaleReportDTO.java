package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;

public class SaleReportDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName;

	public SaleReportDTO(Long id, Double amount, LocalDate date) {
		this.id = id;
		this.amount = amount;
		this.date = date;
	}
	
	public SaleReportDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public SaleReportDTO(SalesReportProjection projection) {
		id = projection.getId();
		amount = projection.getAmount();
		date = projection.getDate();
		sellerName = projection.getName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}
}
