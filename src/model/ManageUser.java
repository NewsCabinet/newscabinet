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
				
			//conn.setAutoCommit(false);
			
			try {
				String query = "INSERT INTO newscabinet.user (user_id, user_pw, user_name, user_email, category_id, user_age) VALUES(?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("conn ¹®Á¦");
			}
			
			pstmt.setString(1, userid);
			pstmt.setString(2, passwd);
			pstmt.setNString(3, name);
			pstmt.setString(4, email);
			pstmt.setInt(5, category);
			pstmt.setInt(6, number);
			
			
			result = pstmt.executeUpdate();
			conn.commit();
			//conn.setAutoCommit(true);
			
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
	
	
	public static ResultSet searchUserPasswdByID(Connection conn, String userID) {
		
		String query = "SELECT user_pw FROM newscabinet.user WHERE user_id =" + "'" + userID + "'" ; 
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
