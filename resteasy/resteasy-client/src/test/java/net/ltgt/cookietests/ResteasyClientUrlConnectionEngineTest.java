package net.ltgt.cookietests;

import javax.ws.rs.client.Client;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.URLConnectionEngine;

public class ResteasyClientUrlConnectionEngineTest extends JaxrsClientTest {

  @Override
  protected Client getClient() {
    return new ResteasyClientBuilder()
        .httpEngine(new URLConnectionEngine())
        .build();
  }
}
