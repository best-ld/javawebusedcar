package mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
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
import mvc.service.CarService;
import mvc.service.UserLoginService;
import mvc.service.UserService;

@WebServlet("*.usermaindo")
public class UserhomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userservice;
	private UserLoginService userloginservice;
	private CarService carservice;
	private AdmirSevice admirSevice;

	public UserhomeServlet() {
		super();
		this.userservice = new UserService();
		this.userloginservice = new UserLoginService();
		this.admirSevice = new AdmirSevice();
		this.carservice = new CarService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - ".usermaindo".length());
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 重定向，进入修改会员信息界面
	public void changeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(request, response);
	}

	// 修改个人信息
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String user_name = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		User user = userservice.search_UserById(user_id);
		if (!userloginservice.checkLoginName(user_name) && !user.getUser_name().equals(user_name)) {
			request.setAttribute("user", user);
			request.setAttribute("message", "!该用户名已存在");
			request.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(request, response);
		} else {
			List<Car> cars = new ArrayList<Car>();
			userservice.update_User(user_id, user_name, password, phone, city);
			cars = userservice.search_AllCar();
			request.setAttribute("cars", cars);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
		}
	}

	// 重定向，进入卖车（车辆信息添加页面）
	public void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		//List<Car> cars = new ArrayList<Car>();
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/AddSellCar.jsp").forward(request, response);

	}
	
	//回到主界面
	public void bcaktoHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		List<Car> cars = new ArrayList<Car>();
		cars = userservice.search_AllCar();
		User user = userservice.search_UserById(user_id);
		List<Car> cars_re = new ArrayList<Car>();
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			SellPremission sellPremission = admirSevice.searchByC_id(car.getCar_id());
			if (sellPremission!=null && sellPremission.getPremission()==1 && sellPremission.getReleasing()==1) {
				cars_re.add(car);
			}
		}
		request.setAttribute("cars",cars_re);
		request.setAttribute("user",user);
		request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
	}
	//查询车辆
	public void searchCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String lowprice_s = request.getParameter("lowprice");
		String heightprice_s = request.getParameter("heightprice");
		String years_s = request.getParameter("years");
		int lowprice = 0;
		int heightprice = 99999;
		if (lowprice_s != "") {
			lowprice = Integer.parseInt(lowprice_s);
		}
		if (heightprice_s != "") {
			heightprice = Integer.parseInt(heightprice_s);
		}
		List<Car> cars1 = new ArrayList<Car>();
		List<Car> cars2 = new ArrayList<Car>();
		List<Car> reslut = new ArrayList<Car>();
		if (brand=="") {
			if (model=="") {
				cars1 = carservice.search_CarbyPrice(lowprice, heightprice);
			} else {
				cars1 = carservice.search_CarbyModel_Price(model, lowprice, heightprice);
			}
		} else {
			if (model=="") {
				cars1 = carservice.search_CarbyBrand_Price(brand, lowprice, heightprice);
			}else {
				cars1 =carservice.search_CarbyBrand_Model_Price(brand, model, lowprice, heightprice);
			}
		}
		if (years_s != "") {
			int years = Integer.parseInt(years_s);
			cars2 = carservice.search_CarbyYears(years);
			for(Car car1:cars1) {
				for(Car car2:cars2) {
					if (car1.getCar_id() == car2.getCar_id()) {
						reslut.add(car1);
					}
				}
			}
		}
		else {
			reslut = cars1;
		}
		List<Car> cars_re = new ArrayList<Car>();
		for (int i = 0; i < reslut.size(); i++) {
			Car car =reslut.get(i);
			SellPremission sellPremission = admirSevice.searchByC_id(car.getCar_id());
			if (sellPremission!=null && sellPremission.getPremission()==1 && sellPremission.getReleasing()==1) {
				cars_re.add(car);
			}
		}
		User user = userservice.search_UserById(user_id);
		request.setAttribute("searchcarsuccessmessage", "回到主页");
		request.setAttribute("cars",cars_re);
		request.setAttribute("user",user);
		request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
		
	}
	
	//返回登录界面
	public void backtoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
	}
	
}
