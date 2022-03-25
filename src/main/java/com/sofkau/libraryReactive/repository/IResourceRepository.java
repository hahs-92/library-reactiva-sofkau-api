package com.sofkau.libraryReactive.repository;

import com.sofkau.libraryReactive.collection.Resource;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IResourceRepository extends ReactiveMongoRepository<Resource, String> {
    Flux<Resource> findAll();
    @Query(value = "{ type: ?0 }")
    Flux<Resource> findResourcesByType(String type);
    @Query(value = "{ theme: ?0 }")
    Flux<Resource> findResourcesByTheme(String theme);
    @Query(value = "{ type: ?0, theme: ?1}")
    Flux<Resource> findAllByQueries(String type, String theme);
}
