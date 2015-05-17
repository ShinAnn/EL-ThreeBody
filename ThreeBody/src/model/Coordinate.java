package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Coordinate implements Serializable{
    
    /**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int UNKNOWN = 998;
    public static final int PROTECTED = 10086;
    
    public final static int DIMENSIONS = 4;
    public final static int MAX_NUM = 9;
    
    private int[] sequence;
    private boolean[] isProtected;
    
    public Coordinate(int[] sequence) {
		this.sequence = sequence;
		this.isProtected = new boolean[DIMENSIONS];
		Arrays.fill(isProtected, false);
	}
    
    /**
     * 生成Coordinate的工厂方法
     * @return 每个坐标随机生成的一个Coordinate
     */
    public static Coordinate generateCoordinate(){
    	Random random = new Random();
		int sequence[] = new int[DIMENSIONS];
		for (int i = 0; i < sequence.length; i++) {
			sequence[i] = random.nextInt(MAX_NUM);
		}
		return new Coordinate(sequence);
    }

    /**
     * 生成多个不重复Coordinate的工厂方法
     * @param number 想要生成的Coordinate的个数
     * @return number个不重复的Coordinate组成的数组
     */
	public static Coordinate[] generateCoordinates(int number){
		Coordinate[] coordinates = new Coordinate[number];
		tag1:
		for (int i = 0; i < number; i++) {
			coordinates[i] = Coordinate.generateCoordinate();
			// 检查是否有重复
			for (int j = 0; j < i; j++) {
				if(coordinates[j].equals(coordinates[i])){
					i--;
					continue tag1;
				}
			}
		}
		return coordinates;
	}

	/**
     * 
     * @param position 坐标的第几个
     * @return 如果没有被保护，返回正常的坐标，否则返回PROTECTED
     */
    public int getCoordinateElement(int position){
    	if(isProtected[position]){
    		return PROTECTED;
    	}
    	return sequence[position];
    }
    
    public void setProtected(int position,boolean value){
    	isProtected[position] = value;
    }

	public void setCoordinateElement(int position, int value) {
		sequence[position] = value;
	}

	/**
	 * 所有坐标都相等才返回ture
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinate){
			Coordinate compared = (Coordinate)obj;
			for (int i = 0; i < DIMENSIONS; i++) {
				if(compared.getCoordinateElement(i) != this.getCoordinateElement(i)){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < DIMENSIONS; i++) {
			if(sequence[i] == Coordinate.UNKNOWN){
				sb.append("不明-");
			}else{
				sb.append(sequence[i]+"-");
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	// TODO test
	public String getProtectingState(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<DIMENSIONS;i++){
			if(isProtected[i]){
				sb.append("被保护-");
			}else{
				sb.append(sequence[i]+"-");
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
}
