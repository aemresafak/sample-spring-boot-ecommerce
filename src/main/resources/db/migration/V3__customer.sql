CREATE TABLE IF NOT EXISTS customer
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255)                               NOT NULL,
    last_name  VARCHAR(255)                               NOT NULL,
    email      VARCHAR(255)                               NOT NULL,
    birth_date DATE                                       NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS cart
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id UUID                                       NOT NULL REFERENCES customer (id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS cart_item
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID                                       NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    cart_id    UUID                                       NOT NULL REFERENCES cart (id) ON DELETE CASCADE,
    quantity   INT                                        NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS shipping
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    price        NUMERIC(10, 2)                             NOT NULL,
    to_address   VARCHAR(255)                               NOT NULL,
    shipped_at   TIMESTAMP,
    delivered_at TIMESTAMP,
    status       VARCHAR(255)                               NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted      BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS discount
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code         VARCHAR(255)                               NOT NULL,
    start_date   TIMESTAMP                                  NOT NULL,
    end_date     TIMESTAMP                                  NOT NULL,
    percentage   NUMERIC(2, 2)                              NOT NULL,
    fixed_amount NUMERIC(10, 2)                             NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted      BOOLEAN          DEFAULT FALSE             NOT NULL
);


CREATE TABLE IF NOT EXISTS customer_order
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cart_id      UUID                                       NOT NULL REFERENCES cart (id) ON DELETE CASCADE,
    shipping_id  UUID                                       NOT NULL REFERENCES shipping (id),
    discount_id  UUID REFERENCES discount (id),
    order_status VARCHAR(255)                               NOT NULL,
    order_date   TIMESTAMP                                  NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted      BOOLEAN          DEFAULT FALSE             NOT NULL
);

CREATE TABLE IF NOT EXISTS payment
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id      UUID                                       NOT NULL REFERENCES customer_order (id) ON DELETE CASCADE,
    payment_date  TIMESTAMP                                  NOT NULL,
    amount        NUMERIC(10, 2)                             NOT NULL,
    currency_code VARCHAR(3)                                 NOT NULL REFERENCES currency (code),
    status        VARCHAR(255)                               NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted       BOOLEAN          DEFAULT FALSE             NOT NULL
);


CREATE TABLE IF NOT EXISTS review
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    rating      NUMERIC(2, 2)                              NOT NULL,
    comment     TEXT,
    product_id  UUID                                       NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    order_id    UUID                                       NOT NULL REFERENCES customer_order (id) ON DELETE CASCADE,
    customer_id UUID                                       NOT NULL REFERENCES customer (id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN          DEFAULT FALSE             NOT NULL
);
