package net.ltgt.cookietests;

import org.junit.Rule;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.MockWebServer;

public class OkHttpTestTest extends OkHttpTest {

  @Rule public MockWebServer mockWebServer = createMockWebServer();

  @Override
  protected HttpUrl getUrl(String path) {
    return mockWebServer.url(path);
  }
}
