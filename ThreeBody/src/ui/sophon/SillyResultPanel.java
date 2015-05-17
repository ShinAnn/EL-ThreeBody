package ui.sophon;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.FrameUtil;

public class SillyResultPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton resultOne;
	private JButton resultTwo;
	private JButton resultThree;
	private JButton btnReturn;
	private JFrame sophonFinder;
	private JPanel resultPanel;
	private int coordinate;
	int coordinateOne;
	int coordinateTwo;
	int coordinateThree;
	
	public SillyResultPanel(JFrame sophonFinder) {
		this.setLayout(null);
		this.sophonFinder=sophonFinder;
		resultPanel = new ResultPanel(sophonFinder);
		this.initComonent();
	}
	
	private void initComonent() {
		
		coordinate = 0;
		
		this.resultOne = new JButton();
		this.resultOne.setBounds(11, 16, 99, 60);
		resultOne.setFont(new Font("黑体", Font.BOLD, 60));
		resultOne.setContentAreaFilled(false);
		resultOne.setBorderPainted(false);
		resultOne.addMouseListener(new ResultOneListener());
		
		
		this.resultTwo = new JButton();
		this.resultTwo.setBounds(170, 16, 99, 60);
		resultTwo.setFont(new Font("黑体", Font.BOLD, 60));
		resultTwo.setContentAreaFilled(false);
		resultTwo.setBorderPainted(false);
		resultTwo.addMouseListener(new ResultTwoListener());
		
		
		this.resultThree = new JButton();
		this.resultThree.setBounds(329, 16, 99, 60);
		resultThree.setFont(new Font("黑体", Font.BOLD, 60));
		resultThree.setContentAreaFilled(false);
		resultThree.setBorderPainted(false);
		resultThree.addMouseListener(new ResultThreeListener());
		
		this.add(resultOne);
		this.add(resultTwo);
		this.add(resultThree);
		this.setPicture(coordinate);
		
		
		this.btnReturn = new JButton(new ImageIcon("images/btnOk.png"));
		this.btnReturn.setContentAreaFilled(false);
		this.btnReturn.setBounds(190, 125, 60,30);
		btnReturn.addMouseListener(new ReturnListener());
		this.add(btnReturn);

		
	}
	
	class ReturnListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			setVisible(false);
			sophonFinder.setContentPane(resultPanel);
			resultPanel.setVisible(true);	
			sophonFinder.validate();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	class ResultOneListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=0;
			setPicture(coordinate);
			repaint();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	class ResultTwoListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=1;
			setPicture(coordinate);
			repaint();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	class ResultThreeListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=2;
			setPicture(coordinate);
			repaint();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	public void setPicture(int cooperate) {
		resultOne.setIcon(new ImageIcon("images/coNothing.png"));
		resultTwo.setIcon(new ImageIcon("images/coNothing.png"));
		resultThree.setIcon(new ImageIcon("images/coNothing.png"));
		if(cooperate==0){
			resultOne.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==1){
			resultTwo.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==2){
			resultThree.setIcon(new ImageIcon("images/select.png"));
		}
		
	}
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon("images/img1.jpg").getImage();
		g.drawImage(img, 0, 0, null);
		FrameUtil.drawCoordinate(164, 323, 558, g);
		}
	
}
