package br.edu.ifsp.ifitness.servlets;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

import br.edu.ifsp.ifitness.model.Gender;
import br.edu.ifsp.ifitness.model.User;
import br.edu.ifsp.ifitness.model.util.DataSourceSearcher;
import br.edu.ifsp.ifitness.model.util.users.PasswordEncoder;
import br.edu.ifsp.ifitness.model.util.users.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet{

	@Serial
	private static final long serialVersionUID = 1L;

	public UserRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String gender = req.getParameter("gender");

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(PasswordEncoder.encode(password));
		user.setDateOfBirth(LocalDate.parse(dateOfBirth));
		user.setGender(Gender.valueOf(gender));

		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());

		RequestDispatcher dispatcher;

		if(userDao.save(user)) {
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}
		else
		{
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("user-register.jsp");
		}

		dispatcher.forward(req, resp);
	}

}