package net.ltgt.cookietests;

import static org.junit.Assert.*;

import java.net.HttpCookie;

import org.junit.Test;

public class JavaNetHttpCookieTest {
  @Test public void newHttpCookie() {
    HttpCookie cookie;
    try {
      cookie = new HttpCookie("$Secure-Foo", "value");
    } catch (IllegalArgumentException iae) {
      fail();
      return;
    }
    cookie.setVersion(0);
    assertEquals("$Secure-Foo=value", cookie.toString());
  }
}
