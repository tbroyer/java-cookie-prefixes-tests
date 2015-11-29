package net.ltgt.cookietests;

import java.net.InetSocketAddress;

import org.jboss.resteasy.plugins.server.sun.http.SunHttpJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;
import com.sun.net.httpserver.HttpServer;

public class ResteasySunHttpTest extends OkHttpTest {

  SunHttpJaxrsServer server;

  @Before
  public void setUp() throws Exception {
    server = new SunHttpJaxrsServer();
    server.setHttpServer(HttpServer.create(new InetSocketAddress(TestPortProvider.getHost(), TestPortProvider.getPort()), 10));
    server.setPort(TestPortProvider.getPort());
    server.getDeployment().setApplicationClass(Application.class.getCanonicalName());
    server.start();
  }

  @After
  public void tearDown() {
    server.stop();
  }

  @Override
  protected HttpUrl getUrl(String path) {
    return HttpUrl.parse(TestPortProvider.generateURL(path));
  }
}
