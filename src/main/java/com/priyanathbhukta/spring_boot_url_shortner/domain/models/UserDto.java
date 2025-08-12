package com.priyanathbhukta.spring_boot_url_shortner.domain.models;

import java.io.Serializable;

/**
 * DTO for {@link com.priyanathbhukta.spring_boot_url_shortner.domain.entities.User}
 */
public record UserDto(Long id, String name) implements Serializable {
}