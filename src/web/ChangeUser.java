package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageUser;

/**
 * Servlet implementation class ChangeUser
 */
@WebServlet("/changeuser")
public class ChangeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");	
		String userEmailId = request.getParameter("userEmailId");
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		ResultSet rs = ManageUser.updateChangeUser(conn, userEmailId, userName);
/*		HttpSession httpSession = request.getSession();
		String user_id = request.getParameter("user_id");
		String userEmailId = request.getParameter("userEmailId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");	
		String userPhone = request.getParameter("userPhone");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String category = request.getParameter("category");*/
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
