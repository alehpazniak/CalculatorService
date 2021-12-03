drop table operation_log if exists;

create table operation_log(
ID int IDENTITY PRIMARY KEY,
OPERATION_NAME varchar(100) not null,
ARG_FIRST float8 not null,
ARG_SECOND float8 not null,
RESULT float8 not null,
CREATE_DATE DATE
);