package com.sofkau.libraryReactive.usecase;

import com.sofkau.libraryReactive.collection.Resource;
import com.sofkau.libraryReactive.dto.ResourceDto;
import com.sofkau.libraryReactive.mapper.ResourceMapper;
import com.sofkau.libraryReactive.repository.IResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseCreate implements SaveResource {
    private final IResourceRepository repository;
    private final ResourceMapper mapper;

    @Autowired
    public UseCaseCreate(IResourceRepository repository, ResourceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(ResourceDto dto) {
        return repository.save(
                mapper.mapperToResource(null).apply(dto)
        ).map(Resource::getId);
    }
}
