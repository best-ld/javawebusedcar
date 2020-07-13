package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.Car;


public interface CarDao {
	public void addCar(Connection connection, Car car)//����³�
			throws SQLException;
	public Car searchCarByCarid(Connection connection,Integer car_id)//���ݳ�����Ų�ѯ
			throws SQLException;
	public List<Car> searchCarByUerID(Connection connection,Integer user_id)//�����û���Ų�ѯ
			throws SQLException;
	public Car searchCarByLicense(Connection connection,String license_plate)//���ݳ��ƺŲ�ѯ
			throws SQLException;
	public List<Car> searchAllCar(Connection connection)//��ѯ���г���
			throws SQLException;
	public List<Car> searchCarByBrand_Model_Price(Connection connection,String brand,String model,int lowprice,int heightprice)//�����ͺš�Ʒ�ơ��۸��ѯ
			throws SQLException;
	public List<Car> searchCarByBrand_Model(Connection connection,String brand,String model)//�����ͺš�Ʒ�Ʋ�ѯ
			throws SQLException;
	public List<Car> searchCarByBrand_Price(Connection connection,String brand,int lowprice,int heightprice)//����Ʒ�ơ��۸��ѯ
			throws SQLException;
	public List<Car> searchCarByBrand(Connection connection, String brand)//����Ʒ�Ʋ�ѯ
			throws SQLException;
	public List<Car>searchCarByModel(Connection connection, String model)//�����ͺŲ�ѯ
			throws SQLException;
	public List<Car> searchCarByPrice(Connection connection,int lowprice,int heightprice)//�۸��ѯ
			throws SQLException;
	public List<Car> searchCarByModel_Price(Connection connection,String model,int lowprice,int heightprice)//�����ͺš��۸��ѯ
			throws SQLException;
	public void updateCar(Connection connection,Car car)//�޸ĳ�����Ϣ
			throws SQLException;
	public void deleteCar(Connection connection,int car_id)//���������ɾ������
			throws SQLException;
	public List<Car>searchCarByYears(Connection connection, int years)//�����޲�ѯ
			throws SQLException;
	public List<Car>ordertopCars(Connection connection)//��������
			throws SQLException;
	public List<Car>orderdropCars(Connection connection)//��������
			throws SQLException;
}
