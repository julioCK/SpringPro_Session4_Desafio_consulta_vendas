package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.projections.SalesAndSellersProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional
	public Page<SaleMinDTO> salesReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {

		/*	Por algum motivo, o banco H2 não aceita a conversão de LocalDate para DATE. A solução foi enviar as datas como String e fazer o CAST dentro da própria query  */

		String maxDate = maxDateStr.isEmpty() ?
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).toString() : maxDateStr;

		String minDate = minDateStr.isEmpty() ?
				LocalDate.parse(maxDate).minusYears(1L).toString() : minDateStr;

		Page<SalesAndSellersProjection> resultSet = repository.reportSearch(minDate, maxDate, name, pageable);
		return resultSet.map(SaleMinDTO::new);
	}
}
