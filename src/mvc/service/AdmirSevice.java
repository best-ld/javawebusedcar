package mvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mvc.bean.Admir;
import mvc.bean.SellPremission;
import mvc.dao.AdmirDao;
import mvc.dao.PremissionDao;
import mvc.daoImpl.AdmirDaoImpl;
import mvc.daoImpl.PremissionDaoImpl;
import mvc.utils.JdbcTools;

public class AdmirSevice {
	private AdmirDao admirDao;
	private Connection connection;
	private PremissionDao premissionDao;
	
	public AdmirSevice() {
		super();
		this.admirDao = new AdmirDaoImpl();
		this.connection = null;
		this.premissionDao = new PremissionDaoImpl();
	}


	//存在返回true
	public boolean checkAdnmirName(String admir_name) {
		boolean flag = false;
		try {
			connection = JdbcTools.getConnection();
			Admir admir = admirDao.searchAdmirByName(connection, admir_name);
			if (admir != null) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return flag;
	}
	
	//存在返回true
	public boolean checkAdnmirPassword(String admir_name,String password) {
		boolean flag = false;
		try {
			connection = JdbcTools.getConnection();
			Admir admir = admirDao.searchAdmirByNamePas(connection, admir_name, password);
			if (admir != null) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return flag;
	}
	//返回所有信息
	public List<SellPremission> getAllSellMessage() {
		List<SellPremission> spremissions = new ArrayList<SellPremission>();
		try {
			connection = JdbcTools.getConnection();
			spremissions =  premissionDao.searchAllPre(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return spremissions;
	}
	//按id查找
	public SellPremission searchByS_id(int s_id) {
		SellPremission sellPremission = new SellPremission();
		try {
			connection = JdbcTools.getConnection();
			sellPremission = premissionDao.searchPremissionBys_id(connection, s_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return sellPremission;
	}
	//修改权限
	public void updatePremission(SellPremission sellPremission) {
		try {
			connection = JdbcTools.getConnection();
			premissionDao.updateSellPremit(connection, sellPremission);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	//按carid查找
	public  SellPremission searchByC_id(int car_id) {
		SellPremission sellPremission = new SellPremission();
		try {
			connection = JdbcTools.getConnection();
			sellPremission = premissionDao.searchPremissionByC_id(connection, car_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return sellPremission;
	}
	//添加新权限
	public void addNewPremission(int car_id,int user_id) {
		try {
			SellPremission sellPremission = new SellPremission(null,car_id,user_id,0,0);
			connection = JdbcTools.getConnection();
			premissionDao.addPremission(connection, sellPremission);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	//删除权限
	public void deletePremission(int car_id) {
		try {
			connection = JdbcTools.getConnection();
			premissionDao.deleteSellPremitByc_id(connection, car_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
}
