package net.ltgt.cookietests;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.utils.ResourceUtils;
import org.junit.After;
import org.junit.Before;

import com.squareup.okhttp.HttpUrl;

public class CXFTest extends OkHttpTest {

  Server server;

  @Before public void setUp() {
    JAXRSServerFactoryBean sf = ResourceUtils.createApplication(new Application(), true);
    sf.setApplication(new Application());
    sf.setAddress(getUrl("/").toString());
    server = sf.create();
  }

  @After public void tearDown() {
    server.stop();
    server.destroy();
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
