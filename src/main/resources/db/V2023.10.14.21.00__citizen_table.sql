CREATE TABLE citizen
(
    id                  BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name          VARCHAR     NOT NULL,
    last_name           VARCHAR     NOT NULL,
    email               VARCHAR     NOT NULL,
    password            VARCHAR     NOT NULL,
    phone               BIGINT      NOT NULL,
    country             VARCHAR     NOT NULL,
    is_active           boolean     NOT NULL,
    created_by          VARCHAR     NOT NULL,
    created_on          TIMESTAMP   NOT NULL,
    modified_by         VARCHAR     NULL,
    modified_on         TIMESTAMP   NULL,
    CONSTRAINT pk_citizen PRIMARY KEY (id)
);