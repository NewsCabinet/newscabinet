package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ManageCategory {
	// category id를 받아옴 
	//회원가입시 getAttribute하면 id값이 같이 넘어옴,
	//public static ResultSet GETinterestCategory(Connection con, String interestCategory, String userID) {
	public static ResultSet getCategoryId(Connection con, String category_Id) {
		//String user_id = "t_1234";
		String sqlSt = "select category_id from newscabinet.user where user_id=" + category_Id;
		//String sqlSt = "SELECT category_id FROM newscabinet.user WHERE user_id=user_id";
		Statement st;
		try {
			st = con.createStatement();
			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getCategoryName(Connection con, String category_Id) {
	//	String sqlSt = "SELECT category_name FROM newscabinet.category WHERE category_id=" + category_Id;
		
		String sqlSt = "SELECT category_name FROM newscabinet.category WHERE category_id = 2";
		Statement st;
		try {
			st = con.createStatement();
			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet GetSubCategoryName(Connection con, String userCategory) {
	//	String sqlSt = "SELECT subcategory_name, subcategory_name FROM newscabinet.subcategory WHERE category_id="+string;
		String sqlSt = "SELECT subcategory_name, subcategory_name FROM newscabinet.subcategory WHERE category_id= 3";
		Statement st;
		try {
			st = con.createStatement();
			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
