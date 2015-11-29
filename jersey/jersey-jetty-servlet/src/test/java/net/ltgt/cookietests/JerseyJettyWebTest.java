package net.ltgt.cookietests;

import java.util.Collections;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.servlet.JettyWebContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class JerseyJettyWebTest extends OkHttpTest {

  Server server;

  @Before public void setUp() throws Exception {
    server = JettyWebContainerFactory.create(getUrl("/").uri(),
        Collections.singletonMap(ServletProperties.JAXRS_APPLICATION_CLASS, Application.class.getCanonicalName()));
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
