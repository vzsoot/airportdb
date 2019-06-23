package com.ibm.interview.airportdb.service;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.ibm.interview.airportdb.dto.Airport;
import com.ibm.interview.airportdb.dto.AirportDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CloudantAirportService {

  private Logger logger = LoggerFactory.getLogger(CloudantAirportService.class);

  private CloudantClient client;

  public CloudantAirportService(@Value("${cloudant.account}") String account) {
    this.client = ClientBuilder.account(account)
      .build();

    logger.info("Cloudant server version: " + this.client.serverVersion());
  }

  public List<Airport> search(Double latFrom, Double latTo, Double lonFrom, Double lonTo) {
    String dbQuery = String.format("lat:[%f TO %f] AND lon:[%f TO %f]", latFrom, latTo, lonFrom, lonTo);

    logger.info("Airportdb query: " + dbQuery);

    return this.client
      .database("airportdb", false)
      .search("view1/geo")
      .includeDocs(true)
      .limit(200)
      .query(dbQuery, Airport.class);
  }

  public List<AirportDistance> calculateDistance(Double lat, Double lon, List<Airport> airports) {
    return airports.stream()
      .map((airport -> new AirportDistance(
        distanceInKM(lat, lon, airport.getGeometry().getCoordinates()[0], airport.getGeometry().getCoordinates()[1]), airport)))
      .collect(Collectors.toList());
  }

  private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

  // Haversine formula.
  private Integer distanceInKM(Double fromLat, Double fromLon, Double toLat, Double toLon) {
    double latDistance = Math.toRadians(fromLat - toLat);
    double lngDistance = Math.toRadians(fromLon - toLon);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
      + Math.cos(Math.toRadians(fromLat)) * Math.cos(Math.toRadians(toLat))
      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return Math.toIntExact((Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c)));
  }}
