package web;

import java.io.IOException;
import java.io.PrintWriter;
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

import model.CustomCategoryData;
import model.ManageCategory;
import model.ManageRecord;
import model.ManageScrapNews;
import model.NewsData;
import model.RecordData;
import model.UserScrapNewsData;

/**
 * Servlet implementation class DisplayScrapNews
 */
@WebServlet("/scrap/category")
public class DisplayScrapNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayScrapNews() {
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
		
		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");

		String firstCategory = (String)request.getParameter("first");
		String subCategory = (String)request.getParameter("sub");
		
		int firstCategoryId = Integer.parseInt(firstCategory);
		int subCategoryId = -1;
		
		ResultSet resultscrapNewsIdSet = null;
		
		if(subCategory != null) { // 서브카테고리 눌러 넘어온 경우
			subCategoryId  = Integer.parseInt(subCategory);
			
			if(subCategoryId > 200) { // custom category로 넘어온 경우
				int customCategoryId = subCategoryId - 200;
				resultscrapNewsIdSet = ManageScrapNews.searchScrapNewsIdByUserIdAndcustomCategoryId(conn, userId, customCategoryId);
			}else { //기본 서브 카테고리로 넘어온 경우
				resultscrapNewsIdSet = ManageScrapNews.searchScrapNewsIdByUserIdAndSubcategoryId(conn, userId, subCategoryId);
			}
			
		}else { // 상위 카테고리 눌러서 넘어온 경우
			resultscrapNewsIdSet = ManageScrapNews.searchScrapNewsIdByUserIdAndCategoryId(conn, userId, firstCategoryId);
		}
		
		ArrayList<UserScrapNewsData> userScrapList = new ArrayList<>();
		
		try {
			if(resultscrapNewsIdSet != null) {
				while(resultscrapNewsIdSet.next()) {
					int newsId = resultscrapNewsIdSet.getInt(1);
					ResultSet scrapNews = ManageScrapNews.searchScrapNewsDataByNewsId(conn, newsId);
					if(scrapNews != null && scrapNews.next()) {
						int scrapSubCategoryId = scrapNews.getInt("subcategory_id");
						   String scrapSubCategoryName = scrapNews.getString("subcategory_name");
						   String scrapHeadline = scrapNews.getString("headline");
						   String scrapURL = scrapNews.getString("url");
						   String scrapDescription = scrapNews.getString("description");
						   String scrapPublicDate = scrapNews.getString("published_date");
						   int scrapCount = scrapNews.getInt("scrap_count");
						   
						   UserScrapNewsData tmp = new UserScrapNewsData();
						   tmp.setNewsId(newsId);
						   tmp.setHeadline(scrapHeadline);
						   tmp.setNewsURL(scrapURL);
						   tmp.setDescription(scrapDescription);
						   tmp.setPublishedDate(scrapPublicDate);
						   tmp.setScrapCount(scrapCount);
						   tmp.setSubCategoryId(scrapSubCategoryId);
						   tmp.setSubCategoryName(scrapSubCategoryName);
						
						   userScrapList.add(tmp);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Custom Category Setting
			ResultSet resultUserCustomCategory = ManageCategory.searchAllUserCustomCateogry(conn, userId);
			ArrayList<CustomCategoryData> userCustomCategoryList = new ArrayList<CustomCategoryData>();
			
			try {
				if(resultUserCustomCategory != null) {
					while(resultUserCustomCategory.next()) {
						int firstId = resultUserCustomCategory.getInt(1);
						int customId = resultUserCustomCategory.getInt(2);
						String customCategoryName = resultUserCustomCategory.getString(3);
						
						CustomCategoryData tmp = new CustomCategoryData();
						tmp.setFirstCategoryId(firstId);
						tmp.setCustomCategoryId(customId);
						tmp.setCustomCategoryName(customCategoryName);
						tmp.setUserId(userId);
						
						userCustomCategoryList.add(tmp);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		request.setAttribute("userScrapList", userScrapList);
		request.setAttribute("SelectedCategoryId", firstCategoryId);
		request.setAttribute("SelectedSubCategoryId", subCategoryId);
		request.setAttribute("userCustomCategoryList", userCustomCategoryList);
		
		RequestDispatcher view = request.getRequestDispatcher("/Scrap/scrapNewsCategory.jsp");
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
