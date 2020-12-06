package model;

public class CustomCategoryData {

	int userId;
	int categoryId;
	int customCategoryId;
	String customCategoryName;
	
	public CustomCategoryData() {
		this.userId = 0;
		this.categoryId = 0;
		this.customCategoryId = 0;
		this.customCategoryName="";
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
