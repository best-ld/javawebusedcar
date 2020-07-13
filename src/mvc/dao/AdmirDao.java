package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import mvc.bean.Admir;

public interface AdmirDao {
	
	public Admir searchAdmirById(Connection connection,Integer admir_id)//按管理员id查找会员
			throws SQLException; 
	
	public Admir searchAdmirByName(Connection connection,String admir_name)//按管理员姓名查找会员
			throws SQLException; 
	
	public Admir searchAdmirByNamePas(Connection connection,String admir_name,String password)//按管理员查找会员
			throws SQLException; 
}
