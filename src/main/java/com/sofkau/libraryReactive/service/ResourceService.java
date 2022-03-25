package com.sofkau.libraryReactive.service;

import com.sofkau.libraryReactive.collection.Resource;
import com.sofkau.libraryReactive.repository.IResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResourceService {

    @Autowired
    private IResourceRepository repository;

    public Flux<Resource> getAll() {
        return repository.findAll();
    }

    public Mono<Resource> getById(String id) {
        return repository.findById(id);
    }

    public Mono<Resource> create(Resource resource) {
       return repository.save(resource);
    }

    public Mono<Resource> update(Resource resource) {
        return repository.save(resource);
    }

    public void deleteById(String id) {
       repository.deleteById(id);
    }

}
