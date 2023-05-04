package org.acme;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyEmbeddableDTO {

    @NotNull
    private String embeddedProperty;
}
