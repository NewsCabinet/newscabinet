package model;

public class RecordData {

	int userId;
	String userName;
	int recordId;
	String firstCategoryName;
	int subcategoryId;
	String subcategoryName;
	String recordTitle;
	String recordDate;
	String recordComment;
	int recordCount;
	
	public RecordData() {
		this.userId = 0;
		this.userName = "";
		this.firstCategoryName = "";
		this.subcategoryId = 0;
		this.subcategoryName = "";
		this.recordId = 0;
		this.recordTitle = "";
		this.recordDate = "";
		this.recordCount = 0;
	}
	
	public RecordData(int userId, int recordId, String recordTitle, String recordDate, int recordCount) {
		super();
		this.userId = userId;
		this.userName =  "";
		this.recordId = recordId;
		this.firstCategoryName = "";
		this.subcategoryId = 0;
		this.subcategoryName = "";
		this.recordTitle = recordTitle;
		this.recordDate = recordDate;
		this.recordCount = recordCount;
	}
	

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getRecordTitle() {
		return recordTitle;
	}
	public void setRecordTitle(String recordTitle) {
		this.recordTitle = recordTitle;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}


	public String getSubcategoryName() {
		return subcategoryName;
	}


	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	
	
	public String getRecordComment() {
		return recordComment;
	}


	public void setRecordComment(String recordComment) {
		this.recordComment = recordComment;
	}
	

	
	public String getFirstCategoryName() {
		return firstCategoryName;
	}


	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}
	
	
	public void printRecord() {
		System.out.println("------------------------");
		System.out.println("userId = " + userId);
		System.out.println("userName = " + userName);
		System.out.println("recordId = " + recordId);
		System.out.println("firstCategoryName = " + firstCategoryName);
		System.out.println("subcategoryId = " + subcategoryId);
		System.out.println("subcategoryName = " + subcategoryName);
		System.out.println("recordTitle = " + recordTitle);
		System.out.println("recordDate = " + recordDate);
		System.out.println("recordComment = " + recordComment);
		System.out.println("recordCount = " + recordCount);
		
	}

}
