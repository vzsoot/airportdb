package com.ibm.interview.airportdb.controller;

import com.ibm.interview.airportdb.dto.Airport;
import com.ibm.interview.airportdb.dto.AirportDistance;
import com.ibm.interview.airportdb.dto.Query;
import com.ibm.interview.airportdb.service.CloudantAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController implements WebMvcConfigurer {

  private CloudantAirportService airportService;

  public SearchController(@Autowired CloudantAirportService airportService) {
    this.airportService = airportService;
  }

  @PostMapping(path = "/search")
  public String search(@Valid Query query, BindingResult bindingResult, Model model) {
    model.addAttribute("query", query);

    if (bindingResult.hasErrors()) {
      model.addAttribute("error", buildErrorMessages(bindingResult));
    } else {

      int LATITUDE_KM = 111;
      Double radius = (double) query.getRadius() / LATITUDE_KM;

      List<Airport> airports = airportService.search(
        query.getLatitude() - radius, query.getLatitude() + radius,
        query.getLongitude() - radius, query.getLongitude() + radius
      );

      List<AirportDistance> airportDistances = airportService
        .calculateDistance(query.getLatitude(), query.getLongitude(), airports)

        .stream()
        .filter((airport) -> airport.getDistance() <= query.getRadius())
        .sorted(Comparator.comparing(AirportDistance::getDistance))
        .collect(Collectors.toList());

      model.addAttribute("airports", airportDistances);
      model.addAttribute("query", query);
    }
    return "index";
  }

  private String buildErrorMessages(BindingResult bindingResult) {
    StringBuilder errorMessage = new StringBuilder();
    for (FieldError error : bindingResult.getFieldErrors()) {
      errorMessage.append("<b>").append(error.getField()).append("</b> ");
      errorMessage.append(error.getDefaultMessage());
      errorMessage.append("<br/>");
    }
    return errorMessage.toString();
  }

}
