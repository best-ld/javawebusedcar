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


	//���ڷ���true
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
	
	//���ڷ���true
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
	//����������Ϣ
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
	//��id����
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
	//�޸�Ȩ��
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
	//��carid����
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
	//�����Ȩ��
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
	
	//ɾ��Ȩ��
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
