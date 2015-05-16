package ui.sophon;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Broadcast;
import model.Player;
import model.card.SillySophon;
import model.card.Sophon;
import model.operation.CardUse;
import dto.GameDTO;

public class SophonFinderPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton btnCoordinateOne;
	private JButton btnCoordinateTwo;
	private JButton btnCoordinateThree;
	private JButton btnCoordinateFour;
	private JButton btnOK;
	private JButton btnReturn;
	private JFrame sophonFinder;
	private JComboBox<String> select;
	private String result;
	private JPanel sillyResultPanel;
	private ResultPanel resultPanel;
	private int coordinate;
	List<Player> players=null;
	Player user;
	
	public SophonFinderPanel(JFrame sophonFinder) {
		this.setLayout(null);
		players= GameDTO.getInstance().getPlayers();
		user=GameDTO.getInstance().getUser();
		this.sophonFinder=sophonFinder;
		sillyResultPanel = new SillyResultPanel(sophonFinder);
		resultPanel = new ResultPanel(sophonFinder);
		this.initComonent();
	}
	
	private void initComonent() {
		
		coordinate=1;
		
		this.btnCoordinateOne = new JButton();
		this.btnCoordinateOne.setBounds(11, 16, 99, 60);
		btnCoordinateOne.setFont(new Font("黑体", Font.BOLD, 60));
		btnCoordinateOne.setContentAreaFilled(false);
		btnCoordinateOne.addMouseListener(new CoordinateOneListener());
		
		this.btnCoordinateTwo = new JButton();
		this.btnCoordinateTwo.setBounds(120, 16, 99, 60);
		btnCoordinateTwo.setFont(new Font("黑体", Font.BOLD, 60));
		btnCoordinateTwo.setContentAreaFilled(false);
		btnCoordinateTwo.addMouseListener(new CoordinateTwoListener());
		
		this.btnCoordinateThree = new JButton();
		this.btnCoordinateThree.setBounds(229, 16, 99, 60);
		btnCoordinateThree.setFont(new Font("黑体", Font.BOLD, 60));
		btnCoordinateThree.setContentAreaFilled(false);
		btnCoordinateThree.addMouseListener(new CoordinateThreeListener());
		
		this.btnCoordinateFour = new JButton();
		this.btnCoordinateFour.setBounds(339, 16, 99, 60);
		btnCoordinateFour.setFont(new Font("黑体", Font.BOLD, 60));
		btnCoordinateFour.setContentAreaFilled(false);
		btnCoordinateFour.addMouseListener(new CoordinateFourListener());
		
		this.add(btnCoordinateOne);
		this.add(btnCoordinateTwo);
		this.add(btnCoordinateThree);
		this.add(btnCoordinateFour);
		this.setPicture(coordinate);
		
		this.btnOK = new JButton(new ImageIcon("images/find.png"));
		this.btnOK.setContentAreaFilled(false);
		this.btnOK.setBounds(250, 125, 60, 30);
		btnOK.addMouseListener(new FindListener());
		this.add(btnOK);
		
		this.btnReturn = new JButton(new ImageIcon("images/littlecancel.png"));
		this.btnReturn.setContentAreaFilled(false);
		this.btnReturn.setBounds(335, 125, 60, 30);
		btnReturn.addMouseListener(new ReturnListener());
		this.add(btnReturn);
		
		select = new JComboBox<String>();
		select.setFont(new Font("宋体", Font.PLAIN, 30));
		select.setBounds(50,105, 60, 30);
		//
		if (players != null) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).equals(user)) {
					continue;
				} else {
					select.addItem(players.get(i).getAccount().getId());
				}
			}
		}else{
			select.addItem("aa");
			select.addItem("bb");
		}
		this.add(select);
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
			sophonFinder.setVisible(false);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	class FindListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		private void jumpPanel() {
			if(sophonFinder.getTitle().equals("智子")){
				setVisible(false);
				sophonFinder.setContentPane(resultPanel);
				resultPanel.setVisible(true);	
				sophonFinder.validate();
			}else if(sophonFinder.getTitle().equals("人造智子")){
				setVisible(false);
				sophonFinder.setContentPane(sillyResultPanel);
				sillyResultPanel.setVisible(true);	
				sophonFinder.validate();
			}
			
		}
		private void useSophon() {
			if(sophonFinder.getTitle().equals("智子")){
				Sophon sophon  =new Sophon(user.getAccount().getId(), select.getSelectedItem().toString(), coordinate);
				CardUse cardSophon = new CardUse(user.getAccount().getId(), select.getSelectedItem().toString(), sophon);
				GameDTO.getInstance().depositOperation(cardSophon);
				ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>) GameDTO.getInstance().getBroadcasts();
				result=broadcasts.get(broadcasts.size()-1).toString();
				resultPanel.setResult(result);
			}else if(sophonFinder.getTitle().equals("人造智子")){
				SillySophon sillySophon  =new SillySophon(user.getAccount().getId(), select.getSelectedItem().toString(), coordinate);
				CardUse cardSophon = new CardUse(user.getAccount().getId(), select.getSelectedItem().toString(), sillySophon);
				GameDTO.getInstance().depositOperation(cardSophon);
			}
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
//			useSophon();
			jumpPanel();
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	class CoordinateOneListener implements MouseListener {

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
	class CoordinateTwoListener implements MouseListener {

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
	class CoordinateThreeListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=3;
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
	class CoordinateFourListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			coordinate=4;
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
		btnCoordinateOne.setIcon(new ImageIcon("images/coNothing.png"));
		btnCoordinateTwo.setIcon(new ImageIcon("images/coNothing.png"));
		btnCoordinateThree.setIcon(new ImageIcon("images/coNothing.png"));
		btnCoordinateFour.setIcon(new ImageIcon("images/coNothing.png"));
		if(cooperate==1){
			btnCoordinateOne.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==2){
			btnCoordinateTwo.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==3){
			btnCoordinateThree.setIcon(new ImageIcon("images/select.png"));
		}
		if(cooperate==4){
			btnCoordinateFour.setIcon(new ImageIcon("images/select.png"));
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon("images/img1.jpg").getImage();
		g.drawImage(img, 0, 0, null);
//		FrameUtil.drawCoordinate(123,156,502,666,g);
		
	}


	
}
