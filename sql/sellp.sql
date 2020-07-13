SELECT * FROM ldusedcar.sellpermission;
delete from ldusedcar.sellpermission where car_id = 18;

select * from ldusedcar.sellpermission where s_id = 1;

insert into ldusedcar.sellpermission(car_id,user_id,premission,releasing) values(18,18,1,1);

update ldusedcar.sellpermission set car_id = 18,user_id=18,premission=1,releasing=1 where s_id = 18;