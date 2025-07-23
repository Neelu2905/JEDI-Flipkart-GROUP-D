package com.flipfit.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GymClientFactory {
  private static final Map<String, GymClient> gymClientMap = new HashMap<String, GymClient>();

  static {
    gymClientMap.put("admin", new GymAdminClient());
    gymClientMap.put("customer", new GymCustomerClient());
    gymClientMap.put("owner",  new GymOwnerClient());
  }

  public static GymClient getGymClient(String role) {
    return Optional.ofNullable(gymClientMap.get(role.toLowerCase())).orElseThrow(
        () -> new IllegalArgumentException("No GymClient with role " + role)
    );
  }
}
