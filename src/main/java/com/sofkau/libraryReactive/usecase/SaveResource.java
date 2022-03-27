package com.sofkau.libraryReactive.usecase;

import com.sofkau.libraryReactive.dto.ResourceDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveResource {
    public Mono<String> apply(ResourceDto dto);
}
