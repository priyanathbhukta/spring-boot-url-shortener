package com.priyanathbhukta.spring_boot_url_shortner.domain.services;

import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.ShortUrl;
import com.priyanathbhukta.spring_boot_url_shortner.domain.models.ShortUrlDto;
import com.priyanathbhukta.spring_boot_url_shortner.domain.repositories.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
    }

    public List<ShortUrlDto> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls()
                .stream().map(entityMapper::toShortUrlDto).toList();
    }


}
