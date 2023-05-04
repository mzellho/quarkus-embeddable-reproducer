package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,
                   callSuper = false)
public class MyEntity
        extends PanacheEntityBase {

    @Id
    @NotNull
    @GeneratedValue
    @Column(nullable = false,
            insertable = false,
            updatable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(joinColumns = @JoinColumn(nullable = false,
                                               updatable = false))
    @AttributeOverride(name = "embeddedProperty",
                       column = @Column(nullable = false,
                                        updatable = false))
    @OrderColumn
    private List<MyEmbeddable> myEmbeddables = new ArrayList<>();
}
