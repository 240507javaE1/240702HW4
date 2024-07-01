package controller.member;

import java.awt.Color;
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
import javax.swing.JTextField;

import controller.LoginPassUI;
import controller.LoginUI;
import dao.MemberDaoAndImpl;
import model.Member;
import util.myTool;
import util.myUItool;

public class CRUDmemberUI extends JFrame implements ActionListener {
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUDmemberUI frame = new CRUDmemberUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	private JFrame parentUI;
	private Member userNow;
	private JPasswordField pwUserID;
	private JTextField txtPassword,txtName,txtAccount,txtAddress,txtPhone;
	/**
	 * Create the frame.
	 */
	public CRUDmemberUI(JFrame parentUI) {
		super("CRUDmemberUI");
		this.parentUI = parentUI;
		this.userNow = myTool.userNow;
//set Container & Component
		JPanel pnMain = new JPanel();	setContentPane(pnMain);
			JCheckBox chkIDshow = new JCheckBox("顯示身分證號");
			pwUserID = new JPasswordField();
			pwUserID.setEchoChar((char) 0);
			txtPassword = new JTextField(20);
			txtName = new JTextField(20);
			txtAccount = new JTextField(20);
			txtAddress = new JTextField(20);
			txtPhone = new JTextField(20);
			JButton btnLeft = new JButton("<返回前頁");
			JButton btnRight = new JButton("註冊");
//put Component into Container
		pnMain.setLayout(new GridLayout(10,2));
		pnMain.add(new JLabel("資料填寫"));	pnMain.add(chkIDshow);
		pnMain.add(new JLabel("尊姓大名"));	pnMain.add(txtName);
		pnMain.add(new JLabel("身分證號"));	pnMain.add(pwUserID);
		pnMain.add(new JLabel("登入密碼"));	pnMain.add(txtPassword);
		pnMain.add(new JLabel("戶頭號碼"));	pnMain.add(txtAccount);
		pnMain.add(new JLabel("戶籍地址"));	pnMain.add(txtAddress);
		pnMain.add(new JLabel("連絡電話"));	pnMain.add(txtPhone);
		pnMain.add(btnLeft);				pnMain.add(btnRight);
//if userLogin, change memberCreate View to memberUpdate View
		if(userNow != null) {
			chkIDshow.setText("修改資料");
        	pwUserID.setText(userNow.getUserID());
        	pwUserID.setEnabled(false);
        	pwUserID.setEditable(false);
        	txtPassword.setText(userNow.getPassword());
            txtName.setText(userNow.getName());		
            txtAccount.setText(userNow.getAccount());
            txtAddress.setText(userNow.getAddress());
            txtPhone.setText(userNow.getPhone());
            btnRight.setText("更新資料");
            JButton btnDel=new JButton("刪除銷戶");	pnMain.add(btnDel);
            btnDel.setForeground(new Color(255, 0, 0));
            btnDel.addActionListener(this);
            JTextField[] txts={txtName,txtPassword,txtAccount,txtAddress,txtPhone};
            myUItool.editTxts(chkIDshow,txts);
        }else myUItool.markPassword(chkIDshow,pwUserID);
//set Listener to trigger/fire event
		chkIDshow.setSelected(true);
        btnLeft.addActionListener(this);
        btnRight.addActionListener(this);
//set this Jframe Font & Size
        myUItool.setFont(this,new Font("微软雅黑", Font.BOLD, 16));
		this.pack();	//setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
//Fire when any button click
	@Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        switch (btnClicked.getText()) {
        	case "<返回前頁":
        		dispose();return;
        	case "註冊":
        		if(myTool.isIDlegal(pwUserID)) {
            		String userID=new String(pwUserID.getPassword());
    				Member m=new MemberDaoAndImpl().findByID(userID);
    				if(m!=null) {
    					JOptionPane.showMessageDialog(null,"此身分證號已有人註冊，是您本人嗎?"+myTool.IDexample);
    					return;
    				}else {
    					String name = txtName.getText();
    					String password = txtPassword.getText();
    					String account = txtAccount.getText();
    					String address = txtAddress.getText();
    					String phone = txtPhone.getText();
    					new MemberDaoAndImpl().addMember(name,userID,password,account,address,phone);
    					JOptionPane.showMessageDialog(null,"帳號開戶成功，請回登入頁重新登入");
    					dispose();return;
    				}
            	}else JOptionPane.showMessageDialog(null,"身分證號格式錯誤，"+myTool.IDexample);
        		return;
        	case "更新資料":
        		userNow.setName(txtName.getText());
				userNow.setPassword(txtPassword.getText());
				userNow.setAccount(txtAccount.getText());
				userNow.setAddress(txtAddress.getText());
				userNow.setPhone(txtPhone.getText());
				new MemberDaoAndImpl().updateMember(userNow);
				JOptionPane.showMessageDialog(null,"資料已修改，請登出後再登入");
				new LoginUI().setVisible(true);
				parentUI.dispose();
				dispose();return;
        	case "刪除銷戶":
        		new MemberDaoAndImpl().deleteMember(userNow);
				JOptionPane.showMessageDialog(null,"此帳戶已刪除，請登出後再選擇其他帳戶登入");
				new LoginUI().setVisible(true);
				parentUI.dispose();
        		dispose();return;
        	default:
        		JOptionPane.showMessageDialog(null,"按鈕功能出錯，請找開發者");
        		return;
        }
    }
}