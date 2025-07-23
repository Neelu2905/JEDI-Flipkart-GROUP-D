package com.flipfit.dao;

import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import com.flipfit.exceptions.RoleAlreadyExistsException;
import com.flipfit.exceptions.RoleDoesNotExistsException;
import com.flipfit.exceptions.UserAlreadyExistsException;
import com.flipfit.exceptions.UserDoesNotExistsException;
import java.util.Collection;

/**
 * Interface for Data Access Object (DAO) operations related to GymUser and Role entities.
 * This interface defines the contract for managing users and roles in the FlipFit system.
 */
public interface GymUserDAO {

  /**
   * Adds a new GymUser to the system.
   * A unique ID is generated for the user.
   * @param user The GymUser object to add.
   * @return The added GymUser object with its new ID.
   * @throws UserAlreadyExistsException If a user with the same email already exists.
   * @throws UserDoesNotExistsException This exception is not expected during add but included for consistency if underlying methods throw it.
   */
  GymUser addUser(GymUser user) throws UserAlreadyExistsException, UserDoesNotExistsException;

  /**
   * Retrieves a GymUser by their ID.
   * @param id The ID of the user to retrieve.
   * @return The GymUser object if found.
   * @throws UserDoesNotExistsException If the user with the given ID does not exist.
   */
  GymUser getUserById(Long id) throws UserDoesNotExistsException;

  /**
   * Retrieves a GymUser by their email address.
   * @param email The email address of the user to retrieve.
   * @return The GymUser object if found.
   * @throws UserDoesNotExistsException If the user with the given email does not exist.
   */
  GymUser getUserByEmail(String email) throws UserDoesNotExistsException;

  /**
   * Updates an existing GymUser.
   * The user's ID must be set for the update to occur.
   * @param userId The ID of the user to update.
   * @param user The GymUser object with updated information.
   * @return The updated GymUser object.
   * @throws UserDoesNotExistsException If the user with the given ID was not found.
   */
  GymUser updateUser(Long userId, GymUser user) throws UserDoesNotExistsException;

  /**
   * Deletes a GymUser by their ID.
   * @param userId The ID of the user to delete.
   * @throws UserDoesNotExistsException If the user with the given ID does not exist.
   */
  void deleteUser(Long userId) throws UserDoesNotExistsException;

  /**
   * Retrieves all GymUser objects in the system.
   * @return A collection of all GymUser objects.
   */
  Collection<GymUser> getAllUsers();

  /**
   * Adds a new Role to the system.
   * A unique ID is generated for the role.
   * @param role The Role object to add.
   * @return The added Role object with its new ID.
   * @throws RoleAlreadyExistsException If a role with the same name already exists.
   * @throws RoleDoesNotExistsException This exception is not expected during add but included for consistency if underlying methods throw it.
   */
  Role addRole(Role role) throws RoleAlreadyExistsException, RoleDoesNotExistsException;

  /**
   * Retrieves a Role by its ID.
   * @param id The ID of the role to retrieve.
   * @return The Role object if found.
   * @throws RoleDoesNotExistsException If the role with the given ID does not exist.
   */
  Role getRoleById(Long id) throws RoleDoesNotExistsException;

  /**
   * Retrieves a Role by its name.
   * @param name The name of the role to retrieve.
   * @return The Role object if found.
   * @throws RoleDoesNotExistsException If the role with the given name does not exist.
   */
  Role getRoleByName(String name) throws RoleDoesNotExistsException;

  /**
   * Updates an existing Role.
   * The role's ID must be set for the update to occur.
   * @param roleId The ID of the role to update.
   * @param role The Role object with updated information.
   * @return The updated Role object.
   * @throws RoleDoesNotExistsException If the role with the given ID was not found.
   */
  Role updateRole(Long roleId, Role role) throws RoleDoesNotExistsException;

  /**
   * Deletes a Role by its ID.
   * @param id The ID of the role to delete.
   * @throws RoleDoesNotExistsException If the role with the given ID does not exist.
   */
  void deleteRole(Long id) throws RoleDoesNotExistsException;

  /**
   * Retrieves all Role objects in the system.
   * @return A collection of all Role objects.
   */
  Collection<Role> getAllRoles();
}
