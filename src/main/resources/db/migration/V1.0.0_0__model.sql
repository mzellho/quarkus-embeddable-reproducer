create table MyEntity
(
    id uuid not null,
    primary key (id)
);

create table MyEntity_myEmbeddables
(
    MyEntity_id         uuid         not null,
    embeddedProperty    varchar(255) not null,
    myEmbeddables_ORDER integer      not null,
    primary key (MyEntity_id, myEmbeddables_ORDER)
);
alter table if exists MyEntity_myEmbeddables
    add constraint MYFK foreign key (MyEntity_id) references MyEntity;
