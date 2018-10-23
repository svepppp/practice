package io.khasang.ba.controller;

import io.khasang.ba.entity.Role;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Integration test for Role REST layer
 */
public class RoleControllerIntegrationTest {
    private static final String TEST_ROLE_NAME_PREFIX = "TEST_ROLE_";
    private static final String TEST_ROLE_DESCRIPTION = "Test role";
    private static final int TEST_ENTITIES_COUNT = 30;

    private static final String ROOT = "http://localhost:8080/role";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String GET_ALL = "/get/all";
    private static final String UPDATE = "/update";
    private static final String DELETE_BY_ID = "/delete/{id}";

    /**
     * Check role addition
     */
    @Test
    public void checkAddRole() {
        Role createdRole = getCreatedRole();
        Role receivedRole = getRoleById(createdRole.getId());
        assertNotNull(receivedRole);
        checkRolesEquality(createdRole, receivedRole);
    }

    /**
     * Check role name uniqueness constraint. It must be impossible to add role
     * with duplicate {@link Role#name} value
     */
    @Test(expected = org.springframework.web.client.HttpServerErrorException.class)
    public void checkRoleNameUniqueness() {
        Role createdRole = getCreatedRole();
        Role duplicateRole = new Role();
        duplicateRole.setName(createdRole.getName());
        getRoleResponseEntityFromAdditionRequest(duplicateRole);
    }

    /**
     * Checks sequential addition of certain amount of roles addition and getting. Amount is set in
     * {@link #TEST_ENTITIES_COUNT} constant
     */
    @Test
    public void checkGetAllRoles() {
        List<Role> createdRoles = new ArrayList<>(TEST_ENTITIES_COUNT);
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            createdRoles.add(getCreatedRole());
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Role>> responseEntity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {
                }
        );
        List<Role> allReceivedRoles = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(allReceivedRoles);
        assertFalse(allReceivedRoles.isEmpty());

        List<Role> receivedRolesSubList =
                allReceivedRoles.subList(allReceivedRoles.size() - TEST_ENTITIES_COUNT, allReceivedRoles.size());
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            checkRolesEquality(createdRoles.get(i), receivedRolesSubList.get(i));
        }
    }

    /**
     * Check of role entity update via PUT request
     */
    @Test
    public void checkUpdateRole() {
        Role role = getCreatedRole();
        fillRole(role);
        putRoleToUpdate(role);
        Role updatedRole = getRoleById(role.getId());
        assertNotNull(updatedRole);
        checkRolesEquality(role, updatedRole);
    }

    /**
     * Check of role deletion
     */
    @Test
    public void checkRoleDelete() {
        Role role = getCreatedRole();
        Role deletedRole = getDeletedRole(role.getId());
        checkRolesEquality(role, deletedRole);
        assertNull(getRoleById(role.getId()));
    }

    /**
     * Utility method which deletes role by id and retrieves role entity from DELETE response body
     *
     * @param id Id of the role which should be deleted
     * @return Deleted role
     */
    private Role getDeletedRole(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> responseEntity = restTemplate.exchange(
                ROOT + DELETE_BY_ID,
                HttpMethod.DELETE,
                null,
                Role.class,
                id
        );
        Role deletedRole = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(deletedRole);
        return deletedRole;
    }

    /**
     * Method for role getting by id
     *
     * @param id Id in table of roles
     * @return Found {@link Role} instance
     */
    private Role getRoleById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                Role.class,
                id
        );
        Role receivedRole = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return receivedRole;
    }

    /**
     * Methods check field of the roles on equality condition
     *
     * @param role1 First role
     * @param role2 Second role
     */
    private void checkRolesEquality(Role role1, Role role2) {
        assertEquals(role1.getId(), role2.getId());
        assertEquals(role1.getName(), role2.getName());
        assertEquals(role1.getDescription(), role2.getDescription());
    }

    /**
     * Put role for update
     *
     * @param role Role, which should be updated on service
     */
    private void putRoleToUpdate(Role role) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Role> httpEntity = new HttpEntity<>(role, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Role.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * Get created test role entity from POST response during role creation procedure. Instead of creating {@link Role}
     * instance by constructor, this method returns instance from response, thus created role contains table identifier
     *
     * @return Instance of {@link Role} with generated identifier
     */
    private Role getCreatedRole() {
        Role role = new Role();
        fillRole(role);
        ResponseEntity<Role> responseEntity = getRoleResponseEntityFromAdditionRequest(role);
        Role createdRole = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(createdRole);
        assertNotNull(createdRole.getId());
        return createdRole;
    }

    /**
     * Add role entity via POST request
     *
     * @param role {@link Role} instance, which should be added via POST request
     * @return {@link ResponseEntity} containing response data
     */
    private ResponseEntity<Role> getRoleResponseEntityFromAdditionRequest(Role role) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Role> httpEntity = new HttpEntity<>(role, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Role.class
        );
    }

    /**
     * Fill test role entity with unique {@link Role#name} for further persistence process.
     * {@link UUID} suffix is used to provide name uniqueness
     */
    private void fillRole(Role role) {
        role.setName(TEST_ROLE_NAME_PREFIX + UUID.randomUUID().toString());
        role.setDescription(TEST_ROLE_DESCRIPTION);
    }
}
