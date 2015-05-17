package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.login.LoginFrame;
import ui.login.LoginPanel;
import ui.sound.Media;
import ui.sound.Sound;
import control.MainControl;
import dto.AccountDTO;

public class StartMenuPanel extends JPanel{
	/*
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnStartGame;
	private JButton btnOption;
	private JButton btnAboutUs;
	private JButton btnExit;
	private JButton btnHelp;
	private JButton btnLogIn;
	public JLabel labelLogIn;
	private MainControl mainControl;
	public String accountId="233";
	private Image opaque  = new ImageIcon("coNothing.png").getImage();
	
	public StartMenuPanel(MainControl mainControl) {
		this.setLayout(null);
		this.mainControl = mainControl;
		this.accountId= AccountDTO.getInstance().getId();
		this.initComonent();
		
	}
	
	private void initComonent() {

		this.btnStartGame = new JButton(new ImageIcon("images/GameStart.png"));
		this.btnStartGame.setContentAreaFilled(false);
		this.btnStartGame.setBounds(523, 120, 260, 260);
		this.btnStartGame.setBorderPainted(false);
		this.btnStartGame.addMouseListener(new StartGameListener());
		this.add(btnStartGame);

		this.btnOption = new JButton(new ImageIcon("images/Preference2.png"));
		this.btnOption.setContentAreaFilled(false);
		this.btnOption.setBounds(380, 92, 150, 150);
		this.btnOption.setBorderPainted(false);
		this.btnOption.addMouseListener(new OptionListener());
		this.add(btnOption);

		Image aboutUs = new ImageIcon("images/AboutUs.png").getImage();
		aboutUs=aboutUs.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		this.btnAboutUs = new JButton(new ImageIcon(aboutUs));
		this.btnAboutUs.setContentAreaFilled(false);
		this.btnAboutUs.setBounds(350, 340, 100, 100);
		this.btnAboutUs.setBorderPainted(false);
		this.btnAboutUs.addMouseListener(new AboutUsListener());
		this.add(btnAboutUs);

		this.btnExit = new JButton(new ImageIcon("images/Menuexit.png"));
		this.btnExit.setContentAreaFilled(false);
		this.btnExit.setBounds(473, 360, 150, 150);
		this.btnExit.setBorderPainted(false);
		this.btnExit.addMouseListener(new ExitListener());
		this.add(btnExit);
		
		Image help = new ImageIcon("images/Help.png").getImage();
		help=help.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		this.btnHelp = new JButton(new ImageIcon(help));
		this.btnHelp.setContentAreaFilled(false);
		this.btnHelp.setBounds(400, 236, 150, 150);
		this.btnHelp.setBorderPainted(false);
		this.btnHelp.addMouseListener(new HelpListener());
		this.add(btnHelp);
		
		this.btnLogIn = new JButton();
		this.btnLogIn.setIcon(new ImageIcon(opaque.getScaledInstance(150, 25, Image.SCALE_SMOOTH)));
		this.btnLogIn.setContentAreaFilled(false);
		this.btnLogIn.setBounds(0, 0, 150, 25);
		this.btnLogIn.addMouseListener(new LogInListener());
		this.add(btnLogIn);
		
		this.labelLogIn = new JLabel(accountId,JLabel.CENTER);
		this.labelLogIn.setBounds(0, 0, 150, 25);
		this.labelLogIn.setForeground(Color.YELLOW);
		this.labelLogIn.setFont(new Font("宋体",Font.PLAIN,20));
		this.labelLogIn.setVisible(true);
		this.add(labelLogIn);
	}
	@Override
	public void paintComponent(Graphics g) {
		Image IMG_MAIN = new ImageIcon("images/sky3.jpg").getImage();
		g.drawImage(IMG_MAIN, 0, 0, 1158, 650, null);
	}

	class StartGameListener extends MouseAdapter {

		
		@Override
		public void mouseReleased(MouseEvent e) {
			Media.playSound(Sound.enter);
			mainControl.toLobby();
		}
	}
	class OptionListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			Media.playSound(Sound.enter);
			mainControl.toPreference();
		}
	}
	class AboutUsListener extends MouseAdapter {

		
		@Override
		public void mouseReleased(MouseEvent e) {
			Media.playSound(Sound.enter);
			Media.playBGM(Sound.career);
			mainControl.toAboutUs();
		}
	}
	class ExitListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			Media.playSound(Sound.goback);
			mainControl.exit();
		}
	}
	
	class LogInListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			Media.playSound(Sound.choose);
			if(AccountDTO.getInstance().getId() == "本地玩家"){
				JFrame loginFrame = new LoginFrame();
				JPanel loginPanel = new LoginPanel(loginFrame,mainControl.accountControl);
				loginFrame.setContentPane(loginPanel);
				repaint();
			}else{
				if(mainControl.isConnected()){
					mainControl.toAccount(AccountDTO.getInstance().getId());
					System.out.println("Account界面，目前账号为："+AccountDTO.getInstance().getId()+" 已连接");
				}else{
					mainControl.toAccount(AccountDTO.getInstance().getId());
					System.out.println("Account界面，目前账号为："+AccountDTO.getInstance().getId()+" 未连接");
				}
			}
		}
		
	}
	
	class HelpListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			Media.playSound(Sound.goback);
		}
	}
}
