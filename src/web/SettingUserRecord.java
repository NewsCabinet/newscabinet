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

import model.CustomCategoryData;
import model.FristCategoryData;
import model.ManageCategory;
import model.ManageRecord;
import model.ManageScrapNews;
import model.NewsData;
import model.SubcategoryData;
import model.UserFolderData;
import model.UserScrapNewsData;

/**
 * Servlet implementation class SettingUserRecord
 */

/*
 * 기록 작성 처음 페이지 로딩시 미리 셋팅 되어야할 데이터
 * 기록 작성하기
 * 제목, 날짜, 카테고리 설정, 사용자 폴더, 스크랩 뉴스 띄우기, 뉴스 보기, 작성칸, 저장하기 
 */



@WebServlet("/record/user/write")
public class SettingUserRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");

		ServletContext sc = getServletContext();
		Calendar cal = Calendar.getInstance();
	    String todayDate = cal.get(Calendar.YEAR) + "."+ (cal.get(Calendar.MONTH) + 1)+ "." + cal.get(Calendar.DAY_OF_MONTH);
		Connection conn= (Connection)sc.getAttribute("DBconnection");


		
		//Custom Category Setting
		ResultSet resultUserCustomCategory = ManageCategory.searchAllUserCustomCateogry(conn, userId);
		ArrayList<CustomCategoryData> userCustomCategoryList = new ArrayList<CustomCategoryData>();
		
		try {
			if(resultUserCustomCategory != null) {
				while(resultUserCustomCategory.next()) {
					int firstCategoryId = resultUserCustomCategory.getInt(1);
					int customCategoryId = resultUserCustomCategory.getInt(2);
					String customCategoryName = resultUserCustomCategory.getString(3);
					
					CustomCategoryData tmp = new CustomCategoryData();
					tmp.setFirstCategoryId(firstCategoryId);
					tmp.setCustomCategoryId(customCategoryId);
					tmp.setCustomCategoryName(customCategoryName);
					tmp.setUserId(userId);
					
					userCustomCategoryList.add(tmp);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		//사용자 폴더 내용 가져오기
		ResultSet resultUserFolder = (ResultSet) ManageRecord.searchFolderNameByUserId(conn, userId);
		ArrayList<UserFolderData> userForderList = new ArrayList<UserFolderData>();
		
		
		if (resultUserFolder != null) {
				try {
					while (resultUserFolder.next()) {
						String folderName = resultUserFolder.getString(1);
						int folderId = resultUserFolder.getInt(2);
						
						UserFolderData tmp = new UserFolderData();
						tmp.setFolderId(folderId);
						tmp.setFolderName(folderName);
						tmp.setUserId(userId);
						userForderList.add(tmp);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} else {
			System.out.println("resultUserFolder Problem");
		}

		//사용자가 스크랩한 뉴스 가져오기
		ArrayList<UserScrapNewsData> userScrapList = new ArrayList<UserScrapNewsData>();
		ResultSet userScrapResult = ManageScrapNews.searchAllUserScrapNewsForRecord(conn, userId);
		if (userScrapResult != null) {
			try {
				while(userScrapResult.next()) {
					UserScrapNewsData tmp = new UserScrapNewsData();
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
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("userCustomCategoryList", userCustomCategoryList);
		request.setAttribute("userForderList", userForderList);
		request.setAttribute("userScrapList", userScrapList);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/writingPage.jsp");
		view.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
