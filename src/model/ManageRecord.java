package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;


public class ManageRecord {

	
	public static int insertUserRecord(Connection conn, HttpServletRequest request) {
		
		int result = -1;
		PreparedStatement pstmt = null;

		int userId = (Integer)request.getAttribute("recordUserId");
		int recordSubcategoryId = (Integer)request.getAttribute("userSelectedSubCategoryId");
		int recordCustomCategoryId = (Integer)request.getAttribute("userSelectedCustomCategoryId");
		
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
			
			int checkSameRecord = ManageRecord.searchRecordIdByUserIdAndTitle(conn, userId, recordTitle, recordPrivate, recordSubcategoryId);
			
			//userId, recordTitle, recordPrivate, recordSubcategoryId가 모두 동일한 기록이 있는것
			if(checkSameRecord > 0) {
				return -2;
			}
			
			result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			if(result == -1)
				return -1;
			
			int recordId = ManageRecord.searchRecordIdByUserIdAndTitle(conn, userId, recordTitle, recordPrivate, recordSubcategoryId);
			
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



	public static int searchRecordIdByUserIdAndTitle(Connection conn, int userId, String title, boolean recordPrivate, int subcategoryId) {
		String query = "SELECT record_id FROM newscabinet.user_record "
					+ "WHERE user_id=? and record_title = ? and record_private=? and subcategory_id=?";
		ResultSet rs = null;
		int result = -1;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setString(2, title);
			pstmt.setBoolean(3, recordPrivate);
			pstmt.setInt(4, subcategoryId);
			rs = pstmt.executeQuery();
			
			boolean checkSameRecord = true;
			while(rs.next()) {
				result = rs.getInt(1);
				System.out.println("DB : " + result);
				if(checkSameRecord == true) {
					checkSameRecord = false;
				}else { 
					checkSameRecord = false;
					return -1;
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
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
	
	
	public static ResultSet searchAllPublicRecord(Connection conn) {
		String query = "SELECT record_id, user_id, subcategory_id, record_title, record_date, record_count FROM newscabinet.user_record WHERE record_private='0'";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next())
					return rs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet searchPublicRecordBySubcategoryId(Connection conn, int subcategoryId) {
		String query = "SELECT record_id, user_id, subcategory_id, record_title, record_date, record_count "
					+ "FROM newscabinet.user_record WHERE record_private='0' and subcategory_id='" + subcategoryId + "'";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next())
					return rs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static ResultSet searchPublicRecordIdByFirstcategoryId(Connection conn, int firstCategoryId) {
		String query = "SELECT record_id FROM newscabinet.user_record "
					+ "JOIN newscabinet.subcategory ON user_record.subcategory_id = subcategory.subcategory_id "
					+ "WHERE subcategory.category_id ='" + firstCategoryId + "' "
					+ "ORDER BY record_id DESC";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next())
					return rs;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("searchPublicRecordIdByFirstcategoryId Err");
		}
		return null;
	}
	
	
	//전체 기록보기에서 사용
	public static ResultSet searchSimpleUserRecordByRecordId(Connection conn, int recordId) {
		String query = "SELECT user.user_id, user.user_name, user_record.subcategory_id, subcategory_name, record_title, record_date, record_count "
						+ "FROM newscabinet.user_record "
						+ "JOIN newscabinet.user ON user.user_id = user_record.user_id "
						+ "JOIN newscabinet.subcategory "
						+ "ON user_record.subcategory_id = subcategory.subcategory_id "
						+ "WHERE record_id='"+ recordId + "'";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				return rs;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("searchSimpleUserRecordByRecordId Error");
		}
		return null;
	}
	
	
	//특정기록보기에서 사용
	public static ResultSet searchSpecificRecordByRecordId(Connection conn, int recordId) {
		String query = "SELECT user.user_id, user.user_name, user_record.subcategory_id, subcategory_name, record_title, record_date,record_comment, record_count "
				+ "FROM newscabinet.user_record "
				+ "JOIN newscabinet.user ON user.user_id = user_record.user_id"
				+ "JOIN newscabinet.subcategory ON user_record.subcategory_id = subcategory.subcategory_id"
				+ "WHERE record_id='"+ recordId + "'";

		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next())
					return rs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//조회순 top 10 기록 가져오기
	public static ResultSet searchPublicRecordIdTop10(Connection conn) {
		String query = "SELECT record_id FROM newscabinet.user_record ORDER BY record_count DESC LIMIT 10";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next())
					return rs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
