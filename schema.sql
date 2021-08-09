create table users (
    id serial primary key,
    name varchar(255) not null,
    email varchar(125) not null,
    master_password varchar(15) not null
    unique key  UQ_USERS_NAME (name),
    unique key UQ_USERS_EMAIL (email)
);
