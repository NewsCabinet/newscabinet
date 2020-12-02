package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManageRecord {

	public static int insertUserRecord(Connection conn, HttpServletRequest request) {
		
		int result = -1;
		PreparedStatement pstmt = null;
		
	
		
		HttpSession userSession = request.getSession(false);
		int userId = (Integer)userSession.getAttribute("userId");
		System.out.println("insert Record userId = " + userId);
		
		String userSelectCategory = request.getParameter("subCategory");
		int recordSubcategoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, userSelectCategory);
		System.out.println("insert Record userSelectCategory = " + recordSubcategoryId + userSelectCategory);
		
		String userCustomCategory = "전체";
		int recordCustomCategoryId = ManageCategory.searchCustomCategoryIdByName(conn, userId, userCustomCategory);
		System.out.println("insert Record userCustomCategory= " + recordCustomCategoryId + userCustomCategory);		
		
		String userFolderStr =  request.getParameter("userFolder");
		int folderId = Integer.parseInt(userFolderStr);
		System.out.println("insert Record folderId= " + folderId);
		
	
		String recordTitle = (String)request.getParameter("recordTitle");
		System.out.println("insert Record recordTitle= " + recordTitle);
		String recordDate = (String)request.getAttribute("todayDate");
		System.out.println("insert Record todayDate= " + recordDate);
		String recordComment = request.getParameter("recordComment");
		System.out.println("insert Record recordComment= " + recordComment);
		
		String recordPrivateStr[] = request.getParameterValues("recordPrivate");
		boolean recordPrivate = false;
		for(String str : recordPrivateStr) {
			if(str.equals("true"))
				recordPrivate = true;
		}
		System.out.println("insert Record recordPrivate = " + recordPrivate);
		
		
		
		String query = "INSERT INTO newscabinet.user_record"
				+ " (user_id, subcategory_id, custom_category_id, folder_id, record_title, record_date, record_private, record_comment)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn.setAutoCommit(false);
			
			try {
				pstmt = conn.prepareStatement(query);
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("connection problem");
			}
			
			pstmt.setInt(1, userId);
			pstmt.setInt(2, recordSubcategoryId);
			pstmt.setInt(3, recordCustomCategoryId);
			pstmt.setInt(4, folderId);
			pstmt.setString(5, recordTitle);
			pstmt.setString(6, recordDate);
			pstmt.setBoolean(7, recordPrivate);
			pstmt.setString(8, recordComment);
			
			System.out.println("userId = "+ userId);
			System.out.println("recordSubcategoryId = " + recordSubcategoryId);
			System.out.println("recordCustomCategoryId = " + recordCustomCategoryId);
			System.out.println("folderId = " + folderId);
			System.out.println("record Title = "+recordTitle);
			System.out.println("record date = " + recordDate);
			System.out.println("record private = " + recordPrivate);
			System.out.println("record comment = " + recordComment);
			
			result = pstmt.executeUpdate();
			System.out.println("결과 = "+ result);
			conn.commit();
			conn.setAutoCommit(true);
			
			if(result == -1)
				return -1;
			
			
			int recordId = ManageRecord.searchRecordIdByUserIdAndTitle(conn, userId, recordTitle);
			
			if(recordId == -1)
				return -1;
			
			System.out.println("recordId = "+ recordId);
			return recordId;
			
			
		}catch(Exception e) {
			
		}
			
		return result;	
	}

	
	public static int searchRecordIdByUserIdAndTitle(Connection conn, int userId, String title) {
		
		String query = "SELECT record_id FROM newscabinet.user_record WHERE user_id="+userId + " and record_title ='"+title + "'";
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				ResultSet rs = st.getResultSet();
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	
	}


	public static int insertUserScrapRecord(Connection conn, int recordId, int newsId) {
		String query = "INSERT INTO newscabinet.user_scrap_record" + "(record_id, news_id)" + " VALUES(?, ?)";
		int result = -1;
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, recordId);
				pstmt.setInt(2, newsId);
				
				result = pstmt.executeUpdate();
				
				conn.commit();
				conn.setAutoCommit(true);
				
				return result;
			}catch(SQLException e) {
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("connection problem");
		}
			
			
		return result;

	}
	
	
	public static ResultSet searchFolderByUserId(Connection conn, int userId) {
		String query = "SELECT folder_id, folder_name FROM newscabinet.user_record_folder WHERE user_id=" + "'" + userId + "'";
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	public static int searchFolderIdByFolderName(Connection conn, int userId, String folderName) {
		int result = -1;

		String query = "SELECT folder_id FROM newscabinet.user_record_folder "
				+ "WHERE user_id ='"+userId+"' and folder_name='" +folderName + "'";
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				rs = st.getResultSet();
				if(rs.next()){
					return rs.getInt(1);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	
	
	public static ResultSet searchUserByID(Connection conn, String userID) {
		
		String query = "SELECT * FROM newscabinet.user WHERE user_id =" + "'" + userID + "'" ; 
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return st.getResultSet();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
