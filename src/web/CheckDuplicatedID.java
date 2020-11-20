package web;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/checkID")
public class CheckDuplicatedID extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = (String)request.getAttribute("userid");
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		ResultSet rs = ManageUser.searchUserByID(conn, userID);
		PrintWriter out = response.getWriter();
		boolean checkID = false;
		
		if (rs != null) {
			try {
				if (rs.next()) {  // 이미 존재하는 아이디
					checkID = false;
					out.print("이미 존재하는 아이디입니다.");
				}
				else {
					checkID = true;
					out.print("사용가능한 아이디입니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} // try end
		} 
		
		request.setAttribute("checkID", checkID);
		
		RequestDispatcher view = request.getRequestDispatcher("/checkID.jsp");
		view.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	
	

	
}
