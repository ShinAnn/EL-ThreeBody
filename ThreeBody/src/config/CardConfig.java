package config;

public class CardConfig {
	
	private String className;
	
	private int requiredResource;
	
	private int requiredTechPoint;
	
	private int lifetime;


	public CardConfig(String className,int lifetime,int requiredResource,int requiredTechPoint){
	
		this.className=className;
		this.lifetime=lifetime;
		this.requiredResource=requiredResource;
		this.requiredTechPoint=requiredTechPoint;
	}

	public int getRequiredResource() {
		return requiredResource;
	}
	
	public int getRequiredTechPoint() {
		return requiredTechPoint;
	}

	public String getClassName() {
		return className;
	}	 
	
	public int getLifetime(){
		return lifetime;
	}
	
}
