package org.craftchain.bootifulreactive.producer;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

@Import(ReservationHttpConfiguration.class)
@SpringBootTest(properties = "server.port=0",
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class BaseClass {

    @MockBean
    private ReservationRepository reservationRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void before () {

        RestAssured.baseURI = "http://localhost:" + this.port;

        Mockito.when(this
                .reservationRepository.findAll())
                .thenReturn(Flux.just(new Reservation("1", "Anicet")));
    }
}
