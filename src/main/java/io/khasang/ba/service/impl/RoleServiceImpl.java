package io.khasang.ba.service.impl;

import io.khasang.ba.dao.RoleDao;
import io.khasang.ba.entity.Role;
import io.khasang.ba.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of RoleService based on DAO-layer utilization
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * Add new role
     *
     * @param newRole New instance of role
     * @return Added {@link Role} instance
     */
    @Override
    public Role addRole(Role newRole) {
        return roleDao.add(newRole);
    }

    /**
     * Get role by id
     *
     * @param id Identifier of the desired role
     * @return Found {@link Role} instance
     */
    @Override
    public Role getRoleById(long id) {
        return roleDao.getById(id);
    }

    /**
     * Update existing role with new instance
     *
     * @param updatedRole Updated role instance
     * @return Updated {@link Role} instance
     */
    @Override
    public Role updateRole(Role updatedRole) {
        return roleDao.update(updatedRole);
    }

    /**
     * Get all roles
     *
     * @return {@link List} instance of all roles
     */
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }

    /**
     * Delete role by id
     *
     * @param id Identifier of the role which should be deleted
     * @return Deleted {@link Role} instance
     */
    @Override
    public Role deleteRole(long id) {
        return roleDao.delete(getRoleById(id));
    }
}
