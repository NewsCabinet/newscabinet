  
package web;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.ManageUser;



/**
 * Servlet implementation class DoJoin
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String userEmailId = request.getParameter("userEmailId");
		String categoryStr[] = request.getParameterValues("category");
		int category = 0;
		int userIdinDB = -1;
		PrintWriter out = response.getWriter();
		for(String str : categoryStr) {
			category = Integer.parseInt(str);
		}
		
		String todayDate = null;
		ServletContext sc = getServletContext();
		

		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("conn is nul");
		}
		
		
		int result = ManageUser.insertUser(conn, request);
		int folder = -1;
		int custom = -1;
		
		try {
			if(result == 0) {
				out.print("<script>alert('중복된 아이디입니다. 다시 작성해주세요'); location.href='signUp.jsp'; </script>\\r\\n");
			}else if (result != -1) {
				System.out.println("입력 성공");
				userIdinDB = ManageUser.searchUserIDByEmail(conn, userEmailId);
				System.out.println("userID = " + userIdinDB);
				
				if(userIdinDB != -1) {
					folder = ManageUser.insertFirstUserFolderByID(conn, userIdinDB);
					custom = ManageUser.insertFirstUserCustomCategoryByID(conn, userIdinDB, category);
				}
				
				if(folder != -1 && custom != -1) {
					System.out.println("folder, custom table 입력 성공");
					RequestDispatcher view = request.getRequestDispatcher("index.html");
					view.forward(request, response);
					
				}
			}
			
		}catch(Exception e){
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}