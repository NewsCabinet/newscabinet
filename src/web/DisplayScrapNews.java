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
		int firstCategoryId = -1;
		int subCategoryId = -1;
		
		
		ResultSet resultUserScrapIdSet = null;
		resultUserScrapIdSet = ManageScrapNews.searchUserScrapNewsByUserId(conn, userId);
		
		ArrayList<NewsData> scrapNewsList = new ArrayList<>();
		ArrayList<CustomCategoryData> customCategoryList = new ArrayList<>();
		
		try {
			if(resultUserScrapIdSet!= null) {
				while(resultUserScrapIdSet.next()) {
					int newsId = resultUserScrapIdSet.getInt(1);
					int customCategoryId = resultUserScrapIdSet.getInt(2);
					if(newsId > 0 && customCategoryId > 0) {
						ResultSet scapNews = ManageScrapNews.searchScrapNewsDataByNewsId(conn, newsId);
						ResultSet customCategory = ManageCategory.searchCustomCategoryDataByCustomId(conn, customCategoryId);
						
						if(scapNews != null && scapNews.next()) {
							
						}
						
						
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(resultUserScrapIdSet != null) {
			
				
			}
		
		
		
		
		
		if(subCategory != null) { // 상위 카테고리로 가져오기
			//resultPublicRecordIdSet = ManageRecord.searchPublicRecordIdByFirstcategoryId(conn, firstCategoryId);
		}else if(firstCategory != null) {
			subCategoryId = Integer.parseInt(subCategory);
			//resultPublicRecordIdSet = ManageRecord.searchPublicRecordIdBySubcategoryId(conn, subCategoryId);
		}
		
		
		//get parameter로 넘어온게 없다면 사용자 category로 설정
		
		
		// TODO: custom category 받아와서 뿌려주기
		
		String selectedCategory = request.getParameter("Step1");
		String selectedSubCategory = request.getParameter("Step2");
		request.setAttribute("selectedCategory", selectedCategory);
		request.setAttribute("selectedSubCategory", selectedSubCategory);
		int categoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, selectedSubCategory);
		ResultSet rs = ManageScrapNews.searchScrapNewsByUserIdAndCategory(conn, userId, categoryId);
		
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
