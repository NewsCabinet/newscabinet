package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DBConnection
 *
 */
@WebListener
public class DBConnection implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBConnection() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	Connection conn= (Connection) sce.getServletContext().getAttribute("DBconnection");
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/**/
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	// 여기에 db connection하는 내용을 넣어두면 된다.
		Connection conn = null;
		Properties connectionProps = new Properties();
		
		ServletContext sc = sce.getServletContext();
		String DBUrl = sc.getInitParameter("JDBCUrl");
		String DBUser = sc.getInitParameter("DBuser");
		String DBpasswd = sc.getInitParameter("DBpasswd");
		String DBTimeZone = sc.getInitParameter("DBTimeZone");/**/
		String NaverAPIUrl = sc.getInitParameter("NaverAPIUrl");
		String NaverClientId = sc.getInitParameter("X-Naver-Client-Id");
		String NaverClientSecret = sc.getInitParameter("X-Naver-Client-Secret");
		
		connectionProps.put("user", DBUser);
		connectionProps.put("password", DBpasswd);
		connectionProps.put("serverTimezone", DBTimeZone);
		
		try {
			conn = DriverManager.getConnection(DBUrl, connectionProps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sc.setAttribute("DBconnection", conn); // 이 DBconnection을 얻으면 바로 커넥션해서 사용 가능./**/
		sc.setAttribute("NaverAPIUrl", NaverAPIUrl);
		sc.setAttribute("X-Naver-Client-Id", NaverClientId);
		sc.setAttribute("X-Naver-Client-Secret", NaverClientSecret);
    }
	
}
