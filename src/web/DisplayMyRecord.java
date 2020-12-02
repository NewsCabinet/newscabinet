package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageRecord;

/**
 * Servlet implementation class DisplayMyRecord
 */
@WebServlet("/UserRecord/main")
public class DisplayMyRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayMyRecord() {
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
		
		ResultSet rs = ManageRecord.searchFolderNameByUserId(conn, userId);
		request.setAttribute("folders", rs);
		
		String tmp = (String) request.getParameter("folderId");
		int folderId;
		if(tmp==null) {
			folderId=0;
		}else {
			folderId = Integer.parseInt(tmp);
		}
		rs = ManageRecord.searchRecordByUserIdAndFolderId(conn, userId, folderId);
		request.setAttribute("recordData", rs);
		
		RequestDispatcher view = request.getRequestDispatcher("../Record/user/recordMainPage.jsp");
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