package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@ApplicationScoped
public class MyEntityService {

    @Transactional
    public MyEntityDTO createMyEntityAndAddMyEmbeddable() {
        var myEntity = new MyEntity();

        var myEmbeddable = new MyEmbeddable();
        myEmbeddable.setEmbeddedProperty("reproducer-create");

        myEntity.getMyEmbeddables()
                .add(myEmbeddable);

        myEntity.persistAndFlush();

        return toDTO(myEntity);
    }

    @Transactional
    public Optional<MyEntityDTO> addMyEmbeddable(UUID id) {
        var myEmbeddable = new MyEmbeddable();
        myEmbeddable.setEmbeddedProperty("reproducer-add");

        return MyEntity.<MyEntity>findByIdOptional(id)
                       .map(myEntity -> {
                           myEntity.getMyEmbeddables()
                                   .add(myEmbeddable);

                           return myEntity;
                       })
                       .map(myEntity -> {
                           myEntity.persistAndFlush();

                           return myEntity;
                       })
                       .map(MyEntityService::toDTO);
    }

    private static MyEntityDTO toDTO(MyEntity myEntity) {
        return MyEntityDTO.builder()
                          .id(myEntity.getId())
                          .myEmbeddables(myEntity.getMyEmbeddables()
                                                 .stream()
                                                 .map(x -> MyEmbeddableDTO.builder()
                                                                          .embeddedProperty(x.getEmbeddedProperty())
                                                                          .build())
                                                 .toList())
                          .build();
    }
}
