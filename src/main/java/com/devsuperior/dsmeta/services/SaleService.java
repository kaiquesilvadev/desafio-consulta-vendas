package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDto;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> ListReport(String min, String max, String name, Pageable pageable) {
		
		LocalDate currentDate = LocalDate.now();
		max = FormataMax(max, currentDate);
		min = formataMin(min, currentDate);
		LocalDate minDate = LocalDate.parse(min); 
		LocalDate maxDate = LocalDate.parse(max);

		Page<Sale> listEntiuty = repository.report(minDate, maxDate, name, pageable);
		return listEntiuty.map(SaleMinDTO::new);
	}
	
	public List<SummaryDto> getSummary(String min, String max) {
	
		LocalDate currentDate = LocalDate.now();
		max = FormataMax(max, currentDate);
		min = formataMin(min, currentDate);
		LocalDate minDate = LocalDate.parse(min); 
		LocalDate maxDate = LocalDate.parse(max);

		return repository.summary(minDate, maxDate);
	}

	private String FormataMax(String max, LocalDate currentDate) {
		if (max == null) {
			max = currentDate.toString();
		}
		return max;
	}

	private String formataMin(String min, LocalDate currentDate) {
		if (min == null) {
			min = currentDate.minusYears(1L).toString();
		}
		return min;
	}
}
