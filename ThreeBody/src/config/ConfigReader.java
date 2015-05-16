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
			
			Element game=doc.getRootElement();
			List<Element> cards=game.elements("card");
			
			

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	
	}
	
	
}
