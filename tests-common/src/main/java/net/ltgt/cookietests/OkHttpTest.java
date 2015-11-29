package net.ltgt.cookietests;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

public abstract class OkHttpTest {

  public static MockWebServer createMockWebServer() {
    MockWebServer mockWebServer = new MockWebServer();
    mockWebServer.setDispatcher(new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        switch (request.getPath()) {
          case "/set-secure-cookie":
          case "/set-secure-cookie/cookie":
          case "/set-secure-cookie/header":
            return new MockResponse()
                .setResponseCode(204)
                .addHeader("Set-Cookie", "$Secure-Foo=value;Path=/;Secure;HttpOnly");
          case "/check-secure-cookie": {
            // XXX: not exactly equivalent to CheckSecureCookie's behavior
            String cookie = request.getHeader("Cookie");
            if (cookie == null || !cookie.contains("$Secure-Foo")) {
              return new MockResponse().setResponseCode(400);
            }
            return new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "text/plain")
                .setBody(request.getHeader("Cookie"));
          }
          default:
            return new MockResponse().setResponseCode(404);
        }
      }
    });
    return mockWebServer;
  }

  protected abstract HttpUrl getUrl(String path);

  @Test public void checkSecureCookie() throws Exception {
    Response response = new OkHttpClient().newCall(new Request.Builder()
        .url(getUrl("/check-secure-cookie"))
        .addHeader("Cookie", "$Secure-Foo=value")
        .build())
        .execute();
    assertThat(response.code()).isEqualTo(200);
    assertThat(response.body().string()).isEqualTo("$Secure-Foo=value");
  }

  @Test public void setSecureCookieViaCookie() throws Exception {
    Response response = new OkHttpClient().newCall(new Request.Builder()
        .url(getUrl("/set-secure-cookie/cookie"))
        .build())
        .execute();
    assertThat(response.isSuccessful()).isTrue();
    checkCookies(response);
  }

  @Test public void setSecureCookieViaHeader() throws Exception {
    Response response = new OkHttpClient().newCall(new Request.Builder()
        .url(getUrl("/set-secure-cookie/header"))
        .build())
        .execute();
    assertThat(response.isSuccessful()).isTrue();
    checkCookies(response);
  }

  private void checkCookies(Response response) {
    boolean found = false;
    for (String setCookie : response.headers("Set-Cookie")) {
      String[] parts = setCookie.split(";");
      String[] nameValue = parts[0].split("=", 2);
      if (nameValue.length != 2) {
        continue;
      }
      String name = nameValue[0].trim();
      if (!name.equals("$Secure-Foo")) {
        continue;
      }
      found = true;
      assertThat(nameValue[1].trim()).isEqualTo("value");

      for (int i = 1; i < parts.length; i++) {
        String part = parts[i];
        nameValue = part.split("=", 2);
        name = nameValue[0].trim();
        if (name.equalsIgnoreCase("Path")) {
          assertThat(nameValue[1]).named("Path").isEqualTo("/");
        } else if (name.equalsIgnoreCase("Secure")) {
          assertThat(nameValue.length).isEqualTo(1);
        } else if (name.equalsIgnoreCase("HttpOnly")) {
          assertThat(nameValue.length).isEqualTo(1);
        } else if (name.equalsIgnoreCase("Domain") || name.equalsIgnoreCase("Expires") || name.equalsIgnoreCase("Max-Age")) {
          fail("Unexpected $Secure-Foo parameter: " + name);
        }
      }
    }
    assertThat(found).named("Found $Secure-Foo cookie").isTrue();
  }
}
