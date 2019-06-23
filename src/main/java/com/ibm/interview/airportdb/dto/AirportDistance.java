package com.ibm.interview.airportdb.dto;

public class AirportDistance {
  private Integer distance;
  private Airport airport;

  public Integer getDistance() {
    return distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }

  public AirportDistance(Integer distance, Airport airport) {
    this.distance = distance;
    this.airport = airport;
  }
}
