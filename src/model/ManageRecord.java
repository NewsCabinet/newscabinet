package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;


public class ManageRecord {

	
	public static ResultSet searchFolderNameByUserId(Connection conn, int userId) {
		String query = "SELECT folder_name, folder_id FROM newscabinet.user_record_folder WHERE user_id=? ORDER BY folder_name";

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
	
	public static String searchFolderNameByFolderId(Connection conn, int userId, int folderId) {

		String query = "SELECT folder_name FROM newscabinet.user_record_folder "
				+ "WHERE user_id ='"+userId+"' and folder_id=" +folderId;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				rs = st.getResultSet();
				if(rs.next()){
					return rs.getString(1);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
				return st.getResultSet();

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet searchPublicRecordIdBySubcategoryId(Connection conn, int subcategoryId) {
		String query = "SELECT record_id FROM newscabinet.user_record "
				+ "WHERE record_private='0' and subcategory_id='" + subcategoryId + "' "
				+ "ORDER BY record_id DESC";
		Statement st = null;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return st.getResultSet();
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

		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return st.getResultSet();
				
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
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return st.getResultSet();
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("searchSimpleUserRecordByRecordId Error");
		}
		return null;
	}
	
	
	//특정기록보기에서 사용
	public static ResultSet searchSpecificRecordByRecordId(Connection conn, int recordId) {
		String query = "SELECT user.user_id, user.user_name, user_record.subcategory_id, subcategory_name, record_title, record_date, record_comment, record_count "
				+ "FROM newscabinet.user_record "
				+ "JOIN newscabinet.user ON user_record.user_id = user.user_id "
				+ "JOIN newscabinet.subcategory ON subcategory.subcategory_id = user_record.subcategory_id "
				+ "WHERE record_id='"+ recordId + "'";

		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return st.getResultSet();
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
				return st.getResultSet();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//record조회시 조회수 +1
	public static int updateRecordCount(Connection conn, int recordId) {
		String query = "UPDATE newscabinet.user_record SET record_count = record_count+1 "
					+ "WHERE record_id ='" + recordId + "'"; 
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
	//해당 기록에 연결된 스크랩뉴스 가져오기
	public static ResultSet searchNewsIdByRecordId(Connection conn, int recordId) {
		String query = "SELECT news_id FROM newscabinet.user_scrap_record WHERE record_id='" + recordId + "'";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				return st.getResultSet();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//자신이 쓴 기록인지 확인
	public static boolean checkRecordIdByUserId(Connection conn, int userId, int recordId) {
		String query = "SELECT user_id FROM newscabinet.user_record WHERE record_id =" + recordId + " AND user_id= " + userId;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			if (st.execute(query)) {
				rs = st.getResultSet();
				if (rs.next())
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//처음 record insert시 사용
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
	
	public static int insertFolderUsingFolderName(Connection conn, int userId, String folderName) {
		if(searchFolderIdByFolderName(conn, userId, folderName) != -1) {
			return -1;
		}
		
		String query = "insert into newscabinet.user_record_folder (user_id, folder_name) values(?, ?)";
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setString(2, folderName);
			int tuple = pstat.executeUpdate();
			return tuple;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int removeRecordInUserScrapRecord(Connection conn, int recordId) {
		String query = "DELETE FROM newscabinet.user_scrap_record where record_id=?";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, recordId);
			int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return -1;
	}
	
	public static int removeRecordInLikeRecord(Connection conn, int userId, int recordId) {
		String query = "DELETE FROM newscabinet.user_like_record where user_id=? and record_id=?";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setInt(2, recordId);
			int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return -1;
	}
	
	public static int removeRecordByRecordId(Connection conn, int userId, int recordId) {
		int checkScrap = -1;
		int checkLike = -1;
		checkScrap = removeRecordInUserScrapRecord(conn, recordId);
		checkLike = removeRecordInLikeRecord(conn, userId, recordId);
		
		if(checkScrap>-1 && checkLike>-1) {
			String query = "DELETE FROM newscabinet.user_record where user_id=? and record_id=?";
			
			try {
				PreparedStatement pstat = conn.prepareStatement(query);
				pstat.setInt(1, userId);
				pstat.setInt(2, recordId);
				int result = pstat.executeUpdate();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(checkScrap ==1 ){
			System.out.println("스크랩이 업데이트 안됨");
		}else if(checkLike ==1 ){
			System.out.println("좋아요가 업데이트 안됨");
		}else {
			System.out.println("다 안됨");
		}
		
		return -1;
	}
	
	public static int removeFolderByFolderId(Connection conn, int userId, int folderId) {
		int checkRecord = -1;
		ResultSet records = searchRecordByUserIdAndFolderId(conn, userId, folderId);

		int defaultFolderId = searchFolderIdByFolderName(conn, userId, "default");
		
		if (records != null) {
			try {
				while (true) {
					if (records.next()) {
						checkRecord = updateUserRecordFolderId(conn, records.getInt("record_id"), defaultFolderId);
					}else {
						checkRecord = 0;
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(checkRecord>-1) {
			String query = "DELETE FROM newscabinet.user_record_folder where folder_id=?";
			
			try {
				PreparedStatement pstat = conn.prepareStatement(query);
				pstat.setInt(1, folderId);
				int result = pstat.executeUpdate();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(checkRecord != 1 ){
			System.out.println("기록이 업데이트 안됨");
		}
		
		return -1;
	}
	
	public static int updateUserRecordFolderId(Connection conn, int recordId, int folderId) {
		String query = "UPDATE newscabinet.user_record SET folder_id=? WHERE record_id=?";
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, folderId);
			pstat.setInt(2, recordId);
			int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int updateUserRecord(Connection conn, HttpServletRequest request) {
		PreparedStatement pstmt = null;
		
		String tmpId = (String)request.getParameter("recordId");
		int recordId = Integer.parseInt(tmpId);
		
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
		
		
		String query = "UPDATE newscabinet.user_record"
				+ " SET user_id=?, subcategory_id=?, custom_category_id=?, folder_id=?, record_title=?, record_date=?, record_private=?, record_comment=?"
				+ " WHERE record_id=?";
		
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
			pstmt.setInt(9, recordId);
			
			int result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			return result;
			
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
			
		return -1;	
	}
}

