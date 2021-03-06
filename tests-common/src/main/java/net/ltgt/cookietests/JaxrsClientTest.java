package net.ltgt.cookietests;

import static com.google.common.truth.Truth.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import com.squareup.okhttp.mockwebserver.MockWebServer;

public abstract class JaxrsClientTest {

  @Rule public MockWebServer mockWebServer = OkHttpTest.createMockWebServer();

  protected abstract Client getClient();

  @Test public void checkSecureCookieViaCookie() throws Exception {
    Response response = getClient().target(mockWebServer.url("/check-secure-cookie").toString())
        .request()
        .cookie(new Cookie(Constants.TEST_COOKIE_NAME, Constants.TEST_COOKIE_VALUE, null, null, 0))
        .get();
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(response.readEntity(String.class)).isEqualTo(Constants.TEST_COOKIE);
  }

  @Test public void checkSecureCookieViaHeader() throws Exception {
    Response response = getClient().target(mockWebServer.url("/check-secure-cookie").toString())
        .request()
        .header("Cookie", Constants.TEST_COOKIE)
        .get();
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(response.readEntity(String.class)).isEqualTo(Constants.TEST_COOKIE);
  }

  @Test public void setSecureCookie() throws Exception {
    Response response = getClient().target(mockWebServer.url("/set-secure-cookie").toString())
        .request()
        .get();
    assertThat(response.getStatusInfo().getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);
    assertThat(response.getCookies()).named("cookies").containsKey(Constants.TEST_COOKIE_NAME);
    NewCookie cookie = response.getCookies().get(Constants.TEST_COOKIE_NAME);
    assertThat(cookie.getName()).named("name").isEqualTo(Constants.TEST_COOKIE_NAME);
    assertThat(cookie.getValue()).named("value").isEqualTo(Constants.TEST_COOKIE_VALUE);
    assertThat(cookie.isSecure()).named("Secure").isFalse();
    assertThat(cookie.isHttpOnly()).named("HttpOnly").isTrue();
    assertThat(cookie.getDomain()).named("Domain").isNull();
    assertThat(cookie.getMaxAge()).named("Max-Age").isLessThan(0);
    assertThat(cookie.getExpiry()).named("Expires").isNull();
  }
}
