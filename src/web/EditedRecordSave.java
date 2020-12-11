package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ManageCategory;
import model.ManageRecord;

/**
 * Servlet implementation class EditedRecordSave
 */
@WebServlet("/UserRecord/record/edit/save")
public class EditedRecordSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditedRecordSave() {
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
		
		ServletContext sc = getServletContext();
		Connection conn= (Connection)sc.getAttribute("DBconnection");
		if(conn == null) {
			System.out.println("DBconnection is null");
		}
		
		PrintWriter out = response.getWriter();
		
		//Date Setting
		Calendar cal = Calendar.getInstance();
		String today = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH) + 1) + "."
				+ cal.get(Calendar.DAY_OF_MONTH);
		request.setAttribute("todayDate", today);

		// DB insert하기 전에 custom category인지, 아닌지 판별
		String firstCategory = request.getParameter("firstCategory");
		int firstCategoryId = Integer.parseInt(firstCategory);
		String firstCategoryName = ManageCategory.searchCategoryIdByCategoryName(conn, firstCategoryId);

		String checkCustomStr = request.getParameter("subCategory");

		int userSelectedCustomCategoryId = ManageCategory.searchCustomCategoryIdByName(conn, userId, checkCustomStr);
		int userSelectedSubCategoryId = -1;

		// custom category일 경우
		if (userSelectedCustomCategoryId != -1) {
			// subcategory는 상위 카테고리와 같은 이름의 subcategory 넣어줘야함
			userSelectedSubCategoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, firstCategoryName);

			if (userSelectedSubCategoryId == -1) {
				System.out.println("WriteRecord | searchSubcatogory 가져오는데 문제생김 : " + firstCategoryName);
			}

		} else { // custom category가 아닌 경우
			// subcategory는 그대로 받아오고
			userSelectedSubCategoryId = ManageCategory.searchSubcatogoryIdBySubcateogoryName(conn, checkCustomStr);

			// custom category는 user_id, "전체"인 custom_category_id 가져와야함
			userSelectedCustomCategoryId = ManageCategory.searchCustomCategoryIdByName(conn, userId, "전체");

		}

		request.setAttribute("recordUserId", userId);
		request.setAttribute("userSelectedSubCategoryId", userSelectedSubCategoryId);
		request.setAttribute("userSelectedCustomCategoryId", userSelectedCustomCategoryId);
		
		int check = ManageRecord.updateUserRecord(conn, request);
		if(check >-1) {
			out.print("<script>alert('기록이 수정되었습니다!'); location.href='/NewsCabinet/UserRecord/main'; </script>\r\n");
		}else {
			out.print("<script>alert('기록이 수정되지 않았습니다!'); location.href='/NewsCabinet/UserRecord/main'; </script>\r\n");		
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
