package ui.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.FrameUtil;
import control.AccountControl;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton btnlogin;
	private JButton btnlogup;
	private JFrame loginFrame;
	private JTextField idField;
	private JPasswordField passwordField;
	private JLabel idLabel;
	private JPanel thisPanel;
	private JLabel passwordLabel;
	private JLabel errorMsgLabel;
	private JLabel loadingLabel;
	//双缓冲机制
	private Image iBuffer;
	private Graphics gBuffer;
	public JFrame getLoginFrame() {
		return loginFrame;
	}
	private AccountControl accountControl;
	private JPanel panelLogup;
	public LoginPanel(JFrame loginFrame,AccountControl accountControl) {
		this.accountControl = accountControl;
		thisPanel=this;
		this.setLayout(null);
		this.loginFrame=loginFrame;
		panelLogup = new LogupPanel(this,accountControl);
		this.initComonent();
	}
	private void initComonent() {
		this.btnlogin = new JButton(new ImageIcon("images/login.png"));
		this.btnlogin.setBounds(220, 220, 80, 40);
		btnlogin.setContentAreaFilled(false);
		btnlogin.addMouseListener(new LoginListener());
		this.add(btnlogin);
		
		this.btnlogup = new JButton(new ImageIcon("images/logup.png"));
		this.btnlogup.setBounds(100, 220, 80, 40);
		btnlogup.setContentAreaFilled(false);
		btnlogup.addMouseListener(new LogupListener());
		this.add(btnlogup);
		
		idField = new JTextField();
		idField.setBounds(100,60,240,30);
		this.add(idField);
		
		passwordField =new JPasswordField();
		passwordField.setBounds(100,110,240,30);
		this.add(passwordField);
		
		idLabel = new JLabel();
		idLabel.setBounds(30,60,60,30);
		idLabel.setIcon(new ImageIcon("images/logid.png"));
		this.add(idLabel);
		
		passwordLabel = new JLabel();
		passwordLabel.setBounds(30,110,60,30);
		passwordLabel.setIcon(new ImageIcon("images/logpassword.png"));
		this.add(passwordLabel);
		
		errorMsgLabel = new JLabel("密码输入错误");
		errorMsgLabel.setForeground(Color.RED);
		errorMsgLabel.setFont(new Font("宋体", Font.BOLD, 20));
		errorMsgLabel.setBounds(110,140,190,30);
		
		loadingLabel = new JLabel();
		loadingLabel.setBounds(110,170,190,40);
	}
	class LoginListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		 private void setGif() {
			 loadingLabel.setIcon(new ImageIcon("images/loading2.gif"));
				add(loadingLabel);
				loginFrame.setContentPane(thisPanel);
		}

		private void login() {
			String id = idField.getText();
			String password = passwordField.getText();
			
			// TODO 消息窗口
			switch(accountControl.login(id, password)){
			case SUCCESS:
				loginFrame.setVisible(false);
				FrameUtil.sendMessageByFrame("登录成功", "登录成功！");
//				AccountDTO.getInstance().getId()
				break;
			case ALREADY_IN:
				errorMsgLabel.setText("账户已在别处登录");
				add(errorMsgLabel);
				loginFrame.setContentPane(thisPanel);
				break;
			case INVALID:
				errorMsgLabel.setText("密码输入错误");
				add(errorMsgLabel);
				loginFrame.setContentPane(thisPanel);
				break;
			case NOT_EXISTED:
				errorMsgLabel.setText("账户不存在");
				add(errorMsgLabel);
				loginFrame.setContentPane(thisPanel);
				break;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
//			setGif();
			login();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	class LogupListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			setVisible(false);
			panelLogup.setVisible(true);
			loginFrame.setTitle("注册");
			loginFrame.setContentPane(panelLogup);
			repaint();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	@Override
	public void update(Graphics scr)
	{
	    if(iBuffer==null)
	    {
	       iBuffer=createImage(this.getSize().width,this.getSize().height);
	       gBuffer=iBuffer.getGraphics();
	    }
	       gBuffer.setColor(getBackground());
	       gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
	       paint(gBuffer);
	       scr.drawImage(iBuffer,0,0,this);
	}
	
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon("images/img1.jpg").getImage();
		g.drawImage(img, 0, 0, null);
		}
}
