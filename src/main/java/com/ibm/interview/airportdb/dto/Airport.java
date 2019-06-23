package com.ibm.interview.airportdb.dto;

public class Airport {
  private String country;
  private String city;
  private String icao;
  private String name;
  private Coordinates geometry;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getIcao() {
    return icao;
  }

  public void setIcao(String icao) {
    this.icao = icao;
  }

  public Coordinates getGeometry() {
    return geometry;
  }

  public void setGeometry(Coordinates geometry) {
    this.geometry = geometry;
  }
}
