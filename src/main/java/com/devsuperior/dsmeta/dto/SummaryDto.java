package com.devsuperior.dsmeta.dto;

public class SummaryDto {

	private String sellerName;
	private Double total;
	

	public SummaryDto() {	
	}
	
	public SummaryDto(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	public String getSellerName() {
		return sellerName;
	}
	public Double getTotal() {
		return total;
	}
	
	
}
