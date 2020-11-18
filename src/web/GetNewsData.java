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

/**
 * Servlet implementation class GetNewsData
 */
@WebServlet("/getNewsData")
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
		PrintWriter out = response.getWriter();
		
		String keyword = "IT";
		ServletContext sc = getServletContext(); 
		
		BufferedReader br = null;
		try {
			String urlstr = sc.getAttribute("NaverAPIUrl")+"?query=" +  keyword + "&display=10&start=1&sort=sim";
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			urlconnection.setRequestProperty("X-Naver-Client-Id", (String) sc.getAttribute("X-Naver-Client-Id"));            
			urlconnection.setRequestProperty("X-Naver-Client-Secret", (String) sc.getAttribute("X-Naver-Client-Secret"));
			
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			//out.append(result);
			DataParsing dp = new DataParsing();
			dp.getXmlData(result);
			List title = dp.getTitle();
			request.setAttribute("title", title);
			RequestDispatcher view = request.getRequestDispatcher("news.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			//response.getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><header><resultCode>-999</resultCode><resultMsg>�븣 �닔 �뾾�뒗 �삤瑜�</resultMsg></header></response>");
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
