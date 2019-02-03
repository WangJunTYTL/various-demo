create table apm_tag_index
(
  id          bigint auto_increment primary key,
  tag         varchar(1000) default '',
  hashcode    int           default 0 comment 'hashcode for tag',
  metric_num  int comment 'Metric serial number',
  create_time TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  key `tag_hashcode` (`hashcode`)
) charset utf8;


create table if not exists apm_metric_01
(
  `tag`         varchar(1000) not null default '',
  `hashcode`    int           not null default 0,
  `count`       bigint        not null default 0,
  `avg`         bigint        not null default 0,
  `max`         bigint        not null default 0,
  `min`         bigint        not null default 0,
  `create_time` TIMESTAMP              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  key `tag_hashcode` (`hashcode`)
);


create table if not exists apm_metric_11
(
  `tag`             varchar(1000) not null default '',
  `hashcode`        int           not null default 0,
  `heap_memory`     bigint        not null default 0,
  `non_heap_memory` bigint        not null default 0,
  `gc_time`         bigint        not null default 0,
  `gc_count`        bigint        not null default 0,
  `thread_count`    bigint        not null default 0,
  `create_time`     TIMESTAMP              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  key `tag_hashcode` (`hashcode`)
);
