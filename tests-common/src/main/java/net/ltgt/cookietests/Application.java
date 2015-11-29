package net.ltgt.cookietests;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Application extends javax.ws.rs.core.Application {
  @Override
  public Set<Class<?>> getClasses() {
    return new LinkedHashSet<Class<?>>(Arrays.asList(
        CheckSecureCookie.class,
        SetSecureCookie.class
    ));
  }
}
