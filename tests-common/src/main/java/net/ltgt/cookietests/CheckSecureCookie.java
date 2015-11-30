package net.ltgt.cookietests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/check-secure-cookie")
public class CheckSecureCookie {
  @GET
  public Response get(@Context HttpHeaders httpHeaders) {
    Cookie secureFoo = httpHeaders.getCookies().get(Constants.TEST_COOKIE_NAME);
    if (secureFoo == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok()
        .type(MediaType.TEXT_PLAIN_TYPE)
        .entity(secureFoo.getName() + "=" + secureFoo.getValue())
        .build();
  }
}
