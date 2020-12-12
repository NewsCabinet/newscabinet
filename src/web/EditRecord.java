package web;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.SubcategoryData;
import model.UserFolderData;
import model.UserScrapNewsData;

/**
 * Servlet implementation class EditRecord
 */
@WebServlet("/record/user/edit")
public class EditRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		PrintWriter out = response.getWriter();
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		String tmpId = (String)request.getParameter("id");
		int recordId = Integer.parseInt(tmpId);
		request.setAttribute("recordId", recordId);
		
		//Date Setting
		Calendar cal = Calendar.getInstance();
	    String todayDate = cal.get(Calendar.YEAR) + "."+ (cal.get(Calendar.MONTH) + 1)+ "." + cal.get(Calendar.DAY_OF_MONTH);

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

		
		request.setAttribute("userId", userId);
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("userCustomCategoryList", userCustomCategoryList);
		request.setAttribute("userForderList", userForderList);
		request.setAttribute("userCustomCategoryList", userCustomCategoryList);
		
		//해당 기록에서 선택한 스트랩한 뉴스 setting
		ResultSet relatedScrapNewIdSet = ManageRecord.searchNewsIdByRecordId(conn, recordId);
		ArrayList<UserScrapNewsData> scrapNewsList = new ArrayList<UserScrapNewsData>();
		
		try {
			if(relatedScrapNewIdSet!= null) {
				while(relatedScrapNewIdSet.next()) {
					int newsId = relatedScrapNewIdSet.getInt(1);
					if(newsId != -1) {
						ResultSet resultNews = ManageScrapNews.searchScrapNewsByNewsId(conn, newsId);
						if(resultNews != null && resultNews.next()) {
							int scrapNewsSubcategoryId = resultNews.getInt(1);
							String scrapNewsSubcategoryName = resultNews.getString(2);
							String scrapNewsHeadLine = resultNews.getString(3);
							String scrapNewsUrl = resultNews.getString(4);
							
							UserScrapNewsData tmpData = new UserScrapNewsData();
							tmpData.setNewsId(newsId);
							tmpData.setNewsURL(scrapNewsUrl);
							tmpData.setHeadline(scrapNewsHeadLine);
							tmpData.setSubCategoryId(scrapNewsSubcategoryId);
							tmpData.setSubCategoryName(scrapNewsSubcategoryName);
							
							scrapNewsList.add(tmpData);
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("scrapNewsList", scrapNewsList);
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/editingPage.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
