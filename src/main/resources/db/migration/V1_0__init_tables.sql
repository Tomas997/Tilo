CREATE CACHED TABLE "PUBLIC"."TILO_USER_ROLES"(
    "TILO_USER_ID" BIGINT NOT NULL,
    "ROLES" CHARACTER VARYING(16)
);
    CREATE TABLE "PUBLIC"."USERS"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "PASSWORD" CHARACTER VARYING(64),
    "USERNAME" CHARACTER VARYING(25)
);

ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" PRIMARY KEY("ID");

CREATE TABLE "PUBLIC"."YOGA_SERVICE"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "DESCRIPTION" CHARACTER VARYING(4096),
    "IMG_NAME" CHARACTER VARYING(64),
    "NAME" CHARACTER VARYING(64),
    "PRICE" CHARACTER VARYING(16),
    "SHORT_DESCRIPTION" CHARACTER VARYING(100)
);
ALTER TABLE "PUBLIC"."YOGA_SERVICE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_32" PRIMARY KEY("ID");


ALTER TABLE "PUBLIC"."TILO_USER_ROLES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" CHECK("ROLES" IN('ROLE_USER', 'ROLE_COACH', 'ROLE_ADMIN')) NOCHECK;
ALTER TABLE "PUBLIC"."YOGA_SERVICE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3" UNIQUE("NAME");
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" UNIQUE("USERNAME");
ALTER TABLE "PUBLIC"."TILO_USER_ROLES" ADD CONSTRAINT "PUBLIC"."FK5EMA48S71JVB1MBQWHXN8HJVS" FOREIGN KEY("TILO_USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;
