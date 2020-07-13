package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.bean.Car;
import mvc.bean.SellPremission;
import mvc.bean.User;
import mvc.service.AdmirSevice;
import mvc.service.UserLoginService;
import mvc.service.UserService;


@WebServlet("/userloginServlet")
public class UserloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UserloginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("username");
		String password = request.getParameter("password");
		UserLoginService userloginservice = new UserLoginService();
		UserService userservice = new UserService();
		AdmirSevice admirSevice = new AdmirSevice();
		User user = new User();
		List<Car> cars = new ArrayList<Car>();
		List<Car> cars_re = new ArrayList<Car>();
		if(userloginservice.checkLoginName(user_name)) {
			request.setAttribute("message","!请输入正确的用户名");
			request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
		}
		else {
			if(userloginservice.checkLoginNamePassword(user_name, password)) {
				request.setAttribute("message","!请输入正确的密码");
				request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
			}
			else {
				user = userloginservice.searchUserByNamePassword(user_name, password);
				if(user.getPermission()==0) {
					request.setAttribute("message","!您无权限进入该网站");
					request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
				}
				else {
					cars = userservice.search_AllCar();
					for (int i = 0; i < cars.size(); i++) {
						Car car = cars.get(i);
						SellPremission sellPremission = admirSevice.searchByC_id(car.getCar_id());
						if (sellPremission != null && sellPremission.getPremission()==1 && sellPremission.getReleasing()==1) {
							cars_re.add(car);
						}
					}
					request.setAttribute("cars",cars_re);
					request.setAttribute("user",user);
					request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
