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
import model.SubcategoryData;

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String userEmailId = request.getParameter("userEmailId");
		String userPW = request.getParameter("passwd");
		int userIdinDB = -1;
		int userCategory;
		String userName; 
		HttpSession session; 
		
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		
		ResultSet rs = ManageUser.searchUserByEmailId(conn, userEmailId);
		PrintWriter out = response.getWriter();
		
		
		
		if (rs != null) {
			try {
				if (rs.next()) {  // 해당 아이디가 있음
					String checkpw = rs.getString(1);
					userIdinDB = rs.getInt(2);
					userCategory = rs.getInt(3);
					userName = rs.getString(4);
					//System.out.println("pw : "+ checkpw + " : "+userIdinDB + " : " +  userCategory+ " : " + userName);
					if(userPW.equals(checkpw)) { //로그인 성공
						System.out.println("로그인 성공");
						session = request.getSession();
						
						//System.out.println(userIdinDB + " : " +  userCategory+ " : " + userName);
						session.setAttribute("userId", userIdinDB);
						session.setAttribute("userCategoryId", userCategory);
						session.setAttribute("userName", userName);
						
					
						if(session.isNew()) {
							System.out.println("------session 생성됨");
							System.out.println(userIdinDB + " : " +  userCategory+ " : " + userName);
							session.setAttribute("userId", userIdinDB);
							session.setAttribute("userCategoryId", userCategory);
							session.setAttribute("userName", userName);
						}
						
						// insert subcategory data into the servlet context
						int userCategoryId = (int) session.getAttribute("userCategoryId");
						int size = ManageCategory.searchCountSubCategory(conn, userCategoryId);
						
						try {
							ResultSet resultSet = ManageCategory.searchSubCategoryName(conn, userCategoryId);
							SubcategoryData[] subcateData = new SubcategoryData[size];
							if(resultSet!= null) {
								int count = 0;
								while(true) {
									if(resultSet.next()) {
										subcateData[count++] = new SubcategoryData(resultSet.getInt(1),resultSet.getString(2));
									}else {
										break;
									}
								}
								
							}
							sc.setAttribute("subcateData", subcateData); // 화면에 보여줄 subCategoryData
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						out.print("<script>alert('로그인 성공! 환영합니다'); location.href='home.jsp'; </script>\r\n");
						//RequestDispatcher view = request.getRequestDispatcher("/home.jsp");
						//view.forward(request, response);
					}
					else {
						System.out.println("비밀번호 틀림");
						out.print("<script>alert('비밀번호가 틀렸습니다. 다시 로그인해주세요'); location.href='index.html'; </script>\r\n");
					}
				}
				else{//아이디가 없음 
					System.out.println("아이디없음");
					out.print("<script>alert('아이디가 없습니다. 다시 로그인해주세요'); location.href='index.html'; </script>\r\n");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			} // try end
		} 

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}