CREATE TABLE notes
(
    id                SERIAL PRIMARY KEY,
    uuid              UUID         NOT NULL,
    title             VARCHAR(255) NOT NULL,
    description       VARCHAR(255) NOT NULL
);