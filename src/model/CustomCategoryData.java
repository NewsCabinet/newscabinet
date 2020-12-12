package model;

public class CustomCategoryData {

	int userId;
	int firstCategoryId;
	String firstCategoryName;
	int customCategoryId;
	String customCategoryName;
	
	public CustomCategoryData() {
		this.userId = 0;
		this.firstCategoryId = 0;
		this.firstCategoryName = "";
		this.customCategoryId = 0;
		this.customCategoryName="";
	}
	
	public void print() {
		System.out.println("-------------------------");
		System.out.println("userId = " + userId);
		System.out.println("firstCategoryId = " + firstCategoryId);
		System.out.println("firstCategoryName = " + firstCategoryName);
		System.out.println("customCategoryId = " + customCategoryId);
		System.out.println("customCategoryName = " + customCategoryName);
		System.out.println("-------------------------");
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getFirstCategoryId() {
		return firstCategoryId;
	}
	
	public void setFirstCategoryId(int firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}
	
	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}
	
	public int getCustomCategoryId() {
		return customCategoryId;
	}
	public void setCustomCategoryId(int customCategoryId) {
		this.customCategoryId = customCategoryId;
	}
	public String getCustomCategoryName() {
		return customCategoryName;
	}
	public void setCustomCategoryName(String customCategoryName) {
		this.customCategoryName = customCategoryName;
	}

	
	
	
}
