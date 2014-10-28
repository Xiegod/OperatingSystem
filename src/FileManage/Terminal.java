package FileManage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.RenderingHints.Key;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.AbstractDocument.Content;

public class Terminal extends JFrame {
	TextArea t;
	Finder finder;
	String tempString;
	String[] orderStrings = new String[8];
	
	public Terminal() throws IOException {
		finder = new Finder();    //----ȡ�ö��ļ�ϵͳ�Ĳ���Ȩ��----
		finder.setVisible(false);
		
		t = new TextArea();      //-----���textArea���ı�����---
		t.setBackground(Color.black);
		t.setForeground(Color.green);
		add(t,BorderLayout.CENTER);
		
		//----�����Terminal�����棬װB�õ�----
		t.append("SCAU Software Engineering R1 Copyright.\n\n" + finder.textField.getText() + "~ $ ");
		t.setCaretPosition(t.getText().length());
		
		initKeyListener();
	}
	
	
	public void initKeyListener() {
		
		t.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				String[] tempsStrings = new String[100];
				int key = e.getKeyCode();
				
				//----���������겻��Խ�����ʼ��$ ---
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_BACK_SPACE) {
					if (t.getText().endsWith("$ ")) {
						e.consume();
					}
				}
				//----��������ģʽ�£�up����Ч----
				if (key == KeyEvent.VK_UP) {
					e.consume();
				}
				//----�ӵ��س���������ʼִ������-----
				if (key == KeyEvent.VK_ENTER) {
					e.consume();
					
					//----ȡ������textArea������ݵ����һ�У��������һ������----
					String s = t.getText();
//					System.out.println(s);
					tempsStrings = s.split("\n");
					String order = tempsStrings[tempsStrings.length - 1];
					
					String temp = order.substring(order.indexOf("$ ") + 2, order.length());
					System.out.println(temp);
					try {
						execute(temp);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	public void execute(String orderString) throws IOException{

		orderStrings = orderString.split(" ");
		
		String command = orderStrings[0];   //----��ȡ����---
		System.out.println(command);
		
		switch (command) {
		case "delete":
			delete();
			break;
		case "create":
			create();
			break;
		case "cd":
			tempString = finder.textField.getText() + "/" +orderStrings[1];  
			if(Catalog_Function.toDistinationPath(tempString) != 1){			
				t.append("\n  " + tempString + ":No such file or directory");
			}else {
				finder.textField.setText(tempString);				
			}
			break;
			
		case "..":
			tempString = finder.textField.getText();
			if (tempString.length() > 3) {
				tempString = tempString.substring(0,tempString.lastIndexOf("/"));
				Catalog_Function.toDistinationPath(tempString);
				finder.textField.setText(tempString);
			}
			break;
		
		case "mkdir":
			Catalog_Function.createCatalog(orderStrings[1], 0, Explorer.getCDB());	
			
			break;
			
		case "rmdir":
			rmdir();
			break;
		
		case "copy":
			copy();
			break;
			
		case "type":
			type();
			break;
			
		case "help":
			showHelp();
			break;

		default:
			t.append("\n    ������ڣ�");
			break;
		}
		
		t.append("\n\n" + finder.textField.getText() + "~ $ ");
	}
	
	public void showHelp() {
		t.append("\n    help                  -> ��ȡָ����ʾ");
		t.append("\n    ..             	  	  -> �˻���һ��Ŀ¼");
		t.append("\n    cd a           		  -> �ı䵱ǰĿ¼Ϊa");
		t.append("\n    create aa/bb(.txt)    -> ��aa(aaΪ�Ѵ���)Ŀ¼�´���bb (bb������Ŀ¼��Ҳ������.txt) ");
		t.append("\n    mkdir a        		  -> �ڵ�ǰĿ¼����aĿ¼");
		t.append("\n    delete aa/bb(.txt)    -> ɾ��aaĿ¼�µ�bb (bb������Ŀ¼��Ҳ������.txt)");
		t.append("\n    redir a        		  -> ɾ����ǰĿ¼�µ�aĿ¼");
		t.append("\n    type a.txt      	  -> ��a.txt�ĵ�");
		t.append("\n    copy a/b.txt c/		  -> ����b.txt��cĿ¼��");
	}
	
	public void type() {
		List<Catalog> catalogs;
		
		stringToForm();

		if(Catalog_Function.toDistinationPath(tempString) != 1){			
			t.append("\n  " + tempString + ":No such file or directory");
		}else {
			String string = orderStrings[1].substring(orderStrings[1].lastIndexOf("/") + 1,orderStrings[1].length());		
			if (string.endsWith(".txt")) {
				String string2 = string.substring(0,string.indexOf("."));	
				catalogs = Catalog_Function.getFileCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(string2, catalogs.get(i).getName())) {
						finder.readFile(Explorer.getCDB());
						Txt txt = new Txt(catalogs.get(i));
						txt.setSize(400, 500);
						txt.setVisible(true);
						return ;
					}
				}
			t.append("\n  " + tempString + ":No such file or directory");	
			}
		}
	}
	
