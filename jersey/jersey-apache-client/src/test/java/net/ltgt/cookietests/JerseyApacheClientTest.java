package net.ltgt.cookietests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

public class JerseyApacheClientTest extends JaxrsClientTest {
  @Override
  protected Client getClient() {
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.connectorProvider(new ApacheConnectorProvider());
    return ClientBuilder.newClient(clientConfig);
  }
}
