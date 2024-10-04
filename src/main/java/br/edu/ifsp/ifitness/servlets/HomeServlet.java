package br.edu.ifsp.ifitness.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import br.edu.ifsp.ifitness.model.Activity;
import br.edu.ifsp.ifitness.model.User;
import br.edu.ifsp.ifitness.model.util.activities.ActivitiesReader;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public HomeServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url;
		HttpSession session = req.getSession(false);
		
		User user = (User)session.getAttribute("user");
		// listar atividades do usuário logado
		List<Activity> userActivities = 
				ActivitiesReader.readByUser(user);
		req.setAttribute("userActivities", userActivities);
		url = "/home.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}





