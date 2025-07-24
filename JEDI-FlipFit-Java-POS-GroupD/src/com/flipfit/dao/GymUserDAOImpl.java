package com.flipfit.dao;

import com.flipfit.beans.GymUser;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymCustomer;// NEW: Import GymOwner
import com.flipfit.beans.Role;
import com.flipfit.constants.Constants;
import com.flipfit.exceptions.RoleAlreadyExistsException;
import com.flipfit.exceptions.RoleDoesNotExistsException;
import com.flipfit.exceptions.UserAlreadyExistsException;
import com.flipfit.exceptions.UserDoesNotExistsException;
import org.w3c.dom.ls.LSOutput; // Consider removing if unused, as per earlier suggestions

import java.sql.SQLOutput; // Consider removing if unused
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet; // NEW: Import HashSet for roles
import java.util.Map;
import java.util.Set; // NEW: Import Set for roles
import java.util.concurrent.atomic.AtomicLong;

public class GymUserDAOImpl implements GymUserDAO {
  private static final Map<Long, GymUser> userMap = new HashMap<>();
  private static final Map<Long, Role> roleMap = new HashMap<>();

  // Start counters from 0 or 1, and let them increment naturally.
  // Setting to 3 here means your first generated ID will be 4.
  // If you want IDs to start from 1, set to 0.
  private final static AtomicLong userIdCounter = new AtomicLong(0); // Changed to 0 for simpler ID sequence
  private final static AtomicLong roleIdCounter = new AtomicLong(0); // Changed to 0 for simpler ID sequence

  public GymUserDAOImpl() {
    System.out.println("Initializing GymUserDAOImpl...");

    // Initialize basic roles first
      long adminRoleId = roleIdCounter.incrementAndGet(); // This will make first role ID 1
      roleMap.putIfAbsent(adminRoleId, new Role(adminRoleId, Constants.ADMIN, "Admin permissions")); // Pass ID to Role constructor

      long customerRoleId = roleIdCounter.incrementAndGet(); // This will make second role ID 2
      roleMap.putIfAbsent(customerRoleId, new Role(customerRoleId, Constants.CUSTOMER, "Customer permissions"));

      long ownerRoleId = roleIdCounter.incrementAndGet(); // This will make third role ID 3
      roleMap.putIfAbsent(ownerRoleId, new Role(ownerRoleId, Constants.OWNER, "Owner permissions"));

      // Initialize some dummy users if userMap is empty
    if (userMap.isEmpty()) {
      try {
        // Retrieve roles from roleMap to assign them to users
        Role adminRole = getRoleByName(Constants.ADMIN);
        Role customerRole = getRoleByName(Constants.CUSTOMER);
        Role ownerRole = getRoleByName(Constants.OWNER);

        // User 1: Sajid Anis (Admin)
        long sajidId = userIdCounter.incrementAndGet(); // This will make first user ID 1
        GymUser sajid = new GymUser("Sajid Anis", "sajid.anis20@gmail.com", "dummy");
        sajid.setUserId(sajidId);
        if(adminRole != null) sajid.addRole(adminRole); // Assign Admin role
        userMap.putIfAbsent(sajidId, sajid);

        // User 2: Neelu (Customer)
        long neeluId = userIdCounter.incrementAndGet(); // This will make second user ID 2
        GymCustomer neeluOwner = new GymCustomer( "Neelu", "neelu@flipkart.com", "password");
        neeluOwner.setUserId(neeluId);
        if(customerRole != null) neeluOwner.addRole(customerRole); // Assign Customer role
        userMap.putIfAbsent(neeluId, neeluOwner);

        // User 3: XYZ (Owner) - FIX THIS ONE TO BE GYMOWNER
        long xyzId = userIdCounter.incrementAndGet(); // This will make third user ID 3
        // FIX: Create a GymOwner object, not a GymUser object
        GymOwner xyzOwner = new GymOwner(
                "XYZ",
                "xyz@flipkart.com",
                "dummy",
                "XYZPAN123",  // Example PAN
                "XYZAADHAR456" // Example Aadhar
        );
        xyzOwner.setUserId(xyzId); // Set the generated ID
        if(ownerRole != null) xyzOwner.addRole(ownerRole); // Assign Owner role
        userMap.putIfAbsent(xyzId, xyzOwner); // Store the GymOwner object

        System.out.println("Pre-populated userMap after initialization:");
        userMap.forEach((id, user) -> System.out.println("  " + id + "=" + user.getClass().getSimpleName() + user.toString()));

      } catch (RoleDoesNotExistsException e) {
        System.err.println("Error pre-populating users: " + e.getMessage());
      }
    }
  }

