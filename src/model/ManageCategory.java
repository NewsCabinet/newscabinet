package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManageCategory {
	
	
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
	
	public static int searchUserFirstCategoryIdByUserId(Connection conn, int userId) {
		String query = "SELECT category_id FROM newscabinet.user WHERE user_id ='" + userId + "'" ; 
		Statement st;
		ResultSet rs;

		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
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
	
	
	public static String searchFirstCategoryNameBySubcategoryId(Connection conn, int subcategoryId) {
		String query = "SELECT category_name FROM newscabinet.subcategory " 
					+ "JOIN newscabinet.category ON subcategory.category_id = category.category_id " 
					+ "WHERE subcategory_id ='" +  subcategoryId + "'" ; 
		
		Statement st;
		ResultSet rs;

		try {
			st = conn.createStatement();
			if(st.execute(query)) {
				rs = st.getResultSet();
				if(rs.next()) {
					return rs.getString(1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	

	
	public static int searchDefaultCustomCategoryIdByUserId(Connection conn, int userid) {
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
	
	
	public static ResultSet searchCustomCategoryDataByCustomId(Connection conn, int customCategoryId) {
		String query = "SELECT custom_category.category_id, category_name, custom_category_name "
				+ "FROM newscabinet.custom_category, newscabinet.category " 
				+ "WHERE category.category_id = custom_category.category_id " 
				+ "AND custom_category.custom_category_id =" + customCategoryId;
		Statement st;
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
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
	
	public static int updateFirstCategoryInCustomCategory(Connection conn, int userId, int categoryId) {
		String query = "UPDATE newscabinet.custom_category SET category_id=? WHERE user_id=? and custom_category_name='전체'";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, categoryId);
			pstat.setInt(2, userId);
			int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int updateCustomCategoryInUserRecord(Connection conn, int userId, int customCategoryId, int defaultCustomCategoryId) {
		String query = "UPDATE newscabinet.user_record SET custom_category_id=? WHERE user_id=? and custom_category_id=?";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, defaultCustomCategoryId);
			pstat.setInt(2, userId);
			pstat.setInt(3, customCategoryId);
			int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int updateCustomCategoryInUserScrapNews(Connection conn, int userId, int customCategoryId, int defaultCustomCategoryId) {
		String query = "UPDATE newscabinet.user_scrap_news SET custom_category_id=? WHERE user_id=? and custom_category_id=?";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, defaultCustomCategoryId);
			pstat.setInt(2, userId);
			pstat.setInt(3, customCategoryId);
			int result = pstat.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int removeCustomCategoryByCustomCategoryName(Connection conn, int userId, String customCategoryName) {
		int defaultCustomCategoryId = searchDefaultCustomCategoryIdByUserId(conn, userId);
		int customCategoryId = searchCustomCategoryIdByName(conn, userId, customCategoryName);
		int checkRecord = -1;
		int checkScrap = -1;
		checkRecord = updateCustomCategoryInUserRecord(conn, userId, customCategoryId, defaultCustomCategoryId);
		checkScrap = updateCustomCategoryInUserScrapNews(conn, userId, customCategoryId, defaultCustomCategoryId);
		
		System.out.println("checkRecord : " + checkRecord + " checkScrap : "+checkScrap);
		
		if(checkRecord >-1 && checkScrap > -1) {
			String query = "DELETE FROM newscabinet.custom_category where custom_category_id=?";
			
			try {
				PreparedStatement pstat = conn.prepareStatement(query);
				pstat.setInt(1, customCategoryId);
				int result = pstat.executeUpdate();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else if(checkRecord ==1 ){
			System.out.println("스크랩이 업데이트 안됨");
		}else if(checkScrap ==1 ){
			System.out.println("기록이 업데이트 안됨");
		}else {
			System.out.println("다 안됨");
		}
		
		return -1;
	}
}
