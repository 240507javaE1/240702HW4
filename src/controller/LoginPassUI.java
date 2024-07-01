package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.member.CRUDmemberUI;
import controller.trader.TraderUI;
import dao.ClientDAO;
import dao.MemberDaoAndImpl;
import dao.OrderDaoAndImpl;
import model.Client;
import model.Dealt;
import model.Member;
import model.Order;
import util.myTool;
import util.myUItool;

public class LoginPassUI extends JFrame implements ActionListener,MouseListener {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPassUI frame = new LoginPassUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private final Member userNow;
	private final List<String> ListTableFunctionName = List.of("功能1");
	private final List<Class> ListTableFunctionType=List.of(JButton.class);
	private List<JButton> listBtn1= new ArrayList<JButton>(); 
	
	private List<Order> listOrder= new ArrayList<Order>();
	private List<Dealt> listDealt= new ArrayList<Dealt>();
	private List<Member> listMember= new ArrayList<Member>();
	
	private MyTableModel mytblModel;
	private JTable myTable;
	private JScrollPane myScrollPane;
	
	private JTextField txtBanking;
	private JComboBox cbxBanking;
	private JButton btnBanking,btnMember;
	private JLabel lbltoBtnMember;
	private JPanel pnUnderTable;
	/**
	 * Create the frame.
	 */
	public LoginPassUI() {
		super("LoginPass UI");
		this.userNow = myTool.userNow;
		List<JButton> btns=new ArrayList();
//set Container & Component
		JPanel pnMain = new JPanel();	setContentPane(pnMain);
			JLabel lblhead1=new JLabel("帳戶戶頭:" + userNow.getAccount());
			JLabel lblhead2=new JLabel("歡迎: " + userNow.getName() + " 登入");
			JButton btnSelfData = new JButton("查詢/修改本人資料");	btns.add(btnSelfData);
			JLabel lbltimeNow=new JLabel();	myUItool.setlblTimerNow(lbltimeNow);
			JButton btnTrade = new JButton("交易下單");	btns.add(btnTrade);
			JButton btnOrder = new JButton("委託查詢");	btns.add(btnOrder);
			JButton btnDealt = new JButton("成交查詢");	btns.add(btnDealt);
			JButton btnStock = new JButton("庫存查詢");	btns.add(btnStock);
			JButton btnBank = new JButton("存款查詢");	btns.add(btnBank);

		pnUnderTable = new JPanel();
			String[] ListBanking= {"選項","存入","取出"};
			cbxBanking = new JComboBox(ListBanking);
			//String[] ListCurrency= {"台幣","港幣","歐元","美金"};
			//JComboBox cbxCurrency = new JComboBox(ListCurrency);
			txtBanking = new JTextField(20);
			btnBanking = new JButton("更新存款");btns.add(btnBanking);btnBanking.setEnabled(false);
		
		JButton btnPrint = new JButton("列印當前Table");	btns.add(btnPrint);
		lbltoBtnMember=new JLabel("請點這行字消失後多點3下");	
		btnMember = new JButton("會員管理");	btns.add(btnMember);
//set table & put into ScrollPane
		mytblModel=new MyTableModel();
		myTable = new JTable(mytblModel);
		myScrollPane = new JScrollPane(myTable);
		myTable.setFillsViewportHeight(true);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto-resize
        //myTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering
		myScrollPane.setBorder(new TitledBorder("Scroll標題"));
//put Component into Container, with myGridBag setting
		pnMain.setLayout(new GridBagLayout());
		GridBagConstraints myGrid = new GridBagConstraints();
		myGrid.fill = GridBagConstraints.BOTH;
		myGrid.weightx=1;	myGrid.gridx = 0;
		pnMain.add(lblhead1, myGrid);
		pnMain.add(lblhead2, myGrid);
		pnMain.add(btnSelfData, myGrid);
		pnMain.add(lbltimeNow, myGrid);
		pnMain.add(btnTrade, myGrid);
		pnMain.add(btnOrder, myGrid);
		pnMain.add(btnDealt, myGrid);
		pnMain.add(btnStock, myGrid);
		pnMain.add(btnBank, myGrid);
		myGrid.ipady = 200;
		pnMain.add(myScrollPane, myGrid);
		myGrid.ipady = 0;
		pnMain.add(pnUnderTable, myGrid);
			pnUnderTable.add(cbxBanking);
			//pnUnderTable.add(cbxCurrency);
			pnUnderTable.add(txtBanking);
			pnUnderTable.add(btnBanking);
		
		pnMain.add(btnPrint, myGrid);
		pnMain.add(new JPanel(), myGrid);
		pnMain.add(lbltoBtnMember, myGrid);
		pnMain.add(new JPanel(), myGrid);
		pnMain.add(btnMember, myGrid);btnMember.setVisible(false);
//set Listener to trigger/fire event
		for(JButton element:btns) {
			element.addActionListener(this);
		}
		cbxBanking.addActionListener(this);
		lbltoBtnMember.addMouseListener(this);
		btnBank.doClick();
		myTable.addMouseListener(new TableButtonMouseAdapter(myTable));
// set this Jframe Font & Size
		myUItool.setlblFontAndCenter(pnMain,new Font("微软雅黑", Font.BOLD, 16));
		this.pack();		setBounds(100, 100, 800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//Fire when any ActionListener (include button click)
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btnClicked = (JButton)e.getSource();
			switch (btnClicked.getText()) {
			case "查詢/修改本人資料":	new CRUDmemberUI(this).setVisible(true);	return;
			case "交易下單":			new TraderUI().setVisible(true);			return;
			case "委託查詢":
				Field[] orderFields = Order.class.getDeclaredFields();
				List<String> listOrderFieldName = new ArrayList();
				List<Class> listOrderFieldType = new ArrayList();
				for(Field fieldElement:orderFields) { 
					listOrderFieldName.add(fieldElement.getName());
					listOrderFieldType.add(fieldElement.getType());
		        }
				mytblModel.setColName(listOrderFieldName);
				mytblModel.setColType(listOrderFieldType);
				listOrder =new OrderDaoAndImpl().findAll();
				mytblModel.setList(listOrder);
				myTable.getColumn("功能1").setCellRenderer(new TableButtonRender());
				listBtn1.clear();
			    for(int i=0; i<listOrder.size(); i++){
		            JButton btn = new JButton("刪改");
		            btn.addActionListener(this);
		            listBtn1.add(btn);
		        }	
			    pnUnderTable.setVisible(false);return;
			case "存款查詢":
				Field[] bankFields = Member.class.getDeclaredFields();
				List<String> listBankFieldName = new ArrayList();
				List<Class> listBankFieldType = new ArrayList();
				for(Field fieldElement:bankFields) { 
					listBankFieldName.add(fieldElement.getName());
					listBankFieldType.add(fieldElement.getType());
		        }
				mytblModel.setColName(listBankFieldName);
				mytblModel.setColType(listBankFieldType);
				mytblModel.setList(List.of(userNow));
				myTable.getColumn("功能1").setCellRenderer(new TableButtonRender());
				listBtn1.clear();
			    for(int i=0; i<List.of(userNow).size(); i++){
		            JButton btn = new JButton("刪改");
		            btn.addActionListener(this);
		            listBtn1.add(btn);
		        }
			    pnUnderTable.setVisible(true);return;
			case "會員管理":
				Field[] memberFields = Member.class.getDeclaredFields();
				List<String> listMemberFieldName = new ArrayList();
				List<Class> listMemberFieldType = new ArrayList();
				for(Field fieldElement:memberFields) { 
					listMemberFieldName.add(fieldElement.getName());
					listMemberFieldType.add(fieldElement.getType());
		        }
				mytblModel.setColName(listMemberFieldName);
				mytblModel.setColType(listMemberFieldType);
				listMember =new MemberDaoAndImpl().findAll();
				mytblModel.setList(listMember);
				myTable.getColumn("功能1").setCellRenderer(new TableButtonRender());
				listBtn1.clear();
			    for(int i=0; i<listMember.size(); i++){
		            JButton btn = new JButton("刪單");
		            btn.addActionListener(this);
		            listBtn1.add(btn);
		        }	
			    pnUnderTable.setVisible(false);return;
			case "更新存款":
				if(btnBanking.isEnabled()) {
					switch(cbxBanking.getSelectedItem().toString()) {
					case "存入":
						if(userNow.getSavings()+Double.valueOf(txtBanking.getText()) >= 0) {
							userNow.setSavings(userNow.getSavings()+Double.valueOf(txtBanking.getText()));
						}else JOptionPane.showMessageDialog(null,"取出超過餘額，請重填");break;
					case "取出":
						if(userNow.getSavings()-Double.valueOf(txtBanking.getText()) >= 0) {
							userNow.setSavings(userNow.getSavings()-Double.valueOf(txtBanking.getText()));
						}else JOptionPane.showMessageDialog(null,"取出超過餘額，請重填");break;
					default:JOptionPane.showMessageDialog(null,"請選 存入 或 取出");return;
					}
					new MemberDaoAndImpl().updateBanking(userNow);
					txtBanking.setText("");
				}
			    mytblModel.setList(List.of(userNow));
			    myTable.getColumn("功能1").setCellRenderer(new TableButtonRender());return;
			case "列印當前Table":
				try {
					myTable.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}return;
			default:JOptionPane.showMessageDialog(null,"此按鈕功能尚未設定");return;
			}
		}else if(e.getSource() instanceof JComboBox) {
			JComboBox cbxSelected = (JComboBox)e.getSource();
			switch (cbxSelected.getSelectedItem().toString()) {
			case "選項":		btnBanking.setEnabled(false);	return;
			case "存入":		btnBanking.setEnabled(true);	return;
			case "取出":		btnBanking.setEnabled(true);	return;
			default:JOptionPane.showMessageDialog(null,"此選擇功能尚未設定");return;
			}
		}
	}
	
