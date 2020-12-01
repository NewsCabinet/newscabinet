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

import model.ManageCategory;
import model.ManageUser;

/**3
 * Servlet implementation class ChangeUser
 */
@WebServlet("/user/changeuser")
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
		HttpSession httpSession = request.getSession();
		System.out.println("회원정보수정 서블릿");
		String userName = request.getParameter("userName");	
		String passWd = request.getParameter("userPassword");
		String userEmailId = request.getParameter("userEmailId");
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		if( userName!= null) {
	    System.out.println("성공");
	    
		 ManageUser.updateChangeUser(conn,userEmailId,userName);
		 
		 //session에서 받아온걸 attribute
		 request.setAttribute("id", userName);
		 RequestDispatcher view = sc.getRequestDispatcher("/home.jsp");
         view.forward(request, response);	
		}		
		
	else {
		System.out.println("이름이 없습니다.");
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
