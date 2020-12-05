package model;


public class UserScrapNews {

	int newsId;
	String headline;
	int subCategoryId;
	String subCategoryName;
	String newsURL;


	public UserScrapNews() {
		newsId = 0;
		headline = "";
		subCategoryId = 0;
		subCategoryName = "";
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	
	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getNewsURL() {
		return this.newsURL;
	}

	public void setNewsURL(String newsURL) {
		this.newsURL = newsURL;
	}
	
	
}
