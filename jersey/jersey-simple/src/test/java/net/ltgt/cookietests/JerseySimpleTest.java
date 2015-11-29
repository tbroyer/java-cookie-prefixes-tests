package net.ltgt.cookietests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.simple.SimpleContainerFactory;
import org.glassfish.jersey.simple.SimpleServer;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class JerseySimpleTest extends OkHttpTest {

  SimpleServer server;

  @Before public void setUp() {
    server = SimpleContainerFactory.create(getUrl("/").uri(), ResourceConfig.forApplicationClass(Application.class));
  }

  @After public void tearDown() throws Exception {
    server.close();
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
