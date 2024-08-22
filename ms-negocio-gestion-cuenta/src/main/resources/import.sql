
insert into tbl_cuenta(tipo_cuenta,nro_cuenta,nro_cci,moneda,estado,total,fk_cliente) values('Cuenta Ahorros','11145678921','1114567892514456','SOLES',1,'8740.00',1);
insert into tbl_cuenta(tipo_cuenta,nro_cuenta,nro_cci,moneda,estado,total,fk_cliente) values('Cuenta Corriente','22245678921','2224567892514456','DOLARES',1,'0',1);

insert into tbl_detalle_cuenta(tipo_operacion,monto,fecha_operacion,numero_operacion,cuenta_id) values('ABONO','10500.00',		'2024-08-10', '31101',1);
insert into tbl_detalle_cuenta(tipo_operacion,monto,fecha_operacion,numero_operacion,cuenta_id) values('RETIRO ALC','-200.00',	'2024-08-12', '31102',1);
insert into tbl_detalle_cuenta(tipo_operacion,monto,fecha_operacion,numero_operacion,cuenta_id) values('RETIRO ALC','-500.00',	'2024-08-12', '31103',1);
insert into tbl_detalle_cuenta(tipo_operacion,monto,fecha_operacion,numero_operacion,cuenta_id) values('RETIRO','-1000.00',		'2024-08-13', '31104',1);
insert into tbl_detalle_cuenta(tipo_operacion,monto,fecha_operacion,numero_operacion,cuenta_id) values('ABONO ALC','100.00',	'2024-08-13', '31105',1);
insert into tbl_detalle_cuenta(tipo_operacion,monto,fecha_operacion,numero_operacion,cuenta_id) values('RETIRO ALC','-160.00',	'2024-08-14', '31106',1);