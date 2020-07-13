SELECT * FROM ldusedcar.backmessage;

insert into ldusedcar.backmessage(m_id,send_id,content) values(19,22,"test1");
insert into ldusedcar.backmessage(m_id,send_id,content) values(19,22,"test2");
insert into ldusedcar.backmessage(m_id,send_id,content) values(19,22,"test3");
insert into ldusedcar.backmessage(m_id,send_id,content) values(21,22,"test4");

SELECT * FROM ldusedcar.backmessage where m_id = 18;

delete from ldusedcar.backmessage where b_id = 22;