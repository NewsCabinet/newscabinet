package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageScrapNews;

/**
 * Servlet implementation class CancelScrappedNews
 */
@WebServlet("/scrap/cancel")
public class CancelScrappedNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelScrappedNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		
		int newsId = Integer.parseInt((String)request.getParameter("newsId"));
		try {
			int checkCount = ManageScrapNews.updateScrapNewsCountMinus(conn, newsId);
			int checkRemove = ManageScrapNews.removeScrapNewsByUserId(conn, userId, newsId);
			if(checkRemove == 1) {
				System.out.println("스크랩 취소 완료");
				out.print("<script>alert('스크랩 취소되었습니다!'); location.href='/NewsCabinet/news/main'; </script>\r\n");
			}else {
				System.out.println("스크랩 취소 안됨");
				out.print("<script>alert('스크랩 취소가 안되었습니다.!'); location.href='/NewsCabinet/news/main'; </script>\r\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
