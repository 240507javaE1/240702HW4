package controller;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.member.CRUDmemberUI;
import dao.MemberDaoAndImpl;
import model.Member;
import util.myTool;
import util.myUItool;

public class LoginUI extends JFrame implements ActionListener {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JPasswordField pwUserID,pwPassword;
	/**
	 * Create the frame.
	 */
	public LoginUI() {
		super("Login UI");
		myTool.userNow=null;
//set Container & Component
		JPanel pnMain = new JPanel();	setContentPane(pnMain);
			JLabel lbltimeNow=new JLabel();	myUItool.setlblTimerNow(lbltimeNow);
			JCheckBox chkIDshow = new JCheckBox("顯示身分證號");
			JCheckBox chkPWshow = new JCheckBox("顯示登入密碼");
			pwUserID = new JPasswordField();
			pwPassword = new JPasswordField();
			JButton btnLeft = new JButton("註冊");
			JButton btnRight = new JButton("登入");
//put Component into Container
		pnMain.setLayout(new GridLayout(5,2));
		pnMain.add(new JLabel("交易模擬App"));	pnMain.add(chkIDshow);
		pnMain.add(lbltimeNow);					pnMain.add(chkPWshow);
		pnMain.add(new JLabel("輸入身分證號"));	pnMain.add(pwUserID);
		pnMain.add(new JLabel("輸入登入密碼"));	pnMain.add(pwPassword);
		pnMain.add(btnLeft);					pnMain.add(btnRight);
//set Listener to trigger/fire event
		myUItool.markPassword(chkIDshow,pwUserID);
		myUItool.markPassword(chkPWshow,pwPassword);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
//set this Jframe Font & Size
		myUItool.setFont(this,new Font("微软雅黑", Font.BOLD, 16));
		this.pack();	setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//Fire when any button click
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton)e.getSource();
		switch (btnClicked.getText()) {
			case "註冊":
				new CRUDmemberUI(this).setVisible(true);
				return;
			case "登入":
				String userID=new String(pwUserID.getPassword());
				String password=new String(pwPassword.getPassword());
				Member m=new MemberDaoAndImpl().findByIDpw(userID,password);
				if(m!=null) {
					JOptionPane.showMessageDialog(null,"歡迎\n"+m.getName()+"\n登入");
					myTool.userNow=m;
					myTool.saveFile(m, "whoLogin.txt");
					
					new LoginPassUI().setVisible(true);
					dispose();return;
				}else {
					JOptionPane.showMessageDialog(null,"帳號密碼出錯，請重打\n或註冊");
					return;
				}
			default:
        		JOptionPane.showMessageDialog(null,"按鈕功能出錯，請找開發者");
        		return;
		}
	}
}