package com.demo.stockmarket.price.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImportResponse {

	private boolean status;
	
	private long fileSize;
	
	private int count;
	
	private String message;
	
}
