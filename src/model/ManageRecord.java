package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManageRecord {

	public static int insertUserRecord(Connection conn, HttpServletRequest request) {
		
		int result = -1;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO newscabinet.user_record"
				+ " (user_id, news_id, folder_id, record_title, record_date, record_private, record_comment)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		HttpSession userSession = request.getSession(false);
		String userId = (String) userSession.getAttribute("userId");
		
		String newsUrl = (String)request.getAttribute("newsUrl");
		System.out.println("url = " + newsUrl);
		int newsId = ManageScrapNews.searchScrapNewsIdByUrl(conn, newsUrl);
		String userFolderStr[] =  request.getParameterValues("userFolder");
		String folderName = null;
		
		for(String str : userFolderStr) {
			folderName = str;
		}
		
		int folderId = ManageRecord.searchFolderIdByFolderName(conn, userId, folderName);   
		
		String recordTitle = (String)request.getParameter("recordTitle");
		String recordDate = (String)request.getAttribute("recordDate");
		String recordComment = request.getParameter("recordComment");
		
		String recordPrivateStr[] = request.getParameterValues("recordPrivate");
		boolean recordPrivate = false;
		for(String str : recordPrivateStr) {
			if(str.equals("true"))
				recordPrivate = true;
		}
		
		try {
			conn.setAutoCommit(false);
			
			try {
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
			
			System.out.println("userId = "+ userId);
			System.out.println("newsid = " + newsId);
			System.out.println("folderId = " + folderId);
			System.out.println("record Title = "+recordTitle);
			System.out.println("record date = " + recordDate);
			System.out.println("record private = " + recordPrivate);
			System.out.println("record comment = " + recordComment);
			
			result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			return result;
			
		}catch(Exception e) {
			
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
	
	
	public static int searchFolderIdByFolderName(Connection conn, String userId, String folderName) {
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
	
	public static ResultSet searchRecordByRecordId(Connection conn, int recordId) {
		String query = "SELECT * FROM newscabinet.user_record WHERE record_id=?";

		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, recordId);
			rs = pstat.executeQuery();
				return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
