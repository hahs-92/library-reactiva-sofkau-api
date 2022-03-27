package com.sofkau.libraryReactive.usecase;

import com.sofkau.libraryReactive.dto.ResourceDto;
import com.sofkau.libraryReactive.mapper.ResourceMapper;
import com.sofkau.libraryReactive.repository.IResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

public class UseCaseGetAll implements Supplier<Flux<ResourceDto>> {
    private final IResourceRepository repository;
    private final ResourceMapper mapper;

    @Autowired
    public UseCaseGetAll(IResourceRepository repository, ResourceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<ResourceDto> get() {
        return repository.findAll()
                .map(mapper.mapResourceToDTO());
    }
}
