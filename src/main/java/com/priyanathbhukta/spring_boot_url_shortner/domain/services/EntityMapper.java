package com.priyanathbhukta.spring_boot_url_shortner.domain.services;

import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.ShortUrl;
import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.User;
import com.priyanathbhukta.spring_boot_url_shortner.domain.models.ShortUrlDto;
import com.priyanathbhukta.spring_boot_url_shortner.domain.models.UserDto;
import org.springframework.stereotype.Component;

@Component

public class EntityMapper {
    public ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
        UserDto userDto = null;
        if (shortUrl.getCreatedBy() != null) {
            userDto = toUserDto(shortUrl.getCreatedBy());
        }

        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getShortKey(),
                shortUrl.getOriginalUrl(),
                shortUrl.getIsPrivate(),
                shortUrl.getExpiresAt(),
                userDto,
                shortUrl.getClickCount(),
                shortUrl.getCreatedAt()
        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }


}
