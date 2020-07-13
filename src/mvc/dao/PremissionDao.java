package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.SellPremission;

public interface PremissionDao {
	
	public SellPremission searchPremissionBys_id(Connection connection,Integer s_id)
			throws SQLException; 
	
	public List<SellPremission> searchAllPre(Connection connection)
			throws SQLException; 
	
	public void updateSellPremit(Connection connection,SellPremission spremission)
			throws SQLException;
	
	public void addPremission(Connection connection,SellPremission spremission)
			throws SQLException;
	
	public SellPremission searchPremissionByC_id(Connection connection,Integer car_id)
			throws SQLException; 
	
	public void deleteSellPremitByc_id(Connection connection,Integer c_id)
			throws SQLException;
}