  public Map<Long, GymUser> getUserMap() {
    return userMap;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GymUserDAOImpl{\n");
    sb.append("  userMap={\n");
    // Iterate and append each user's details, showing their actual class type
    userMap.forEach((id, user) -> {
      sb.append("    ").append(id).append("=").append(user.getClass().getSimpleName()).append(user.toString()).append(",\n");
    });
    sb.append("  },\n");
    sb.append("  roleMap=").append(roleMap.values()).append(",\n");
    sb.append("  userIdCounter=").append(userIdCounter).append(",\n");
    sb.append("  roleIdCounter=").append(roleIdCounter).append("\n");
    sb.append("}");
    return sb.toString();
  }

  // --- EXISTING METHODS (no functional changes needed here, only context-aware comments) ---

  public GymUser addUser(GymUser user) throws UserAlreadyExistsException, UserDoesNotExistsException {
    GymUser existingUser = userMap.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail())).findFirst().orElse(null);
    if(existingUser != null){
      throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
    }
    long newId = userIdCounter.incrementAndGet();
    user.setUserId(newId);
    userMap.put(newId, user); // This will store the actual type of 'user' (GymUser or GymOwner)
    System.out.println("User added: " + user.getName() + " with ID: " + newId);
    return user;
  }

  public GymUser getUserById(Long id) throws UserDoesNotExistsException {
    if(!userMap.containsKey(id)){
      throw new UserDoesNotExistsException("User does not exist with this id: " + id);
    }
    return userMap.get(id); // Returns the actual object type (GymUser or GymOwner)
  }

  public GymUser getUserByEmail(String email) throws UserDoesNotExistsException {
    // FIX: Add a null check for user.getEmail() before calling equalsIgnoreCase
    return userMap.values().stream()
            .filter(user -> user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) // ADDED null check
            .findFirst()
            .orElseThrow(() -> new UserDoesNotExistsException("User does not exist with this email: " + email));
  }


  public GymUser updateUser(Long userId, GymUser user) throws UserDoesNotExistsException {
    if (!userMap.containsKey(userId)) {
      throw new UserDoesNotExistsException("User does not exist with this userId: " + userId);
    }
    user.setUserId(userId);
    userMap.put(userId, user);
    System.out.println("[+] User updated: " + user.getName() + " with ID: " + user.getUserId());
    return user;
  }

  public void deleteUser(Long userId) throws UserDoesNotExistsException {
    if (!userMap.containsKey(userId)) {
      throw new UserDoesNotExistsException("User does not exist with this userId: " + userId);
    }
    GymUser removedUser = userMap.remove(userId);
    System.out.println("User deleted: " + removedUser.getName() + " with ID: " + userId);
  }

  public Collection<GymUser> getAllUsers() {
    return userMap.values();
  }

  // --- ROLE METHODS ---

  public Role addRole(Role role) throws RoleAlreadyExistsException, RoleDoesNotExistsException {
    // FIX: Use try-catch or check against null for getRoleByName as it throws
    try {
      if (getRoleByName(role.getRoleName()) != null) {
        throw new RoleAlreadyExistsException("[-] This role already exists: " + role.getRoleName());
      }
    } catch (RoleDoesNotExistsException e) {
      // This means the role doesn't exist, which is good for adding it. Do nothing here.
    }

    long newId = roleIdCounter.incrementAndGet();
    role.setRoleId(newId);
    roleMap.put(newId, role);
    System.out.println("Role added: " + role.getRoleName() + " with ID: " + newId);
    return role;
  }

  public Role getRoleById(Long id) throws RoleDoesNotExistsException {
    if(!roleMap.containsKey(id)){
      throw new RoleDoesNotExistsException("Role does not exist with this id: " + id);
    }
    return roleMap.get(id);
  }

  public Role getRoleByName(String name) throws RoleDoesNotExistsException {
    // Ensure this correctly filters through your roleMap.values()
    return roleMap.values().stream()
            .filter(role -> role.getRoleName().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new RoleDoesNotExistsException("Role does not exist with this name: " + name));
  }

  public Role updateRole(Long roleId, Role role) throws RoleDoesNotExistsException {
    if ( !roleMap.containsKey(role.getRoleId())) { // Should check roleId, not role.getRoleId() directly if roleId is the parameter
      throw new RoleDoesNotExistsException("Role does not exist with this id: " + roleId);
    }
    role.setRoleId(roleId); // Ensure the ID of the 'role' object matches the ID being updated
    roleMap.put(role.getRoleId(), role);
    System.out.println("Role updated: " + role.getRoleName() + " with ID: " + role.getRoleId());
    return role;
  }

  public void deleteRole(Long id) throws RoleDoesNotExistsException {
    if ( !roleMap.containsKey(id)) {
      throw new RoleDoesNotExistsException("Role does not exist with this id: " + id);
    }

    Role removedRole = roleMap.remove(id);
    System.out.println("Role deleted: " + removedRole.getRoleName() + " with ID: " + id);
  }

  public Collection<Role> getAllRoles() {
    return roleMap.values();
  }
}