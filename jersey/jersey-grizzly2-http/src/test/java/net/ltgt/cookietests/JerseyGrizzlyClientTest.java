package net.ltgt.cookietests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;

public class JerseyGrizzlyClientTest extends JaxrsClientTest {
  @Override
  protected Client getClient() {
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.connectorProvider(new GrizzlyConnectorProvider());
    return ClientBuilder.newClient(clientConfig);
  }
}
