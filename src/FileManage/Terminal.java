package FileManage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.RenderingHints.Key;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Terminal extends JFrame {
	TextArea t;
	Finder finder;
	
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
		String tempString;
		String[] orderStrings = new String[8];
		orderStrings = orderString.split(" ");
		
		String command = orderStrings[0];
		System.out.println(command);
		
		switch (command) {
		case "cd":
			tempString = finder.textField.getText() + "/" +orderStrings[1];
			if(Catalog_Function.toDistinationPath(tempString) != 1){			
				t.append("\n    文件不存在！");
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
			System.out.println("In2");
			Catalog_Function.createCatalog(orderStrings[1], 0, Explorer.getCDB());	
			
			break;

		default:
			t.append("\n    命令不存在！");
			break;
		}
		
		t.append("\n\n" + finder.textField.getText() + "~ $ ");
	}
	
}
