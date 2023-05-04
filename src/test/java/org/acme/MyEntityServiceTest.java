package org.acme;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
@TestTransaction
class MyEntityServiceTest {

    @Inject
    MyEntityService myEntityService;

    @Test
    void createMyEntityAndAddMyEmbeddable_shouldAddMyEmbeddable() {
        // createMyEntityAndAddMyEmbeddable works
        Assertions.assertTrue(this.myEntityService.createMyEntityAndAddMyEmbeddable()
                                                  .getMyEmbeddables()
                                                  .stream()
                                                  .anyMatch(myEmbeddableDTO -> "reproducer-create".equals(myEmbeddableDTO.getEmbeddedProperty())));
    }

    @Test
    void addMyEmbeddable_withExistingEntity_shouldAddMyEmbeddable() {
        // addMyEmbeddable fails with java.lang.NullPointerException:
        // Cannot invoke "org.hibernate.sql.model.PreparableMutationOperation.canBeBatched(org.hibernate.engine.jdbc.batch.spi.BatchKey, int)" because "jdbcOperation" is null
        Assertions.assertTrue(this.myEntityService.addMyEmbeddable(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                                                  .get()
                                                  .getMyEmbeddables()
                                                  .stream()
                                                  .anyMatch(myEmbeddableDTO -> "reproducer-add".equals(myEmbeddableDTO.getEmbeddedProperty())));
    }
}
