package controller.trader;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.member.CRUDmemberUI;
import model.Member;
import util.MyWebThread;
import util.myTool;
import util.myUItool;

public class TraderUI extends JFrame implements ActionListener,MouseListener  {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraderUI frame = new TraderUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private final Member userNow;
	/**
	 * Create the frame.
	 */
	public TraderUI() {
		super("Trader UI");
		this.userNow = myTool.userNow;
		List<JButton> btns=new ArrayList();

//set Container & Component
		JPanel pnMain = new JPanel();	setContentPane(pnMain);
		String[] itemList= {"請選商品","2330","00940","騰訊","NVDA","ASML"};
		JComboBox cbxItem = new JComboBox(itemList);
		//JLabel lblName = new JLabel("台GG");
		JLabel lblPrice = new JLabel("900");
		JButton btnLot = new JButton("整股");	btns.add(btnLot);
		//JButton btnAH = new JButton("盤後");		btns.add(btnAH);
		//JButton btnOdd = new JButton("零股");	btns.add(btnOdd);
		JLabel lbltimeNow=new JLabel();	myUItool.setlblTimerNow(lbltimeNow);
		JButton btnROD = new JButton("ROD");	btns.add(btnROD);
		JButton btnIOC = new JButton("IOC");	btns.add(btnIOC);
		JButton btnFOK = new JButton("FOK");	btns.add(btnFOK);
		JButton btnLimit = new JButton("限價");	btns.add(btnLimit);
		JButton btnMO = new JButton("市價");		btns.add(btnMO);
		String[] limitList= {"條件取價","現價","漲停","平盤","跌停"};
		JComboBox cbxLimitCondition = new JComboBox(limitList);
		JButton btnBuy = new JButton("買進");	btns.add(btnBuy);
		JButton btnSell = new JButton("賣出");	btns.add(btnSell);
		JLabel lblUnit=new JLabel("<html>1單位<br>1000股</html>");
		JTextField txtUnit = new JTextField(10);
		JButton btnUAdd = new JButton("＋量");	btns.add(btnUAdd);
		JButton btnUCut = new JButton("－量");	btns.add(btnUCut);
		JTextField txtPrice = new JTextField(10);
		JButton btnPAdd = new JButton("＋價");	btns.add(btnPAdd);
		JButton btnPCut = new JButton("－價");	btns.add(btnPCut);
		JButton btnLeft = new JButton("確認下單");	btns.add(btnLeft);
		JButton btnRight = new JButton("回主選單");	btns.add(btnRight);
		
		
		
		
		
		
//put Component into Container
		pnMain.setLayout(new GridBagLayout());
		GridBagConstraints myGrid = new GridBagConstraints();
		//myGrid.fill = GridBagConstraints.BOTH;
		myGrid.weightx=1;	//myGrid.gridx = 0; //ipady=
		myGrid.gridy=0;	pnMain.add(new JLabel("商品"),myGrid);	pnMain.add(cbxItem,myGrid);	myGrid.gridwidth=2;	pnMain.add(lblPrice,myGrid); myGrid.gridwidth=1;
		myGrid.gridy=1;	pnMain.add(new JLabel("交易"),myGrid);	pnMain.add(btnLot,myGrid);	//pnMain.add(btnAH,myGrid);	pnMain.add(btnOdd,myGrid);
		myGrid.gridy=2;	pnMain.add(new JLabel("時間"),myGrid);	myGrid.gridwidth = 3;	pnMain.add(lbltimeNow,myGrid);	myGrid.gridwidth = 1;
		myGrid.gridy=3;	pnMain.add(new JLabel("條件"),myGrid);	pnMain.add(btnROD,myGrid);	pnMain.add(btnIOC,myGrid);	pnMain.add(btnFOK,myGrid);
		myGrid.gridy=4;	pnMain.add(new JLabel("類別"),myGrid);	pnMain.add(btnLimit,myGrid);pnMain.add(btnMO,myGrid);	pnMain.add(cbxLimitCondition,myGrid);
		myGrid.gridy=5;	pnMain.add(new JLabel("買賣"),myGrid);	pnMain.add(btnBuy,myGrid);	pnMain.add(btnSell,myGrid);	pnMain.add(lblUnit,myGrid);
		myGrid.gridy=6;	pnMain.add(new JLabel("單位"),myGrid);	pnMain.add(txtUnit,myGrid);	pnMain.add(btnUCut,myGrid);	pnMain.add(btnUAdd,myGrid);
		myGrid.gridy=7;	pnMain.add(new JLabel("價格"),myGrid);	pnMain.add(txtPrice,myGrid);pnMain.add(btnPCut,myGrid);	pnMain.add(btnPAdd,myGrid);
		myGrid.gridy=8;	myGrid.gridwidth = 2;	pnMain.add(btnLeft,myGrid);	pnMain.add(btnRight,myGrid);	myGrid.gridwidth = 4;
		myGrid.gridy=9;	pnMain.add(new JLabel("商品-騰訊(港):交易時間為台灣時間9:30~16:00"),myGrid);
		myGrid.gridy=10;pnMain.add(new JLabel("商品-ASML(歐):交易時間為台灣時間15:00~23:30"),myGrid);
		myGrid.gridy=11;pnMain.add(new JLabel("商品-NVDA(美):交易時間為台灣時間21:30~04:00"),myGrid);
//set Listener to trigger/fire event
		for(JButton element:btns) {
			element.addActionListener(this);
		}
//WebThreadTest
		new MyWebThread(cbxItem,lblPrice);
// set this Jframe Font & Size
		myUItool.setFont(this, new Font("微软雅黑", Font.BOLD, 16));
		this.pack();		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
//Fire Event when any ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton)e.getSource();
		switch (btnClicked.getText()) {
		case "回主選單":
			dispose();	return;
		default:
    		JOptionPane.showMessageDialog(null,"按鈕功能尚未設定");
    		return;
		}
		
	}
//Event for any addMouseListener(mouseClicked)
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btnClicked = (JButton)e.getSource();
			switch (btnClicked.getText()) {
				//case "請點這行字消失後多點3下":lblClicked.setText("   ");return;
				//case "   ":	lblClicked.setText("  ");	return;
				//case "  ":	lblClicked.setText(" ");	return;
				//case " ":	lblClicked.setVisible(false);	btnMember.setVisible(true);	return;
				default:JOptionPane.showMessageDialog(null,"點擊功能尚未設定");return;
	    	}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
