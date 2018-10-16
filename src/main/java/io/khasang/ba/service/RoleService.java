package io.khasang.ba.service;

import io.khasang.ba.entity.Role;

import java.util.List;

/**
 * Service layer interface for role management
 */
public interface RoleService {

    /**
     * Add new role
     *
     * @param newRole New instance of role
     * @return Added {@link Role} instance
     */
    Role addRole(Role newRole);

    /**
     * Get role by id
     *
     * @param id Identifier of the desired role
     * @return Found {@link Role} instance
     */
    Role getRole(long id);

    /**
     * Update existing role with new instance
     *
     * @param updatedRole Updated role instance
     * @return Updated {@link Role} instance
     */
    Role updateRole(Role updatedRole);

    /**
     * Get all roles
     *
     * @return {@link List} instance of all roles
     */
    List<Role> getAllRoles();

    /**
     * Delete role by id
     *
     * @param id Identifier of the role which should be deleted
     * @return Deleted {@link Role} instance
     */
    Role deleteRole(long id);
}
