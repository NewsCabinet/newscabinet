package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HandlingNews {

	public NewsData[] getNewsFromOpenAPI(String apiUrl, String clientId, String clientPasswd, String keyword, String type) {
		
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
			
			NewsData[] newsdata = getNewsData(result);
			return newsdata;		
		} catch (Exception e) {
			return null;
		}
	}
	
	public NewsData[] getNewsData(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlFile));
        Document document = builder.parse(is);
        document.getDocumentElement().normalize();
        
        NodeList personTagList = document.getElementsByTagName("item");
        
        int length = personTagList.getLength();
        NewsData[] newsData = new NewsData[length];
        String headline = null, url = null, description = null, pubDate = null;
        for (int i = 0; i < personTagList.getLength(); ++i) {
            NodeList childNodes = personTagList.item(i).getChildNodes();
            
            for (int j = 0; j < childNodes.getLength(); ++j) {
            	
                if ("title".equals(childNodes.item(j).getNodeName())) {
                	headline = (String)childNodes.item(j).getTextContent();
                }
                
                if ("originallink".equals(childNodes.item(j).getNodeName())) {
                	url = (String)childNodes.item(j).getTextContent();
                }
                
                if ("description".equals(childNodes.item(j).getNodeName())) {
                	description = (String)childNodes.item(j).getTextContent();
                }
                if ("pubDate".equals(childNodes.item(j).getNodeName())) {
                	pubDate = (String)childNodes.item(j).getTextContent();
                }
            }
            newsData[i] = new NewsData(headline,url,description,pubDate);
        }
        
		return newsData;
    }
}
