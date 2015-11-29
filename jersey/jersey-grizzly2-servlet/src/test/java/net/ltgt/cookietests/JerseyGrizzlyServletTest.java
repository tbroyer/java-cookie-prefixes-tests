package net.ltgt.cookietests;

import java.io.IOException;
import java.util.Collections;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class JerseyGrizzlyServletTest extends OkHttpTest {

  HttpServer server;

  @Before public void setUp() throws IOException {
    server = GrizzlyWebContainerFactory.create(getUrl("/").uri(),
        Collections.singletonMap(ServletProperties.JAXRS_APPLICATION_CLASS, Application.class.getCanonicalName()));
  }

  @After public void tearDown() {
    server.shutdownNow();
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
