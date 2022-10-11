package com.solarsan.cacheexample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookRunner implements CommandLineRunner {

    private final BookRepository repository;
    private final CacheManager cacheManager;

    @Override
    public void run(final String... args) throws Exception {
        log.info("Fetching books...");
        log.info("with id 1 -> " + repository.getById(1));
        log.info("with id 1 -> " + repository.getById(1));
        log.info("now lets invalidate cache for id 1");
        cacheManager.getCache("books").evict(1);
        log.info("with id 1 -> " + repository.getById(1));
        log.info("with id 1 -> " + repository.getById(1));

        log.info("with id 2 -> " + repository.getById(2));
        log.info("with id 2 -> " + repository.getById(2));

        log.info("now lets invalidate all cache");
        cacheManager.getCache("books").invalidate();
        log.info("with id 1 -> " + repository.getById(1));
        log.info("with id 2 -> " + repository.getById(2));
        log.info("with id 1 -> " + repository.getById(1));
        log.info("with id 2 -> " + repository.getById(2));
    }
}
