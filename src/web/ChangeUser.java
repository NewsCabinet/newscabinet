package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		response.setCharacterEncoding("UTF-8");

		HttpSession userSession = request.getSession();
		int userId = (int) userSession.getAttribute("userId");
		System.out.println("회원정보수정 서블릿");
		String userName = request.getParameter("userName");	
		//String passWd = request.getParameter("userPassword");
		String userEmailId = request.getParameter("userEmailId");
		String userPhone = request.getParameter("userPhone");
		String userAge = request.getParameter("userAge");
		String Sgender = request.getParameter("userGender");
		Boolean userGender = Boolean.parseBoolean(Sgender);
		String Scategory = request.getParameter("category");
		int category = Integer.parseInt(Scategory);
		String userPassword = request.getParameter("userPassword");
		ServletContext sc = getServletContext();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		try {
			ManageUser.updateChangeUser(conn,userName,userPhone,userAge,userGender,category,userPassword,userEmailId);
			int check = ManageCategory.updateFirstCategoryInCustomCategory(conn, userId, category);
			if(check ==1) {
				System.out.println("카테고리가 잘 업데이트되었습니다.");
			}else {
				System.out.println("카테고리가 업데이트 안됨");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HttpSession session = request.getSession(false);
		 session.setAttribute("userName",userName);

		 out.print("<script>alert('회원정보수정 완료되었습니다.'); location.href='../home.jsp'; </script>\r\n");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
