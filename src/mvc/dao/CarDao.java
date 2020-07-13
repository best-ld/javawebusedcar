package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.Car;


public interface CarDao {
	public void addCar(Connection connection, Car car)//添加新车
			throws SQLException;
	public Car searchCarByCarid(Connection connection,Integer car_id)//根据车辆编号查询
			throws SQLException;
	public List<Car> searchCarByUerID(Connection connection,Integer user_id)//根据用户编号查询
			throws SQLException;
	public Car searchCarByLicense(Connection connection,String license_plate)//根据车牌号查询
			throws SQLException;
	public List<Car> searchAllCar(Connection connection)//查询所有车辆
			throws SQLException;
	public List<Car> searchCarByBrand_Model_Price(Connection connection,String brand,String model,int lowprice,int heightprice)//根据型号、品牌、价格查询
			throws SQLException;
	public List<Car> searchCarByBrand_Model(Connection connection,String brand,String model)//根据型号、品牌查询
			throws SQLException;
	public List<Car> searchCarByBrand_Price(Connection connection,String brand,int lowprice,int heightprice)//根据品牌、价格查询
			throws SQLException;
	public List<Car> searchCarByBrand(Connection connection, String brand)//根据品牌查询
			throws SQLException;
	public List<Car>searchCarByModel(Connection connection, String model)//根据型号查询
			throws SQLException;
	public List<Car> searchCarByPrice(Connection connection,int lowprice,int heightprice)//价格查询
			throws SQLException;
	public List<Car> searchCarByModel_Price(Connection connection,String model,int lowprice,int heightprice)//根据型号、价格查询
			throws SQLException;
	public void updateCar(Connection connection,Car car)//修改车辆信息
			throws SQLException;
	public void deleteCar(Connection connection,int car_id)//按车辆编号删除车辆
			throws SQLException;
	public List<Car>searchCarByYears(Connection connection, int years)//按年限查询
			throws SQLException;
	public List<Car>ordertopCars(Connection connection)//升序排列
			throws SQLException;
	public List<Car>orderdropCars(Connection connection)//降序排列
			throws SQLException;
}
