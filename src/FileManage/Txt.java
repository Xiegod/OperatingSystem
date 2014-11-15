package FileManage;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Txt extends JFrame{
	JPanel panel;
	TextArea textArea;
	JButton saveButton;
	String string;
	byte[] stringToByte;
	int startBlock;
	int flag = 0;
	Catalog _catalog;
	int stringLength;
	
	public Txt(Catalog catalog){
		_catalog = catalog;
		stringLength = catalog.getLength();
		startBlock = catalog.getStartBlock();
		
		stringToByte = new byte[800];
		panel = new JPanel();
		textArea = new TextArea();
		saveButton = new JButton("save");
	
		loadData();
		
		panel.add(saveButton,BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(panel,BorderLayout.SOUTH);
		add(textArea,BorderLayout.CENTER);
		setTitle(catalog.getName());
		
		InitListener();
	}
	
	//-----为save按钮和界面关闭按钮添加监听（用以保存数据）----
	public void InitListener() {
		
		//----点击save按钮，调用saveData()保存数据----
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					flag = 1;
					saveData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		});
		
		//----点击窗口关闭时，文本自动调用 saveData()保存数据----
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					System.out.println("In closing");
					if(flag == 0)
						saveData();
					Finder.fatPanel.removeAll();
					Finder.draw();
					Finder.fatPanel.setVisible(false);
					Finder.fatPanel.setVisible(true);
				} catch (IOException e1) {	
					e1.printStackTrace();
				}
			}
		});
	}
	
	//-----导入文本内容至显示内容面板（打开一个文本时调用）-----
	//-----该导入数据函数，将文本导入后，会清空其所占的块 ------
	public void loadData() {
		int newStartBlock;
		int numberOfBlock = 0;
		//若文件所占用的块不只一个，则根据文件链一直将其所有块的内容读取，并将被读取后的块致为未使用-----
		while (Finder.disk.block[startBlock / 64][startBlock % 64] != (byte)-1) {
			for (int i = 0; i < 64; i++) {
				System.out.println("In");
				stringToByte[i + numberOfBlock * 64] = Finder.disk.block[startBlock][i];
			}
			newStartBlock = Finder.disk.block[startBlock / 64][startBlock % 64];
			Finder.disk.block[startBlock / 64][startBlock % 64] = 0;   //----重写前清空----
			startBlock = newStartBlock;
			numberOfBlock++;
		}
		
		for (int i = 0; i < 64; i++) {
			stringToByte[i + numberOfBlock * 64] = Finder.disk.block[startBlock][i];
		}
		
		System.out.println(startBlock+"  " + Finder.disk.block[startBlock / 64][startBlock % 64]);
		Finder.disk.block[startBlock / 64][startBlock % 64] = 0;
		
		//----从磁盘里读取的内容是byte数据，显示文本需要将byte数据转化为String类型---
		string = new String(stringToByte);
		byte[] bytes = new byte[2];
		bytes[0] = bytes[1] = -1;
		String temp = new String(bytes);
		System.out.println(string);
		string.substring(0,stringLength);
		if (string.indexOf(temp) != -1) {
			string = string.substring(0,string.indexOf(temp));
		}else if(string.indexOf("?") != -1){
			string = string.substring(0,string.indexOf("?"));
		}
		
		//----将内容String添加到显示文本------
		textArea.setText(string);
	}
	
	
	public void saveData() throws IOException {
		string = textArea.getText();
		stringToByte = string.getBytes();
		int startBlock = Catalog_Function.freeBlock();
		int oldStartBlock;
		
		//-----将文本内容分为每64个byte一组存储，即一个磁盘块。找一个空闲块存储。直到最后一组数据不足64byte为止----
		for (int i = 0; i < (stringToByte.length - 1) / 64; i++) {
			for (int j = 0; j < 64; j++) {
				Finder.disk.block[startBlock][j] = stringToByte[i * 64 + j];
			}
			oldStartBlock = startBlock;
			startBlock = Catalog_Function.freeBlock();
			Finder.disk.block[oldStartBlock / 64][oldStartBlock % 64] = (byte)startBlock;
		}

		
		//------存储最后一组数据-----
		if (stringToByte.length == 64) {
			for (int i = 0; i < stringToByte.length; i++) {
				Finder.disk.block[startBlock][i] = stringToByte[i];
			}
		}else {
			for (int i = 0; i < stringToByte.length % 64; i++) {
				Finder.disk.block[startBlock][i] = stringToByte[stringToByte.length - stringToByte.length % 64 + i];
			}
		}
		
		//----将文件长度赋值到目录长度------
		for (int i = 0; i < 8; i++) {
			Catalog tempCatalog = new Catalog(Finder.disk.block[Explorer.getCDB()], i * 8);
			if (tempCatalog.getName().equals(_catalog.getName())) {
				System.out.println("stringToByte.length " + stringToByte.length);
				System.out.println(i +" " + tempCatalog.getName());
				Finder.disk.block[Explorer.getCDB()][i * 8 + 7] = (byte) stringToByte.length;
				System.out.println(i +"byte " + Finder.disk.block[Explorer.getCDB()][i * 8 + 7]);
				break;
			}
		}
		
		Catalog_Function.writeInDisk();
		Finder.disk.load();
	}
	
}
