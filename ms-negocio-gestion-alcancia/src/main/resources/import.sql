
insert into tbl_alcancia(nombre,estado,total,fk_cuenta) values('Casa',1,'600.00',1);
insert into tbl_alcancia(nombre,estado,total,fk_cuenta) values('Vacaciones',1,'160.00',1);

insert into tbl_movimiento(tipo_movimiento,fecha_movimiento,monto,alcancia_id) values('ABONO ALC','2024-08-12','200.00',1);
insert into tbl_movimiento(tipo_movimiento,fecha_movimiento,monto,alcancia_id) values('ABONO ALC','2024-08-12','500.00',1);
insert into tbl_movimiento(tipo_movimiento,fecha_movimiento,monto,alcancia_id) values('RETIRO ALC','2024-08-13','-100.00',1);

insert into tbl_movimiento(tipo_movimiento,fecha_movimiento,monto,alcancia_id) values('ABONO ALC','2024-08-14','160.00',2); 

 