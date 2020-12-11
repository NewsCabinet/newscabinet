package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/scrap/scrapNews")
public class ScrappingNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrappingNews() {
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
		
		String subCategory = (String) sc.getAttribute("subCategory");
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		
		NewsData[] nd = (NewsData[]) sc.getAttribute("newsdata");
		int location = Integer.parseInt((String)request.getParameter("location"));
		int subcategoryId = Integer.parseInt((String)request.getParameter("subId"));
		//System.out.println("subcategory id : "+subcategoryId);
		String customcategoryName = (String)request.getParameter("custom");
		String check = (String) request.getParameter("check");
		System.out.println(check);
		
		if(check.equals("false")) {
			try {
				String newsUrl = ManageScrapNews.insertScrapNewsData(conn, subcategoryId, nd[location]);
				//System.out.println(newsUrl);
				int newsId = ManageScrapNews.searchScrapNewsIdByUrl(conn, newsUrl);
				int customCategoryId = customCategoryId = ManageCategory.searchDefaultCustomCategoryIdByUserId(conn, userId);
				customcategoryName = customcategoryName.trim();
				if(customcategoryName.length() > 0) {
					customCategoryId = ManageCategory.searchCustomcategoryIdByUserAndCustomcategoryName(conn, userId, customcategoryName);
					//System.out.println("custom category id : " + customCategoryId);
				}
				ManageScrapNews.insertScrapNewsRelationWithUser(conn, newsId, userId, customCategoryId);
				
				out.print("<script>alert('스크랩 되었습니다!'); location.href='/NewsCabinet/news/main'; </script>\r\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(check.equals("true")){
			String newsUrl;
			try {
				newsUrl = ManageScrapNews.insertScrapNewsData(conn, subcategoryId, nd[location]);
				int newsId = ManageScrapNews.searchScrapNewsIdByUrl(conn, newsUrl);
				int checkCount = ManageScrapNews.updateScrapNewsCountMinus(conn, newsId);
				int checkRemove = ManageScrapNews.removeScrapNewsByUserId(conn, userId, newsId);
				if(checkRemove == 1) {
					System.out.println("스크랩 취소 완료");
					out.print("<script>alert('스크랩 취소되었습니다!'); location.href='/NewsCabinet/news/main'; </script>\r\n");
				}else {
					System.out.println("스크랩 취소 안됨");
					out.print("<script>alert('스크랩 취소가 안되었습니다.!'); location.href='/NewsCabinet/news/main'; </script>\r\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}