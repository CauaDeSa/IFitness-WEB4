package br.edu.ifsp.ifitness.servlets;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import br.edu.ifsp.ifitness.model.User;
import br.edu.ifsp.ifitness.model.util.DataSourceSearcher;
import br.edu.ifsp.ifitness.model.util.users.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Serial
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
		Optional<User> optional = userDao.getUserByEmailAndPassword(email, password);
		RequestDispatcher dispatcher;

		if(optional.isPresent()) {
			User user = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("user", user);
			dispatcher = req.getRequestDispatcher("/homeServlet");
		}
		else
		{
			req.setAttribute("result", "loginError");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}

		dispatcher.forward(req, resp);
	}
}