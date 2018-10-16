package io.khasang.ba.service.impl;

import io.khasang.ba.entity.Role;
import io.khasang.ba.service.RoleService;

import java.util.List;

/**
 * Implementation of RoleService based on DAO-layer utilization
 */
public class RoleServiceImpl implements RoleService {

    /**
     * Add new role
     *
     * @param newRole New instance of role
     * @return Added {@link Role} instance
     */
    @Override
    public Role addRole(Role newRole) {
        return null;
    }

    /**
     * Get role by id
     *
     * @param id Identifier of the desired role
     * @return Found {@link Role} instance
     */
    @Override
    public Role getRole(long id) {
        return null;
    }

    /**
     * Update existing role with new instance
     *
     * @param updatedRole Updated role instance
     * @return Updated {@link Role} instance
     */
    @Override
    public Role updateRole(Role updatedRole) {
        return null;
    }

    /**
     * Get all roles
     *
     * @return {@link List} instance of all roles
     */
    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    /**
     * Delete role by id
     *
     * @param id Identifier of the role which should be deleted
     * @return Deleted {@link Role} instance
     */
    @Override
    public Role deleteRole(long id) {
        return null;
    }
}
