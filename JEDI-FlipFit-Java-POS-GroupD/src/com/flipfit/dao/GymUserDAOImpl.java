package com.flipfit.dao;

import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import com.flipfit.constants.Constants;
import com.flipfit.exceptions.RoleAlreadyExistsException;
import com.flipfit.exceptions.RoleDoesNotExistsException;
import com.flipfit.exceptions.UserAlreadyExistsException;
import com.flipfit.exceptions.UserDoesNotExistsException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GymUserDAOImpl implements GymUserDAO {
  private static final Map<Long, GymUser> userMap = new HashMap<>();
  private static final Map<Long, Role> roleMap = new HashMap<>();

  private final static AtomicLong userIdCounter = new AtomicLong(3);
  private final static AtomicLong roleIdCounter = new AtomicLong(3);

  public GymUserDAOImpl() {
    // Initialize basic roles

    long adminRoleId = roleIdCounter.incrementAndGet();
    roleMap.putIfAbsent(adminRoleId, new Role(Constants.ADMIN, "Admin permissions"));

    long customerRoleId = roleIdCounter.incrementAndGet();
    roleMap.putIfAbsent(customerRoleId, new Role(Constants.CUSTOMER, "Customer permissions"));

    long ownerRoleId = roleIdCounter.incrementAndGet();
    roleMap.putIfAbsent(ownerRoleId, new Role(Constants.OWNER, "Owner permissions"));

    // Initialize some dummy users
    // User 1: Sajid Anis (Admin)
    long sajidId = userIdCounter.incrementAndGet();
    GymUser sajid = new GymUser("Sajid Anis", "sajid.anis20@gmail.com", "dummy");
    sajid.setUserId(sajidId);
    sajid.addRole(roleMap.get(adminRoleId)); // Assign Admin role
    userMap.putIfAbsent(sajidId, sajid);

    // User 2: Neelu (Customer)
    long neeluId = userIdCounter.incrementAndGet();
    GymUser neelu = new GymUser( "Neelu", "neelu@flipkart.com", "password");
    neelu.setUserId(neeluId);
    neelu.addRole(roleMap.get(customerRoleId)); // Assign Customer role
    userMap.putIfAbsent(neeluId, neelu);

    // User 3: XYZ (Owner)
    long xyzId = userIdCounter.incrementAndGet();
    GymUser xyz = new GymUser("XYZ", "xyz@flipkart.com", "dummy");
    xyz.setUserId(xyzId);
    xyz.addRole(roleMap.get(ownerRoleId)); // Assign Owner role
    userMap.putIfAbsent(xyzId, xyz);
  }

  /**
   * Adds a new GymUser to the system.
   * A unique ID is generated for the user.
   * @param user The GymUser object to add.
   * @return The added GymUser object with its new ID, or null if the user already exists (by email).
   */
  public GymUser addUser(GymUser user) throws UserAlreadyExistsException, UserDoesNotExistsException {
    // Check if a user with the same email already exists
    GymUser existingUser = userMap.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail())).findFirst().orElse(null);
    if(existingUser != null){
      throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
    }
    long newId = userIdCounter.incrementAndGet();
    user.setUserId(newId); // Set the generated ID for the user
    userMap.put(newId, user);
    System.out.println("User added: " + user.getName() + " with ID: " + newId);
    return user;
  }

  /**
   * Retrieves a GymUser by their ID.
   * @param id The ID of the user to retrieve.
   * @return The GymUser object if found, otherwise null.
   */
  public GymUser getUserById(Long id) throws UserDoesNotExistsException {
    if(!userMap.containsKey(id)){
        throw new UserDoesNotExistsException("User does not exist with this id: " + id);
    }
    return userMap.get(id);
  }

  /**
   * Retrieves a GymUser by their email address.
   * @param email The email address of the user to retrieve.
   * @return The GymUser object if found, otherwise null.
   */
  public GymUser getUserByEmail(String email) throws UserDoesNotExistsException {
    // Stream through the userMap values and find a user with the matching email
    return userMap.values().stream()
        .filter(user -> user.getEmail().equalsIgnoreCase(email))
        .findFirst()
        .orElseThrow(() -> new UserDoesNotExistsException("User does not exist with this email: " + email));
  }

  /**
   * Updates an existing GymUser.
   * The user's ID must be set for the update to occur.
   * @param user The GymUser object with updated information.
   * @return The updated GymUser object, or null if the user was not found.
   */
  public GymUser updateUser(Long userId, GymUser user) throws UserDoesNotExistsException {
    if (!userMap.containsKey(userId)) {
      throw new UserDoesNotExistsException("User does not exist with this userId: " + userId);
    }
    user.setUserId(userId);
    userMap.put(userId, user); // Overwrite the existing user with the updated one
    System.out.println("[+] User updated: " + user.getName() + " with ID: " + user.getUserId());
    return user;
  }

  /**
   * Deletes a GymUser by their ID.
   * @param userId The ID of the user to delete.
   * @return True if the user was deleted, false otherwise.
   */
  public void deleteUser(Long userId) throws UserDoesNotExistsException {
    if (!userMap.containsKey(userId)) {
      throw new UserDoesNotExistsException("User does not exist with this userId: " + userId);
    }
    GymUser removedUser = userMap.remove(userId);
    System.out.println("User deleted: " + removedUser.getName() + " with ID: " + userId);
  }

  /**
   * Retrieves all GymUser objects in the system.
   * @return A collection of all GymUser objects.
   */
  public Collection<GymUser> getAllUsers() {
    return userMap.values();
  }

  /**
   * Adds a new Role to the system.
   * A unique ID is generated for the role.
   * @param role The Role object to add.
   * @return The added Role object with its new ID, or null if the role already exists (by name).
   */
  public Role addRole(Role role) throws RoleAlreadyExistsException, RoleDoesNotExistsException {
    if (getRoleByName(role.getRoleName()) != null) {
      throw new RoleAlreadyExistsException("[-] This role already exists: " + role.getRoleName());
    }
    long newId = roleIdCounter.incrementAndGet();
    role.setRoleId(newId); // Assuming Role class has a setRoleId method
    roleMap.put(newId, role);
    System.out.println("Role added: " + role.getRoleName() + " with ID: " + newId);
    return role;
  }

  /**
   * Retrieves a Role by its ID.
   * @param id The ID of the role to retrieve.
   * @return The Role object if found, otherwise null.
   */
  public Role getRoleById(Long id) throws RoleDoesNotExistsException {
    if(!roleMap.containsKey(id)){
      throw new RoleDoesNotExistsException("Role does not exist with this id: " + id);
    }
    return roleMap.get(id);
  }

  /**
   * Retrieves a Role by its name.
   * @param name The name of the role to retrieve.
   * @return The Role object if found, otherwise null.
   */
  public Role getRoleByName(String name) throws RoleDoesNotExistsException {
    return roleMap.values().stream()
        .filter(role -> role.getRoleName().equalsIgnoreCase(name))
        .findFirst()
        .orElseThrow(() -> new RoleDoesNotExistsException("Role does not exist with this name: " + name));
  }

  /**
   * Updates an existing Role.
   * The role's ID must be set for the update to occur.
   * @param role The Role object with updated information.
   * @return The updated Role object, or null if the role was not found.
   */
  public Role updateRole(Long roleId, Role role) throws RoleDoesNotExistsException {
    if ( !roleMap.containsKey(role.getRoleId())) {
      throw new RoleDoesNotExistsException("Role does not exist with this id: " + roleId);
    }
    role.setRoleId(roleId);
    roleMap.put(role.getRoleId(), role); // Overwrite the existing role with the updated one
    System.out.println("Role updated: " + role.getRoleName() + " with ID: " + role.getRoleId());
    return role;
  }

  /**
   * Deletes a Role by its ID.
   * @param id The ID of the role to delete.
   * @return True if the role was deleted, false otherwise.
   */
  public void deleteRole(Long id) throws RoleDoesNotExistsException {
    if ( !roleMap.containsKey(id)) {
      throw new RoleDoesNotExistsException("Role does not exist with this id: " + id);
    }

    Role removedRole = roleMap.remove(id);
    System.out.println("Role deleted: " + removedRole.getRoleName() + " with ID: " + id);
  }

  /**
   * Retrieves all Role objects in the system.
   * @return A collection of all Role objects.
   */
  public Collection<Role> getAllRoles() {
    return roleMap.values();
  }
}
