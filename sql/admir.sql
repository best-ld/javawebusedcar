SELECT * FROM ldusedcar.admir;

insert into ldusedcar.admir(admir_name,password) values("admir1","dgut11111");

insert into ldusedcar.admir(admir_name,password) values("admir2","dgut11111");

insert into ldusedcar.admir(admir_name,password) values("admir3","dgut11111");

select * from ldusedcar.admir where admir_id = 18;

select * from ldusedcar.admir where admir_name = "admir1";

select * from ldusedcar.admir where admir_name = "admir1" and password = "dgut11111";