	class MyTableModel extends AbstractTableModel {
		private List<String> colName=List.of("功能2");
		private List<Class> colType;//=List.of(JButton.class,JButton.class);
		private List listOftable;//=new ArrayList<>(List.of("AAA","BBB"));
		// public boolean isCellEditable(int row,int col) {return true;}
		@Override public String getColumnName(int id) {return colName.get(id);}
		@Override public Class<?> getColumnClass(int id) {return colType.get(id);}
		@Override public int getColumnCount() {return colName.size();}
		@Override public int getRowCount() {return listOftable.size();}
		@Override public Object getValueAt(int rowId, int colId) {
			switch(listOftable.get(0).getClass().getSimpleName()) {
				case "Order":
					switch(colId) {
						case 0:return listBtn1.get(rowId);
						case 1:	return ((Order) listOftable.get(rowId)).getId();
						case 2:	return ((Order) listOftable.get(rowId)).getStockNo();
						case 3:	return ((Order) listOftable.get(rowId)).get委託狀態();
						case 4:	return ((Order) listOftable.get(rowId)).get盤別();
						case 5:	return ((Order) listOftable.get(rowId)).get交易別();
						case 6:	return ((Order) listOftable.get(rowId)).get條件();
						case 7:	return ((Order) listOftable.get(rowId)).get委託價();
						case 8:	return ((Order) listOftable.get(rowId)).get委託股數();
						case 9:	return ((Order) listOftable.get(rowId)).get成交股數();
						case 10:return ((Order) listOftable.get(rowId)).get委託時間();
						case 11:return ((Order) listOftable.get(rowId)).get委託書號();
						case 12:return ((Order) listOftable.get(rowId)).get交易日期();
						case 13:return ((Order) listOftable.get(rowId)).get幣別();
						default:return "Error";
					}
				case "Member":
					switch(colId) {
						case 0:	return listBtn1.get(rowId);
						case 1:	return ((Member) listOftable.get(rowId)).getId();
						case 2:	return ((Member) listOftable.get(rowId)).getName();
						case 3:	return ((Member) listOftable.get(rowId)).getUserID();
						case 4:	return ((Member) listOftable.get(rowId)).getPassword();
						case 5:	return ((Member) listOftable.get(rowId)).getAccount();
						case 6:	return ((Member) listOftable.get(rowId)).getSavings();
						case 7:	return ((Member) listOftable.get(rowId)).getAddress();
						case 8:	return ((Member) listOftable.get(rowId)).getPhone();
						default:return "Error";
					}
				default:
					JOptionPane.showMessageDialog(null,"Class/Model用Table呈現有誤");
					return "Error";
			}
		}
		public void setColName(List<String> colName) {
			List<String> colNameAll=new ArrayList<>();
			Stream.of(ListTableFunctionName,colName).forEach(colNameAll::addAll);
			this.colName=colNameAll;
			//fireTableStructureChanged();fireTableDataChanged();
		}
		public void setColType(List<Class> colType) {
			List<Class> colTypeAll=new ArrayList<>();
			Stream.of(ListTableFunctionType,colType).forEach(colTypeAll::addAll);
			this.colType=colTypeAll;
			//fireTableStructureChanged();fireTableDataChanged();
		}
		public void setList(List listOftable) {
			this.listOftable= listOftable;
	        fireTableStructureChanged();//fireTableDataChanged();
		}
		public List getListOftable() {
	        return listOftable;
	    }
	}

