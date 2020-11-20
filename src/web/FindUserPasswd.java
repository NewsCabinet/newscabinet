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

/**
 * Servlet implementation class FindUserPasswd
 */
@WebServlet("/Find/userpw")
public class FindUserPasswd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String userId = (String)request.getAttribute("userIdForPw");
		String userEmail = (String)request.getAttribute("usereMmailForPw");
		System.out.println("userID = " + userId);
		System.out.println("Mail = " + userEmail);
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String resultUserPw = ManageUser.searchUserPasswdByIDAndEmail(conn, userId, userEmail);
		
		
		
		if(resultUserPw != null) 
			request.setAttribute("userPw", resultUserPw);
		else
			request.setAttribute("userPw", null);
		
		RequestDispatcher view = request.getRequestDispatcher("/Sign/findPasswdAfter.jsp");
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
