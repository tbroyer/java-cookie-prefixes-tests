package net.ltgt.cookietests;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;

import org.junit.Test;

public class ServletCookieTest {
  @Test public void newCookie() {
    Cookie cookie;
    try {
      cookie = new Cookie("$Secure-Foo", "value");
    } catch (IllegalArgumentException iae) {
      fail();
      return;
    }
    assertEquals(0, cookie.getVersion());
  }
}
