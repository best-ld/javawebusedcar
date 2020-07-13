SELECT * FROM ldusedcar.user;

insert into user(user_name,password,name,idcard,phone,city) values ('ld','2335','lindun','445122130256','8721398','东莞');

insert into user(user_name,password,name,idcard,phone,city,permission) values ('ldd','123','liudandan','445122130247','1308987','珠海',True);

SET SQL_SAFE_UPDATES = 0;
delete from user where user_name = "ldd";