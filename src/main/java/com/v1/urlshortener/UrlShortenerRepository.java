package com.v1.urlshortener;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends CrudRepository<UrlShortenerModel, Integer> {

    UrlShortenerModel findByRandomString(String randomString);
}
