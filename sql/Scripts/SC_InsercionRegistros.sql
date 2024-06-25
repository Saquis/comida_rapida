use gestion_pedidos;
 
-- Se obtienen los datos de rol y usuario para asignar rol Administrador
set @w_rol = 0;
set @w_usuario = 0;

select idrol into @w_rol from rol where nombre = 'Administrador';
select idusuario into @w_usuario from usuario where cedula = '1122334455';

-- Se elimina relacion usuario rol
delete from usuario_rol where idusuario = @w_usuario;

-- Inserción de roles
delete from rol;
insert into rol (nombre) values ('Administrador');
insert into rol (nombre) values ('Mesero');
insert into rol (nombre) values ('Cocina');
insert into rol (nombre) values ('Caja');

-- Inserción de Usuario Administrador Password (Elon123)
delete from usuario where cedula = '1122334455';
insert into usuario(nombres,apellidos,cedula,telefono,
                    email,login,password,estado)
values('Elon', 'Musk', '1122334455', '0321111001',
       'elon.musk@correo.com', 'emusk', 'a4d216162a4bef6b932d038a0cd6f28c03846b9c46448d2408d2c55de26c6233', 'V');

select idrol into @w_rol from rol where nombre = 'Administrador';
select idusuario into @w_usuario from usuario where cedula = '1122334455';

-- Insercion de Usuario - Rol
insert into usuario_rol(idusuario,idrol)
values(@w_usuario, @w_rol);
