package mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.SellPremission;
import mvc.dao.PremissionDao;

public class PremissionDaoImpl extends DaoJdbcImpl<SellPremission> implements PremissionDao {

	public SellPremission searchPremissionBys_id(Connection connection, Integer s_id) throws SQLException {
		String sql = "select * from ldusedcar.sellpermission where s_id = ?;";
		return fetch(connection, sql, s_id);
	}

	public List<SellPremission> searchAllPre(Connection connection) throws SQLException {
		String sql = "select * from ldusedcar.sellpermission;";
		return fetchList(connection, sql);
	}

	public void updateSellPremit(Connection connection, SellPremission spremission) throws SQLException {
		String sql = "update ldusedcar.sellpermission set car_id = ?,user_id=?,premission = ?,releasing=? where s_id = ?;";
		Object objs[] = { spremission.getCar_id(), spremission.getUser_id(), spremission.getPremission(),
				spremission.getReleasing(), spremission.getS_id() };
		update(connection, sql, objs);
	}

	public void addPremission(Connection connection, SellPremission spremission) throws SQLException {
		String sql = "insert into ldusedcar.sellpermission(car_id,user_id,premission,releasing) values(?,?,?,?);";
		Object objs[] = {spremission.getCar_id(), spremission.getUser_id(), spremission.getPremission(),
				spremission.getReleasing()};
		update(connection, sql, objs);
	}

	@Override
	public SellPremission searchPremissionByC_id(Connection connection, Integer car_id) throws SQLException {
		String sql = "select * from ldusedcar.sellpermission where car_id = ?;";
		return fetch(connection, sql, car_id);
	}

	@Override
	public void deleteSellPremitByc_id(Connection connection, Integer car_id) throws SQLException {
		String sql = "delete from ldusedcar.sellpermission where car_id = ?;";
		update(connection, sql, car_id);
	}

}
