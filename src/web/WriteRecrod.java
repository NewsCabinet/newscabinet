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
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		PrintWriter out = response.getWriter();
		Calendar cal = Calendar.getInstance();
	    String today = cal.get(Calendar.YEAR) + "."+ (cal.get(Calendar.MONTH) + 1)+ "." + cal.get(Calendar.DAY_OF_MONTH);
		request.setAttribute("todayDate", today);
		
		//DB insert하기 전에 custom category인지, 아닌지 판별
		String firstCategory = request.getParameter("firstCategory");
		int firstCategoryId = Integer.parseInt(firstCategory);
		String firstCategoryName = ManageCategory.searchCategoryIdByCategoryName(conn, firstCategoryId);
		
		
		String checkCustomStr = request.getParameter("subCategory");
	
		int userSelectedCustomCategoryId = ManageCategory.searchCustomCategoryIdByName(conn, userId, checkCustomStr);
		int userSelectedSubCategoryId = -1;
		
		//custom category일 경우
		if(userSelectedCustomCategoryId != -1) {
			//subcategory는 상위 카테고리와 같은 이름의 subcategory 넣어줘야함
			userSelectedSubCategoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, firstCategoryName);
			
			if(userSelectedSubCategoryId == -1) {
				System.out.println("WriteRecord | searchSubcatogory 가져오는데 문제생김 : " + firstCategoryName);
			}
			
			/*
			 * System.out.println("-----------custom category일 경우---------------");
			 * System.out.println("custom name = " + checkCustomStr + " , id = " +
			 * userSelectedCustomCategoryId); System.out.println("sub name = " +
			 * firstCategoryName+ " , id = " + userSelectedSubCategoryId);
			 */
			
		} else { //custom category가 아닌 경우
			//subcategory는 그대로 받아오고
			userSelectedSubCategoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, checkCustomStr);
			
			//custom category는 user_id, "전체"인 custom_category_id 가져와야함
			userSelectedCustomCategoryId = ManageCategory.searchCustomCategoryIdByName(conn, userId, "전체");
			
			/*
			 * System.out.println("-----------일반 category일 경우---------------");
			 * System.out.println("custom name = 전체 , id = " +
			 * userSelectedCustomCategoryId); System.out.println("sub name = " +
			 * checkCustomStr+ " , id = " + userSelectedSubCategoryId);
			 */
			
 		}
		
		request.setAttribute("recordUserId", userId);
		request.setAttribute("userSelectedSubCategoryId", userSelectedSubCategoryId);
		request.setAttribute("userSelectedCustomCategoryId", userSelectedCustomCategoryId);
		
		int recordId = ManageRecord.insertUserRecord(conn, request);
		System.out.println("recordId = " + recordId);
		
		String[] userSelectNews = request.getParameterValues("checkBoxSelectedNews");
		try {
			if (recordId > 0 ) {					
				for(int i = 0; i <userSelectNews.length; i++) {
					int selectedNewsId =  Integer.parseInt(userSelectNews[i]);
					int recordResult = ManageRecord.insertUserScrapRecord(conn, recordId, selectedNewsId);
					
					if(recordResult == -1) {
						System.out.println("user_scrap_record 저장하는데 문제가 생김");
						throw new Exception();
					}
				}
				System.out.println("저장성공!");
				out.print("<script>alert('저장되었습니다.'); location.href='./main' </script>\n");
			//	RequestDispatcher view = request.getRequestDispatcher("/UserRecord/main");
			//	view.forward(request, response);
				out.flush();
				
			}else if(recordId == -2){
				System.out.println("같은 기록이 존재함");
				out.println("<script>alert('같은 글이 존재합니다. 제목이나 공개 여부를 다르게 하여 작성하여주세요'); location.href='../home.jsp'; </script>\r\n");
				out.flush();

			}else {
				out.print("<script>alert('저장에 실패하였습니다.'); location.href='../home.jsp'; </script>\r\n");
				out.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
