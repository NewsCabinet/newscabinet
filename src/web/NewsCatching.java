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
		int userCategoryId = (int) userSession.getAttribute("userCategory");
		
		int size = ManageCategory.searchCountSubCategory(conn, userCategoryId);
		
		try {
			ResultSet tmp = ManageCategory.searchCategoryNameById(conn, userCategoryId);
			String userCategoryName = null;;
			if(tmp != null) {
				while(true) {
					if(tmp.next()) {
						userCategoryName = tmp.getString(1);	
						//System.out.println(userCategoryName);
					}else {
						break;
					}
				}
			}
			
			ResultSet rs = ManageCategory.searchSubCategoryName(conn, userCategoryId);
			SubcategoryData[] subcateData = new SubcategoryData[size];
			if(rs!= null) {
				int count = 0;
				while(true) {
					if(rs.next()) {
						subcateData[count++] = new SubcategoryData(rs.getInt(1),rs.getString(2));
						//System.out.println(rs.getString(2));
					}else {
						break;
					}
				}
				
			}
			request.setAttribute("userCategoryName", userCategoryName);
			request.setAttribute("subcateData", subcateData); // 화면에 보여줄 subCategoryData
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String subCategory = request.getParameter("subCategory"); // keyword로 사용할 subCategory
		request.setAttribute("subCategory", subCategory);
		if(subCategory == null) {subCategory = "IT";}
		
		String newsType = request.getParameter("newsType");
		if(newsType == null) {newsType = "sim";}
		request.setAttribute("newsType", newsType);
		
		HandlingNews gn = new HandlingNews();
		NewsData[] newsdata = gn.getNewsFromOpenAPI(url, clientId, clientPW, subCategory, newsType);
		request.setAttribute("newsdata", newsdata);
		sc.setAttribute("newsdata", newsdata);
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
