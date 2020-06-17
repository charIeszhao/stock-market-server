package com.demo.stockmarket.price.model;

public class PriceHistory {

  public PriceHistory() {
  }

  private int companyId;

  private String companyName;

  private double latestPrice;

  private double priorPrice;
  
  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

  public int getCompanyId() {
    return this.companyId;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public void setLatestPrice(double price) {
    this.latestPrice = price;
  }

  public double getLatestPrice() {
    return this.latestPrice;
  }

  public void setPriorPrice(double price) {
    this.priorPrice = price;
  }

  public double getPriorPrice() {
    return this.priorPrice;
  }
}