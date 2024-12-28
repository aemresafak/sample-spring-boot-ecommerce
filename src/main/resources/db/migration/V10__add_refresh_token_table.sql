CREATE TABLE IF NOT EXISTS refresh_token
(
    id         uuid PRIMARY KEY                      NOT NULL DEFAULT gen_random_uuid(),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted    BOOLEAN     DEFAULT FALSE             NOT NULL,
    member_id  uuid                                  NOT NULL REFERENCES member,
    expires_at timestamptz                           NOT NULL,
    token      TEXT UNIQUE                           NOT NULL
)