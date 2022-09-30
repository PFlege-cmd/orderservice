drop table if exists product_entity;

create table product_entity(
    id bigint           not null auto_increment primary key,
    order_status        varchar(30),
    creation_timestamp  timestamp,
    updated_timestamp   timestamp
) engine = InnoDB;
