package config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 游戏配置器
 */
public class GameConfig {
	
	/*
	 * 技能属性
	 */
	private List<CardConfig> cardsConfig;
	
	/*
	 * 
	 */
	private List<RoleConfig> rolesConfig;
	
	/*
	 * 构造函数
	 * 读取XML文件，获取全部游戏配置
	 */
	public GameConfig()  {
		
		//创建XML读取器
		SAXReader reader=new SAXReader();
		//读取XML文件
		Document doc = null;
		try {
			doc = reader.read("config/cfg.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//获得XML文件的根节点
		Element game=doc.getRootElement();
		
 //   	Element cards=game.element("cards");
		
		//配置技能参数
		this.setCardConfig(game.element("cards"));
		//配置角色参数
		this.setRolesConfig(game.element("roles"));
		
	}
	
	/*
	 * 配置技能参数
	 */
	private void setCardConfig(Element cards){
		 List<Element> cardList=cards.elements("card");
		
		cardsConfig=new ArrayList<CardConfig>();
		
		for(Element card:cardList){
			CardConfig cc=new CardConfig(
					card.attributeValue("className"),
					Integer.parseInt(card.attributeValue("lifetime")),
					Integer.parseInt(card.attributeValue("requiredResource")),
					Integer.parseInt(card.attributeValue("requiredTechPoint"))
				);
			cardsConfig.add(cc);
		}
	}
	
	/*
	 * 配置角色参数
	 */
	private void setRolesConfig(Element roles){
		List<Element> getRoles=roles.elements("role");
		
		rolesConfig=new ArrayList<RoleConfig>();
		for(Element role:getRoles){
			RoleConfig rc=new RoleConfig(
					role.attributeValue("className"),
					Integer.parseInt(role.attributeValue("initialResource")),
					Integer.parseInt(role.attributeValue("initialTechPoint")),
					Integer.parseInt(role.attributeValue("tchDevelopSpeed")),
					Integer.parseInt(role.attributeValue("rsrRestoreSpeed"))					
					);
			
			rolesConfig.add(rc);
		}
	}

	public List<CardConfig> getCardsConfig() {
		return cardsConfig;
	}
	
	public List<RoleConfig> getRolesConfig(){
		return rolesConfig;
	}

}
