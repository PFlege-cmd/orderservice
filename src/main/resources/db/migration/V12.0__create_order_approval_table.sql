CREATE TABLE order_approval(
    id bigint not null auto_increment primary key,
    approved_by varchar(100),
    creation_timestamp timestamp,
    updated_timestamp timestamp
) engine = InnoDB;


