package ui.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Account;
import ui.FrameUtil;
import ui.InformFrame;
import control.AccountControl;
import control.MainControl;



public class AccountPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel psId;
	private JLabel labelId;
	private JLabel labelHead;
	private JLabel psPoint;
	private JLabel labelPoint;
	private JLabel psRank;
	private JLabel labelRank;
	private JLabel psTotalGames;
	private JLabel labelTotalGames;
	private JLabel psWins;
	private JLabel labelWins;
	private JLabel psLosts;
	private JLabel labelLosts;
	private JButton btnReturn;
	private JButton btnLogout;
	private JButton btnRevisePW;
	private Account account;
	private AccountControl accountControl;
	private MainControl mainControl;
	public AccountPanel(MainControl mainControl, String id,AccountControl accountControl){
		this.setLayout(null);
		this.account=new Account(id);
		this.mainControl=mainControl;
		this.accountControl=accountControl;
		init();
	}
	private void init() {
		psId = new JLabel();
		psId.setBounds(400,140,120,60);
		psId.setIcon(new ImageIcon("images/accountId.png"));
		this.add(psId);
		
		labelId = new JLabel();
		labelId.setBounds(440,200,160,60);
		labelId.setFont(new Font("宋体",Font.PLAIN,60));
		labelId.setForeground(Color.YELLOW);
		labelId.setText(account.getId());
		this.add(labelId);
		
		labelHead = new JLabel();
		labelHead.setBounds(600,120,150,150);
		if(account.getHead()!=null){
			Image headImage=account.getHead();
			headImage=headImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			labelHead.setIcon(new ImageIcon(headImage));
		}else{
			Image headImage = new ImageIcon("images/headtest.jpg").getImage();
			headImage=headImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			labelHead.setIcon(new ImageIcon(headImage));
		}
		labelHead.addMouseListener(new HeadSelectListener());
		this.add(labelHead);
		
		psPoint = new JLabel();
		psPoint.setBounds(400,300,60,30);
		psPoint.setIcon(new ImageIcon("images/accountpoint.png"));
		this.add(psPoint);
		
		labelPoint = new JLabel();
		labelPoint.setBounds(690,300,60,30);
		labelPoint.setFont(new Font("宋体",Font.PLAIN,30));
		labelPoint.setForeground(Color.YELLOW);
		labelPoint.setText(account.getPoint()+"");
		this.add(labelPoint);
		
		psRank = new JLabel();
		psRank.setBounds(400,350,60,30);
		psRank.setIcon(new ImageIcon("images/accountlevel.png"));
		this.add(psRank);
		
		labelRank = new JLabel();
		labelRank.setBounds(690,350,60,30);
		labelRank.setFont(new Font("宋体",Font.PLAIN,30));
		labelRank.setForeground(Color.YELLOW);
		labelRank.setText(account.getRank()+"");
		this.add(labelRank);;
		
		psTotalGames = new JLabel();
		psTotalGames.setBounds(400,400,80,30);
		psTotalGames.setIcon(new ImageIcon("images/accounttotalgames.png"));
		this.add(psTotalGames);
		
		labelTotalGames = new JLabel();
		labelTotalGames.setBounds(690,400,60,30);
		labelTotalGames.setFont(new Font("宋体",Font.PLAIN,30));
		labelTotalGames.setForeground(Color.YELLOW);
		labelTotalGames.setText(account.getTotalGames()+"");
		this.add(labelTotalGames);
		
		
		psWins = new JLabel();
		psWins.setBounds(400,450,80,30);
		psWins.setIcon(new ImageIcon("images/accountwins.png"));
		this.add(psWins);
		
		labelWins = new JLabel();
		labelWins.setBounds(690,450,60,30);
		labelWins.setFont(new Font("宋体",Font.PLAIN,30));
		labelWins.setForeground(Color.YELLOW);
		labelWins.setText(account.getWins()+"");
		this.add(labelWins);
		
		
		psLosts = new JLabel();
		psLosts.setBounds(400,500,80,30);
		psLosts.setIcon(new ImageIcon("images/accountloses.png"));
		this.add(psLosts);
		
		
		labelLosts = new JLabel();
		labelLosts.setBounds(690,500,60,30);
		labelLosts.setFont(new Font("宋体",Font.PLAIN,30));
		labelLosts.setForeground(Color.YELLOW);
		labelLosts.setText(account.getLosts()+"");
		this.add(labelLosts);
		
		this.btnReturn = new JButton("返回");
		this.btnReturn.setContentAreaFilled(false);
		this.btnReturn.setBounds(950, 515, 100, 30);
		btnReturn.setFont(new Font("黑体", Font.BOLD, 20));
		btnReturn.setForeground(Color.YELLOW);
		btnReturn.addMouseListener(new ReturnListener());
		this.add(btnReturn);
		
		this.btnLogout = new JButton("登出");
		this.btnLogout.setContentAreaFilled(false);
		this.btnLogout.setBounds(950, 470, 100, 30);
		btnLogout.setFont(new Font("黑体", Font.BOLD, 20));
		btnLogout.setForeground(Color.YELLOW);
		btnLogout.addMouseListener(new LogOutListener());
		this.add(btnLogout);
		
		this.btnRevisePW = new JButton("修改密码");
		this.btnRevisePW.setContentAreaFilled(false);
		this.btnRevisePW.setBounds(50, 515, 120, 30);
		btnRevisePW.setFont(new Font("黑体", Font.BOLD, 20));
		btnRevisePW.setForeground(Color.YELLOW);
		btnRevisePW.addMouseListener(new RevisePWListener());
		this.add(btnRevisePW);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		Image IMG_MAIN = new ImageIcon("images/img1.jpg").getImage();
		// ������Ϸ����
		g.drawImage(IMG_MAIN, 0, 0, 1158, 650, null);
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
			mainControl.toStartMenu();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	class LogOutListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {

		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			switch(accountControl.logoutAndClear()){
			case SUCCESS:
				FrameUtil.sendMessageByFrame("登出成功", "登出成功，本地缓存已删除");
				break;
			default:
				FrameUtil.sendMessageByFrame("登出失败", "登出失败 T_T");
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	class RevisePWListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {

		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			InformFrame revisePWFrame = new InformFrame("修改密码", 400, 300);
			RevisePWPanel revisePWpanel = new RevisePWPanel(revisePWFrame, accountControl);
			revisePWFrame.add(revisePWpanel);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	class HeadSelectListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {

		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(jfc);
			File fileHead = jfc.getSelectedFile();
			System.out.println(fileHead.getPath());
			Image headImage = new ImageIcon(fileHead.getPath()).getImage();
			headImage=headImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			labelHead.setIcon(new ImageIcon(headImage));
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	
}
