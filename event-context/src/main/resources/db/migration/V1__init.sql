CREATE TABLE events (
                        id           BIGSERIAL PRIMARY KEY,
                        proposal_id  BIGINT  NOT NULL,
                        start_date   TIMESTAMP,
                        end_date     TIMESTAMP,
                        status       VARCHAR(32),
                        description  TEXT,
                        location     TEXT,
                        created_at   TIMESTAMP,
                        updated_at   TIMESTAMP
);

CREATE TABLE gallery_items (
                               id         BIGSERIAL PRIMARY KEY,
                               image_url  TEXT,
                               caption    TEXT,
                               event_id   BIGINT REFERENCES events(id),
                               created_at TIMESTAMP,
                               updated_at TIMESTAMP
);
