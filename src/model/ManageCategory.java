package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManageCategory {
	
	
	public static ResultSet searchAllCategoryAndSubCategory(Connection conn) {
		String sqlSt = "select category_name, subcategory_name from newscabinet.category join newscabinet.subcategory "
						+"on newscabinet.category.category_id = newscabinet.subcategory.category_id";
		Statement st = null;
		try { 
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	public static String searchCategoryIdByCategoryName(Connection conn, int categoryId) {

		String sqlSt = "SELECT category_name FROM newscabinet.category WHERE category_id=" +categoryId;
		Statement st;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			if (st.execute(sqlSt)) {
				rs = st.getResultSet();
				if(rs.next())
					return rs.getString(1); 
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
		String sqlSt = "SELECT subcategory_id FROM newscabinet.subcategory WHERE subcategory_name='" +subcategoryName + "'";
		
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

	public static int searchCustomcategoryIdByUserAndCustomcategoryName(Connection conn, int userId, String customCategoryName) {
		String query = "SELECT custom_category_id FROM newscabinet.custom_category WHERE user_id=? and custom_category_name=? ";
		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setString(2, customCategoryName);
			
			rs = pstat.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
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

	
	public static int searchCustomCategoryIdByName(Connection conn, int userId, String customCategoryName) {
		String query = "SELECT custom_category_id FROM newscabinet.custom_category WHERE user_id="
						+"'"+ userId +"'" + "and custom_category_name='" +customCategoryName + "'";
		
		int result = -1;
		
		Statement st;
		ResultSet rs;
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {
					int userCustomCategoryId = rs.getInt(1);
					result = userCustomCategoryId;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public static ResultSet searchAllFirstCateogry(Connection conn) {
		String query = "SELECT category_id, category_name FROM newscabinet.category";
		ResultSet rs = null;
		Statement st = null;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				 if(rs != null)
					return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public static ResultSet searchAllSubCateogry(Connection conn) {
		String query = "SELECT category_id, subcategory_id, subcategory_name FROM newscabinet.subcategory";
		ResultSet rs = null;
		Statement st = null;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				
				if(rs != null)
					return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet searchAllUserCustomCateogry(Connection conn, int userId) {
		String query = "SELECT category_id, custom_category_id, custom_category_name FROM newscabinet.custom_category WHERE user_id='" + userId + "'";
		ResultSet rs = null;
		Statement st = null;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				
				if(rs != null)
					return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
