package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class ManageRecord {

	public static int insertUserRecord(Connection conn, HttpServletRequest request) {
		
		int result = -1;
		PreparedStatement pstmt = null;
		
		String userId = (String)request.getAttribute("userId");
		int newsId = (Integer)request.getAttribute("newsId");
		int folderId = (Integer)request.getAttribute("folderId");
		String recordTitle = (String)request.getAttribute("recordTitle");
		String recordDate = (String)request.getAttribute("recordDate");
		String categoryStr[] = request.getParameterValues("category");
		String recordComment = request.getParameter("recordComment");
		boolean recordPrivate = false;
		
		for(String str : categoryStr) {
			if(str.equals("true"))
				recordPrivate = true;
		}
		
		try {
			conn.setAutoCommit(false);
			
			try {
				String query = "INSERT INTO newscabinet.user_record (user_id, news_id, folder_id, record_title, record_date, record_private, record_comment) VALUES(?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("connection problem");
			}
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, newsId);
			pstmt.setInt(3, folderId);
			pstmt.setString(4, recordTitle);
			pstmt.setString(5, recordDate);
			pstmt.setBoolean(6, recordPrivate);
			pstmt.setString(7, recordComment);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}
			
		return result;	
	}
	
	public static ResultSet searchFolderNameById(Connection conn, String userId) {
		String query = "SELECT folder_name, folder_id FROM newscabinet.user_record_folder WHERE user_id=" + "'" + userId + "'";
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
