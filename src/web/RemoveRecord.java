package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageRecord;

/**
 * Servlet implementation class RemoveRecord
 */
@WebServlet("/record/user/remove")
public class RemoveRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveRecord() {
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
		HttpSession userSession = request.getSession(false);
		int userId = (int) userSession.getAttribute("userId");
		PrintWriter out = response.getWriter();
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		
		String tmp = (String) request.getParameter("id");
		int recordId = Integer.parseInt(tmp);
		
		int check = ManageRecord.removeRecordByRecordId(conn, userId, recordId);
		if(check >-1) {
			out.print("<script>alert('기록이 삭제되었습니다!'); location.href='/NewsCabinet/record/user/folder'; </script>\r\n");
		}else {
			out.print("<script>alert('기록이 삭제되지 않았습니다!'); location.href='/NewsCabinet/record/user/folder'; </script>\r\n");
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
