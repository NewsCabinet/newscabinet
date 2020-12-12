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
import javax.servlet.http.HttpSession;

import model.FristCategoryData;
import model.ManageCategory;
import model.ManageRecord;
import model.ManageUser;
import model.RecordData;
import model.SubcategoryData;

/**
 * Servlet implementation class SettingOtherRecord
 */
@WebServlet("/record/shared/main")
public class SettingOtherRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		//int userFirstCategoryId = ManageCategory.searchUserFirstCategoryByUserId(conn, userId);

		ResultSet resultTop10Record = ManageRecord.searchPublicRecordIdTop10(conn);
		ArrayList<RecordData> popularTop10RecordList = new ArrayList<RecordData>();
		
		
		//top-10 기록 가져오기
		try {
			if(resultTop10Record!= null) {
				while(resultTop10Record.next()) {
					int recordId = resultTop10Record.getInt(1);
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
							
							popularTop10RecordList.add(tmp);
							
						}
					}
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("userId", userId);
		request.setAttribute("popularTop10RecordList", popularTop10RecordList);
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/other/otherRecordMain.jsp");
		view.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
