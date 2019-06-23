package com.ibm.interview.airportdb.controller;

import com.ibm.interview.airportdb.dto.Query;
import com.ibm.interview.airportdb.service.CloudantAirportdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class SearchController implements WebMvcConfigurer {

  private CloudantAirportdbService airportdbService;

  public SearchController(@Autowired CloudantAirportdbService airportdbService) {
    this.airportdbService = airportdbService;
  }

  @PostMapping(path = "/search")
  public String search(@Valid Query query, BindingResult bindingResult, Model model) {
    model.addAttribute("query", query);

    if (bindingResult.hasErrors()) {
      StringBuilder errorMessage = new StringBuilder();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errorMessage.append("<b>").append(error.getField()).append("</b> ");
        errorMessage.append(error.getDefaultMessage());
        errorMessage.append("<br/>");
      }
      model.addAttribute("error", errorMessage.toString());
    }

    return "index";
  }

}
