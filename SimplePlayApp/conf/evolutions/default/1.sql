
# --- !Ups

create table autos (
  auto_id                       serial not null,
  model                         varchar(255),
  user_id                       integer,
  constraint pk_autos primary key (auto_id)
);

create table security_role (
  id                            bigserial not null,
  role_name                     varchar(255),
  constraint pk_security_role primary key (id)
);

create table users (
  id                            serial not null,
  name                          varchar(255),
  constraint pk_users primary key (id)
);

alter table autos add constraint fk_autos_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_autos_user_id on autos (user_id);


# --- !Downs

alter table if exists autos drop constraint if exists fk_autos_user_id;
drop index if exists ix_autos_user_id;

drop table if exists autos cascade;

drop table if exists security_role cascade;

drop table if exists users cascade;

