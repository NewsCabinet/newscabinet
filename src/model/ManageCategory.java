package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ManageCategory {
	
	public static ResultSet searchCategoryNameById(Connection con, int categoryId) {

		String sqlSt = "SELECT category_name FROM newscabinet.category WHERE category_id=" +categoryId;
		Statement st;
		
		try {
			st = con.createStatement();
			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static ResultSet searchSubCategoryName(Connection con, int categoryId) {
		
		String sqlSt = "SELECT subcategory_id, subcategory_name FROM newscabinet.subcategory WHERE category_id=" + categoryId;
		Statement st;
		try {
			st = con.createStatement();
			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static int searchCountSubCategory(Connection con, int categoryId) {
		String sqlSt = "SELECT COUNT(*) FROM newscabinet.subcategory WHERE category_id=" + categoryId;
		Statement st;
		try {
			st = con.createStatement();
			if (st.execute(sqlSt)) {
				ResultSet rs = st.getResultSet();
				if(rs!=null) {
					while(true) {
						if(rs.next()) {
							return rs.getInt(1);
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int searchDefualtCustomCategoryIdByUserId(Connection conn, String userid) {
		String query = "SELECT custom_category_id FROM newscabinet.custom_category WHERE user_id=? and custom_category_name=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setString(1, userid);
			pstat.setString(2, "전체");
			
			rs = pstat.executeQuery();
				if(rs.next()) {
					System.out.println(rs.getInt(1));
					return rs.getInt(1);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
