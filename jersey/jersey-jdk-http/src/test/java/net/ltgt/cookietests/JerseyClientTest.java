package net.ltgt.cookietests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

public class JerseyClientTest extends JaxrsClientTest {
  @Override
  protected Client getClient() {
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.connectorProvider(new HttpUrlConnectorProvider());
    return ClientBuilder.newClient(clientConfig);
  }
}
