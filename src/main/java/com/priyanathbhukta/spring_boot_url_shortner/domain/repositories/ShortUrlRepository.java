package com.priyanathbhukta.spring_boot_url_shortner.domain.repositories;

import com.priyanathbhukta.spring_boot_url_shortner.domain.entities.ShortUrl;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long> {


//    @Query("select su from ShortUrl su where su.isPrivate=false order by su.createdAt desc ")
//    @EntityGraph(attributePaths = {"createdBy"})
    @Query("select su from ShortUrl su left join fetch su.createdBy where su.isPrivate=false order by su.createdAt desc ")
    List<ShortUrl> findPublicShortUrls();

    boolean existsByShortKey(String shortKey);

    Optional<ShortUrl> findByShortKey(String shortKey);
}
