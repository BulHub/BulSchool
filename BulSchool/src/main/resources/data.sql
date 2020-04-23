INSERT into roles(name, created, updated, status) values ('ROLE_USER', localtimestamp , localtimestamp, 'ACTIVE');

INSERT into roles(name, created, updated, status)
values ('ROLE_ADMIN', localtimestamp, localtimestamp, 'ACTIVE');

CREATE TABLE feedback(
  ID serial primary key,
  FirstName varchar(50),
  Email varchar(100),
  Message varchar(5000)
);