package org.acme;

import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MyEntityDTO {

    @Valid
    private UUID id;

    @Valid
    @Builder.Default
    private List<MyEmbeddableDTO> myEmbeddables = new ArrayList<>();
}
