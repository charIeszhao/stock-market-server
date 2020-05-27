package com.demo.stockmarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Exchange implements Serializable {

	private static final long serialVersionUID = -4227944321497321383L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", unique = true, nullable = true)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;
}
