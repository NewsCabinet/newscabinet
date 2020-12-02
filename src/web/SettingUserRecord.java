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
 * 기록 작성하기
 * 제목, 날짜, 카테고리 설정, 사용자 폴더, 스크랩 뉴스 띄우기, 뉴스 보기, 작성칸, 저장하기 
 */



@WebServlet("/UserRecord/write")
public class SettingUserRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
	
		ResultSet userFolder = null;
		
		HttpSession userSession = request.getSession(false);
		int userId = (Integer) userSession.getAttribute("userId");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		

		//사용자 폴더 내용 가져오기
		userFolder = (ResultSet) ManageRecord.searchFolderByUserId(conn, userId);

		request.setAttribute("userFolder", userFolder);
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/writingPage.jsp");
		view.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
