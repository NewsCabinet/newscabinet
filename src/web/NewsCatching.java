package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HandlingNews;
import model.ManageCategory;
import model.ManageScrapNews;
import model.NewsData;
import model.SubcategoryData;

/**
 * Servlet implementation class GetNewsData
 */
@WebServlet("/news/main")
public class NewsCatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsCatching() {
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
		response.setContentType("text/xml");
		
		ServletContext sc = getServletContext();
		String url = (String) sc.getAttribute("NaverAPIUrl");
		String clientId = (String) sc.getAttribute("X-Naver-Client-Id");
		String clientPW = (String) sc.getAttribute("X-Naver-Client-Secret");
		
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		HttpSession userSession = request.getSession(false);
		int userCategoryId = (int) userSession.getAttribute("userCategoryId");
		int userId = (int) userSession.getAttribute("userId");
		
		try {
			ResultSet tmp = ManageCategory.searchCategoryNameById(conn, userCategoryId);
			String userCategoryName = null;;
			if(tmp != null) {
				while(true) {
					if(tmp.next()) {
						userCategoryName = tmp.getString(1);
					}else {
						break;
					}
				}
			}
			
			request.setAttribute("userCategoryName", userCategoryName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SubcategoryData[] subcateData = (SubcategoryData[]) sc.getAttribute("subcateData");
		request.setAttribute("subcateData", subcateData);
		String subCategory = request.getParameter("subCategory"); // keyword로 사용할 subCategory
		if(subCategory == null) {subCategory = (String) request.getAttribute("userCategoryName");}
		request.setAttribute("subCategory", subCategory);
		
		String newsType = request.getParameter("newsType");
		if(newsType == null) {newsType = "sim";}
		request.setAttribute("newsType", newsType);
		
		HandlingNews gn = new HandlingNews();
		NewsData[] newsdata = gn.getNewsFromOpenAPI(url, clientId, clientPW, subCategory, newsType);
		request.setAttribute("newsdata", newsdata);
		sc.setAttribute("newsdata", newsdata);
		
		List tmp = new ArrayList<String>();
		try {
			ResultSet customCategoryArray = ManageCategory.searchCustomcategoryNameByUser(conn, userId, userCategoryId);	
			if(customCategoryArray!=null) {
				while(true) {
					if(customCategoryArray.next()) {
						tmp.add(customCategoryArray.getString(1));
					}else {
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("customCategories", tmp);
		
		
		try {
			
			ResultSet scrappedNews = ManageScrapNews.searchAllScrapNewsByUserId(conn, userId);
			List scrappedNewsId = new ArrayList();
			if(scrappedNews !=null) {
				while(true) {
					if(scrappedNews.next()) {
						for(int i = 0; i< newsdata.length; i++) {
							if(newsdata[i].getUrl().equals(scrappedNews.getString(6))) {
								scrappedNewsId.add(i);
								//System.out.println("scrapped : " + i + " " + newsdata[i].getUrl());
							}
						}
					}else {
						break;
					}
				}
			}
			//System.out.println(scrappedNewsId);
			
			request.setAttribute("scrappedNewsId", scrappedNewsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/news.jsp");
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
