package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameUtil {
	public static final Image NUMBER =  new ImageIcon("images/num2.png").getImage();
	static int IMG_NUMBER_W=NUMBER.getWidth(null)/10;
	static int IMG_NUMBER_H=NUMBER.getHeight(null);
	/**
	 * 窗口居中
	 */
	public static void setFrameCenter(JFrame jf){
		// 窗口居中
    	Toolkit toolkit =  Toolkit.getDefaultToolkit();
    	Dimension screen =  toolkit.getScreenSize();
    	int x = screen.width-jf.getWidth()>>1;
    	int y = (screen.height-jf.getHeight()>>1)-32;
    	jf.setLocation(x,y);
	}
	
	public static void drawNumberLeftPad(int x, int y, int num, int maxCount,
			Graphics g) {
		String strNum = Integer.toString(num);
		for (int i = 0; i < maxCount; i++) {
			if (maxCount - i <= strNum.length()) {
				int idx = i + strNum.length()-maxCount;
				int bit = strNum.charAt(idx) - '0';
				g.drawImage(NUMBER, 
						 x + IMG_NUMBER_W  * i, y,
						 x + IMG_NUMBER_W  * (i + 1),	 y + IMG_NUMBER_H ,
						bit * IMG_NUMBER_W, 0,
						(bit + 1) * IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
		}
	}
	
	public static  void drawCoordinate(int i, int j, int k, int l, Graphics g) {
		drawNumberLeftPad(5, 28,i,4, g);
		drawNumberLeftPad(114, 28,j,4, g);
		drawNumberLeftPad(223, 28,k,4, g);
		drawNumberLeftPad(333, 28,l,4, g);
	}
	
	public static  void drawCoordinate(int i, int j, int k, Graphics g) {
		drawNumberLeftPad(5, 28,i,4, g);
		drawNumberLeftPad(164, 28,j,4, g);
		drawNumberLeftPad(323, 28,k,4, g);
	}
	
	public static void sendMessageByFrame(String frameName,String message){
		InformFrame successInformFrame = new InformFrame(frameName, 300,200); 
		JPanel successIn = new MessageByFramePanel(successInformFrame,message);
		successInformFrame.add(successIn);
	}
}
