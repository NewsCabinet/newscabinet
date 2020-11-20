  
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
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("conn is nul");
		}
		
		int result = ManageUser.insertUser(conn, request);
		
		//¾ÆÀÌµð Áßº¹ È®ÀÎ ÄÚµå ÀÛ¼º


		//user Á¤º¸ DB¿¡ ³Ö±â
		
		try {
			if (result != -1) {
<<<<<<< HEAD:src/web/DoJoin.java
			System.out.println("ÀÔ·Â ¼º°ø");
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
=======
				System.out.println("ìž…ë ¥ ì„±ê³µ");
				RequestDispatcher view = request.getRequestDispatcher("index.html");
				view.forward(request, response);
>>>>>>> main:src/web/SignUp.java
			}
			
		}catch(Exception e){
			
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