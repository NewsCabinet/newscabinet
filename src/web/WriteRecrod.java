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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		String today = (String)sc.getAttribute("todayDate");
		PrintWriter out = response.getWriter();
		request.setAttribute("todayDate", today);
		
		int recordId = ManageRecord.insertUserRecord(conn, request);
		
		String[] userSelectNews = request.getParameterValues("userSelectedNews");
		
		for(int i = 0; i <userSelectNews.length; i++) {
			System.out.println("Selected News = " + userSelectNews[i]);
		}
		
		if (recordId != -1) {			
			//out.print("<script>alert('저장되었습니다.'); location.href='../Record/user/recordMainPage.jsp'; </script>\r\n");
			
			for(int i = 0; i <userSelectNews.length; i++) {
				System.out.println("Selected News = " + userSelectNews[i]);
				int selectedNewsId =  Integer.parseInt(userSelectNews[i]);
				int recordResult = ManageRecord.insertUserScrapRecord(conn, recordId, selectedNewsId);
				
				if(recordResult == -1) {
					System.out.println("user_scrap_record 저장하는데 문제가 생김");
				}
			}
			
			
			RequestDispatcher view = request.getRequestDispatcher("../Record/user/recordMainPage.jsp");
			view.forward(request, response);
		}else {
			out.print("<script>alert('저장에 실패하였습니다.'); location.href='../home.jsp'; </script>\r\n");
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
