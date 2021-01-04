package org.craftchain.bootifulreactive.producer;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservationPojoTest {

    @Test
    @DisplayName("verify pojo properties through assertions from jupiter.api and matchers from hamcrest")
    public void create() throws Exception {

        Reservation re = new Reservation("1", "Anicet");
        Assertions.assertNotNull(re.getId());
        Assertions.assertEquals(re.getName(), "Anicet");
        MatcherAssert.assertThat(re.getName(), Matchers.equalTo("Anicet"));
        MatcherAssert.assertThat(re.getName(), Matchers.equalToIgnoringCase("Anicet"));
        MatcherAssert.assertThat(re.getName(), new BaseMatcher() {
            @Override
            public boolean matches(Object o) {
                return o != null;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("The name should not be null!");
            }
        });

    }
}
