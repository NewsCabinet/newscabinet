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
	
	private void refineDate(String pubDate) {
		String date[] = pubDate.split(" ");
		setPubDate(date[3]+"년 " + translateMonth(date[2])+ date[1] + "일 " + translateDay(date[0]));
	}
	
	private String translateMonth(String data) {
		String month = null;
		switch(data){
			case "Jan": month = "1월 "; break;
			case "Feb": month = "2월 "; break;
			case "Mar": month = "3월 "; break;
			case "Apr": month = "4월 "; break;
			case "May": month = "5월 "; break;
			case "June": month = "6월 "; break;
			case "July": month = "7월 "; break;
			case "Aug": month = "8월 "; break;
			case "Sep": month = "9월 "; 
			case "Sept": month = "9월 "; break;
			case "Oct": month = "10월 "; break;
			case "Nov": month = "11월 "; break;
			case "Dec": month = "12월 "; break;
			default: month = ""; break;
		}
		return month;
	}
	
	private String translateDay(String data) {
		String day = null;
		switch(data){
			case "Mon,": day = "월요일"; break;
			case "Tu,": day = "화요일 ";
			case "Tue,": day = "화요일 ";
			case "Tues,": day = "화요일 ";break;
			case "Wed,": day = "수요일"; break;
			case "Th,": day = "목요일";
			case "Thu,": day = "목요일";
			case "Thur,": day = "목요일";
			case "Thurs,": day = "목요일"; break;
			case "Fri,": day = "금요일"; break;
			case "Sat,": day = "토요일"; break;
			case "Sun,": day = "일요일"; break;
			default: day = ""; break;
		}
		return day;
	}
	
}