package util;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Duration;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;



public class MyWebThread implements Runnable {
	private Thread thread;
	private WebDriver driver;
	
	private JLabel lblPrice;
	
	
	public MyWebThread(JComboBox cbxItem,JLabel lblPrice) {
		
		this.lblPrice=lblPrice;
		/*
		//JComboBox cbxSelected = (JComboBox)e.getSource();
		//switch (cbxSelected.getSelectedItem().toString()) {
		//case "選項":		btnBanking.setEnabled(false);	return;
		//case "存入":		btnBanking.setEnabled(true);	return;
		//case "取出":		btnBanking.setEnabled(true);	return;
		default:JOptionPane.showMessageDialog(null,"此選擇功能尚未設定");return;
		}
		*/
		
		
		
		
		
		EdgeOptions optionHeadless=new EdgeOptions();
		optionHeadless.addArguments("--headless");
		driver=new EdgeDriver(optionHeadless);
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		
		
		
		cbxItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch(cbxItem.getSelectedItem().toString()) {
					case "2330":driver.get("https://tw.tradingview.com/chart/?symbol=TWSE%3A2330");return;
					case "00940":driver.get("https://tw.tradingview.com/chart/?symbol=TWSE%3A00940");return;
					case "騰訊":driver.get("https://tw.tradingview.com/chart/?symbol=HKEX%3A700");return;
					case "ASML":driver.get("https://tw.tradingview.com/chart/?symbol=EURONEXT%3AASML");return;
					case "NVDA":driver.get("https://tw.tradingview.com/chart/?symbol=NASDAQ%3ANVDA");return;
					default:driver.get("https://github.com/240507javaE1");return;
				}
			}
		});
		
		
			
			
			
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				lblPrice.setText(driver.getTitle());
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
