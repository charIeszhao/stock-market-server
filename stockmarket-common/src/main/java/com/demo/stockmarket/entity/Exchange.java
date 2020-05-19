package com.demo.stockmarket.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_exchange")
@NoArgsConstructor
@Getter
@Setter
public class Exchange {

	private int id;
	
	private String name;
	
	private String description;
	
	private String address;
}
