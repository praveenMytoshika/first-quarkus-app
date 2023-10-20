CREATE TABLE citizen
(
    id                  BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name          VARCHAR     NOT NULL,
    last_name           VARCHAR     NOT NULL,
    gender              VARCHAR(1)  NOT NULL,
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

CREATE TABLE aadhar
(
    id                  BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY,
    citizen_id          BIGINT      NOT NULL,
    aadhar_number       BIGINT      NOT NULL CONSTRAINT check_bigint_length CHECK (LENGTH(CAST(aadhar_number AS CHAR(16))) = 16),
    company             VARCHAR     NOT NULL,
    is_active           boolean     NOT NULL,
    created_by          VARCHAR     NOT NULL,
    created_on          TIMESTAMP   NOT NULL,
    modified_by         VARCHAR     NULL,
    modified_on         TIMESTAMP   NULL,
    CONSTRAINT pk_aadhar PRIMARY KEY (id),
    CONSTRAINT fk_aadhar_citizen_citizen_id_id FOREIGN KEY (citizen_id) REFERENCES citizen(id)
);

CREATE TABLE sim_card
(
    id                  BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY,
    citizen_id          BIGINT      NOT NULL,
    number              BIGINT      NOT NULL CONSTRAINT check_number_length CHECK (LENGTH(CAST(number AS CHAR(10))) = 10),
    provider            VARCHAR     NOT NULL,
    is_active           boolean     NOT NULL,
    created_by          VARCHAR     NOT NULL,
    created_on          TIMESTAMP   NOT NULL,
    modified_by         VARCHAR     NULL,
    modified_on         TIMESTAMP   NULL,
    CONSTRAINT pk_sim_card PRIMARY KEY (id),
    CONSTRAINT fk_sim_card_citizen_citizen_id_id FOREIGN KEY (citizen_id) REFERENCES citizen(id)
);