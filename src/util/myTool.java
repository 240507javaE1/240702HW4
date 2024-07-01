package util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPasswordField;

import model.Member;


public class myTool {
	public static void main(String[] args) {
		Member m =myTool.userNow;
		System.out.println(m.getAccount());
		System.out.println(m.getId());
	}
	public static Member userNow;// =(Member) myTool.readFile("whoLogin.txt");
	public static final String IDexample = "或用以下範例 :\nJ223456789" + "\nX223456789" + "\nF223456777" + "\nC223456765"
			+ "\nA123456707" + "\nX123456787" + "\nA123656565";

//Methods,
//saveFile()是save一個Obj到file內
	public static void saveFile(Object o, String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

//readFile()是從file內嘗試Read一個Obj,沒有Obj就null
	public static Object readFile(String filename) {
		Object o = null;
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			o = ois.readObject();// System.out.println(ois.readObject());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return o;
	}

//isIDlegal()是確認passField符合身分證格式&公式
	public static Boolean isIDlegal(JPasswordField pwUserID) {
		char[] chrID = pwUserID.getPassword();
		if (chrID.length != 10)
			return false;
		if (!Character.isUpperCase(chrID[0]))
			return false;
		for (int i = 1; i < chrID.length; i++) {//檢查首位以外全數字,從chrID[1]開始
			if (!Character.isDigit(chrID[i]))
				return false;
		}
		int checksum = 0;
		if (chrID[0] < 73 || chrID[0] == 87) {
			checksum = (chrID[0] - 55) / 10;
			chrID[0] = (char) ((chrID[0] - 55) % 10 + '0');
		} else if (chrID[0] == 73) {
			checksum = (chrID[0] - 39) / 10;
			chrID[0] = (char) ((chrID[0] - 39) % 10 + '0');
		} else if (Math.abs(chrID[0] - 76) < 3) {
			checksum = (chrID[0] - 56) / 10;
			chrID[0] = (char) ((chrID[0] - 56) % 10 + '0');
		} else if (chrID[0] == 79) {
			checksum = (chrID[0] - 44) / 10;
			chrID[0] = (char) ((chrID[0] - 44) % 10 + '0');
		} else if (Math.abs(chrID[0] - 83) < 4 || chrID[0] == 90) {
			checksum = (chrID[0] - 57) / 10;
			chrID[0] = (char) ((chrID[0] - 57) % 10 + '0');
		} else if (chrID[0] < 90) {
			checksum = (chrID[0] - 58) / 10;
			chrID[0] = (char) ((chrID[0] - 58) % 10 + '0');
		}
		for (int i = 0; i < chrID.length; i++)
			checksum += ((chrID[i] - '0') * (9 - i));
		checksum += chrID[chrID.length - 1] - '0';
		if ((checksum % 10) != 0)
			return false;
		else
			return true;
	}
}
