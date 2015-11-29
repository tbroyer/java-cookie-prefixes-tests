package net.ltgt.cookietests;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;
import com.sun.net.httpserver.HttpServer;

public class JerseySunHttpTest extends OkHttpTest {

  HttpServer server;

  @Before public void setUp() {
    server = JdkHttpServerFactory.createHttpServer(getUrl("/").uri(), ResourceConfig.forApplicationClass(Application.class));
  }

  @After public void tearDown() {
    server.stop(0);
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
