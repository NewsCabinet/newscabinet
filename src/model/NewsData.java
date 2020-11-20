package model;

public class NewsData {

	public NewsData(String headline, String url, String description, String pubDate) {
		super();
		this.headline = headline;
		this.url = url;
		this.description = description;
		this.pubDate = pubDate;

		refineDate(this.pubDate);
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
	
	private void refineDescription(String description) {
		
	}
	
	private void refineDate(String pubDate) {
		String date[] = pubDate.split(" ");
		setPubDate(date[3]+"." + translateMonth(date[2])+"." +  date[1]);
	}
	
	private String translateMonth(String data) {
		String month = null;
		switch(data){
			case "Jan": month = "1"; break;
			case "Feb": month = "2"; break;
			case "Mar": month = "3"; break;
			case "Apr": month = "4"; break;
			case "May": month = "5"; break;
			case "June": month = "6"; break;
			case "July": month = "7"; break;
			case "Aug": month = "8"; break;
			case "Sep": month = "9"; 
			case "Sept": month = "9"; break;
			case "Oct": month = "10"; break;
			case "Nov": month = "11"; break;
			case "Dec": month = "12"; break;
			default: month = ""; break;
		}
		return month;
	}
	
	private String translateDay(String data) {
		String day = null;
		switch(data){
			case "Mon,": day = "������"; break;
			case "Tu,": day = "ȭ���� ";
			case "Tue,": day = "ȭ���� ";
			case "Tues,": day = "ȭ���� ";break;
			case "Wed,": day = "������"; break;
			case "Th,": day = "�����";
			case "Thu,": day = "�����";
			case "Thur,": day = "�����";
			case "Thurs,": day = "�����"; break;
			case "Fri,": day = "�ݿ���"; break;
			case "Sat,": day = "�����"; break;
			case "Sun,": day = "�Ͽ���"; break;
			default: day = ""; break;
		}
		return day;
	}
	
}