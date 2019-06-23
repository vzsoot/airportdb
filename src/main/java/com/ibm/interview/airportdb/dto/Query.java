package com.ibm.interview.airportdb.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class Query {

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  @Range(min = 0, max = 12756)
  private Integer radius;

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Integer getRadius() {
    return radius;
  }

  public void setRadius(Integer radius) {
    this.radius = radius;
  }
}
