package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageCategory;

/**
 * Servlet implementation class UpdateCustomCategory
 */
@WebServlet("/news/customCategory/management")
public class UpdateCustomCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomCategory() {
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
		response.setContentType("text/xml");
		
		ServletContext sc = getServletContext();
		String url = (String) sc.getAttribute("NaverAPIUrl");
		String clientId = (String) sc.getAttribute("X-Naver-Client-Id");
		String clientPW = (String) sc.getAttribute("X-Naver-Client-Secret");
		
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		HttpSession userSession = request.getSession(false);
		int userCategoryId = (int) userSession.getAttribute("userCategoryId");
		int userId = (int) userSession.getAttribute("userId");
		
		List tmp = new ArrayList<String>();
		try {
			ResultSet customCategoryArray = ManageCategory.searchCustomcategoryNameByUser(conn, userId, userCategoryId);	
			if(customCategoryArray!=null) {
				while(true) {
					if(customCategoryArray.next()) {
						tmp.add(customCategoryArray.getString(1));
					}else {
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("customCategories", tmp);
		
		String[] removeKeyword = request.getParameterValues("removeCategory");
		
		if(removeKeyword != null) {
			for(int i = 0; i<removeKeyword.length; i++) {
				int check = ManageCategory.removeCustomCategoryByCustomCategoryName(conn, userId, removeKeyword[i]);
				if(check == 1) {
					System.out.println("삭제되었습니다.");
				}else {
					System.out.println("삭제가 안되었습니다.");
				}
			}			
		}

		
		
		RequestDispatcher view = request.getRequestDispatcher("../../News/manageCustomCategory.jsp");
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
