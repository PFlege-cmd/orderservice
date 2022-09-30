update orderservice.order_header
    set version = 0 where version is null;

update orderservice.order_line
    set version = 0 where version is null;
