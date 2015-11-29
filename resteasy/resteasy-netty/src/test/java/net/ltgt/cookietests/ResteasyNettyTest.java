package net.ltgt.cookietests;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class ResteasyNettyTest extends OkHttpTest {

  NettyJaxrsServer server;

  @Before public void setUp() {
    server = new NettyJaxrsServer();
    server.setHostname(TestPortProvider.getHost());
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
