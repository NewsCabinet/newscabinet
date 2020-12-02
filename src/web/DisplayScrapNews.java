package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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

/**
 * Servlet implementation class DisplayScrapNews
 */
@WebServlet("/scrap/main")
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
		
		// TODO: 삭제하기
		ResultSet rs = ManageCategory.searchAllCategoryAndSubCategory(conn);
		request.setAttribute("Categories", rs);
		
		String selectedCategory = request.getParameter("Step1");
		String selectedSubCategory = request.getParameter("Step2");
		request.setAttribute("selectedCategory", selectedCategory);
		request.setAttribute("selectedSubCategory", selectedSubCategory);
		int categoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, selectedSubCategory);
		rs = ManageScrapNews.searchScrapNewsByUserIdAndCategory(conn, userId, categoryId);
		
		request.setAttribute("ScrapNews", rs);
		ResultSet scrapNews = (ResultSet) request.getAttribute("ScrapNews");
		
		
		RequestDispatcher view = request.getRequestDispatcher("/scrapnews.jsp");
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
