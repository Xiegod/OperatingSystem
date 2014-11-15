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
	
	//-----Ϊsave��ť�ͽ���رհ�ť��Ӽ��������Ա������ݣ�----
	public void InitListener() {
		
		//----���save��ť������saveData()��������----
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
		
		//----������ڹر�ʱ���ı��Զ����� saveData()��������----
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
	
	//-----�����ı���������ʾ������壨��һ���ı�ʱ���ã�-----
	//-----�õ������ݺ��������ı�����󣬻��������ռ�Ŀ� ------
	public void loadData() {
		int newStartBlock;
		int numberOfBlock = 0;
		//���ļ���ռ�õĿ鲻ֻһ����������ļ���һֱ�������п�����ݶ�ȡ����������ȡ��Ŀ���Ϊδʹ��-----
		while (Finder.disk.block[startBlock / 64][startBlock % 64] != (byte)-1) {
			for (int i = 0; i < 64; i++) {
				System.out.println("In");
				stringToByte[i + numberOfBlock * 64] = Finder.disk.block[startBlock][i];
			}
			newStartBlock = Finder.disk.block[startBlock / 64][startBlock % 64];
			Finder.disk.block[startBlock / 64][startBlock % 64] = 0;   //----��дǰ���----
			startBlock = newStartBlock;
			numberOfBlock++;
		}
		
		for (int i = 0; i < 64; i++) {
			stringToByte[i + numberOfBlock * 64] = Finder.disk.block[startBlock][i];
		}
		
		System.out.println(startBlock+"  " + Finder.disk.block[startBlock / 64][startBlock % 64]);
		Finder.disk.block[startBlock / 64][startBlock % 64] = 0;
		
		//----�Ӵ������ȡ��������byte���ݣ���ʾ�ı���Ҫ��byte����ת��ΪString����---
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
		
		//----������String��ӵ���ʾ�ı�------
		textArea.setText(string);
	}
	
	
	public void saveData() throws IOException {
		string = textArea.getText();
		stringToByte = string.getBytes();
		int startBlock = Catalog_Function.freeBlock();
		int oldStartBlock;
		
		//-----���ı����ݷ�Ϊÿ64��byteһ��洢����һ�����̿顣��һ�����п�洢��ֱ�����һ�����ݲ���64byteΪֹ----
		for (int i = 0; i < (stringToByte.length - 1) / 64; i++) {
			for (int j = 0; j < 64; j++) {
				Finder.disk.block[startBlock][j] = stringToByte[i * 64 + j];
			}
			oldStartBlock = startBlock;
			startBlock = Catalog_Function.freeBlock();
			Finder.disk.block[oldStartBlock / 64][oldStartBlock % 64] = (byte)startBlock;
		}

		
		//------�洢���һ������-----
		if (stringToByte.length == 64) {
			for (int i = 0; i < stringToByte.length; i++) {
				Finder.disk.block[startBlock][i] = stringToByte[i];
			}
		}else {
			for (int i = 0; i < stringToByte.length % 64; i++) {
				Finder.disk.block[startBlock][i] = stringToByte[stringToByte.length - stringToByte.length % 64 + i];
			}
		}
		
		//----���ļ����ȸ�ֵ��Ŀ¼����------
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
