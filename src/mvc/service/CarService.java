package mvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mvc.bean.Car;
import mvc.dao.CarDao;
import mvc.dao.UserDao;
import mvc.daoImpl.CarDaoImpl;
import mvc.daoImpl.UserDaoJdbcImpl;
import mvc.utils.JdbcTools;

public class CarService {

	private UserDao userdao;
	private Connection connection;
	private CarDao cardao;

	public CarService() {
		this.userdao = new UserDaoJdbcImpl();
		this.cardao = new CarDaoImpl();
		this.connection = null;
	}

	// 若车牌号为空，可以进行添加
	public boolean check_license_plate(String license_plate) {
		boolean flag = false;
		try {
			connection = JdbcTools.getConnection();
			Car car = cardao.searchCarByLicense(connection, license_plate);
			if (car == null) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return flag;
	}

	// 添加新的车辆信息
	public void add_NewCar(int user_id, String license_plate, String brand, String model, int years, int evaluation,String photo) {
		try {
			connection = JdbcTools.getConnection();
			Car car = new Car(null, user_id, license_plate, brand, model, years, evaluation, photo);
			cardao.addCar(connection, car);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	// 根据用户编号查询车辆
	public List<Car> search_CarbyUserId(int user_id) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByUerID(connection, user_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 根据车辆编号查询车辆
	public Car search_CarbyCarId(int car_id) {
		Car car = new Car();
		try {
			connection = JdbcTools.getConnection();
			car = cardao.searchCarByCarid(connection, car_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return car;
	}

	// 根据车辆编号修改
	public void update_Car(int car_id, String license_plate, String brand, String model, Integer years,
			Integer evaluation,String finalPhotoName) {
		Car car = new Car(car_id, null, license_plate, brand, model, years, evaluation, finalPhotoName);
		try {
			connection = JdbcTools.getConnection();
			cardao.updateCar(connection, car);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	// 根据车辆编号删除
	public void delete_Car(int car_id) {
		try {
			connection = JdbcTools.getConnection();
			cardao.deleteCar(connection, car_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	// 根据车牌号查找
	public Car search_CarbyLicense_plate(String license_plate) {
		Car car = new Car();
		try {
			connection = JdbcTools.getConnection();
			car = cardao.searchCarByLicense(connection, license_plate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return car;
	}

	// 根据型号、品牌、价格查询
	public List<Car> search_CarbyBrand_Model_Price(String brand, String model, int lowprice, int heightprice) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByBrand_Model_Price(connection, brand, model, lowprice, heightprice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 根据型号、品牌查询
	public List<Car> search_CarbyBrand_Model(String brand, String model) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByBrand_Model(connection, brand, model);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 根据品牌、价格查询
	public List<Car> search_CarbyBrand_Price(String brand, int lowprice, int heightprice) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByBrand_Price(connection, brand, lowprice, heightprice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 根据型号、价格查询
	public List<Car> search_CarbyModel_Price(String model, int lowprice, int heightprice) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByModel_Price(connection, model, lowprice, heightprice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 价格查询
	public List<Car> search_CarbyPrice(int lowprice, int heightprice) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByPrice(connection, lowprice, heightprice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 根据品牌查询
	public List<Car> search_CarbyBrand(String brand) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByBrand(connection, brand);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 根据品牌查询
	public List<Car> search_CarbyModel(String model) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByModel(connection, model);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 年限查找
	public List<Car> search_CarbyYears(int years) {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchCarByYears(connection, years);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 升序
	public List<Car> order_CarTop() {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.ordertopCars(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

	// 降序
	public List<Car> order_CarDrop() {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.orderdropCars(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}
	
	//查找所有的车辆
	public List<Car> search_AllCar() {
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchAllCar(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}

}
