package mvc.tester;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import mvc.bean.Car;
import mvc.dao.CarDao;
import mvc.dao.UserDao;
import mvc.daoImpl.CarDaoImpl;
import mvc.service.UserService;
import mvc.utils.JdbcTools;

class TestCarDao {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddCar() {
		CarDao userdao = new CarDaoImpl();
		Connection connection = null;
		Car car = new Car(null,28,"æ©A003","∫Ï∆Ï","H9",1,200,null);
		try {
			connection = JdbcTools.getConnection();
			userdao.addCar(connection, car);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	@Test
	public void searchCar() {
		CarDao userdao = new CarDaoImpl();
		Connection connection = null;
		String brand = "∫Ï∆Ï";
		String model ="H9";
		int user_id = 28;
		int car_id = 21;
		String license_plate = "æ©A001";
		int lowprice = 150;
		int heightprice =400;
		try {
			connection = JdbcTools.getConnection();
			List<Car> cars1 = userdao.searchCarByBrand(connection, brand);
			System.out.println("1"+cars1);
			List<Car> cars2 = userdao.searchCarByModel(connection, model);
			System.out.println("2"+cars2);
			List<Car> cars3 = userdao.searchCarByUerID(connection, user_id);
			System.out.println("3"+cars3);/*
			List<Car> cars4 = userdao.searchAllCar(connection);
			System.out.println("4"+cars4);
			Car car1 = userdao.searchCarByCarid(connection, car_id);
			System.out.println("5"+car1);
			Car car2 = userdao.searchCarByLicense(connection, license_plate);
			System.out.println("6"+car2);
			List<Car> cars5 = userdao.searchCarByBrand_Model(connection, brand, model);
			System.out.println("7"+cars5);
			List<Car> cars6 = userdao.searchCarByPrice(connection, lowprice, heightprice);
			System.out.println("8"+cars6);
			List<Car> cars7 = userdao.searchCarByBrand_Model_Price(connection, brand, model, lowprice, heightprice);
			System.out.println("9"+cars7);	
			List<Car> cars8 = userdao.searchCarByModel_Price(connection, model, lowprice, heightprice);
			List<Car> cars9 = userdao.searchCarByBrand_Price(connection, brand, lowprice, heightprice);
			System.out.println("10"+cars8);	
			System.out.println("11"+cars9);	*/
			List<Car> reslut = new ArrayList<Car>();
			for(Car car1:cars3) {
				for(Car car2:cars2) {
					if (car1.getCar_id() == car2.getCar_id()) {
						reslut.add(car1);
					}
				}
			}
			System.out.println("12"+reslut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	@Test
	public void updateCar() {
		CarDao userdao = new CarDaoImpl();
		Connection connection = null;
		Car  car = new Car(19,null,"æ©A002","∫Ï∆Ï","L5",2,300,null); 
		try {
			connection = JdbcTools.getConnection();
			userdao.updateCar(connection, car);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	@Test
	public void deleteCar() {
		CarDao userdao = new CarDaoImpl();
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			userdao.deleteCar(connection, 20);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	@Test
	public void testSevice() {
		UserService userservice = new UserService();
		System.out.println(userservice.search_AllCar());
	}
}
