package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HandlingNews;
import model.NewsData;

/**
 * Servlet implementation class GetNewsData
 */
@WebServlet("/news/main")
public class NewsCatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsCatching() {
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
		response.setContentType("text/xml");
		
		ServletContext sc = getServletContext();
		String url = (String) sc.getAttribute("NaverAPIUrl");
		String clientId = (String) sc.getAttribute("X-Naver-Client-Id");
		String clientPW = (String) sc.getAttribute("X-Naver-Client-Secret");
		
		String subCategory = request.getParameter("subCategory");
		request.setAttribute("subCategory", subCategory);
		if(subCategory == null) {subCategory = "IT";}
		
		String newsType = request.getParameter("newsType");
		if(newsType == null) {newsType = "sim";}
		request.setAttribute("newsType", newsType);
		
		HandlingNews gn = new HandlingNews();
		NewsData[] newsdata = gn.getNewsFromOpenAPI(url, clientId, clientPW, subCategory, newsType);
		request.setAttribute("newsdata", newsdata);
		sc.setAttribute("newsdata", newsdata);
		RequestDispatcher view = request.getRequestDispatcher("../news.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
