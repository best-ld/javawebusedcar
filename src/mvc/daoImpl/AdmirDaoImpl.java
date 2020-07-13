package mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import mvc.bean.Admir;

import mvc.dao.AdmirDao;

public class AdmirDaoImpl extends DaoJdbcImpl<Admir> implements AdmirDao {

	@Override
	public Admir searchAdmirById(Connection connection, Integer admir_id) throws SQLException {
		String sql = "select * from ldusedcar.admir where admir_id = ?;";
		return fetch(connection, sql, admir_id);
	}

	@Override
	public Admir searchAdmirByName(Connection connection, String admir_name) throws SQLException {
		String sql = "select * from ldusedcar.admir where admir_name = ?;";
		return fetch(connection, sql, admir_name);
	}

	@Override
	public Admir searchAdmirByNamePas(Connection connection, String admir_name, String password) throws SQLException {
		String sql = "select * from ldusedcar.admir where admir_name = ? and password = ?;" ;
		Object objs[] = {admir_name,password};
		return fetch(connection, sql, objs);
	}

}
