CREATE TABLE Address
(
    id     INT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city   VARCHAR(255) NOT NULL,
    state  VARCHAR(2)   NOT NULL,
    zip    VARCHAR(10)  NOT NULL UNIQUE,
    country VARCHAR(255) NOT NULL
);