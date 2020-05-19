package com.demo.stockmarket.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_company")
@NoArgsConstructor
@Getter
@Setter
public class Company {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "exchange_id")
	private Exchange exchange;
	
	@Column(name = "ceo_name")
	private String ceoName;
	
	@Column(name = "board_members")
	private String boardMembers;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "ipo_date")
	private Date ipoDate;
}
