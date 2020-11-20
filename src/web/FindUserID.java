package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ManageUser;

@WebServlet("/Find/userid")
public class FindUserID extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String)request.getAttribute("emailForId");
		
		System.out.println("아이디 찾기 Mail = " + email);
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String resultUserId = ManageUser.searchUserIDByEmail(conn, email);
		
		if(resultUserId != null) 
			request.setAttribute("userId", resultUserId);
		else
			request.setAttribute("userId", null);
		
		
		request.getRequestDispatcher("/Sign/findIdAfter.jsp").forward(request, response);
	//	RequestDispatcher view = request.getRequestDispatcher("/Sign/findIdAfter.jsp");
	//	view.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}

	
	

}
