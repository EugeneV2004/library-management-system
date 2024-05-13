CREATE TABLE Person (
    id serial primary key,
    name varchar(50),
    surname varchar(50),
    patronymic varchar(50),
    year_of_birth int,
    CONSTRAINT unique_index_name_surname_patronymic UNIQUE (name, surname, patronymic)
);

CREATE TABLE Book (
    id serial primary key,
    owner_id int REFERENCES Person(id),
    title varchar(100),
    author varchar(50),
    year int
)