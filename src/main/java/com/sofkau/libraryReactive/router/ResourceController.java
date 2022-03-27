package com.sofkau.libraryReactive.router;

import com.sofkau.libraryReactive.service.ResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;


@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Bean
    public RouterFunction<ServerResponse> getList(ResourceService service)  {
        return RouterFunctions.route().GET("/",request -> ServerResponse.ok().body(
                service.getAll()
        )).build();
    }

   /* @PostMapping
    public ResponseEntity<Mono<Resource>> create(@RequestBody Resource resource) {
        try {
            return new ResponseEntity<>(service.create(resource), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Flux<Resource>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
