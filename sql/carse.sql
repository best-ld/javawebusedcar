SELECT * FROM ldusedcar.car;

insert into car(user_id,license_plate,brand,model,years,evaluation,photo) values (28,'京A001',"红旗","H9",1,200,null);

select * from ldusedcar.car where brand = "红旗";

select * from ldusedcar.car where model = "H9";

select * from ldusedcar.car where car_id = 19;

select * from ldusedcar.car where user_id = 28;

select * from ldusedcar.car where license_plate = "京A003";

update ldusedcar.car set  car.license_plate="A005",car.brand = "奥迪",car.model = "A8",
car.years=2,car.evaluation=300,car.photo=null  where car_id = 19;

delete from ldusedcar.car where car_id = 24;

select * from ldusedcar.car where brand="bc" and model="s500" and evaluation<=100 and evaluation>=90;


select * from ldusedcar.car where years = 2;

select * from ldusedcar.car order by evaluation desc;