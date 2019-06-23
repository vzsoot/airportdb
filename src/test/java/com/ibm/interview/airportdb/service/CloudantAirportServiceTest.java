package com.ibm.interview.airportdb.service;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.Search;
import com.ibm.interview.airportdb.dto.Airport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudantAirportServiceTest {

  @MockBean
  private CloudantService cloudantService;

  @Autowired
  private CloudantAirportService cloudantAirportService;

  @Test
  public void whenCorrectValuesProvided_thenShouldCreateQuery() {
    CloudantClient cloudantClient = mock(CloudantClient.class);
    Database database = mock(Database.class);
    Search search = mock(Search.class);

    when(cloudantService.getClient()).then((answer) -> cloudantClient);
    when(cloudantClient.database("airportdb", false)).then((answer) -> database);
    when(database.search("view1/geo")).then((answer) -> search);
    when(search.includeDocs(true)).then((answer) -> search);
    when(search.limit(200)).then((answer) -> search);

    this.cloudantAirportService.search(0.0, 5.0, 0.0, 30.0);

    verify(cloudantService, Mockito.times(1)).getClient();
    verify(search, Mockito.times(1)).query("lat:[0.000000 TO 5.000000] AND lon:[0.000000 TO 30.000000]", Airport.class);
  }

}
