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
import model.RecordData;

/**
 * Servlet implementation class DisplayRecordListForFolder
 */
@WebServlet("/record/user/folder/list")
public class DisplayRecordListForFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayRecordListForFolder() {
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
		

		
		String tmp = (String) request.getParameter("folderId");
		int folderId;
		if(tmp==null) {
			folderId=0;
		}else {
			folderId = Integer.parseInt(tmp);
		}
		
		String folderName = (String) ManageRecord.searchFolderNameByFolderId(conn, userId, folderId);
		request.setAttribute("folderName", folderName);
		
		ResultSet rs = ManageRecord.searchRecordByUserIdAndFolderId(conn, userId, folderId);
		ArrayList<RecordData> recordList = new ArrayList<RecordData>();
		try {
			if (rs != null) {
				while (rs.next()) {
					int recordId = rs.getInt("record_id");
					String recordTitle = rs.getString("record_title");
					String recordDate = rs.getString("record_date");
					int recordCount = rs.getInt("record_count");
					int recordSubcatergoryId = rs.getInt("subcategory_id");
					String recordSubcatergoryName = ManageCategory.searchSubcatogoryNameBySubcateogoryId(conn, recordSubcatergoryId);

					RecordData record = new RecordData();
					record.setSubcategoryId(recordSubcatergoryId);
					record.setSubcategoryName(recordSubcatergoryName);
					record.setRecordId(recordId);
					record.setRecordTitle(recordTitle);
					record.setRecordDate(recordDate);
					record.setRecordCount(recordCount);
					recordList.add(record);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("recordData", recordList);
		
		RequestDispatcher view = request.getRequestDispatcher("/Record/user/recordListForFolder.jsp");
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
