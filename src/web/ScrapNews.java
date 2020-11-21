package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageCategory;
import model.ManageScrapNews;
import model.NewsData;

/**
 * Servlet implementation class ScrapNews
 */
@WebServlet("/scrap/main")
public class ScrapNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapNews() {
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
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		
		ManageScrapNews scrapNews = new ManageScrapNews();
		String subCategory = (String) sc.getAttribute("subCategory");
		
		HttpSession userSession = request.getSession(false);
		String userId = (String) userSession.getAttribute("userId");
		int subcategoryId = 1; 
		
		NewsData[] nd = (NewsData[]) sc.getAttribute("newsdata");
		int location = Integer.parseInt((String)request.getParameter("location"));
		
		try {
			String newsUrl = scrapNews.insertScrapNewsData(conn, subcategoryId, nd[location]);
			System.out.println(newsUrl);
			int newsId = scrapNews.searchScrapNewsIdByURL(conn, newsUrl).getInt(1);
			int customCategoryId = ManageCategory.searchDefualtCustomCategoryIdByUserId(conn, userId);
			scrapNews.insertScrapNewsRelationWithUser(conn, newsId, userId, customCategoryId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("../news.jsp");
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
