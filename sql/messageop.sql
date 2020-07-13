SELECT * FROM ldusedcar.message;

insert into ldusedcar.message(send_id,get_id,car_id,content) values(19,22,23,"good");

select * from ldusedcar.message where car_id = 23;
select * from ldusedcar.message where car_id = 23 and send_id =19;
select * from ldusedcar.message where m_id = 9;
delete from ldusedcar.message where m_id = 23;
SET SQL_SAFE_UPDATES = 0;
delete from ldusedcar.message where content ="";

select * from ldusedcar.message where send_id =19;