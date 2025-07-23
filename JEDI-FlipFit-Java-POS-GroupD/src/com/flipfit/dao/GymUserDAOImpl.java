package com.flipfit.dao;

import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GymUserDAOImpl {
  private Map<Long, GymUser> userMap = new HashMap<>();
  private Map<Long, Role> roleMap = new HashMap<>();

  private AtomicLong userId = new AtomicLong();
  private AtomicLong roleId = new AtomicLong();



}
