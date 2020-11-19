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

import model.DataParsing;
import model.GetNews;
import model.NewsData;

/**
 * Servlet implementation class GetNewsData
 */
@WebServlet("/news/getNews")
public class GetNewsData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNewsData() {
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
		
		String newsType = request.getParameter("newsType");
		if(newsType == null) {newsType = "sim";}
		
		GetNews gn = new GetNews();
		NewsData[] newsdata = gn.getNewsFromOpenAPI(url, clientId, clientPW, newsType);
		request.setAttribute("newsdata", newsdata);
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
