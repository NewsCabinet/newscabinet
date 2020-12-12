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
import model.RecordData;

/**
 * Servlet implementation class DisplayRecordListForCategory
 */
@WebServlet("/UserRecord/main/category")
public class DisplayRecordListForCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayRecordListForCategory() {
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
		
		if(firstCategory != null)
			firstCategoryId = Integer.parseInt(firstCategory);
		
		ResultSet resultRecordIdSet = null;
		
		if(subCategory != null) { // 서브카테고리 눌러 넘어온 경우
			subCategoryId  = Integer.parseInt(subCategory);
			
			if(subCategoryId > 200) { // custom category로 넘어온 경우
				int customCategoryId = subCategoryId - 200;
				resultRecordIdSet = ManageRecord.searchUserRecordIdByCustomCategoryId(conn, userId, customCategoryId);
			}else { //기본 서브 카테고리로 넘어온 경우
				resultRecordIdSet = ManageRecord.searchUserRecordIdBySubcategoryId(conn, userId, subCategoryId);
			}
			
		}else if(firstCategory != null){ // 상위 카테고리 눌러서 넘어온 경우
			resultRecordIdSet = ManageRecord.searchUserRecordIdByFirstcategoryId(conn, userId, firstCategoryId);
			
		}else { // 처음 나의 기록보기에 들어왔을 경우 - 사용사가 회원가입시 선택한 first 카테고리로 
			firstCategoryId = ManageCategory.searchUserFirstCategoryIdByUserId(conn, userId);
			resultRecordIdSet = ManageRecord.searchUserRecordIdByFirstcategoryId(conn, userId, firstCategoryId);
		}
		
		
		ArrayList<RecordData> simpleRecordList = new ArrayList<RecordData>();
		
		try {
			if(resultRecordIdSet!= null) {
				while(resultRecordIdSet.next()) {
					int recordId = resultRecordIdSet.getInt(1);
					if(recordId != -1) {
						ResultSet simpleRecord = ManageRecord.searchSimpleUserRecordByRecordId(conn, recordId);
						if(simpleRecord != null && simpleRecord.next()) {
							
							int recordUserId = simpleRecord.getInt(1);
							String recordUserName = simpleRecord.getString(2);
							int recordSubcatergoryId = simpleRecord.getInt(3);
							String recordSubcatergoryName = simpleRecord.getString(4);
							String recordTitle = simpleRecord.getString(5);
							String recordDate = simpleRecord.getString(6);
							int recordCount =  simpleRecord.getInt(7);
							
							RecordData tmp = new RecordData();
							tmp.setUserId(recordUserId);
							tmp.setUserName(recordUserName);
							tmp.setSubcategoryId(recordSubcatergoryId);
							tmp.setSubcategoryName(recordSubcatergoryName);
							tmp.setRecordId(recordId);
							tmp.setRecordTitle(recordTitle);
							tmp.setRecordDate(recordDate);
							tmp.setRecordCount(recordCount);
							
							simpleRecordList.add(tmp);
						}
					}
				}
			}
		}catch (SQLException e) {
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
	
		

		request.setAttribute("simpleRecordList", simpleRecordList);
		request.setAttribute("SelectedCategoryId", firstCategoryId);
		request.setAttribute("SelectedSubCategoryId", subCategoryId);
		request.setAttribute("userCustomCategoryList", userCustomCategoryList);
		
		RequestDispatcher view = request.getRequestDispatcher("../../Record/user/recordListForCategory.jsp");
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
