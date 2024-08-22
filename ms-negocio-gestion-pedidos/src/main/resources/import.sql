insert into tbl_pedido(glosa,fecha_entrega,fecha_pedido,sub_total,descuento,igv,total,cliente_id,situacion,estado) values('Pedido computo + limpiesa - Sr. Novoa','2024-08-10','2024-08-12',16000,0,2880,18880,1,1,'1');

insert into tbl_pedido_item(producto_id, precio, cantidad, sub_total,descuento,igv,total,pedido_id,estado) values(1,7500.0,2,15000,0,2700,17700,1,'1');
insert into tbl_pedido_item(producto_id, precio, cantidad, sub_total,descuento,igv,total,pedido_id,estado) values(3,10.0,100,1000,0,180,1180,1,'1');


