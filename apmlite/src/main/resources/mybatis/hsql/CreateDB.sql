create table person
(
  id               int,
  firstName        varchar(100),
  lastName         varchar(100),
  parent_id        int          default null,
  parent_firstName varchar(100) default null,
  parent_lastName  varchar(100) default null
);


create table apm_tag_index
(
  tag         varchar(1000) default '',
  hashcode    int   default 0,
  create_time TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL


);


create table if not exists apm_metric01
(
  tag       varchar(1000) not null default '',
  hash_code int           not null default 0,
  count     bigint        not null default 0,
  mean      bigint        not null default 0,
  avg       bigint        not null default 0,
  max       bigint        not null default 0,
  min       bigint        not null default 0,
  index     hash_code
);