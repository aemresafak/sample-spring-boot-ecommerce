CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS category
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255)                               NOT NULL,
    description TEXT,
    created_at  TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS sub_category
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255)                               NOT NULL,
    description TEXT,
    category_id uuid                                       NOT NULL,
    created_at  TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN          DEFAULT FALSE             NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS currency
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code       VARCHAR(3) UNIQUE                          NOT NULL,
    name       VARCHAR(255)                               NOT NULL,
    symbol     VARCHAR(8)                                 NOT NULL,
    created_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS product_price
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    currency_id         UUID                                       NOT NULL,
    price               NUMERIC(10, 2)                             NOT NULL,
    discount_percentage NUMERIC(3, 1)                              NOT NULL DEFAULT 0,
    created_at          TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted             BOOLEAN          DEFAULT FALSE             NOT NULL,
    CONSTRAINT fk_product_price_currency FOREIGN KEY (currency_id) REFERENCES currency (id)
);

CREATE TABLE IF NOT EXISTS product_inventory
(
    id         UUID PRIMARY KEY,
    quantity   BIGINT                                NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN     DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at           TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at           TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted              BOOLEAN          DEFAULT FALSE             NOT NULL,
    sub_category_id      uuid                                       NOT NULL REFERENCES sub_category (id),
    name                 VARCHAR(255)                               NOT NULL,
    price_id             uuid                                       NOT NULL REFERENCES product_price (id),
    product_inventory_id uuid                                       NOT NULL REFERENCES product_inventory (id),
    description          TEXT,
    image_url            TEXT,
    CONSTRAINT fk_product_sub_category FOREIGN KEY (sub_category_id) REFERENCES sub_category (id)
);

