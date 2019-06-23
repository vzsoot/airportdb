package com.ibm.interview.airportdb.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class Query {

  @NotNull
  @Range(min = -90, max = 90)
  private Integer latitude;

  @NotNull
  @Range(min = -180, max = 180)
  private Integer longitude;

  @NotNull
  @Range(min = 0, max = 12756)
  private Integer radius;

  public Integer getLatitude() {
    return latitude;
  }

  public void setLatitude(Integer latitude) {
    this.latitude = latitude;
  }

  public Integer getLongitude() {
    return longitude;
  }

  public void setLongitude(Integer longitude) {
    this.longitude = longitude;
  }

  public Integer getRadius() {
    return radius;
  }

  public void setRadius(Integer radius) {
    this.radius = radius;
  }
}
