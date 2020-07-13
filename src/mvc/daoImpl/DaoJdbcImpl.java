package mvc.daoImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import mvc.dao.Dao;

public class DaoJdbcImpl<T> implements Dao<T> {
	private QueryRunner queryRunner;
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public DaoJdbcImpl() {
		queryRunner = new QueryRunner();
		type = getSuperClassGenricType(getClass(), 0);
	}

	public T fetch(Connection connection, String sql, Object... objects) throws SQLException {
		BeanHandler<T> rsh = new BeanHandler<T>(type);
		return queryRunner.query(connection, sql, rsh, objects);
	}

	public List<T> fetchList(Connection connection, String sql, Object... objects) throws SQLException {
		BeanListHandler<T> rsh = new BeanListHandler<T>(type);
		return queryRunner.query(connection, sql, rsh, objects);
	}

	public void update(Connection connection, String sql, Object... objects) throws SQLException {
		queryRunner.update(connection, sql, objects);
	}

	public <E> E fetchScaler(Connection connection, String sql, Object... objects) throws SQLException {
		ScalarHandler<E> rsh = new ScalarHandler<E>();
		return (E)queryRunner.query(connection, sql, rsh, objects);
	}
	
	@SuppressWarnings("rawtypes")
	private Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

}