package com.flipfit.client;

import com.flipfit.exceptions.RoleAlreadyExistsException;
import com.flipfit.exceptions.RoleDoesNotExistsException;

public interface GymClient {
    void Menu() throws RoleDoesNotExistsException, RoleAlreadyExistsException;

}
