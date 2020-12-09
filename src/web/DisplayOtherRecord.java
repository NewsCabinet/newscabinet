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
@WebServlet("/otherRecord")
public class DisplayOtherRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		
		int firstCategoryId = Integer.parseInt((String)request.getParameter("firstCategory"));
		ResultSet resultPublicRecordId = ManageRecord.searchPublicRecordIdByFirstcategoryId(conn, firstCategoryId);
		ArrayList<RecordData> simpleRecordList = new ArrayList<RecordData>();
		
		try {
			if(resultPublicRecordId!= null) {
				while(resultPublicRecordId.next()) {
					int recordId = resultPublicRecordId.getInt(1);
					System.out.println("top10 기록 가져오기 = " + recordId);
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
		RequestDispatcher view = request.getRequestDispatcher("/Record/other/otherRecordCategory.jsp");
		view.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
