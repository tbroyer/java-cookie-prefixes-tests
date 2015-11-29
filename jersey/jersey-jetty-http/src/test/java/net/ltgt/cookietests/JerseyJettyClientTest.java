package net.ltgt.cookietests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jetty.connector.JettyConnectorProvider;

public class JerseyJettyClientTest extends JaxrsClientTest {
  @Override
  protected Client getClient() {
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.connectorProvider(new JettyConnectorProvider());
    return ClientBuilder.newClient(clientConfig);
  }
}
