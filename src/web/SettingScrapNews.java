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

/**
 * Servlet implementation class SettingScrapNews
 */
@WebServlet("/scrap/main")
public class SettingScrapNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   response.setCharacterEncoding("UTF-8");
		
	   ServletContext sc = getServletContext();
	   Connection conn= (Connection)sc.getAttribute("DBconnection");
		
	   HttpSession userSession = request.getSession(false);
	   int userId = (int) userSession.getAttribute("userId");
   
	   ResultSet resultTop10ScrapId = ManageScrapNews.searchScrapNewsIdTop10(conn);
	   ArrayList<UserScrapNewsData> scrapTop10List = new ArrayList<>();
	   
	   try {
		   if(resultTop10ScrapId != null) {
			   while(resultTop10ScrapId.next()) {
				   int newsId = resultTop10ScrapId.getInt(1);
				   if(newsId > 0) {
					   ResultSet scrapNews = ManageScrapNews.searchScrapNewsDataByNewsId(conn, newsId);
					   if(scrapNews != null & scrapNews.next()) {
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
						   
						   scrapTop10List.add(tmp);
					   }
				   }
			   }
		   }
		   
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	   

	   request.setAttribute("scrapTop10List", scrapTop10List);
		
	   RequestDispatcher view = request.getRequestDispatcher("/Scrap/scrapNewsHome.jsp");
	   view.forward(request, response);
	   
   
   }

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
