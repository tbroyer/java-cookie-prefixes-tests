package net.ltgt.cookietests;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

import io.undertow.Undertow;

public class ResteasyUndertowTest extends OkHttpTest {

  UndertowJaxrsServer server;

  @Before
  public void setUp() {
    server = new UndertowJaxrsServer();
    server.deploy(Application.class);
    server.start(Undertow.builder().addHttpListener(TestPortProvider.getPort(), TestPortProvider.getHost()));
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
