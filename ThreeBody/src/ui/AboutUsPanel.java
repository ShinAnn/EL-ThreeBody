package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.sound.Media;
import ui.sound.Sound;
import control.MainControl;


public class AboutUsPanel extends JPanel{
  private static final long serialVersionUID = 1L;
  
  private JButton btnNextPage;
  private JLabel  picture;
  private MainControl mainControl;
  private int pNum=2;
  String[] pictureList=new String[pNum+1];
  private int i=0;
	  

  public AboutUsPanel(MainControl mainControl){
	  
	  //store the turns of pictureInfo  
	  
      pictureList[0]="images/ABOUTUS1.png";
      pictureList[1]="images/ABOUTUS2.png";
	  
	  this.setLayout(null);
	  this.initComonent();
	  this.mainControl = mainControl;
	  
  }
  
  private void initComonent() {
	  this.btnNextPage=new JButton();
	  this.btnNextPage.setIcon(new ImageIcon("images/下一页.png"));
	  this.btnNextPage.setContentAreaFilled(false);
      this.btnNextPage.setBounds(750,500,221,89);
	  //this.btnNextPage.setBorderPainted(false);
 
	  btnNextPage.addMouseListener(new NextPageListener());
	  btnNextPage.setVisible(true);
	  this.add(btnNextPage);
	
	  //show the first picture
	  Icon icon=new ImageIcon(pictureList[0]);
	  this.picture=new JLabel(icon);
	  this.picture.setBounds(200,100,800,400);
	  this.picture.setVisible(true);
	  this.add(picture);
  }
  
  
  public void changeImage(){
	  if(i<(pNum-1)){
		  i++;
		  picture.setIcon(new ImageIcon(pictureList[i]));
		  
		  
		  if(i==(pNum-1)){
			  this.btnNextPage.setIcon(new ImageIcon("images/返回.png"));
		  }
	  }else{
		  mainControl.toStartMenu();
		  
	  }
}
  
  
class NextPageListener implements MouseListener{
	  public void mouseClicked(MouseEvent arg0) {

		  
	  }

	  @Override
	  public void mouseEntered(MouseEvent arg0) {
		  Media.playSound(Sound.choose);
		  
	  }

	  @Override
	  public void mouseExited(MouseEvent arg0) {
	  }

	  @Override
	  public void mousePressed(MouseEvent arg0) {
		  Media.playSound(Sound.enter);
		  changeImage();
	  }

	  @Override
	  public void mouseReleased(MouseEvent arg0) {
		  
	  }
}



  @Override
  //background picture
  public void paintComponent(Graphics g) {
	  Image background=new ImageIcon("images/模糊背景.jpg").getImage();
	  g.drawImage(background,0,0,null);
	}
  
  
}
  
