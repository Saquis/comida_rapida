-- Creación de la base de datos
drop database if exists gestion_pedidos;
create database gestion_pedidos default character set utf8;

use gestion_pedidos;

-- Creación de las tablas

-- Tabla usuario
drop table if exists usuario;
create table if not exists usuario(
  idusuario  int not null auto_increment,
  nombres    varchar(50) not null,
  apellidos  varchar(50) not null,
  cedula     varchar(10) not null,
  telefono   varchar(10) not null,
  email      varchar(50) not null,
  login      varchar(10) not null,
  password   varchar(200) not null,
  estado     char(1) not null,
  primary key (idusuario));
create unique index cedula_unique on usuario (cedula);
create unique index email_unique on usuario (email);
create unique index login_unique on usuario (login);

-- Tabla rol
drop table if exists rol;
create table if not exists rol (
  idrol   int not null auto_increment,
  nombre  varchar(50) not null,
  primary key (idrol));
create unique index nombre_unique on rol (nombre);

-- Tabla usuario_rol
drop table if exists usuario_rol;
create table if not exists usuario_rol (
  idusuario_rol int not null auto_increment,
  idusuario     int not null,
  idrol         int not null,
  primary key (idusuario_rol),
  foreign key (idusuario) references usuario (idusuario),
  foreign key (idrol) references rol (idrol));
create index fk_ur_usuario_idx on usuario_rol (idusuario);
create index fk_ur_rol_idx on usuario_rol (idrol);

-- Tabla pedido
drop table if exists pedido;
create table if not exists pedido (
  idpedido     int not null auto_increment,
  fecha_pedido datetime not null,
  idusuario    int not null,
  estado       char(1) not null,
  primary key (idpedido),
  foreign key (idusuario) references usuario (idusuario));
create index fk_pedido_usuario_idx on pedido (idusuario);

-- Tabla mesa
drop table if exists mesa;
create table if not exists mesa (
  idmesa    int not null auto_increment,
  capacidad int not null,
  estado    char(1) not null,
  primary key (idmesa));

-- Tabla plato
drop table if exists plato;
create table if not exists plato (
  idplato      int not null auto_increment,
  nombre       varchar(25) not null,
  descripcion  varchar(100) not null,
  observacion  varchar(100) null,
  valor double not null,
  stock        int not null,
  estado       char(1) not null,
  primary key (idplato));
create unique index nombre_unique on plato (nombre);

-- Tabla pedido_mesa
drop table if exists pedido_mesa;
create table if not exists pedido_mesa (
  idpedido_mesa int not null auto_increment,
  idpedido      int not null,
  idmesa        int not null,
  primary key (idpedido_mesa),
  foreign key (idmesa) references mesa (idmesa),
  foreign key (idpedido) references pedido (idpedido));
create index fk_pm_mesa_idx on pedido_mesa (idmesa);
create index fk_pm_pedido_idx on pedido_mesa (idpedido);

-- Tabla detalle_pedido
drop table if exists detalle_pedido;
create table if not exists detalle_pedido (
  iddetalle_pedido int not null auto_increment,
  idpedido         int not null,
  idplato          int not null,
  cantidad         int not null,
  primary key (iddetalle_pedido),
  foreign key (idplato) references plato (idplato),
  foreign key (idpedido) references pedido (idpedido));
create index fk_dp_plato_idx on detalle_pedido (idplato);
create index fk_dp_pedido_idx on detalle_pedido (idpedido);

-- Tabla recibo
drop table if exists recibo;
create table if not exists recibo (
  idrecibo         int not null auto_increment,
  fecha            datetime not null,
  nombre_cliente   varchar(100) not null,
  cedula           varchar(100) not null,
  telefono         varchar(10) not null,
  email            varchar(50) not null,
  descripcion      varchar(100) not null,
  cantidad_platos  int not null,
  total_pagar      double not null,
  estado           char(1) not null,
  idpedido         int not null,
  idusuario        int not null,
  primary key (idrecibo),
  foreign key (idusuario) references usuario (idusuario),
  foreign key (idpedido) references pedido (idpedido));
create index fk_recibo_usuario_idx on recibo (idusuario);
create index fk_recibo_pedido_idx on recibo (idpedido);
