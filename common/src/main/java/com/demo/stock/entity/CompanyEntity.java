package com.demo.stock.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_COMPANY")
public class CompanyEntity {
	
	private int id;
	
	private String name;
	
	private String exchange;

    private String description;
    
    private String ceoName;
    
    private String boardMembers;
    
    private Date ipoDate;

    @Id
	@GeneratedValue
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="EXCHANGE")
	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="CEO_NAME")
	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	@Column(name="BOARD_MEMBERS")
	public String[] getBoardMembers() {
		return boardMembers.split(",");
	}

	public void setBoardMembers(String[] boardMembers) {
		this.boardMembers = String.join(",", boardMembers);
	}

	@Column(name="IPO_DATE")
	public Date getIpoDate() {
		return ipoDate;
	}

	
	public void setIpoDate(Date ipoDate) {
		this.ipoDate = ipoDate;
	}

}
