package model;

public class RecordData {
	int userId;
	String userName;
	int recordId;
	int subcategoryId;
	String recordTitle;
	String recordDate;
	int recordCount;
	
	public RecordData() {
		this.userId = 0;
		this.userName = "";
		this.recordId = 0;
		this.recordTitle = "";
		this.recordDate = "";
		this.recordCount = 0;
		this.subcategoryId = 0;
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
	
	
	

}
