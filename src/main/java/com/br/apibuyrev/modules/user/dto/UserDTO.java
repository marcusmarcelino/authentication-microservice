package com.br.apibuyrev.modules.user.dto;

import java.util.UUID;

/**
 * UserDTO
 */
public record UserDTO(UUID id, String email) {
}