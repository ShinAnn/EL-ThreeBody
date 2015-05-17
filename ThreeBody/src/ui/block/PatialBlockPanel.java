package ui.block;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.GameControl;
import dto.GameDTO;
import model.card.PatialBlock;
import model.operation.CardUse;
import ui.FrameUtil;

public class PatialBlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnCoordinateOne;
	private JButton btnCoordinateTwo;
	private JButton btnCoordinateThree;
	private JButton btnCoordinateFour;
	private JButton btnOk;
	private JFrame patialBlock;
	private int coordinate;
//	int coordinateOne;
//	int coordinateTwo;
//	int coordinateThree;
	
	public PatialBlockPanel(JFrame patialBlock) {
		this.setLayout(null);
		this.patialBlock=patialBlock;
		this.initComonent();
	}
	
	private void initComonent() {
		this.coordinate=0;
		
		this.btnCoordinateOne = new JButton();
		this.btnCoordinateOne.setBounds(11, 16, 99, 60);
		this.btnCoordinateOne.setFont(new Font("黑体", Font.BOLD, 60));
		this.btnCoordinateOne.setContentAreaFilled(false);
		this.btnCoordinateOne.addMouseListener(new CoordinateOneListener());
		
		
		this.btnCoordinateTwo = new JButton();
		this.btnCoordinateTwo.setBounds(120, 16, 99, 60);
		this.btnCoordinateTwo.setFont(new Font("黑体", Font.BOLD, 60));
		this.btnCoordinateTwo.setContentAreaFilled(false);
		this.btnCoordinateTwo.addMouseListener(new CoordinateTwoListener());
		
		
		this.btnCoordinateThree = new JButton();
		this.btnCoordinateThree.setBounds(229, 16, 99, 60);
		this.btnCoordinateThree.setFont(new Font("黑体", Font.BOLD, 60));
		this.btnCoordinateThree.setContentAreaFilled(false);
		this.btnCoordinateThree.addMouseListener(new CoordinateThreeListener());
		
		
		this.btnCoordinateFour = new JButton();
		this.btnCoordinateFour.setBounds(339, 16, 99, 60);
		this.btnCoordinateFour.setFont(new Font("黑体", Font.BOLD, 60));
		this.btnCoordinateFour.setContentAreaFilled(false);
		this.btnCoordinateFour.addMouseListener(new CoordinateFourListener());
		
		this.add(btnCoordinateOne);
		this.add(btnCoordinateTwo);
		this.add(btnCoordinateThree);
		this.add(btnCoordinateFour);
		this.setPicture(coordinate);
		
		
		
		this.btnOk = new JButton(new ImageIcon("images/btnOk.png"));
		this.btnOk.setContentAreaFilled(false);
		this.btnOk.setBounds(190, 125, 60,30);
		this.btnOk.addMouseListener(new OkListener());
		this.add(btnOk);

		
	}
	
	class OkListener  extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			String id = GameDTO.getInstance().getUser().getAccount().getId();
			PatialBlock pb = new PatialBlock(id, id, coordinate);
			CardUse cardUsePb=new CardUse(id, id, pb);
			GameControl.getInstance().doOperation(cardUsePb);
			patialBlock.setVisible(false);
		}
	}
	
	class CoordinateOneListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=0;
			setPicture(coordinate);
			repaint();
		}
	}
	class CoordinateTwoListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=1;
			setPicture(coordinate);
			repaint();
		}
	}
	class CoordinateThreeListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=2;
			setPicture(coordinate);
			repaint();
		}
	}
	class CoordinateFourListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=3;
			setPicture(coordinate);
			repaint();
		}
	}
	public void setPicture(int cooperate) {
		btnCoordinateOne.setIcon(new ImageIcon("images/coNothing.png"));
		btnCoordinateTwo.setIcon(new ImageIcon("images/coNothing.png"));
		btnCoordinateThree.setIcon(new ImageIcon("images/coNothing.png"));
		btnCoordinateFour.setIcon(new ImageIcon("images/coNothing.png"));
		if(cooperate==0){
			btnCoordinateOne.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==1){
			btnCoordinateTwo.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==2){
			btnCoordinateThree.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==3){
			btnCoordinateFour.setIcon(new ImageIcon("images/select.png"));
		}
	}
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon("images/img1.jpg").getImage();
		g.drawImage(img, 0, 0, null);
		FrameUtil.drawCoordinate(123,156,502,666,g);
		}
	
}


