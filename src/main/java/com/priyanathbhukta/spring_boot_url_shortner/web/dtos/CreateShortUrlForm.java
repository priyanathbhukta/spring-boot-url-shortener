package com.priyanathbhukta.spring_boot_url_shortner.web.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateShortUrlForm(
        @NotBlank(message = "Original Url is required")
        String originalUrl) {




}
