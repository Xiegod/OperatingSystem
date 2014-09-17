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

public class Terminal extends JFrame {
	TextArea t;
	Finder finder;
	String tempString;
	String[] orderStrings = new String[8];
	
	public Terminal() throws IOException {
		finder = new Finder();
		finder.setVisible(false);
		
		t = new TextArea();
		t.setBackground(Color.black);
		t.setForeground(Color.green);
		add(t,BorderLayout.CENTER);
		
		t.append("SCAU Software Engineering R1 Copyright.\n\n" + finder.textField.getText() + "~ $ ");
		t.setCaretPosition(t.getText().length());
		
		initKeyListener();
	}
	
	public void initKeyListener() {
		
		t.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				String[] tempsStrings = new String[100];
				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_BACK_SPACE) {
					if (t.getText().endsWith("$ ")) {
						e.consume();
					}
				}
				
				if (key == KeyEvent.VK_UP) {
					e.consume();
				}
				
				if (key == KeyEvent.VK_ENTER) {
					e.consume();
					
					String s = t.getText();
					System.out.println(s);
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
		
		String command = orderStrings[0];
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
			delete();
			break;
		
		case "copy":
			copy();
			break;
			
		case "type":
			
			break;

		default:
			t.append("\n    √¸¡Ó≤ª¥Ê‘⁄£°");
			break;
		}
		
		t.append("\n\n" + finder.textField.getText() + "~ $ ");
	}
	
	public void copy() {
		
	}
	
	public void delete() throws IOException {
		List<Catalog> catalogs;
		
		if (orderStrings[1].indexOf("/") == -1) {
			tempString = "C:";
		}else {
			tempString = "C:"+ "/" +orderStrings[1].substring(0,orderStrings[1].lastIndexOf("/"));
		}

		if(Catalog_Function.toDistinationPath(tempString) != 1){			
			t.append("\n  " + tempString + ":No such file or directory");
		}else {
			String string = orderStrings[1].substring(orderStrings[1].lastIndexOf("/") + 1,orderStrings[1].length());		
			if (string.endsWith(".txt")) {
				String string2 = string.substring(0,string.indexOf("."));	
				Boolean flag = false;
				catalogs = Catalog_Function.getFileCatalogs(Explorer.getCDB());
				for (int i = 0; i < catalogs.size(); i++) {
					if (Catalog_Function.rename(string2, catalogs.get(i).getName())) {
						flag = true;
						Catalog_Function.delCatalog(string2, 1);
						return ;
					}
				}
				t.append("\n  " + tempString + ":No such file or directory");
				
			}else {
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
		if (orderStrings[1].indexOf("/") == -1) {
			tempString = "C:";
		}else {
			tempString = "C:"+ "/" +orderStrings[1].substring(0,orderStrings[1].lastIndexOf("/"));
		}

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
	
}