	class TableButtonRender implements TableCellRenderer {
		@Override public Component getTableCellRendererComponent(JTable table,Object value
		,boolean isSelected,boolean hasFocus,int row,int column) {return (JButton) value;}
	}

	class TableButtonMouseAdapter extends MouseAdapter {
		private final JTable table;

		public TableButtonMouseAdapter(JTable table) {
			this.table = table;
		}

		public void mouseClicked(MouseEvent e) {
			int col = table.getColumnModel().getColumnIndexAtX(e.getX());//get col of the button
			int row = e.getY() / table.getRowHeight(); //get row of the button
			// *Checking the row or column is valid or not
			if (row<table.getRowCount() && row>=0 && col<table.getColumnCount() && col>=0){
				Object value = table.getValueAt(row, col);
				if (value instanceof JButton) {// perform a click event
					((JButton) value).doClick();
				}
			}
		}
	}
//event for every addMouseListener(mouseClicked)
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JLabel) {
			JLabel lblClicked = (JLabel)e.getSource();
			switch (lblClicked.getText()) {
				case "請點這行字消失後多點3下":lblClicked.setText("   ");return;
				case "   ":	lblClicked.setText("  ");	return;
				case "  ":	lblClicked.setText(" ");	return;
				case " ":	lblClicked.setVisible(false);	btnMember.setVisible(true);	return;
				default:JOptionPane.showMessageDialog(null,"此點擊功能尚未設定");return;
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
