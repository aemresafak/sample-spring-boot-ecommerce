CREATE TABLE IF NOT EXISTS role
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN          DEFAULT FALSE             NOT NULL,
    name       VARCHAR(128)                               NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS member_role
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN          DEFAULT FALSE             NOT NULL,
    member_id  UUID                                       NOT NULL REFERENCES member (id) ON DELETE CASCADE,
    role_id    UUID                                       NOT NULL REFERENCES role (id),
    unique (member_id, role_id)
);

CREATE INDEX IF NOT EXISTS member_role_member_id_idx on member_role (member_id);
