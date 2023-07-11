package com.br.authentication_microservice.modules.user.dto;

import java.util.UUID;

/**
 * UserDTO
 */
public record UserDTO(UUID id, String email) {
}