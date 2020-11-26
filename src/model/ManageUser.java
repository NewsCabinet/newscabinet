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

		String emailId = request.getParameter("userEmailId");
		String passwd = request.getParameter("userPassword");
		String name = request.getParameter("userName");
		String phone = request.getParameter("userPhone");
		String numberStr = request.getParameter("userAge");
		String genderStr[] = request.getParameterValues("userGender");
		String categoryStr[] = request.getParameterValues("category");

		boolean gender = true; // true - 여성
		for(String str : genderStr) {
			if(str.equals("true"))
				gender = true;
			else
				gender = false;
		}

		int category = 0;
		int age = Integer.parseInt(numberStr);

		for(String str : categoryStr) {
			category = Integer.parseInt(str);
		}


		//member table = (emailId, pw, email, category)
		try {

			conn.setAutoCommit(false);

			try {
				String query = "INSERT INTO newscabinet.user (user_email_id, user_pw, user_name, user_phone, user_age, user_gender, category_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);

			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("conn problem");
			}

			pstmt.setString(1, emailId);
			pstmt.setString(2, passwd);
			pstmt.setNString(3, name);
			pstmt.setString(4, phone);
			pstmt.setInt(5, age);
			pstmt.setBoolean(6, gender);
			pstmt.setInt(7, category);

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

	public ResultSet searchUserByID(Connection conn, String userID) {

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

	public static ResultSet searchUserByEmailId(Connection conn, String userEmailId) {

		String query = "SELECT user_pw, user_id, category_id, user_name FROM newscabinet.user WHERE user_email_id ='" + userEmailId + "'" ; 
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

	public static int serachUserEmailIdForCheck(Connection conn, String userEmailId) {

		String query = "SELECT user_id FROM newscabinet.user WHERE user_email_id='" + userEmailId + "'";
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



	public static int searchUserIDByEmail(Connection conn, String emailId) {

		String query = "SELECT user_id FROM newscabinet.user WHERE user_email_id ='" + emailId + "'" ; 
		Statement st;
		ResultSet rs;

		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {//email로 검색되는 값이 있음
					int userIdinDB = rs.getInt(1);
					if(userIdinDB != -1){
						return userIdinDB;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String searchUserEmailIdIdByPhone(Connection conn, String phone) {

		String query = "SELECT user_email_id FROM newscabinet.user WHERE user_phone ='" + phone + "'" ; 
		Statement st;
		ResultSet rs;

		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {//email로 검색되는 값이 있음
					String userEmailId = rs.getString(1);
					if(userEmailId != null) {
						return userEmailId;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String searchUserPasswdByEmailIdAndPhone(Connection conn, String userEmailId, String phone) {

		String query = "SELECT user_pw FROM newscabinet.user WHERE user_email_id =" + "'" + userEmailId + "' AND user_phone='" + phone+ "'"; 
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





	public static int insertFirstUserFolderByID(Connection conn, int userId) {

		int result = -1;
		PreparedStatement pstmt = null;

		String query = "INSERT INTO newscabinet.user_record_folder (user_id, folder_name) VALUES(?, ?)";

		try {

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, userId);
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

	public static int insertFirstUserCustomCategoryByID(Connection conn, int userId, int category) {

		int result = -1;
		PreparedStatement pstmt = null;

		String query = "INSERT INTO newscabinet.custom_category (user_id, category_id, custom_category_name) VALUES(?, ?, ?)";

		try {

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, userId);
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
	
	public static ResultSet updateChangeUser(Connection conn, String userEmailId, String name) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE newscabinet.user SET user_pw=?, user_name=?, user_phone=?, user_age=?, user_gender=?, category_id=?"
				+ "WHERE = userEmailId";
	
	
	return null;
	}



	/*public static String updateChangeUser(Connection conn, String passwd, String name, String phone, String numberStr, String categoryStr) {
		int result = -1;
		PreparedStatement pstmt = null;

		//String emailId = (String)request.getParameter("userEmailId");
		String passwd =request. etParameter("userPassword");
		String name = request.getParameter("userName");
		String phone = request.getParameter("userPhone");
		String numberStr = request.getParameter("userAge");
		String genderStr[] = request.getParameterValues("userGender");
		String categoryStr[] = request.getParameterValues("category");

		boolean gender = true; // true - 여성
		for(String str : genderStr) {
			if(str.equals("true"))
				gender = true;
			else
				gender = false;
		}

		int category = 0;
		int age = Integer.parseInt(numberStr);

		for(String str : categoryStr) {
			category = Integer.parseInt(str);
		}

		try {

			conn.setAutoCommit(false);

			try {
				String query = "UPDATE newscabinet.user SET user_pw=?, user_name=?, user_phone=?, user_age=?, user_gender=?, category_id=?"
						+ "WHERE = user_id";
				pstmt = conn.prepareStatement(query);	
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("conn problem");
			}

			pstmt.setString(1, passwd);
			pstmt.setNString(2, name);
			pstmt.setString(3, phone);
			pstmt.setInt(4, age);
			pstmt.setBoolean(5, gender);
			pstmt.setInt(6, category);

			result = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
*/


	}


