package com.ibm.interview.airportdb.controller;

import com.ibm.interview.airportdb.service.CloudantAirportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CloudantAirportService airportService;

  @Test
  public void whenAllZero_thenShouldDisplayResultHeader() throws Exception {
    when(airportService.search(0.0, 0.0, 0.0, 0.0)).thenReturn(Collections.emptyList());
    this.mockMvc.perform(post("/search").param("latitude", "0.0").param("longitude", "0.0").param("radius", "0"))
      .andDo(print()).andExpect(status().isOk())
      .andExpect(content().string(containsString("No airport found.")));
  }
}