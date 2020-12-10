package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageScrapNews {
	
	public static int searchScrapNewsIdByUrl(Connection conn, String newsUrl) {
		String query = "SELECT news_id from newscabinet.scrap_news WHERE url=?";
		ResultSet rs = null;
		int result = -1;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setString(1, newsUrl);
			rs = pstat.executeQuery();
				if(rs.next()) {
					//System.out.println(rs.getInt(1));
					return rs.getInt(1);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static boolean searchScrapNewsAndUserByNewsId(Connection conn, int newsId, int userId) {
		String query = "SELECT news_id from newscabinet.user_scrap_news WHERE news_id=? and user_id=?";
		ResultSet rs = null;
		int result = -1;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, newsId);
			pstat.setInt(2, userId);
			rs = pstat.executeQuery();
				if(rs.next()) {
					//System.out.println(rs.getInt(1));
					return true;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	
	
	
	public static ResultSet searchScrapNewsByNewsId(Connection conn, int newsId) {
		String query = "SELECT scrap_news.subcategory_id, subcategory_name, headline, url " 
					+ "FROM newscabinet.scrap_news "
					+ "JOIN newscabinet.subcategory ON scrap_news.subcategory_id = subcategory.subcategory_id "
					+ "WHERE news_id='" + newsId + "'";
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static ResultSet searchScrapNewsDataByNewsId(Connection conn, int newsId) {
		String query = "SELECT news_id, scrap_news.subcategory_id, subcategory_name, "
					+ "headline, url, published_date, description, scrap_count " 
					+ "FROM newscabinet.scrap_news "
					+ "JOIN newscabinet.subcategory ON scrap_news.subcategory_id = subcategory.subcategory_id "
					+ "WHERE news_id=" + newsId ;
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//스크랩순 top 10 기록 가져오기
	public static ResultSet searchScrapNewsIdTop10(Connection conn) {
		String query = "SELECT news_id FROM newscabinet.scrap_news ORDER BY scrap_count DESC LIMIT 10";
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	
	
	public static ResultSet searchAllScrapNewsByUserId(Connection conn, int userId) {

		String query = "SELECT * FROM newscabinet.scrap_news JOIN newscabinet.user_scrap_news"
				+ " ON newscabinet.scrap_news.news_id = newscabinet.user_scrap_news.news_id"
				+ " WHERE user_id=?";
		ResultSet rs = null;
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			rs = pstat.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public static ResultSet searchUserScrapNewsByUserId(Connection conn, int userId) {

		String query = "SELECT news_id, custom_category_id FROM newscabinet.scrap_news "
				+ " WHERE user_id=" + userId;
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static ResultSet searchAllUserScrapNewsForRecord(Connection conn, int userId) {

		String query = "SELECT user_scrap_news.news_id, headline, subcategory_id, url FROM newscabinet.scrap_news JOIN newscabinet.user_scrap_news"
				+ " ON newscabinet.scrap_news.news_id = newscabinet.user_scrap_news.news_id"
				+ " WHERE user_id='" + userId + "'";
		Statement st;
		
		try {
			st = conn.createStatement();
			if(st.execute(query)) 
				return st.getResultSet();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ResultSet searchScrapNewsByUserIdAndCategory(Connection conn, int userId, int subCategoryId) {
	
		String query = "SELECT * FROM newscabinet.scrap_news JOIN newscabinet.user_scrap_news"
				+ " ON newscabinet.scrap_news.news_id = newscabinet.user_scrap_news.news_id"
				+ " WHERE user_id=? and subcategory_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, userId);
			pstat.setInt(2, subCategoryId);
			rs = pstat.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static int searchScrapCountByUrl(Connection conn, String newsUrl) {

		String query = "SELECT scrap_count from newscabinet.scrap_news WHERE url=?";
		ResultSet rs = null;
		int result = -1;
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setString(1, newsUrl);
			rs = pstat.executeQuery();
				if(rs.next()) {
					//System.out.println(rs.getInt(1));
					return rs.getInt(1);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String insertScrapNewsData(Connection conn, int subcategoryId, NewsData newsData) throws SQLException {
		//searchScrapNewsIdByURL(conn, newsData.getUrl()).next() == true || 
		if (searchScrapNewsIdByUrl(conn, newsData.getUrl()) != -1) {
			int scrapCount = searchScrapCountByUrl(conn, newsData.getUrl());
			// UPDATE 테이블명 SET 칼럼명 = '내용' WHERE 조건문
			String query = "UPDATE newscabinet.scrap_news SET scrap_count = ? where url = ?";
			
			try {
				PreparedStatement pstat = conn.prepareStatement(query);
				pstat.setInt(1, ++scrapCount);
				pstat.setString(2, newsData.getUrl());

				pstat.executeUpdate();
				
				return newsData.getUrl();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		//System.out.println(newsData.getUrl() + "out of the method");
		String query = "INSERT INTO newscabinet.scrap_news (subcategory_id, headline, published_date, description, url, scrap_count) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstat = conn.prepareStatement(query);
			pstat.setInt(1, subcategoryId);
			pstat.setString(2, newsData.getHeadline());
			pstat.setString(3, newsData.getPubDate());
			pstat.setString(4, newsData.getDescription());
			pstat.setString(5, newsData.getUrl());
			pstat.setInt(6, 1); // init scrapCount 1

			pstat.executeUpdate();
			
			return newsData.getUrl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void insertScrapNewsRelationWithUser(Connection conn, int newsId, int userId, int customCategoryId) {
		
		if (searchScrapNewsAndUserByNewsId(conn, newsId, userId)) {
			return;
		}
		
		String queryWithoutCustom = "INSERT INTO newscabinet.user_scrap_news (user_id, news_id) VALUES(?, ?)";
		String queryWithCustom = "INSERT INTO newscabinet.user_scrap_news (user_id, news_id, custom_category_id) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement pstat = null;
			if(customCategoryId == 0 ) {
				pstat = conn.prepareStatement(queryWithoutCustom);
				pstat.setInt(1, userId);
				pstat.setInt(2, newsId);
			}else {
				pstat = conn.prepareStatement(queryWithCustom);
				pstat.setInt(1, userId);
				pstat.setInt(2, newsId);
				pstat.setInt(3, customCategoryId);
			}
			pstat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}