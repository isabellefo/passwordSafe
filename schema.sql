drop table if exists users;
create table users (
    usr_id serial primary key,
    usr_name varchar(255) not null,
    usr_email varchar(125) not null,
    usr_master_password varchar(63) not null,
    constraint UQ_USERS_EMAIL unique (usr_email)
);

-- masterPassword = 123456
insert into users values (1, 'Samuel', 'samuel@email.com', '$2a$10$bdszONkWGzLw733tIwwIeOgjL4uoEi1z37IMszOotRZHPoSs7hCr.');

drop table if exists password;
create table password (
    pwd_id serial primary key,
    pwd_name varchar(63) not null,
    pwd_password varchar(63) not null,
    usr_id int not null,
    CONSTRAINT fk_user FOREIGN KEY(usr_id) REFERENCES users(usr_id)
);
