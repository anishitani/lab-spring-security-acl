CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS writers (
    writer_id UUID DEFAULT uuid_generate_v4(),
    name VARCHAR,
    PRIMARY KEY(writer_id)
);

CREATE TABLE IF NOT EXISTS posts(
    id SERIAL,
    title VARCHAR,
    subject VARCHAR,
    content VARCHAR,
    writer_id UUID,
    PRIMARY KEY(id),
    CONSTRAINT fk_writer_id FOREIGN KEY(writer_id) REFERENCES writers(writer_id)
);