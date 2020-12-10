package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageRecord;
import model.UserFolderData;

/**
 * Servlet implementation class updateFolder
 */
@WebServlet("/UserRecord/main/folder/management")
public class UpdateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFolder() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		
		ResultSet rs = ManageRecord.searchFolderNameByUserId(conn, userId);
		ArrayList<UserFolderData> userForderList = new ArrayList<UserFolderData>();
		
		if (rs != null) {
			try {
				while (rs.next()) {
					String folderName = rs.getString(1);
					int folderId = rs.getInt(2);

					UserFolderData tmp = new UserFolderData();
					tmp.setFolderId(folderId);
					tmp.setFolderName(folderName);
					tmp.setUserId(userId);
					userForderList.add(tmp);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("resultUserFolder Problem");
		}
		
		request.setAttribute("folders", userForderList);
		
		
		String[] addfolder = request.getParameterValues("folder");
		
		if(addfolder != null) {
			int tuple = 0;
			for(int i = 0; i < addfolder.length; i++) {
				System.out.println("folder "+addfolder[i]);
				addfolder[i] = addfolder[i].trim();
				if(addfolder[i]!=null) {
					System.out.println("add folder "+addfolder[i]);
					tuple = ManageRecord.insertFolderUsingFolderName(conn, userId, addfolder[i]);	
				}
			}
			if(tuple == addfolder.length) {
				System.out.println("folder가 잘 추가되었습니다.");
			}else {
				System.out.println("folder가 추가 안 되었습니다..");
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher("../../../Record/user/manageFolder.jsp");
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
