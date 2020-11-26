package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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

	
	public static String searchSubcatogoryNameBySubcateogoryId(Connection conn, int subcategoryId) {
		String sqlSt = "SELECT subcategory_name FROM newscabinet.subcategory WHERE subcategory_id=" + subcategoryId;
		
		Statement st;
		try {
			st = conn.createStatement();
			if (st.execute(sqlSt)) {
				ResultSet rs = st.getResultSet();
				if(rs!=null) {
					if(rs.next()) {
						return rs.getString(1);
					}		
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
	public static int searchSubcatogoryIdBySubcateogoryName(Connection conn, String subcategoryName) {
		String sqlSt = "SELECT subcategory_id FROM newscabinet.subcategory WHERE subcategory_id=" + subcategoryName;
		
		Statement st;
		try {
			st = conn.createStatement();
			if (st.execute(sqlSt)) {
				ResultSet rs = st.getResultSet();
				if(rs!=null) {
					if(rs.next()) {
						return rs.getInt(1);
					}		
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}
	
	/*	public static String[][] searchAllSubcategory(Connection conn){
		
		//ArrayList<String[]> subcategoryList = new ArrayList<String[]>();
		String[][] subcategoryList = null;
		
		String sqlSt = "SELECT category_id, subcategory_name FROM newscabinet.subcategory";
		Statement st, st2;
		ResultSet rs = null;
		int categoryId = -1;
		String subcategoryName = null;
		
		
		try {
			st = conn.createStatement();
			if(st.execute(sqlSt)) {
				rs = st.getResultSet();
			}
			if(rs != null) {
				subcategoryList = new String[8][];
				int count = 0;
				int element = 0;
				while(rs.next()) {
					categoryId = rs.getInt(1);
					subcategoryName = rs.getString(2);
					if(count == 0) {
						element = ManageCategory.searchCountSubCategory(conn, categoryId); // 서브카테고리 개수
						subcategoryList[categoryId - 1] = new String[element];
						subcategoryList[categoryId][element++] = subcategoryName;
						System.out.println(subcategoryName);
					
					}else {
						count++;
						element = 0;
						subcategoryList[categoryId][element] = subcategoryName;
						System.out.println(subcategoryName);
					}
				}
			} 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return subcategoryList;
			
		}

	*/
			
	
	public static int searchDefualtCustomCategoryIdByUserId(Connection conn, int userid) {
		String query = "SELECT custom_category_id FROM newscabinet.custom_category WHERE user_id=? and custom_category_name=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userid);
			pstat.setString(2, "전체");
			
			rs = pstat.executeQuery();
				if(rs.next()) {
					//System.out.println(rs.getInt(1));
					return rs.getInt(1);
				}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ResultSet searchCustomcategoryNameByUser(Connection conn, int userId, int categoryId) {
		String query = "SELECT custom_category_name FROM newscabinet.custom_category WHERE user_id=? and category_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setInt(2, categoryId);
			
			rs = pstat.executeQuery();
				if(rs.next()) {
					return rs;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static void insertCustomcategory(Connection conn, int userId, String customCategoryName, int categoryId) {
		
		if(searchCustomcategoryNameByUser(conn, userId, categoryId) != null) {
			ResultSet tmp = searchCustomcategoryNameByUser(conn, userId, categoryId);
			if(tmp!=null) {
				while(true) {
					try {
						if(tmp.next()) {
							if(tmp.getString(1).equals(customCategoryName))
								return;
						}else {
							break;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
			}
		}
		String tmp = customCategoryName.trim();
		if(tmp.length() == 0) {
			return;
		}
		
		String query = "INSERT INTO newscabinet.custom_category (user_id, custom_category_name, category_id) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setString(2, customCategoryName);
			pstat.setInt(3, categoryId);
			pstat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
