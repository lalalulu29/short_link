DROP TABLE if EXISTS links;



CREATE TABLE if not exists links (
  id BIGSERIAL PRIMARY KEY,
  short_link varchar(128),
  long_link varchar(1024),
  created_at timestamp not null default current_timestamp,
  life_time serial
);
