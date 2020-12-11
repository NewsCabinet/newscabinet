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

import model.ManageCategory;
import model.ManageRecord;
import model.ManageScrapNews;
import model.RecordData;
import model.UserScrapNewsData;

/**
 * Servlet implementation class DisplaySpecificRecord
 */
@WebServlet("/UserRecord/record")
public class DisplaySpecificRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplaySpecificRecord() {
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
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		
		int selectedRecordId = Integer.parseInt((String)request.getParameter("id"));
		
		ResultSet resultSelectedRecord = ManageRecord.searchSpecificRecordByRecordId(conn, selectedRecordId);
		RecordData recordData = new RecordData();
		
		
		// 선택한 record Data setting
		try {
			if(resultSelectedRecord != null) {
				if(resultSelectedRecord.next()) {
					
					//조회수 + 1
					ManageRecord.updateRecordCount(conn, selectedRecordId);
					
					int recordUserId = resultSelectedRecord.getInt(1);
					String recordUserName = resultSelectedRecord.getString(2);
					int recordSubcategoryId = resultSelectedRecord.getInt(3);
					String recordSubcategoryName = resultSelectedRecord.getString(4);
					String recordTitle = resultSelectedRecord.getString(5);
					String recordDate = resultSelectedRecord.getString(6);
					String recordComment =resultSelectedRecord.getString(7);
					int recordCount = resultSelectedRecord.getInt(8);
					
					String recordFirstCategoryName = ManageCategory.searchFirstCategoryNameBySubcategoryId(conn, recordSubcategoryId);
					
					recordData.setUserId(recordUserId);
					recordData.setUserName(recordUserName);
					recordData.setFirstCategoryName(recordFirstCategoryName);
					recordData.setSubcategoryId(recordSubcategoryId);
					recordData.setSubcategoryName(recordSubcategoryName);
					recordData.setRecordId(selectedRecordId);
					recordData.setRecordTitle(recordTitle);
					recordData.setRecordDate(recordDate);
					recordData.setRecordComment(recordComment);
					recordData.setRecordCount(recordCount+1);
				}
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
		}
		
		//recordData.printRecord();
		
		//해당 기록에서 선택한 스트랩한 뉴스 setting
		ResultSet relatedScrapNewIdSet = ManageRecord.searchNewsIdByRecordId(conn, selectedRecordId);
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
							
							UserScrapNewsData tmp = new UserScrapNewsData();
							tmp.setNewsId(newsId);
							tmp.setNewsURL(scrapNewsUrl);
							tmp.setHeadline(scrapNewsHeadLine);
							tmp.setSubCategoryId(scrapNewsSubcategoryId);
							tmp.setSubCategoryName(scrapNewsSubcategoryName);
							
							scrapNewsList.add(tmp);
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("selectedRecordData", recordData);
		request.setAttribute("scrapNewsList", scrapNewsList);
		RequestDispatcher view = request.getRequestDispatcher("/Record/record.jsp");
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
