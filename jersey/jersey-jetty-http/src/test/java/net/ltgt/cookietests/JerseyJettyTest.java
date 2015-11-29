package net.ltgt.cookietests;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class JerseyJettyTest extends OkHttpTest {

  Server server;

  @Before public void setUp() {
    server = JettyHttpContainerFactory.createServer(getUrl("/").uri(), ResourceConfig.forApplicationClass(Application.class));
  }

  @After public void tearDown() throws Exception {
    server.stop();
  }

  @Override
  protected HttpUrl getUrl(String path) {
    return new HttpUrl.Builder()
        .scheme("http")
        .host("localhost")
        .port(9998) // TODO
        .encodedPath(path)
        .build();
  }
}
