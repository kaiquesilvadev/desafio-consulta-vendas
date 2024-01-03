package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SummaryDto;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT s FROM Sale s " 
			+ "WHERE  upper(s.seller.name) LIKE  upper(CONCAT('%', :name , '%')) "
			+ "AND s.date BETWEEN :min AND :max")
	Page<Sale> report(@Param("min") LocalDate minDate, @Param("max") LocalDate maxDate, @Param("name") String name, Pageable pageable);

	
	//where tb_sales.date between '2022-05-01' and '2022-05-31' group by tb_seller.name

	@Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDto(s.seller.name, SUM(s.amount)) "
		       + "FROM Sale s "
		       + "WHERE s.date BETWEEN :min AND :max "
		       + "GROUP BY s.seller.name")
		List<SummaryDto> summary(@Param("min") LocalDate minDate, @Param("max") LocalDate maxDate);

}
