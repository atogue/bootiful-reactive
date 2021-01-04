package org.craftchain.bootifulreactive.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@Import(ReservationHttpConfiguration.class)
@ExtendWith(SpringExtension.class)
public class ReservationHttpTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp () {
        Mockito.when(this
                .reservationRepository.findAll())
                .thenReturn(Flux.just(new Reservation("1", "Anicet")));
    }

    @Test
    public void get() throws Exception {

        this.webTestClient
                .get()
                .uri("http://localhost:8080/reservations")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBody().jsonPath("@.[0].name", "Anicet").exists();
    }
}
