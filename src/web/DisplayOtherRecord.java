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

import model.ManageRecord;
import model.RecordData;

/**
 * Servlet implementation class DisplayOtherRecord
 */
@WebServlet("/record/shared/category")
public class DisplayOtherRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
	
		ResultSet resultPublicRecordIdSet = null;
		ArrayList<RecordData> simpleRecordList = new ArrayList<RecordData>();
		
		int firstCategoryId = Integer.parseInt((String)request.getParameter("first"));
		int subCategoryId = -1;
		String subCategory = (String)request.getParameter("sub");
		
		if(subCategory == null) { // 상위 카테고리로 가져오기
			resultPublicRecordIdSet = ManageRecord.searchPublicRecordIdByFirstcategoryId(conn, firstCategoryId);
		}else{
			subCategoryId = Integer.parseInt(subCategory);
			resultPublicRecordIdSet = ManageRecord.searchPublicRecordIdBySubcategoryId(conn, subCategoryId);
		}
		
		
		try {
			if(resultPublicRecordIdSet!= null) {
				while(resultPublicRecordIdSet.next()) {
					int recordId = resultPublicRecordIdSet.getInt(1);
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
		
		
		request.setAttribute("simpleRecordList", simpleRecordList);
		request.setAttribute("SelectedCategoryId", firstCategoryId);
		request.setAttribute("SelectedSubCategoryId", subCategoryId);
		RequestDispatcher view = request.getRequestDispatcher("/Record/other/otherRecordCategory.jsp");
		view.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
