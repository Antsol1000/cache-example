package com.solarsan.cacheexample;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Cacheable(value = "books", key = "#id")
    public Book getById(final int id) {
        simulateSlowQuery();
        return new Book(id, "some title");
    }

    private void simulateSlowQuery() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
