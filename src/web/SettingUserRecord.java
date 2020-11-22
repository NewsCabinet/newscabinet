package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageRecord;

/**
 * Servlet implementation class SettingUserRecord
 */

/*
 * 기록 작성 처음 페이지 로딩시 미리 셋팅 되어야할 데이터
 * -> 날짜, 사용자 폴더, 뉴스 url 
 */

@WebServlet("/UserRecord/Setting")
public class SettingUserRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		String userId = (String) userSession.getAttribute("userId");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		
		//사용자 폴더 내용 가져오기
		ResultSet rs = ManageRecord.searchFolderNameById(conn, userId);
		
		
		//날짜
		Calendar cal = Calendar.getInstance(); 
		String todayDate = null;
		todayDate = cal.get(Calendar.YEAR)+"."+ (cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DATE);
		request.setAttribute("todayDate", todayDate);
		
		
		
		request.setAttribute("newsId", 1);	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
