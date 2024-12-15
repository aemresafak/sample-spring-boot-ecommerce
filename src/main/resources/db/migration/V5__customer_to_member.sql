ALTER TABLE customer
    DROP COLUMN IF EXISTS email;
ALTER TABLE customer
    DROP COLUMN IF EXISTS password;

CREATE TABLE IF NOT EXISTS member
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at  TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted     BOOLEAN          DEFAULT FALSE             NOT NULL,
    email       TEXT                                       NOT NULL,
    password    TEXT                                       NOT NULL,
    customer_id uuid REFERENCES customer (id)
);
