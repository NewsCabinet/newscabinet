package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ManageCategory;
import model.NewsData;

/**
 * Servlet implementation class WriteRecrod
 */
@WebServlet("/UserRecord/restore")
public class WriteRecrod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		NewsData[] nd = (NewsData[]) sc.getAttribute("newsdata");		
		int location = Integer.parseInt((String)request.getParameter("location"));
		
		try {
			String newsUrl = scrapNews.insertScrapNewsData(conn, subcategoryId, nd[location]);
			int newsId = scrapNews.searchScrapNewsIdByURL(conn, newsUrl).getInt(1);
			받아온 newsUrl을 통해 해당 url을 가지는 news의 newsid를 찾은 후, 해당 news의 subcategory도 받아와서 보여주면 된다.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
