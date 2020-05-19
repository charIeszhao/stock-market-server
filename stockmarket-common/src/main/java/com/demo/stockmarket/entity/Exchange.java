package com.demo.stockmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_exchange")
@NoArgsConstructor
@Getter
@Setter
public class Exchange {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;
}
