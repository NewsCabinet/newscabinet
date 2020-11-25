package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class ManageUser {
	public static int insertUser(Connection conn, HttpServletRequest request) {
		
		int result = -1;
		PreparedStatement pstmt = null;
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String numberStr = request.getParameter("age");
		String categoryStr[] = request.getParameterValues("category");
		int category = 0;
		int number = Integer.parseInt(numberStr);
	
		for(String str : categoryStr) {
			category = Integer.parseInt(str);
		}
		
		//member table = (id, pw, email, category)
		try {
				
			conn.setAutoCommit(false);
			
			try {
				String query = "INSERT INTO newscabinet.user (user_id, user_pw, user_name, user_email, category_id, user_age) VALUES(?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("conn ����");
			}
			
			pstmt.setString(1, userid);
			pstmt.setString(2, passwd);
			pstmt.setNString(3, name);
			pstmt.setString(4, email);
			pstmt.setInt(5, category);
			pstmt.setInt(6, number);
			
			
			result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			return result;
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	
	
	public static String searchUserPasswdByID(Connection conn, String userID) {
		
		String query = "SELECT user_pw FROM newscabinet.user WHERE user_id =" + "'" + userID + "'" ; 
		Statement st;
		String userPw;
		ResultSet rs;
		
		try {
			st = conn.createStatement();
			
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {//email로 검색되는 값이 있음
					String userId = rs.getString(1);
					if(!userId.isEmpty()) {
						return userId;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int serachUserIDForCheck(Connection conn, String userId) {
		
		String query = "SELECT user_id FROM newscabinet.user WHERE user_id='" + userId + "'";
		Statement st;
		ResultSet rs;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {//email로 검색되는 값이 있음
					return 1;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
	public static String searchUserIDByEmail(Connection conn, String userEmail) {
		
		String query = "SELECT user_id FROM newscabinet.user WHERE user_email =" + "'" + userEmail + "'" ; 
		Statement st;
		ResultSet rs;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {//email로 검색되는 값이 있음
					String userId = rs.getString(1);
					if(!userId.isEmpty()) {
						return userId;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String searchUserPasswdByIDAndEmail(Connection conn, String userId, String userEmail) {
		
		String query = "SELECT user_pw FROM newscabinet.user WHERE user_email =" + "'" + userEmail + "' AND user_id='" +userId+ "'"; 
		Statement st;
		ResultSet rs;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {//email로 검색되는 값이 있음
					String userPw = rs.getString(1);
					if(!userPw.isEmpty()) {
						return userPw;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static int insertFirstUserFolderByID(Connection conn, String userId) {
		
		int result = -1;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO newscabinet.user_record_folder (user_id, folder_name) VALUES(?, ?)";
		
		try {
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, "default");
			
			result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static int insertFirstUserCustomCategoryByID(Connection conn, String userId, int category) {
		
			int result = -1;
			PreparedStatement pstmt = null;
			
			String query = "INSERT INTO newscabinet.custom_category (user_id, category_id, custom_category_name) VALUES(?, ?, ?)";
			
			try {
				
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, userId);
				pstmt.setInt(2, category);
				pstmt.setString(3, "전체");
				
				result = pstmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(true);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
			
	}
	
	

}
