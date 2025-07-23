package com.flipfit.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GymClientFactory {
  private static final Map<String, GymClient> gymClientMap = new HashMap<String, GymClient>();

  static {
    gymClientMap.put("admin", new GymAdminMenu());
    gymClientMap.put("customer", new GymCustomerMenu());
    gymClientMap.put("owner",  new GymOwnerMenu());
  }

  public static GymClient getGymClient(String role) {
    return Optional.ofNullable(gymClientMap.get(role.toLowerCase())).orElseThrow(
        () -> new IllegalArgumentException("No GymClient with role " + role)
    );
  }
}
