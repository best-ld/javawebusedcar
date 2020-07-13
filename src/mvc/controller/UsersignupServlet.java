package mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.UserSignupService;


@WebServlet("/usersignupServlet")
public class UsersignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UsersignupServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String again_password = request.getParameter("again_password");
		UserSignupService userservice = new UserSignupService();
		if(!userservice.checkSignName(user_name)) {
			request.setAttribute("message","!该用户名存在");
			request.getRequestDispatcher("/UserSignup.jsp").forward(request, response);
		}else {
			if(!userservice.checkSignupIdcard(idcard)) {
				request.setAttribute("message","!该身份证号码已被注册");
				request.getRequestDispatcher("/UserSignup.jsp").forward(request, response);
			}else {
				if(!userservice.check_Password(password, again_password)) {
					request.setAttribute("message","!两次输入密码不一致");
					request.getRequestDispatcher("/UserSignup.jsp").forward(request, response);
				}else{
					userservice.add_User(user_name, again_password, name, idcard, phone, city);
					request.setAttribute("message", "注册成功！");
					request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
