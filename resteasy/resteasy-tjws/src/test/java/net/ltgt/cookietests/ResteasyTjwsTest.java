package net.ltgt.cookietests;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class ResteasyTjwsTest extends OkHttpTest {

  TJWSEmbeddedJaxrsServer server;

  @Before public void setUp() {
    server = new TJWSEmbeddedJaxrsServer();
    server.setBindAddress(TestPortProvider.getHost());
    server.setPort(TestPortProvider.getPort());
    server.getDeployment().setApplicationClass(Application.class.getCanonicalName());
    server.start();
  }

  @After public void tearDown() {
    server.stop();
  }

  @Override
  protected HttpUrl getUrl(String path) {
    return HttpUrl.parse(TestPortProvider.generateURL(path));
  }
}
