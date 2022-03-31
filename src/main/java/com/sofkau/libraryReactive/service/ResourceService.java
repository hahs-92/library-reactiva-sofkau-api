package com.sofkau.libraryReactive.service;

import com.sofkau.libraryReactive.model.Resource;
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

    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    public Flux<Resource> getAllByQueries(String type, String theme) {
        if(!type.isBlank() && !theme.isBlank()) {
            return repository.findAllByQueries(type, theme);
        }

        if(!type.isBlank()) {
            return repository.findResourcesByType(type);
        }

        if(!theme.isBlank()) {
            return repository.findResourcesByTheme(theme);
        }
        return repository.findAll();
    }

}
