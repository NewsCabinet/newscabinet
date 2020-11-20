package web;

import java.io.IOException;
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
import web.ShowCategory;

import model.ManageUser;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = request.getParameter("userid");
		String userPW = request.getParameter("passwd");
		int userCategory;
		String userName; 
		HttpSession session; 
		
		int check = -1;
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		
		ResultSet rs = ManageUser.searchUserByID(conn, userID);
		
		//ResultSet rs = ManageUser.searchUserPasswdByID(conn, userID);
		
		
		if (rs != null) {
			try {
				if (rs.next()) {  // 해당 아이디가 있음
					String checkpw = rs.getString(4);
					if(userPW.equals(checkpw)) { //로그인 성공
						check = 1;
						System.out.println("로그인 성공");
						session = request.getSession();
						userCategory = rs.getInt(2);
						userName = rs.getString(3);
						System.out.println(userID + userCategory + userName);
						session.setAttribute("userId", userID);
						session.setAttribute("userCategory", userCategory);
						session.setAttribute("userName", userName);
						if(session.isNew()) {
							System.out.println("------session 생성됨");
							userCategory = rs.getInt(2);
							userName = rs.getString(3);
							System.out.println(userID + userCategory + userName);
							session.setAttribute("userId", userID);
							session.setAttribute("userCategory", userCategory);
							session.setAttribute("userName", userName);
						}
					}
					else {
						check = 0;
						System.out.println("비밀번호 틀림");
					}
				}
				else{//아이디가 없음 
					check = -1;
					System.out.println("아이디없음");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			} // try end
		} 
		
		request.setAttribute("SignInCheck", check);
		RequestDispatcher view = request.getRequestDispatcher("home.jsp");
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
