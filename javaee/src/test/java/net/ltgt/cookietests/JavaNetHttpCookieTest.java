package net.ltgt.cookietests;

import static org.junit.Assert.*;

import java.net.HttpCookie;

import org.junit.Test;

public class JavaNetHttpCookieTest {
  @Test public void newHttpCookie() {
    HttpCookie cookie;
    try {
      cookie = new HttpCookie(Constants.TEST_COOKIE_NAME, Constants.TEST_COOKIE_VALUE);
    } catch (IllegalArgumentException iae) {
      fail();
      return;
    }
    cookie.setVersion(0);
    assertEquals(Constants.TEST_COOKIE, cookie.toString());
  }
}
