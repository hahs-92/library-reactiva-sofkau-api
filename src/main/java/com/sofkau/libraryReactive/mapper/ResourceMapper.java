package com.sofkau.libraryReactive.mapper;

import com.sofkau.libraryReactive.collection.Resource;
import com.sofkau.libraryReactive.dto.ResourceDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ResourceMapper {
    public Function<ResourceDto, Resource> mapperToResource(String id) {
        return updateResource -> {
            var resource = new Resource();
            resource.setId(id);
            resource.setTitle(updateResource.getTitle());
            resource.setDescription(updateResource.getDescription());
            resource.setTheme(updateResource.getTheme());
            resource.setType(updateResource.getType());
            resource.setAvailable(updateResource.getAvailable());
            resource.setLastBorrowingDate(updateResource.getLastBorrowingDate());

            return  resource;
        };
    }

    public Function<Resource, ResourceDto> mapResourceToDTO() {
        return  entity -> new ResourceDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getType(),
                entity.getTheme(),
                entity.getLastBorrowingDate(),
                entity.getAvailable()
        );
    }
}

