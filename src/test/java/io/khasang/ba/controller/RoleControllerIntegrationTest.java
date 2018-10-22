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
    private static final int TEST_ENTITIES_COUNT = 10;

    private static final String ROOT = "http://localhost:8080/role";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String GET_ALL = "/get/all";

    /**
     * Check role addition
     */
    @Test
    public void checkAddRole() {
        Role createdRole = getCreatedRole();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                Role.class,
                createdRole.getId()
        );
        Role receivedRole = responseEntity.getBody();

        assertNotNull(receivedRole);
        assertEquals(createdRole.getId(), receivedRole.getId());
        assertEquals(createdRole.getName(), receivedRole.getName());
        assertEquals(createdRole.getDescription(), receivedRole.getDescription());
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
     * Checks sequential addition of N roles addition and extraction
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

        assertNotNull(allReceivedRoles);
        assertFalse(allReceivedRoles.isEmpty());

        List<Role> receivedRolesSubList =
                allReceivedRoles.subList(allReceivedRoles.size() - TEST_ENTITIES_COUNT, allReceivedRoles.size());
        for (int i = 0; i < TEST_ENTITIES_COUNT; i++) {
            assertEquals(createdRoles.get(i).getId(), receivedRolesSubList.get(i).getId());
        }
    }

    /**
     * Get test role entity from POST response during role creation procedure
     *
     * @return Instance of {@link Role} with generated identifier
     */
    private Role getCreatedRole() {
        Role filledRole = prefilledRole();
        ResponseEntity<Role> responseEntity = getRoleResponseEntityFromAdditionRequest(filledRole);
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
     * Create prefilled test role entity with unique {@link Role#name} for further persistence process.
     * {@link UUID} suffix is used to provide name uniqueness
     *
     * @return {@link Role} instance satisfying {@code UNIQUE} constraint
     */
    private Role prefilledRole() {
        Role role = new Role();
        role.setName(TEST_ROLE_NAME_PREFIX + UUID.randomUUID().toString());
        role.setDescription(TEST_ROLE_DESCRIPTION);
        return role;
    }
}
