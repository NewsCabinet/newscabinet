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
		
		String userSelectCategory = request.getParameter("subCategory");
		int recordSubcategoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, userSelectCategory);
		
		String userCustomCategory = "전체";
		int recordCustomCategoryId = ManageCategory.searchCustomCategoryIdByName(conn, userId, userCustomCategory);
		
		String userFolderStr =  request.getParameter("userFolder");
		int folderId = Integer.parseInt(userFolderStr);
		
	
		String recordTitle = (String)request.getParameter("recordTitle");
		String recordDate = (String)request.getAttribute("todayDate");
		String recordComment = request.getParameter("recordComment");
		
		String recordPrivateStr[] = request.getParameterValues("recordPrivate");
		boolean recordPrivate = false;
		for(String str : recordPrivateStr) {
			if(str.equals("true"))
				recordPrivate = true;
		}
		
		
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
			
			
			result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			if(result == -1)
				return -1;
			
			int recordId = ManageRecord.searchRecordIdByUserIdAndTitle(conn, userId, recordTitle);
			
			if(recordId == -1)
				return -1;
			
			return recordId;
			
			
		}catch(Exception e) {
			System.out.println("userId = "+ userId);
			System.out.println("recordSubcategoryId = " + recordSubcategoryId);
			System.out.println("recordCustomCategoryId = " + recordCustomCategoryId);
			System.out.println("folderId = " + folderId);
			System.out.println("record Title = "+recordTitle);
			System.out.println("record date = " + recordDate);
			System.out.println("record private = " + recordPrivate);
			System.out.println("record comment = " + recordComment);
		}
			
		return result;	
	}



	public static int searchRecordIdByUserIdAndTitle(Connection conn, int userId, String title) {
		String query = "SELECT record_id FROM newscabinet.user_record WHERE user_id=? and record_title = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setString(2, title);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}


	public static int insertUserScrapRecord(Connection conn, int recordId, int newsId) {
		String query = "INSERT INTO newscabinet.user_scrap_record (record_id, news_id) VALUES(?, ?)";
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
	
	public static ResultSet searchFolderNameByUserId(Connection conn, int userId) {
		String query = "SELECT folder_name, folder_id FROM newscabinet.user_record_folder WHERE user_id=?";

		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			rs = pstat.executeQuery();
				return rs;
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
	
	public static ResultSet searchRecordByUserIdAndFolderId(Connection conn, int userId, int folderId) {
		String query = "SELECT * FROM newscabinet.user_record WHERE user_id=? and folder_id=?";

		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setInt(2, folderId);
			rs = pstat.executeQuery();
				return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
