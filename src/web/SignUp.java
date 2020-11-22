  
package web;


import java.io.IOException;
import java.sql.Connection;

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
		String userId = request.getParameter("userid");
		String categoryStr[] = request.getParameterValues("category");
		int category = 0;
	
		for(String str : categoryStr) {
			category = Integer.parseInt(str);
		}
		
		System.out.println(userId + category);
		
		
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("conn is nul");
		}
		
		
		int result = ManageUser.insertUser(conn, request);
		
		try {
			if (result != -1) {
				System.out.println("입력 성공");
				
				int folder = ManageUser.insertFirstUserFolderByID(conn, userId);
				//int custom = ManageUser.insertFirstUserCustomCategoryByID(conn, userId, category);
				int custom = ManageUser.insertFirstUserCustomCategoryByID(conn, userId, category);
				
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