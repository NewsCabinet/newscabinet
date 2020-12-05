package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

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
		PrintWriter out = response.getWriter();
		 Calendar cal = Calendar.getInstance();
	    String today = cal.get(Calendar.YEAR) + "."+ (cal.get(Calendar.MONTH) + 1)+ "." + cal.get(Calendar.DAY_OF_MONTH);
		request.setAttribute("todayDate", today);
		
		int recordId = ManageRecord.insertUserRecord(conn, request);
		
		String[] userSelectNews = request.getParameterValues("checkBoxSelectedNews");
	
		if (recordId != -1) {					
			for(int i = 0; i <userSelectNews.length; i++) {
				int selectedNewsId =  Integer.parseInt(userSelectNews[i]);
				int recordResult = ManageRecord.insertUserScrapRecord(conn, recordId, selectedNewsId);
				
				if(recordResult == -1) {
					System.out.println("user_scrap_record 저장하는데 문제가 생김");
				}
			}
			System.out.println("저장성공!");
			RequestDispatcher view = request.getRequestDispatcher("/UserRecord/main");
			view.forward(request, response);
			//out.print("<script>alert('저장되었습니다.'); </script>\r\n");
			
			
		}else {
			out.print("<script>alert('저장에 실패하였습니다.'); location.href='../home.jsp'; </script>\r\n");
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
