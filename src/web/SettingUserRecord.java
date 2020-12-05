package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.UserScrapNews;

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
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");

		ServletContext sc = getServletContext();
		String todayDate = (String)sc.getAttribute("todayDate");
		Connection conn= (Connection)sc.getAttribute("DBconnection");

		//사용자 폴더 내용 가져오기
		ResultSet userFolder = null;
		userFolder = (ResultSet) ManageRecord.searchFolderNameByUserId(conn, userId);
		ArrayList<Integer> forderIdList = new ArrayList<>();
		ArrayList<String> forderNameList = new ArrayList<>();
		
		if (userFolder != null) {
				try {
					while (userFolder.next()) {
						String folderName = userFolder.getString(1);
						
						int folerId = userFolder.getInt(2);
						forderNameList.add(folderName);
						forderIdList.add(folerId);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			} else {
				System.out.println("DB에서 폴더가 로딩되지 않음");
			}

		ArrayList<UserScrapNews> userScrapList = new ArrayList<UserScrapNews>();
		ResultSet userScrapResult = ManageScrapNews.searchAllUserScrapNewsForRecord(conn, userId);
		if (userScrapResult != null) {
			try {
				while(userScrapResult.next()) {
					UserScrapNews tmp = new UserScrapNews();
					tmp.setNewsId(userScrapResult.getInt(1));
					tmp.setHeadline(userScrapResult.getString(2));
					
					int subCategoryId = userScrapResult.getInt(3);
					String subCategoryName = ManageCategory.searchSubcatogoryNameBySubcateogoryId(conn, subCategoryId);
					
					tmp.setSubCategoryId(subCategoryId);
					tmp.setSubCategoryName(subCategoryName);
					tmp.setNewsURL(userScrapResult.getString(4));
					
					userScrapList.add(tmp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("userScrapResult is null");
		}
		
		
		request.setAttribute("userId", userId);
		request.setAttribute("forderIdList", forderIdList);
		request.setAttribute("forderNameList", forderNameList);
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("userScrapList", userScrapList);
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/writingPage.jsp");
		view.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
