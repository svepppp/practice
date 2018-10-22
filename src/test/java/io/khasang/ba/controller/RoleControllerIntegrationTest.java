package io.khasang.ba.controller;

import io.khasang.ba.entity.Role;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration test for Role REST layer
 */
public class RoleControllerIntegrationTest {

    private static final String TEST_ROLE_NAME_PREFIX = "TEST_ROLE_";
    private static final String TEST_ROLE_DESCRIPTION = "Test role";

    private static final String ROOT = "http://localhost:8080/role";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";

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
     * Create prefilled test role entity with unique {@link Role#name} for further persistence process
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
