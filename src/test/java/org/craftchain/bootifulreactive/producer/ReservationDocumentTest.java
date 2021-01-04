package org.craftchain.bootifulreactive.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ReservationDocumentTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void persist() throws Exception {
        // this test requires to run mongodb instance on a k8s or docker in localhost
        Reservation res = new Reservation(null, "Anicet");
        Mono<Reservation> save = this.reservationRepository.save(res);

        StepVerifier
                .create(save)
                .expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("anicet"))
                .verifyComplete();
    }
}
