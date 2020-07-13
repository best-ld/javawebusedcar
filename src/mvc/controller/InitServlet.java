package mvc.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import mvc.utils.JdbcTools;

public class InitServlet implements Servlet {

	public void destroy() {

	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {

	}

}
