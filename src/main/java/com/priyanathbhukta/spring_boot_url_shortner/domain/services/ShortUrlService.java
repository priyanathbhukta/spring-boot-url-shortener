package com.priyanathbhukta.spring_boot_url_shortner.domain.services;

import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.ShortUrl;
import com.priyanathbhukta.spring_boot_url_shortner.domain.repositories.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public List<ShortUrl> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls();
    }


}
