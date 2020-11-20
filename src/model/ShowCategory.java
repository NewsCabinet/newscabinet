package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Servlet implementation class NewsLook_getCategory
 */
@WebServlet("/newscategory")
public class ShowCategory extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCategory() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Vector vec = new Vector();
		//String customCategoryName = request.getParameter("");

		ServletContext sc = getServletContext();
		Connection conn= (Connection) sc.getAttribute("DBconnection");
		
		ResultSet rs = ManageCategory.GetSubCategoryName(conn, "");
		PrintWriter out = response.getWriter();

	
		if(rs != null) {
			try{
				while(true) {
				if(rs.next()) { // existing user
						String subCategory = rs.getString(1);
						System.out.println(subCategory);
						vec.add(subCategory);
						request.setAttribute("subcategory",vec);
				}
				else { // invalid user
					out.print("no data");
					break;
				}
			}
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/test.jsp");
				rd.forward(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			} //try end
		}

		} // if end

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
