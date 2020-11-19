package model;

public class NewsData {

	public NewsData(String headline, String url, String description, String pubDate) {
		super();
		this.headline = headline;
		this.url = url;
		this.description = description;
		this.pubDate = pubDate;
	}

	private String headline;
	private String url;	
	private String description;
	private String pubDate;
	
	public String getHeadline() {
		return headline;
	}
	
	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	private void refineDate(String pubDate) {
		
	}
	
}
