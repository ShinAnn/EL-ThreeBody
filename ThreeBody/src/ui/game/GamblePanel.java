package ui.game;

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
import javax.swing.JTextField;

import control.GameControl;
import control.MainControl;
import model.card.ResourceGambling;
import model.operation.CardUse;
import ui.FrameUtil;
import dto.GameDTO;

public class GamblePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnOK;
	private JLabel msgLabel;
	private JLabel idLabel;
	private JTextField idField;
	/**
	 * 
	 * @param mainControl 
	 * @param successInformFrame 
	 */
	public GamblePanel(JFrame frame,String message) {
		this.setLayout(null);
		this.frame=frame;
		this.initComonent(message);
	}
	private void initComonent(String message) {
		this.btnOK = new JButton(new ImageIcon("images/btnOk.png"));
		this.btnOK.setContentAreaFilled(false);
		this.btnOK.setBounds(120, 132,60, 30);
		btnOK.addMouseListener(new OKListener());
		this.add(btnOK);
		
		msgLabel = new JLabel();
		msgLabel.setForeground(Color.YELLOW);
		msgLabel.setText(message);
		msgLabel.setFont(new Font("宋体", Font.BOLD, 20));
		msgLabel.setBounds(60,0,180,80);
		this.add(msgLabel);
		
		idLabel = new JLabel();
		idLabel.setBounds(30,60,60,30);
		idLabel.setIcon(new ImageIcon("images/gamble.png"));
		this.add(idLabel);
		
		idField = new JTextField();
		idField.setBounds(100,60,140,30);
		this.add(idField);
		
	}
	
	class OKListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			try {
				int NumberOfResource=Integer.parseInt(idField.getText());
				String userAccountId=GameDTO.getInstance().getUser().getAccount().getId();
				ResourceGambling rg = new ResourceGambling(userAccountId, userAccountId, NumberOfResource);
				CardUse cardUseRg = new CardUse(userAccountId, userAccountId, rg);
				GameControl.getInstance().doOperation(cardUseRg);
				frame.setVisible(false);
			} catch (Exception ex) {
				FrameUtil.sendMessageByFrame("赌博", "输入错误！");
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
		Image IMG_MAIN = new ImageIcon("images/img1.jpg").getImage();
		// 绘制游戏界面
		g.drawImage(IMG_MAIN, 0, 0, null);
	}
}
