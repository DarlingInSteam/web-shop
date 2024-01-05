CREATE TABLE "assortment" (
    "id" SERIAL PRIMARY KEY,
    "id_structural_subdivision" INTEGER
);

CREATE TABLE "check_table" (
    "id" SERIAL PRIMARY KEY,
    "id_structural_subdivision" INTEGER,
    "serial_number" INTEGER,
    "date" DATE,
    "time" TIME,
    "id_person" INTEGER,
    "Field7" INTEGER
);

CREATE TABLE "correspondence_position_person" (
                                                  "id_person" INTEGER,
                                                  "id_position" INTEGER,
                                                  "salary" REAL,
                                                  "date_of_employment" DATE,
                                                  "date_of_unemployment" DATE,
                                                  "part_time_worker" INTEGER
);

CREATE TABLE "counterparty" (
                                "id" SERIAL PRIMARY KEY,
                                "inn" INTEGER,
                                "address" TEXT,
                                "banks_name" TEXT,
                                "current_account_number" INTEGER,
                                "name" TEXT
);

CREATE TABLE "goods" (
                         "id" SERIAL PRIMARY KEY,
                         "name" TEXT,
                         "id_unit" INTEGER
);

CREATE TABLE "list_of_goods_in_the_check" (
                                              "id_check" INTEGER,
                                              "id_goods" INTEGER,
                                              "quantity" INTEGER
);

CREATE TABLE "list_of_goods_in_the_price_list" (
                                                   "id_price" INTEGER,
                                                   "id_goods" INTEGER,
                                                   "cost" INTEGER
);

CREATE TABLE "list_of_goods_in_the_request" (
                                                "id_request" INTEGER,
                                                "id_goods" INTEGER,
                                                "quantity" INTEGER NOT NULL
);

CREATE TABLE "list_of_products_in_assortment" (
                                                  "id_assortment" INTEGER,
                                                  "id_goods" INTEGER,
                                                  "quantity" INTEGER,
                                                  "cost" INTEGER
);

CREATE TABLE "organization" (
                                "id" SERIAL PRIMARY KEY,
                                "name" TEXT UNIQUE,
                                "legal_address" TEXT
);

CREATE TABLE "person" (
                          "id" SERIAL PRIMARY KEY,
                          "id_structural_subdivision" INTEGER,
                          "passport_series" INTEGER,
                          "passport_number" INTEGER,
                          "snils" INTEGER,
                          "inn" TEXT,
                          "address" TEXT,
                          "sex" TEXT,
                          "age" INTEGER,
                          "name" TEXT NOT NULL,
                          "surname" TEXT NOT NULL,
                          "father_name" TEXT,
                          "telephone_number" TEXT
);

CREATE TABLE "position" (
                            "id" SERIAL PRIMARY KEY,
                            "name" TEXT
);

CREATE TABLE "price" (
                         "id" SERIAL PRIMARY KEY,
                         "id_counterparty" INTEGER
);

CREATE TABLE "purchase_request" (
                                    "id" SERIAL PRIMARY KEY,
                                    "id_request" INTEGER,
                                    "id_counterparty" INTEGER
);

CREATE TABLE "relocation_request" (
                                      "id" SERIAL PRIMARY KEY,
                                      "id_structural_subdivision" INTEGER,
                                      "id_request" INTEGER
);

CREATE TABLE "request" (
                           "id" SERIAL PRIMARY KEY,
                           "id_structural_subdivision" INTEGER,
                           "date" DATE,
                           "time" TIME
);

CREATE TABLE "role" (
                        "id" SERIAL PRIMARY KEY,
                        "name" TEXT
);

CREATE TABLE "structural_subdivision" (
                                          "id" SERIAL PRIMARY KEY,
                                          "id_organization" INTEGER,
                                          "address" TEXT,
                                          "name" TEXT UNIQUE,
                                          "id_of_type" INTEGER
);

CREATE TABLE "types_of_structural_subdivisions" (
                                                    "id" SERIAL PRIMARY KEY,
                                                    "type" TEXT UNIQUE
);

CREATE TABLE "unit" (
                        "id" SERIAL PRIMARY KEY,
                        "name" TEXT
);

CREATE TABLE "users" (
                         "id" SERIAL PRIMARY KEY,
                         "login" TEXT UNIQUE,
                         "password" TEXT,
                         "role_id" INTEGER,
                         "person_id" INTEGER,
                         "organization_id" INTEGER
);

ALTER TABLE "assortment" ADD FOREIGN KEY ("id_structural_subdivision") REFERENCES "structural_subdivision" ("id");

ALTER TABLE "check_table" ADD FOREIGN KEY ("id_structural_subdivision") REFERENCES "structural_subdivision" ("id");

ALTER TABLE "check_table" ADD FOREIGN KEY ("id_person") REFERENCES "person" ("id");

ALTER TABLE "correspondence_position_person" ADD FOREIGN KEY ("id_person") REFERENCES "person" ("id");

ALTER TABLE "correspondence_position_person" ADD FOREIGN KEY ("id_position") REFERENCES "position" ("id");

ALTER TABLE "goods" ADD FOREIGN KEY ("id_unit") REFERENCES "unit" ("id");

ALTER TABLE "list_of_goods_in_the_check" ADD FOREIGN KEY ("id_goods") REFERENCES "goods" ("id");

ALTER TABLE "list_of_goods_in_the_check" ADD FOREIGN KEY ("id_check") REFERENCES "check_table" ("id");

ALTER TABLE "list_of_goods_in_the_price_list" ADD FOREIGN KEY ("id_price") REFERENCES "price" ("id");

ALTER TABLE "list_of_goods_in_the_price_list" ADD FOREIGN KEY ("id_goods") REFERENCES "goods" ("id");

ALTER TABLE "list_of_goods_in_the_request" ADD FOREIGN KEY ("id_goods") REFERENCES "goods" ("id");

ALTER TABLE "list_of_goods_in_the_request" ADD FOREIGN KEY ("id_request") REFERENCES "request" ("id");

ALTER TABLE "list_of_products_in_assortment" ADD FOREIGN KEY ("id_goods") REFERENCES "goods" ("id");

ALTER TABLE "list_of_products_in_assortment" ADD FOREIGN KEY ("id_assortment") REFERENCES "assortment" ("id");

ALTER TABLE "person" ADD FOREIGN KEY ("id_structural_subdivision") REFERENCES "structural_subdivision" ("id");

ALTER TABLE "price" ADD FOREIGN KEY ("id_counterparty") REFERENCES "counterparty" ("id");

ALTER TABLE "purchase_request" ADD FOREIGN KEY ("id_counterparty") REFERENCES "counterparty" ("id");

ALTER TABLE "purchase_request" ADD FOREIGN KEY ("id_request") REFERENCES "request" ("id");

ALTER TABLE "relocation_request" ADD FOREIGN KEY ("id_request") REFERENCES "request" ("id");

ALTER TABLE "relocation_request" ADD FOREIGN KEY ("id_structural_subdivision") REFERENCES "structural_subdivision" ("id");

ALTER TABLE "request" ADD FOREIGN KEY ("id_structural_subdivision") REFERENCES "structural_subdivision" ("id");

ALTER TABLE "structural_subdivision" ADD FOREIGN KEY ("id_organization") REFERENCES "organization" ("id");

ALTER TABLE "structural_subdivision" ADD FOREIGN KEY ("id_of_type") REFERENCES "types_of_structural_subdivisions" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("role_id") REFERENCES "role" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("person_id") REFERENCES "person" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("organization_id") REFERENCES "organization" ("id");