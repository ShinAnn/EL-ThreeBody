package config;

import java.util.List;

import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;


public class ConfigReader {
	
	
	public static void readConfig(){
		
		SAXReader reader=new SAXReader();
		
		try {
			
			Document doc=reader.read("config/cfg.xml");
			
			System.out.println(doc);
			Element game=doc.getRootElement();
			Element cards=game.element("cards");
			
			List<Element> cardList=cards.elements("card");
			

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static void main(String[] args) {
		ConfigReader.readConfig();
	}
}
