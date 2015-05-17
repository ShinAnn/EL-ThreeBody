package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.sound.Media;
import control.MainControl;

public class PreferencePanel extends JPanel{
	/*
	 * default
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnVolumnUp;
	private JButton btnVolumnDown;
	private JButton btnReturn;
	private JLabel labelVolume;
	private MainControl mainControl;
	private float volume=Media.getBgmPlayer().getVolume();
	
	public PreferencePanel(MainControl mainControl) {
		this.setLayout(null);
		this.mainControl = mainControl;
		this.initComonent();
	}

	private void initComonent() {
		labelVolume = new JLabel();
		labelVolume.setBounds(440,200,160,30);
		labelVolume.setFont(new Font("宋体",Font.PLAIN,30));
		labelVolume.setForeground(Color.YELLOW);
		labelVolume.setText(volume+"");
		this.add(labelVolume);
		
		this.btnReturn = new JButton("返回");
		this.btnReturn.setContentAreaFilled(false);
		this.btnReturn.setBounds(50, 515, 100, 30);
		btnReturn.setFont(new Font("黑体", Font.BOLD, 20));
		btnReturn.setForeground(Color.YELLOW);
		btnReturn.addMouseListener(new ReturnListener());
		this.add(btnReturn);
		
		this.btnVolumnUp = new JButton("Up");
		this.btnVolumnUp.setContentAreaFilled(false);
		this.btnVolumnUp.setBounds(600,200,60,40);
		btnVolumnUp.setFont(new Font("黑体", Font.BOLD, 20));
		btnVolumnUp.setForeground(Color.YELLOW);
		btnVolumnUp.addMouseListener(new VolumnUpListener());
		this.add(btnVolumnUp);
		
		this.btnVolumnDown = new JButton("Down");
		this.btnVolumnDown.setContentAreaFilled(false);
		this.btnVolumnDown.setBounds(360,200,80,40);
		btnVolumnDown.setFont(new Font("黑体", Font.BOLD, 20));
		btnVolumnDown.setForeground(Color.YELLOW);
		btnVolumnDown.addMouseListener(new VolumnDownListener());
		this.add(btnVolumnDown);
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
	
	class VolumnUpListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			

		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			volume*=2;
			if (volume==0.0f) {
				volume=(float)0.00390625;
			}
			volume=volume>=1.0f?1.0f:volume;
			labelVolume.setText(volume+"");
			Media.getBgmPlayer().setVolume(volume);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	class VolumnDownListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			volume/=2;
			volume=volume<0.003f?0.0f:volume;
			labelVolume.setText(volume+"");
			Media.getBgmPlayer().setVolume(volume);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Image IMG_MAIN = new ImageIcon("images/img1.jpg").getImage();
		// ������Ϸ����
		g.drawImage(IMG_MAIN, 0, 0, 1158, 650, null);
	}
}
