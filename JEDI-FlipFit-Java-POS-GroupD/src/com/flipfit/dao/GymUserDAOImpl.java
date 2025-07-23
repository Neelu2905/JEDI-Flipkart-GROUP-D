package com.flipfit.dao;

import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import com.flipfit.constants.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GymUserDAOImpl {
  private Map<Long, GymUser> userMap = new HashMap<>();
  private Map<Long, Role> roleMap = new HashMap<>();

  private AtomicLong userId = new AtomicLong();
  private AtomicLong roleId = new AtomicLong();

 public GymUserDAOImpl() {
   // Initialize basic roles

   roleMap.put(roleId.incrementAndGet(), new Role(Constants.ADMIN, "Admin permissions"));
   roleMap.put(roleId.incrementAndGet(), new Role(Constants.CUSTOMER, "Customer permissions"));
   roleMap.put(roleId.incrementAndGet(), new Role(Constants.OWNER, "Owner permissions"));

 }

}
