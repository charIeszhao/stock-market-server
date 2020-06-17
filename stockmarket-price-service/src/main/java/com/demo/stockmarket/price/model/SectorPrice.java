package com.demo.stockmarket.price.model;

public class SectorPrice {

  public SectorPrice() {
  }

  public SectorPrice(String sectorName, double changePct) {
    this.sectorName = sectorName;
    this.changePct = changePct;
  }

  private String sectorName;

  private double changePct;

  public void setSectorName(String sectorName) {
    this.sectorName = sectorName;
  }

  public String getSectorName() {
    return this.sectorName;
  }

  public void setChangePct(double changePct) {
    this.changePct = changePct;
  }

  public double getChangePct() {
    return this.changePct;
  }
}