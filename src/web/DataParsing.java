package web;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DataParsing {

	public List title, originalLink, link, description, pubDate;
	
	public void getXmlData(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
        // 1. 鍮뚮뜑 �뙥�넗由� �깮�꽦.
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        
        // 2. 鍮뚮뜑 �뙥�넗由щ줈遺��꽣 鍮뚮뜑 �깮�꽦
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        
        InputSource is = new InputSource(new StringReader(xmlFile));

        // 3. 鍮뚮뜑瑜� �넻�빐 XML 臾몄꽌瑜� �뙆�떛�빐�꽌 Document 媛앹껜濡� 媛��졇�삩�떎.
        Document document = builder.parse(is);
        
        // 臾몄꽌 援ъ“ �븞�젙�솕 ?
        document.getDocumentElement().normalize();
        
        // XML �뜲�씠�꽣 以� <item> �깭洹몄쓽 �궡�슜�쓣 媛��졇�삩�떎.
        NodeList personTagList = document.getElementsByTagName("item");
        
        title = new ArrayList();
        
        // <item> �깭洹� 由ъ뒪�듃瑜� �븯�굹�뵫 �룎硫댁꽌 媛믩뱾�쓣 媛��졇�삩�떎.
        for (int i = 0; i < personTagList.getLength(); ++i) {
            
            // <item> �깭洹몄쓽 �븯�쐞 �끂�뱶�뱾�쓣 媛��졇�삩�떎. ( �뿬湲곗꽌 �끂�뱶�뒗 �깭洹몃�� �쓽誘명븳�떎. )
            NodeList childNodes = personTagList.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); ++j) {
                
                if ("title".equals(childNodes.item(j).getNodeName())) {
                    // do somthing...
                	title.add(childNodes.item(j).getTextContent());
                	//System.out.println(childNodes.item(j).getTextContent());
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
	
	public List getTitle() {
		return title;
	}
	
	public List getOriginalLink() {
		return originalLink;
	}
	
	public List getLink() {
		return link;
	}
	
	public List getDescription() {
		return description;
	}
	
	public List getPubDate() {
		return pubDate;
	}
}
