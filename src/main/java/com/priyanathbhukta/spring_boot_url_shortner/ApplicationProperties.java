package com.priyanathbhukta.spring_boot_url_shortner;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "app")
@Validated

public record ApplicationProperties(
        @NotNull
        @DefaultValue("http://localhost:8080")
        String baseUrl,
        @DefaultValue("30")
        @Min(1)
        @Max(100)
        int defaultExpiryDays,
        @DefaultValue("true")
        boolean validateOriginalUrl) {


}
