CREATE TABLE notes
(
    id   SERIAL PRIMARY KEY,
    uuid UUID         NOT NULL,
    text VARCHAR(255) NOT NULL
);