package model;


public class UserScrapNewsData {

	int newsId;
	String headline;
	String newsURL;
	String description;
	String publishedDate;
	int scrapCount;
	int subCategoryId;
	String subCategoryName;
	

	public UserScrapNewsData() {
		newsId = 0;
		headline = "";
		newsURL="";
		description = "";
		publishedDate = "";
		scrapCount = 0;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public int getScrapCount() {
		return scrapCount;
	}

	public void setScrapCount(int scrapCount) {
		this.scrapCount = scrapCount;
	}
	
}
