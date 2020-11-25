package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
import model.NewsData;

/**
 * Servlet implementation class SettingUserRecord
 */

/*
 * 기록 작성 처음 페이지 로딩시 미리 셋팅 되어야할 데이터
 * -> 날짜, 사용자 폴더, 뉴스 url 
 */

@WebServlet("/UserRecord/setting")
public class SettingUserRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String newsUrl = null;
		int newsSubcategory = -1;
		String todayDate = null;
		ResultSet userFolder = null;
		
		
		HttpSession userSession = request.getSession(false);
		String userId = (String) userSession.getAttribute("userId");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		
		
		//사용자 폴더 내용 가져오기
		userFolder = (ResultSet) ManageRecord.searchFolderNameByUserId(conn, userId);

		
		//날짜
		Calendar cal = Calendar.getInstance(); 
		
		todayDate = cal.get(Calendar.YEAR)+"."+ (cal.get(Calendar.MONTH)+1) +"."+ cal.get(Calendar.DATE);
		request.setAttribute("todayDate", todayDate);
		
		
		//뉴스 정보 받아오기
		
		NewsData[] nd = (NewsData[]) sc.getAttribute("newsdata");
		int location = Integer.parseInt((String)request.getParameter("location"));
		newsSubcategory = Integer.parseInt((String)request.getParameter("subid"));
		
		System.out.println("news sub = " + newsSubcategory);
		System.out.println("news = " + location);
		newsUrl = nd[location].getUrl();
		System.out.println("news = " + newsUrl);
		
		String newsSubcategoryName = ManageCategory.searchSubcatogoryNameBySubcateogoryId(conn, newsSubcategory);
		System.out.println("news subname = " + newsSubcategoryName);
		
	
		request.setAttribute("newsUrl", newsUrl);
		request.setAttribute("subCategoryId", newsSubcategory);
		request.setAttribute("subCategoryName", newsSubcategoryName);
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("userFolder", userFolder);
		
		
		RequestDispatcher view = request.getRequestDispatcher("../Record/user/writingPage.jsp");
		//RequestDispatcher view = request.getRequestDispatcher("../../../writingPage.jsp");
		view.forward(request, response);

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
