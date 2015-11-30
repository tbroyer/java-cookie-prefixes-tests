package net.ltgt.cookietests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class CXFClientTest extends JaxrsClientTest {
  @Override
  protected Client getClient() {
    return ClientBuilder.newClient();
  }
}
