package web;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DataParsing {

	public String[] title, originalLink, link, description, pubDate;
	
	public void getXmlData(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
        // 1. 빌더 팩토리 생성.
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        
        // 2. 빌더 팩토리로부터 빌더 생성
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        
        InputSource is = new InputSource(new StringReader(xmlFile));

        // 3. 빌더를 통해 XML 문서를 파싱해서 Document 객체로 가져온다.
        Document document = builder.parse(is);
        
        // 문서 구조 안정화 ?
        document.getDocumentElement().normalize();
        
        // XML 데이터 중 <item> 태그의 내용을 가져온다.
        NodeList personTagList = document.getElementsByTagName("item");
        
        // <item> 태그 리스트를 하나씩 돌면서 값들을 가져온다.
        for (int i = 0; i < personTagList.getLength(); ++i) {
            
            // <item> 태그의 하위 노드들을 가져온다. ( 여기서 노드는 태그를 의미한다. )
            NodeList childNodes = personTagList.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); ++j) {
                
                if ("title".equals(childNodes.item(j).getNodeName())) {
                    // do somthing...
                	
                	System.out.println(childNodes.item(j).getTextContent());
                }
                
                if ("originallink".equals(childNodes.item(j).getNodeName())) {
                    // do somthing...
                }
                if ("link".equals(childNodes.item(j).getNodeName())) {
                    // do somthing...
                }
                if ("description".equals(childNodes.item(j).getNodeName())) {
                    // do somthing...
                }
                if ("pubDate".equals(childNodes.item(j).getNodeName())) {
                    // do somthing...
                }
            }
        }
    }
	
	public String[] getTitle() {
		return title;
	}
	
	public String[] getOriginalLink() {
		return originalLink;
	}
	
	public String[] getLink() {
		return link;
	}
	
	public String[] getDescription() {
		return description;
	}
	
	public String[] getPubDate() {
		return pubDate;
	}
}
