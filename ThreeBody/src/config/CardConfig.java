package config;

public class CardConfig {
	
	private String className;
	
	private int requiredResource;
	
	private int requiredTechPoint;
	
	private int lifetime;

	
	public CardConfig(String className, int requiredResource,int requiredTechPoint,int lifetime) {
		this.className = className;
		this.requiredResource = requiredResource;
		this.requiredTechPoint = requiredTechPoint;
		this.lifetime=lifetime;
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
