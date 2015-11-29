package net.ltgt.cookietests;

import javax.ws.rs.client.Client;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

public class ResteasyClientApacheHttpEngineTest extends JaxrsClientTest {

  @Override
  protected Client getClient() {
    return new ResteasyClientBuilder()
        .httpEngine(new ApacheHttpClient4Engine())
        .build();
  }
}
