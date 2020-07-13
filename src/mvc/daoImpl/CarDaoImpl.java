package mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.Car;
import mvc.bean.User;
import mvc.dao.CarDao;

public class CarDaoImpl extends DaoJdbcImpl<Car> implements CarDao {

	public CarDaoImpl() {
	}

	public void addCar(Connection connection, Car car) throws SQLException {
		String sql = "insert into car(user_id,license_plate,brand,model,years,evaluation,photo) "
				+ "values (?,?,?,?,?,?,?);";
		Object[] objs = {car.getUser_id(),car.getLicense_plate(),car.getBrand(),car.getModel(),
				car.getYears(),car.getEvaluation(),car.getPhoto()};
		update(connection, sql, objs);
	}

	public List<Car> searchCarByBrand(Connection connection, String brand) throws SQLException {
		String sql = "select * from ldusedcar.car where brand = ?;";
		return fetchList(connection, sql,brand);
	}

	public List<Car> searchCarByModel(Connection connection, String model) throws SQLException {
		String sql = "select * from ldusedcar.car where model = ?;";
		return fetchList(connection, sql,model);
	}

	public Car searchCarByCarid(Connection connection, Integer car_id) throws SQLException {
		String sql = "select * from ldusedcar.car where car_id = ?;";
		return fetch(connection, sql, car_id);
	}

	public List<Car> searchCarByUerID(Connection connection, Integer user_id) throws SQLException {
		String sql = "select * from ldusedcar.car where user_id = ?;";
		return fetchList(connection, sql,user_id);
	}

	public Car searchCarByLicense(Connection connection, String license_plate) throws SQLException {
		String sql = "select * from ldusedcar.car where license_plate = ?;";
		return fetch(connection, sql, license_plate);
	}

	public List<Car> searchAllCar(Connection connection) throws SQLException {
		String sql = "select * from ldusedcar.car;";
		return fetchList(connection, sql);
	}

	public void updateCar(Connection connection, Car car) throws SQLException {
		String sql = "update ldusedcar.car set  car.license_plate=?,car.brand = ?,car.model = ?,\r\n" + 
				"car.years=?,car.evaluation=?,car.photo=?  where car_id = ?;";
		Object[] objs = {car.getLicense_plate(),car.getBrand(),car.getModel(),car.getYears(),
				car.getEvaluation(),car.getPhoto(),car.getCar_id()};
		update(connection, sql, objs);
	}

	public void deleteCar(Connection connection, int car_id) throws SQLException {
		String sql = "delete from ldusedcar.car where car_id = ?;";
		update(connection, sql,car_id);
	}


	public List<Car> searchCarByBrand_Model_Price(Connection connection, String brand, String model, int lowprice,
			int heightprice) throws SQLException {
		String sql = "select * from ldusedcar.car where brand=? and model=? and evaluation<=? and evaluation>=?;";
		Object[] objs = {brand,model,heightprice,lowprice};
		return fetchList(connection, sql,objs);
	}


	public List<Car> searchCarByBrand_Model(Connection connection, String brand, String model) throws SQLException {
		String sql = "select * from ldusedcar.car where brand=? and model=?;";
		Object[] objs = {brand,model};
		return fetchList(connection, sql,objs);
	}

	public List<Car> searchCarByBrand_Price(Connection connection, String brand, int lowprice, int heightprice)
			throws SQLException {
		String sql = "select * from ldusedcar.car where brand=? and evaluation<=? and evaluation>=?;";
		Object[] objs = {brand,heightprice,lowprice};
		return fetchList(connection, sql,objs);
	}

	public List<Car> searchCarByPrice(Connection connection, int lowprice, int heightprice) throws SQLException {
		String sql = "select * from ldusedcar.car where evaluation<=? and evaluation>=?;";
		Object[] objs = {heightprice,lowprice};
		return fetchList(connection, sql,objs);
	}

	public List<Car> searchCarByModel_Price(Connection connection, String model, int lowprice, int heightprice)
			throws SQLException {
		String sql = "select * from ldusedcar.car where model=? and evaluation<=? and evaluation>=?;";
		Object[] objs = {model,heightprice,lowprice};
		return fetchList(connection, sql,objs);
	}

	@Override
	public List<Car> searchCarByYears(Connection connection, int years) throws SQLException {
		String sql = "select * from ldusedcar.car where years = ?;";
		return fetchList(connection, sql, years);
	}

	@Override
	//ÉýÐò
	public List<Car> ordertopCars(Connection connection) throws SQLException {
		String sql = "select * from ldusedcar.car order by evaluation;";
		return fetchList(connection, sql);
	}

	@Override
	//½µÐò
	public List<Car> orderdropCars(Connection connection) throws SQLException {
		String sql = "select * from ldusedcar.car order by evaluation desc;";
		return fetchList(connection, sql);
	}
}