	public void copy() throws IOException {
		List<Catalog> catalogs;
		String contentString = "";
		String fileString = "";
		String fileString2 = "";
		boolean isLoad = false;
		
		stringToForm();

		if(Catalog_Function.toDistinationPath(tempString) != 1){			
			t.append("\n  " + tempString + ":No such file or directory");
		}else{
			 fileString = orderStrings[1].substring(orderStrings[1].lastIndexOf("/") + 1,orderStrings[1].length());		
			if (fileString.endsWith(".txt")) {
				fileString2 = fileString.substring(0,fileString.indexOf("."));	
				catalogs = Catalog_Function.getFileCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(fileString2, catalogs.get(i).getName())) {
						finder.readFile(Explorer.getCDB());
						Txt txt = new Txt(catalogs.get(i));;
						txt.setVisible(false);
						contentString = txt.textArea.getText();
						isLoad = true;					
						txt.saveData();
					
					}
				}
			if(!isLoad)
				t.append("\n  " + tempString + ":No such file or directory");	
			}
		}
		
		if (isLoad) {
			orderStrings[1] = orderStrings[2];
			stringToForm();
			
			if(Catalog_Function.toDistinationPath(tempString) != 1){			
				t.append("\n  " + tempString + "�洢·������ȷ");
			}else{
				Catalog_Function.createCatalog(fileString2, 1, Explorer.getCDB());			
				catalogs = Catalog_Function.getFileCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(fileString2, catalogs.get(i).getName())) {
						finder.readFile(Explorer.getCDB());
						Txt txt = new Txt(catalogs.get(i));
						txt.setVisible(false);
						txt.textArea.setText(contentString);
						txt.saveData();
						return ;
					}
				}
			}
		}
	}
	
	public void rmdir() throws IOException{
		List<Catalog> catalogs;
		
		tempString = finder.textField.getText();
		
		if(Catalog_Function.toDistinationPath(tempString) != 1){			
			t.append("\n  " + tempString + ":No such file or directory");
		}else {
			String string = orderStrings[1];		
				//Ŀ¼ɾ��
				catalogs =Catalog_Function.getDirCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(string, catalogs.get(i).getName())) {
						Catalog_Function.delCatalog(string, 0);
					}
				}
		}
		
	}
	
	public void delete() throws IOException {
		List<Catalog> catalogs;
		
		stringToForm();

		if(Catalog_Function.toDistinationPath(tempString) != 1){			
			t.append("\n  " + tempString + ":No such file or directory");
		}else {
			String string = orderStrings[1].substring(orderStrings[1].lastIndexOf("/") + 1,orderStrings[1].length());		
			if (string.endsWith(".txt")) {		//�ļ�ɾ��
				String string2 = string.substring(0,string.indexOf("."));	
				catalogs = Catalog_Function.getFileCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(string2, catalogs.get(i).getName())) {
						Catalog_Function.delCatalog(string2, 1);
						return ;
					}
				}
				t.append("\n  " + tempString + ":No such file or directory");
				
			}else {				//Ŀ¼ɾ��
				catalogs =Catalog_Function.getDirCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(string, catalogs.get(i).getName())) {
						Catalog_Function.delCatalog(string, 0);
					}
				}
			}
		}
	}
	
	public void create() throws IOException {
		stringToForm();
		
		if(Catalog_Function.toDistinationPath(tempString) != 1){			
			t.append("\n  " + tempString + ":No such file or directory");
		}else {
			finder.textField.setText(tempString);
			String string = orderStrings[1].substring(orderStrings[1].lastIndexOf("/") + 1,orderStrings[1].length());
			if (string.endsWith(".txt")) {
				Catalog_Function.createCatalog(string.substring(0,string.indexOf(".")), 1, Explorer.getCDB());
			}else {
				Catalog_Function.createCatalog(string, 0, Explorer.getCDB());
			}
		}
	}
	
	//----�������������·����ʽ��Ϊ �ļ�ϵͳ��ʶ���·����ʽ----
	public void stringToForm(){
		if (orderStrings[1].indexOf("/") == -1) {
			tempString = "C:";
		}else {
			tempString = "C:"+ "/" +orderStrings[1].substring(0,orderStrings[1].lastIndexOf("/"));
		}
	}
}
