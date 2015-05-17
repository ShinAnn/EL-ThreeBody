package ui.login;

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

public class LogupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnCancel;
	private JButton btnlogup;
	private JFrame loginFrame;
	private JTextField idField;
	private JTextField welcomeCodeField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmLabel;
	private JLabel codeLabel;
	private LoginPanel panelLogin;
	private AccountControl accountControl;
	
	public LogupPanel(LoginPanel panelLogin,AccountControl accountControl) {
		this.accountControl = accountControl;
		this.setLayout(null);
		this.panelLogin=panelLogin;
		this.loginFrame=panelLogin.getLoginFrame();
		this.initComonent();
		
	}
	private void initComonent() {
		this.btnCancel = new JButton(new ImageIcon("images/logcancel.png"));
		this.btnCancel.setBounds(220, 220, 80, 40);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addMouseListener(new CancelListener());
		this.add(btnCancel);
		
		this.btnlogup = new JButton(new ImageIcon("images/logup.png"));
		this.btnlogup.setBounds(100, 220, 80, 40);
		btnlogup.setContentAreaFilled(false);
		btnlogup.addMouseListener(new LogupListener());
		this.add(btnlogup);
		
		idField = new JTextField();
		idField.setBounds(105,40,240,30);
		this.add(idField);
		
		passwordField =new JPasswordField();
		passwordField.setBounds(105,80,240,30);
		this.add(passwordField);
		
		passwordConfirmField =new JPasswordField();
		passwordConfirmField.setBounds(105,120,240,30);
		this.add(passwordConfirmField);
		
		welcomeCodeField = new JTextField();
		welcomeCodeField.setBounds(105,160,240,30);
		this.add(welcomeCodeField);
		
		idLabel = new JLabel();
		idLabel.setBounds(30,40,60,30);
		idLabel.setIcon(new ImageIcon("images/logid.png"));
		this.add(idLabel);
		
		passwordLabel = new JLabel();
		passwordLabel.setBounds(30,80,60,30);
		passwordLabel.setIcon(new ImageIcon("images/logpassword.png"));
		this.add(passwordLabel);
		
		passwordConfirmLabel = new JLabel();
		passwordConfirmLabel.setBounds(20,120,80,30);
		passwordConfirmLabel.setIcon(new ImageIcon("images/logpasswordconfirm.png"));
		this.add(passwordConfirmLabel);
		
		codeLabel = new JLabel();
		codeLabel.setBounds(30,160,60,30);
		codeLabel.setIcon(new ImageIcon("images/logcode.png"));
		this.add(codeLabel);
	}
	class CancelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			setVisible(false);
			panelLogin.setVisible(true);
			loginFrame.setTitle("登录");
			loginFrame.setContentPane(panelLogin);
			repaint();
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
			String id = idField.getText();
			String password = passwordField.getText();
			String invitationID = welcomeCodeField.getText();
			if(password.equals(passwordConfirmField.getText())){
				// TODO 消息窗口
				switch(accountControl.logUp(id, password, invitationID)){
				case SUCCESS:
					FrameUtil.sendMessageByFrame("登录成功", "登录成功！");
					break;
				case ALREADY_EXISTED:
					FrameUtil.sendMessageByFrame("账户已存在", "账户已存在！");
					break;
				case NOT_EXISTED:
					FrameUtil.sendMessageByFrame("邀请码不正确", "邀请码不正确！");
					break;
				}
				
				loginFrame.setVisible(false);
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon("images/img1.jpg").getImage();
		g.drawImage(img, 0, 0, null);
		}
}
