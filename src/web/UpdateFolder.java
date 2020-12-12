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

import model.ManageCategory;
import model.ManageRecord;
import model.UserFolderData;

/**
 * Servlet implementation class updateFolder
 */
@WebServlet("/record/user/folder/manage")
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
					if(!folderName.equals("default")) {
						UserFolderData tmp = new UserFolderData();
						tmp.setFolderId(folderId);
						tmp.setFolderName(folderName);
						tmp.setUserId(userId);
						userForderList.add(tmp);
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("resultUserFolder Problem");
		}
		
		request.setAttribute("folders", userForderList);
		
		
		String newFolder = request.getParameter("folder");
		
		if(newFolder != null) {
			int check =  ManageRecord.insertFolderUsingFolderName(conn, userId, newFolder);	
			System.out.println("folder :" + newFolder);
			if(check >-1) {
				System.out.println("folder가 잘 추가되었습니다.");
			}else {
				System.out.println("folder가 추가 안 되었습니다..");
			}
		}
		
		String[] removeFolders = request.getParameterValues("removeFolders");
		
		if(removeFolders != null) {
			for(int i = 0; i<removeFolders.length; i++) {
				int folderId = Integer.parseInt(removeFolders[i]);
				int check = ManageRecord.removeFolderByFolderId(conn, userId, folderId);
				if(check == 1) {
					System.out.println("삭제되었습니다.");
				}else {
					System.out.println("삭제가 안되었습니다.");
				}
			}			
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/manageFolder.jsp");
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
