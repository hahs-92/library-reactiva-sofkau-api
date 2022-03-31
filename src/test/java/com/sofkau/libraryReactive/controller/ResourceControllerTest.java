package com.sofkau.libraryReactive.controller;


import com.sofkau.libraryReactive.model.Resource;
import com.sofkau.libraryReactive.service.ResourceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ResourceControllerTest {

    @MockBean
    private ResourceService service;

    @Autowired
    private WebTestClient testClient;

    @Test
    @DisplayName("POST /resources")
    void create() throws  Exception {
        Resource resource = new Resource();
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");

        Mockito.when(service.create(resource)).thenReturn(Mono.just(resource));

        testClient.post().uri("/resources")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(resource))
                .exchange()
                .expectStatus().isCreated();

    }


    @Test
    @DisplayName("GET /resources")
    void getAll() throws Exception {
        Resource resource = new Resource();
        resource.setId("xxx");
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");
        resource.setLastBorrowingDate(LocalDate.of(2020,12,21));
        resource.setAvailable(true);

        List<Resource> list = List.of(resource);
        Flux<Resource> flux = Flux.fromIterable(list);

        Mockito.when(service.getAll()).thenReturn(flux);

        testClient.get().uri("/resources")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Resource.class);
    }

    @Test
    @DisplayName("GET BY ID /resources")
    void getById() throws Exception {
        Resource resource = new Resource();
        resource.setId("xxx");
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");
        resource.setLastBorrowingDate(LocalDate.of(2020,12,21));
        resource.setAvailable(true);

        Mockito.when(service.getById("xxx")).thenReturn(Mono.just(resource));

        testClient.get().uri("/resources/{id}", "xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("xxx")
                        .jsonPath("$.title").isEqualTo("Testing");

        Mockito.verify(service, Mockito.times(1)).getById("xxx");
    }

    @Test
    @DisplayName(" DELETE /resources/{id}")
    void delete() throws  Exception {
        Mockito.when(service.deleteById("xxx")).thenReturn(Mono.empty());

        testClient.delete().uri("/resources/{id}","xxx")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("IS AVAILABLE  /resources/available/{id}")
    void isAvailable() throws Exception {
        Resource resource = new Resource();
        resource.setId("xxx");
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");
        resource.setLastBorrowingDate(LocalDate.of(2020,12,21));
        resource.setAvailable(true);

        Mockito.when(service.getById("xxx")).thenReturn(Mono.just(resource));

        testClient.get().uri("/resources/available/{id}", "xxx")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service, Mockito.times(1)).getById("xxx");
    }

    @Test
    @DisplayName("IS NOT AVAILABLE  /resources/available/{id}")
    void isNotAvailable() throws Exception {
        Resource resource = new Resource();
        resource.setId("xxx");
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");
        resource.setLastBorrowingDate(LocalDate.of(2020,12,21));
        resource.setAvailable(false);

        Mockito.when(service.getById("xxx")).thenReturn(Mono.just(resource));

        testClient.get().uri("/resources/available/{id}", "xxx")
                .exchange()
                .expectStatus().isForbidden();

        Mockito.verify(service, Mockito.times(1)).getById("xxx");
    }

    @Test
    @DisplayName("BORROW  /resources/borrow/{id}")
    void borrowResource() throws Exception {
        Resource resource = new Resource();
        resource.setId("xxx");
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");
        resource.setLastBorrowingDate(LocalDate.of(2020,12,21));
        resource.setAvailable(true);

        Mockito.when(service.getById("xxx")).thenReturn(Mono.just(resource));

        testClient.get().uri("/resources/borrow/{id}", "xxx")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service, Mockito.times(1)).getById("xxx");
        Mockito.verify(service, Mockito.times(1)).update(resource);
    }

    @Test
    @DisplayName("GIVEBACK  /resources/borrow/{id}")
    void giveBackResource() throws Exception {
        Resource resource = new Resource();
        resource.setId("xxx");
        resource.setTitle("Testing");
        resource.setDescription("This is a description");
        resource.setType("Book");
        resource.setTheme("programming");
        resource.setLastBorrowingDate(LocalDate.of(2020,12,21));
        resource.setAvailable(false);

        Mockito.when(service.getById("xxx")).thenReturn(Mono.just(resource));

        testClient.get().uri("/resources/giveBack/{id}", "xxx")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service, Mockito.times(1)).getById("xxx");
        Mockito.verify(service, Mockito.times(1)).update(resource);
    }

}
