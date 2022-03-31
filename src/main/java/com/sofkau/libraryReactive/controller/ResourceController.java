package com.sofkau.libraryReactive.controller;

import com.sofkau.libraryReactive.model.Resource;
import com.sofkau.libraryReactive.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService service;

    @GetMapping
    public ResponseEntity<Flux<Resource>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mono<Resource>> save(@RequestBody Resource resource) {
        try {
            return new ResponseEntity<>(service.create(resource), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Mono<Resource>> update(@RequestBody Resource resource) {
        try {
            return new ResponseEntity<>(service.update(resource), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Resource>> getById(@PathVariable("id") String id) {
        Mono<Resource> resource = service.getById(id);
        HttpStatus status = resource != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return  new ResponseEntity<>(resource, status);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            service.deleteById(id).subscribe();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available/{id}")
    public ResponseEntity<Mono<String>> isAvailable(@PathVariable("id") String id) {
        try {
            Boolean isAvailable = service.getById(id)
                    .map(Resource::getAvailable)
                    .block();

            if(!isAvailable) {
                return new ResponseEntity<>(
                        Mono.just("Resource is not available"),
                        HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(Mono.just("The resource is available"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/borrow/{id}")
    public ResponseEntity<String> borrowResource(@PathVariable String id) {
        try {
            Mono<Resource> resource = service.getById(id);
            if(!resource.block().getAvailable()) {
                return new ResponseEntity<>(
                        "Resource is not available, last borrowing date: " + resource.block().getLastBorrowingDate(),
                        HttpStatus.FORBIDDEN
                );
            }
            resource.block().setAvailable(false);
            resource.block().setLastBorrowingDate(LocalDate.now());

            service.update(resource.block());
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }  catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/giveBack/{id}")
    public ResponseEntity<String> giveBackResource(@PathVariable String id) {
        try {
            Mono<Resource> resource = service.getById(id);

            if(resource.block().getAvailable()) {
                return new ResponseEntity<>(
                        "Do not giveBack this recourse, because it is not borrowed", HttpStatus.FORBIDDEN);
            }
            resource.block().setAvailable(true);
            service.update(resource.block());
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }  catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<Flux<Resource>> getByQuery(
            @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "theme", defaultValue = "") String theme
    ) {
        try {
            return new ResponseEntity<>(service.getAllByQueries(type, theme),HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


