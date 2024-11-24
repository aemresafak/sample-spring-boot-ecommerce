CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS category
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE IF NOT EXISTS sub_category
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    category_id INT          NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_id     INT          NOT NULL,
    sub_category_id INT          NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    image_url       TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted         BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT fk_product_sub_category FOREIGN KEY (sub_category_id) REFERENCES sub_category (id)
);

CREATE TABLE IF NOT EXISTS product_inventory
(
    id          UUID PRIMARY KEY REFERENCES product (id) ON DELETE CASCADE,
    quantity    BIGINT NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE IF NOT EXISTS currency
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code        VARCHAR(3) UNIQUE NOT NULL,
    name        VARCHAR(255) NOT NULL,
    symbol      VARCHAR(8) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE IF NOT EXISTS product_price
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id          UUID NOT NULL,
    currency_id         UUID NOT NULL,
    price               NUMERIC(10, 2) NOT NULL,
    discount_percentage NUMERIC(3, 1) NOT NULL DEFAULT 0,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted             BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT fk_product_price_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_price_currency FOREIGN KEY (currency_id) REFERENCES currency (id)
);
