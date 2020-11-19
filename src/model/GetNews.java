package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

public class GetNews {

	public NewsData[] getNewsFromOpenAPI(String apiUrl, String clientId, String clientPasswd) {
		
		String keyword = "IT";
		String type = "sim";
		BufferedReader br = null;
		try {
			String urlstr = apiUrl + "?query=" + keyword + "&display=10&start=1&sort=" + type;
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			urlconnection.setRequestProperty("X-Naver-Client-Id", clientId);            
			urlconnection.setRequestProperty("X-Naver-Client-Secret", clientPasswd);
			
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			
			DataParsing dp = new DataParsing();
			NewsData[] newsdata = dp.getNewsData(result);
			return newsdata;		
		} catch (Exception e) {
			return null;
		}
	}
}
