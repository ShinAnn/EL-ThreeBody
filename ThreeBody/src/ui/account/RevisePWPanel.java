package ui.account;

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

import ui.FrameUtil;
import control.AccountControl;

public class RevisePWPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton btnCancel;
	private JButton btnRevise;
	private JPasswordField pwOldField;
	private JPasswordField pwNewField;
	private JPasswordField pwConfirmField;
	private JLabel pwOldLabel;
	private JLabel pwNewLabel;
	private JLabel pwConfirmLabel;
	private JFrame frame;
	private AccountControl accountControl;
	
	public RevisePWPanel(JFrame frame,AccountControl accountControl) {
		this.accountControl = accountControl;
		this.setLayout(null);
		this.frame=frame;
		this.initComonent();
		
	}
	private void initComonent() {
		this.btnCancel = new JButton(new ImageIcon("images/logcancel.png"));
		this.btnCancel.setBounds(220, 220, 80, 40);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addMouseListener(new CancelListener());
		this.add(btnCancel);
		
		this.btnRevise = new JButton(new ImageIcon("images/pwRevise.png"));
		this.btnRevise.setBounds(100, 220, 80, 40);
		btnRevise.setContentAreaFilled(false);
		btnRevise.addMouseListener(new ReviseListener());
		this.add(btnRevise);
		
		pwOldField = new JPasswordField();
		pwOldField.setBounds(105,40,240,30);
		this.add(pwOldField);
		
		pwNewField =new JPasswordField();
		pwNewField.setBounds(105,80,240,30);
		this.add(pwNewField);
		
		pwConfirmField =new JPasswordField();
		pwConfirmField.setBounds(105,120,240,30);
		this.add(pwConfirmField);
		
		pwOldLabel = new JLabel();
		pwOldLabel.setBounds(30,40,60,30);
		pwOldLabel.setIcon(new ImageIcon("images/pwold.png"));
		this.add(pwOldLabel);
		
		pwNewLabel = new JLabel();
		pwNewLabel.setBounds(30,80,60,30);
		pwNewLabel.setIcon(new ImageIcon("images/pwnew.png"));
		this.add(pwNewLabel);
		
		pwConfirmLabel = new JLabel();
		pwConfirmLabel.setBounds(20,120,80,30);
		pwConfirmLabel.setIcon(new ImageIcon("images/logpasswordconfirm.png"));
		this.add(pwConfirmLabel);
		

	}
	class CancelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			frame.setVisible(false);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	class ReviseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!pwNewField.getText().equals(pwConfirmField.getText())){
				FrameUtil.sendMessageByFrame("两次输入的密码不一致", "两次输入的密码不一致");
			}
			switch(accountControl.editPassword(pwOldField.getText(), pwNewField.getText())){
			case SUCCESS:
				FrameUtil.sendMessageByFrame("修改成功", "修改成功");
				frame.setVisible(false);
				break;
			case INVALID:
				FrameUtil.sendMessageByFrame("修改失败", "原密码不正确");
				frame.setVisible(false);
				break;
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
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
