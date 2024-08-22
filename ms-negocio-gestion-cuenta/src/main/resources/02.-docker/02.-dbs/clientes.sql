create database db_ms_clientes;

use db_ms_clientes;


create table tbl_cliente(
    cliente_id int not null auto_increment  primary key,
    razon_social varchar(260) not null unique,
    ruc char(11) not null unique,
    direccion varchar(400) not null,
    telefono varchar(20) null,
    correo varchar(60) null,
    estado char(1) default '1' not null 
);


INSERT INTO tbl_cliente(razon_social,ruc,direccion,telefono,correo)
VALUES('CLARO EMPRESAS','20544987258','AV. LARCO 237-SAN ISIDRO','456-4574','contacto@claro.com.pe');

INSERT INTO tbl_cliente(razon_social,ruc,direccion,telefono,correo)
VALUES('BITEL EMPRESAS','20544987255','AV. LARCO 238-SAN ISIDRO','456-4575','contacto@bitel.com.pe');

INSERT INTO tbl_cliente(razon_social,ruc,direccion,telefono,correo) VALUES('ENTEL EMPRESAS','20544987256','AV. LARCO 239-SAN ISIDRO','456-4576','cont
acto@entel.com.pe');



select razon_social,ruc,direccion,telefono,correo,estado from tbl_cliente;


