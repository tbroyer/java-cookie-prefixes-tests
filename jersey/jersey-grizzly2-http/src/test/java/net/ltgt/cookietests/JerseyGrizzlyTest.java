package net.ltgt.cookietests;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class JerseyGrizzlyTest extends OkHttpTest {

  HttpServer server;

  @Before public void setUp(){
    server = GrizzlyHttpServerFactory.createHttpServer(getUrl("/").uri(), ResourceConfig.forApplicationClass(Application.class));
  }

  @After public void tearDown() {
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
