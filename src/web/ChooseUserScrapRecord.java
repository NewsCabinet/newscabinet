package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageScrapNews;
import model.UserScrapNewsData;
import model.ManageCategory;

/**
 * Servlet implementation class ChooseUserScrapRecord
 */
@WebServlet("/UserRecord/scrapNews")
public class ChooseUserScrapRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
	
		HttpSession userSession = request.getSession(false);
		int userId = -1;
		if(userSession != null){
			userId = (Integer)userSession.getAttribute("userId");
		}
		
		
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
					
					userScrapList.add(tmp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("userScrapResult is null");
		}
		
		
		request.setAttribute("userScrapList", userScrapList);
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/chooseUserScrap.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
