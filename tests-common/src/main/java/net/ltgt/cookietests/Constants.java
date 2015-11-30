package net.ltgt.cookietests;

public interface Constants {
  String TEST_COOKIE_NAME = "$Secure-Foo";
  String TEST_COOKIE_VALUE = "value";

  String TEST_COOKIE = TEST_COOKIE_NAME + "=" + TEST_COOKIE_VALUE;
  String TEST_SET_COOKIE = TEST_COOKIE + ";Path=/;HttpOnly";
}
