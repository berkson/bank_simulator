CREATE TABLE account
(
    account_id     int8 GENERATED ALWAYS AS IDENTITY,
    account_number integer,
    balance        numeric(30, 15),
    CONSTRAINT account_pk PRIMARY KEY (account_id)
)

CREATE TABLE users
(
    user_id    int8 GENERATED ALWAYS AS IDENTITY,
    username   varchar(50)  NOT NULL,
    fullname   varchar(255) NOT NULL,
    age        integer,
    passsword  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    account_id int8,
    CONSTRAINT user_pk PRIMARY KEY (user_id),
    CONSTRAINT user_account_fk FOREIGN KEY (account_id) REFERENCES account (account_id)
)

CREATE TABLE authorizations
(
    authority_id int8 GENERATED ALWAYS AS IDENTITY,
    authority    varchar(100) NOT NULL,
    description  varchar(255) NOT NULL,
    CONSTRAINT auth_pk PRIMARY KEY (authority_id)
);

CREATE TABLE permissions
(
    user_id      int8 NOT NULL,
    authority_id int8 NOT NULL,
    CONSTRAINT permi_id_auth_uk UNIQUE (user_id, authority_id),
    CONSTRAINT permi_auth_id FOREIGN KEY (authority_id) REFERENCES authorizations (authority_id),
    CONSTRAINT permi_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE operations
(
    operation_id   int8 GENERATED ALWAYS AS IDENTITY,
    account_id     int8        NOT NULL,
    operation_type varchar(20) NOT NULL,
    CONSTRAINT operations_pk PRIMARY KEY (operation_id),
    CONSTRAINT oper_account_fk FOREIGN KEY (account_id) REFERENCES account (account_id)
)