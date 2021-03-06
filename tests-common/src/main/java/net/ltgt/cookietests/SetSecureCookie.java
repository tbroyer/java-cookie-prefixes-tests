package net.ltgt.cookietests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/set-secure-cookie")
public class SetSecureCookie {
  @GET
  @Path("/cookie")
  public Response cookie() {
    return Response.noContent()
        .cookie(new NewCookie(Constants.TEST_COOKIE_NAME, Constants.TEST_COOKIE_VALUE,
            "/", null, 0, null, NewCookie.DEFAULT_MAX_AGE, null, false, true))
        .build();
  }

  @GET
  @Path("/header")
  public Response header() {
    return Response.noContent()
        .header(HttpHeaders.SET_COOKIE, Constants.TEST_SET_COOKIE)
        .build();
  }
}
