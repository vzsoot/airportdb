package com.ibm.interview.airportdb.service;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CloudantAirportdbService {

  private Logger logger = LoggerFactory.getLogger(CloudantAirportdbService.class);

  private CloudantClient client;

  public CloudantAirportdbService(@Value("${cloudant.account}") String account) {
    this.client = ClientBuilder.account(account)
      .build();

    logger.info("Cloudant server version: " + this.client.serverVersion());
  }

}
