package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import mvc.bean.Admir;

public interface AdmirDao {
	
	public Admir searchAdmirById(Connection connection,Integer admir_id)//������Աid���һ�Ա
			throws SQLException; 
	
	public Admir searchAdmirByName(Connection connection,String admir_name)//������Ա�������һ�Ա
			throws SQLException; 
	
	public Admir searchAdmirByNamePas(Connection connection,String admir_name,String password)//������Ա���һ�Ա
			throws SQLException; 
}
