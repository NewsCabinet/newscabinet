package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageCategory;
import model.ManageRecord;
import model.ManageScrapNews;
import model.NewsData;

/**
 * Servlet implementation class WriteRecrod
 * userid, newsid, folderid, record -> title, date, private, comment
 */
@WebServlet("/UserRecord/restore")
public class WriteRecrod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		HttpSession userSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		
		int result = ManageRecord.insertUserRecord(conn, request);
		
		if (result != -1) {			
			RequestDispatcher view = request.getRequestDispatcher("../Record/user/recordMainPage.jsp");
			view.forward(request, response);
		}else {
			out.print("<script>alert('저장에 실패하였습니다.'); location.href='../index.html'; </script>\r\n");
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
