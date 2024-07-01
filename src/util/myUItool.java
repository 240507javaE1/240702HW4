package util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class myUItool {
//setFont() 可以對所有JComponent & 其child設同樣Font
	public static void setFont(Component comp, Font font) {
		comp.setFont(font);
		if (comp instanceof Container) {
			for (Component child : ((Container) comp).getComponents()) {
				setFont(child, font);
			}
		}
	}
//setlblFontAndCenter 可以對所有JComponent & 其child設同樣Font
		public static void setlblFontAndCenter(Component comp, Font font) {
			comp.setFont(font);
			if(comp instanceof Container) {
				for (Component child : ((Container) comp).getComponents()) {
					if(child instanceof JLabel) {
						((JLabel) child).setHorizontalAlignment(SwingConstants.CENTER);
						setFont(child, font);
					}
				}
			}
		}

// lblnowTime()是在Label上每秒更新現在時間
	public static void setlblTimerNow(JLabel lblnowTime) {
		//Timer timerOnlbl = new Timer(1000,e -> lblnowTime.setText(
				//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		new Timer(1000,e->lblnowTime.setText(new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()))).start();
		return;
	}

// markPassword()是CheckBox開關password遮蔽功能
	public static void markPassword(JCheckBox chkbx, JPasswordField pwField) {
		chkbx.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chkbx.isSelected())
					pwField.setEchoChar((char) 0);
				else
					pwField.setEchoChar('●');
			}
		});
	}

// editTxts()是CheckBox開關一群Jtxt的編輯功能
	public static void editTxts(JCheckBox chkbx, JTextField[] txts) {
		chkbx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chkbx.isSelected()) {
					for (JTextField elements:txts) {
						elements.setEnabled(true);
						elements.setEditable(true);
					}
				}else {
					for (JTextField elements:txts) {
						elements.setEnabled(false);
						elements.setEditable(false);
					}
				}
			}
		});
	}
}
