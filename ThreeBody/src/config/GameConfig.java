package config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 游戏配置器
 * 
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
	public GameConfig() throws Exception {
		
		//创建XML读取器
		SAXReader reader=new SAXReader();
		//读取XML文件
		Document doc=reader.read("config/cfg.xml");
		//获得XML文件的根节点
		Element game=doc.getRootElement();
		//配置技能参数
		this.setCardConfig(game.element("cards"));
		//配置角色参数
		this.setRolesConfig(game.element("roles"));
		
	
	}
	
	/*
	 * 配置技能参数
	 */
	private void setCardConfig(Element cards){
		List<Element> getCards=cards.elements("card");
		
		cardsConfig=new ArrayList<CardConfig>();
		for(Element card:getCards){
			CardConfig cc=new CardConfig(
				card.attributeValue("className"),
				Integer.parseInt(card.attributeValue("requiredResource")),
				Integer.parseInt(card.attributeValue("requiredTechPoint")),
				Integer.parseInt(card.attributeValue("lifetime"))
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
