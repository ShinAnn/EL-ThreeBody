package ui.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;
import model.card.Card;
import model.card.NoBroadcasting;
import model.operation.CardUse;
import model.operation.Operation;
import model.operation.Priviledge_GetRole;
import control.GameControl;
import dto.AccountDTO;
import dto.GameDTO;

public class SelectEnemyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnOK;
	private JComboBox<String> enemys;
	private JLabel msgLabel;
	List<Player> players=null;
	Player user;
	/**
	 * 
	 * @param successInformFrame 
	 */
	public SelectEnemyPanel(JFrame frame,String message) {
		this.setLayout(null);
		players= GameDTO.getInstance().getPlayers();
		user=GameDTO.getInstance().getUser();
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
		
		enemys = new JComboBox<String>();
		enemys.setFont(new Font("宋体", Font.PLAIN, 30));
		enemys.setBounds(120,60,60,30);
		//
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).equals(user)) {
				continue;
			} else {
				enemys.addItem(players.get(i).getAccount().getId());
			}
		}
		this.add(enemys);
	}
	
	class OKListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			String receiver = (String)enemys.getSelectedItem();
			if(msgLabel.getText().equals("选择要干扰的敌人")){
				// TODO：
				System.out.println("!!!!!!!!!!!!receiver是："+receiver);
				Card card = new NoBroadcasting(AccountDTO.getInstance().getId(), receiver);
				Operation operation = new CardUse(AccountDTO.getInstance().getId(), receiver,card);
				GameControl.getInstance().doOperation(operation);
			}else if(msgLabel.getText().equals("选择要探知的敌人")){
				Operation operation = new Priviledge_GetRole(AccountDTO.getInstance().getId(), receiver);
				GameControl.getInstance().doOperation(operation);
			}
			frame.setVisible(false);
		}
	}
	
	public void paintComponent(Graphics g) {
		Image IMG_MAIN = new ImageIcon("images/img1.jpg").getImage();
		// 绘制游戏界面
		g.drawImage(IMG_MAIN, 0, 0, null);
	}
}
