CREATE TYPE "role" AS ENUM (
    'NULL',
    'SELLER',
    'MODERATOR',
    'ADMIN'
);

CREATE TABLE "store" (
    "id" SERIAL PRIMARY KEY,
    "warehouse_id" INT,
    "store_name" TEXT NOT NULL,
    "location" TEXT
);

CREATE TABLE "seller" (
    "id" SERIAL PRIMARY KEY,
    "user_id" INT,
    "name" TEXT NOT NULL,
    "salary" INT,
    "hire_date" TEXT
);

CREATE TABLE "sellers_in_store" (
    "seller_id" INT,
    "store_id" INT
);

CREATE TABLE "revenue" (
    "id" SERIAL PRIMARY KEY,
    "seller_id" INT,
    "store_id" INT,
    "revenue_amount" INT,
    "sale_date" TEXT
);

CREATE TABLE supplier (
    "id" SERIAL PRIMARY KEY,
    "organization_name" TEXT NOT NULL,
    "address" TEXT,
    "director_name" TEXT,
    "director_phone" TEXT,
    "bank_name" TEXT,
    "checking_account" TEXT,
    "inn" TEXT
);

CREATE TABLE "product" (
    "id" SERIAL PRIMARY KEY,
    "supplier_id" INT,
    "product_name" TEXT NOT NULL,
    "quantity_in_stock" INT,
    "price" INT
);

CREATE TABLE "products_in_warehouse" (
    "product_id" INT,
    "warehouse_id" INT
);

CREATE TABLE "warehouse" (
    "id" SERIAL PRIMARY KEY,
    "quantity_in_stock" INT
);

CREATE TABLE "users" (
    "id" SERIAL PRIMARY KEY,
    "username" TEXT UNIQUE NOT NULL,
    "password_hash" TEXT NOT NULL,
    "role" Role
);

CREATE TABLE "passport" (
    "id" SERIAL PRIMARY KEY,
    "user_id" INT,
    "passport_series" INTEGER,
    "passport_number" INTEGER,
    "full_name" TEXT,
    "birth_year" INT,
    "registration_place" TEXT,
    "issued_by" TEXT
);

ALTER TABLE "store" ADD FOREIGN KEY ("warehouse_id") REFERENCES "warehouse" ("id");

ALTER TABLE "seller" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "sellers_in_store" ADD FOREIGN KEY ("seller_id") REFERENCES "seller" ("id");

ALTER TABLE "sellers_in_store" ADD FOREIGN KEY ("store_id") REFERENCES "store" ("id");

ALTER TABLE "revenue" ADD FOREIGN KEY ("seller_id") REFERENCES "seller" ("id");

ALTER TABLE "revenue" ADD FOREIGN KEY ("store_id") REFERENCES "store" ("id");

ALTER TABLE "products_in_warehouse" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "products_in_warehouse" ADD FOREIGN KEY ("warehouse_id") REFERENCES "warehouse" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("supplier_id") REFERENCES "supplier" ("id");

ALTER TABLE "passport" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");