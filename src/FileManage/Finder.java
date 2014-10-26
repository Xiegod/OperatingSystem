package FileManage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import javax.jws.Oneway;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;


public class Finder extends JFrame{
	ImageIcon qianIcon;
	ImageIcon houIcon;
	ImageIcon txtIcon;
	ImageIcon dirIcon;
	JButton qianButton;
	JButton houButton;
	JButton toButton;
	String textString = new String("C:");
	String tempString = "";
	JTextField textField;
	JScrollPane JSP;
	JPanel panel;
	JPanel contentPanel;
	static JPanel fatPanel;
	static ImageIcon redIcon = new ImageIcon("image/Finder/red.png");
	static ImageIcon greenIcon = new ImageIcon("image/Finder/green.png");
	static int num[] = new int[128];
	
	JPopupMenu createpm,txtpm,dirpm;
	String chooseName;
	int chooseType;
	
	Stack<Integer> stack = new Stack<Integer>();
	
	public static Disk disk;
	

	public Finder() throws IOException{
		disk = new Disk();
		InitGUI();

		InitPanelListener();
		InitDirAndTxtListener();
	}
	
	public void InitGUI(){
		qianIcon = new ImageIcon("image/Finder/back.png");
		houIcon  = new ImageIcon("image/Finder/forward.png");
		qianButton = new JButton(qianIcon);
		houButton  = new JButton(houIcon);
		toButton = new JButton("ת��");
		JLabel label = new JLabel("��ַ��");
		textField = new JTextField(30);
		textField.setText(textString);
		//----ǰ�����˹��ܼ��͵�ַ������------
		JToolBar toolBar = new JToolBar(); 
		
		qianButton.setBorder(null);
		qianButton.setContentAreaFilled(false);
		qianButton.setOpaque(false);
		
		houButton.setBorder(null);
		houButton.setContentAreaFilled(false);
		houButton.setOpaque(false);
		
		toolBar.add(qianButton);
		toolBar.add(houButton);
		
		JPanel p = new JPanel();
		p.add(label);
		p.add(textField);
		p.add(toButton);
		
		JPanel toolPanel = new JPanel(new BorderLayout());
		toolPanel.add(toolBar,BorderLayout.NORTH);
		toolPanel.add(p,BorderLayout.SOUTH);
		
		//----�ڿɻ������������������------
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(640,290));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		readFile(2);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(toolPanel,BorderLayout.NORTH);
		contentPanel.add(panel,BorderLayout.CENTER);
		
		fatPanel = new JPanel();
		fatPanel.setPreferredSize(new Dimension(640,100));
		InitFAT();
		
		setLayout(new BorderLayout());
		add(contentPanel,BorderLayout.CENTER);
		add(fatPanel,BorderLayout.SOUTH);
	}

	//----FAT��ʼ������---
	public void InitFAT(){

		
		fatPanel.setVisible(false);
		fatPanel.removeAll();
		fatPanel.setVisible(true);
		
		fatPanel = new JPanel(new GridLayout(8, 16, 5, 5));
		fatPanel.setBackground(Color.WHITE);	
		fatPanel.setBorder(new TitledBorder("���̷����FAT��"));
		

		draw();
	}
	
	//----FAT��ͼ����----
	public static void draw(){
		for (int i = 0; i < 128; i++) {
			if (i < 64) {
				num[i] = Finder.disk.block[0][i];
			}else {
				num[i] = Finder.disk.block[1][i - 64];
			}
		}
		
		for (int i = 0; i < num.length; i++) {
			if (num[i] != 0) {
				JLabel label = new JLabel();
				label.setIcon(redIcon);
				label.setToolTipText("�� " + i + " �� ��һ���ӿ�Ϊ " + num[i]);
				fatPanel.add(label);
				
			}else {
				JLabel label = new JLabel();
				label.setIcon(greenIcon);
				label.setToolTipText("�� " + i + " �� ��һ���ӿ�Ϊ " + num[i]);
				fatPanel.add(label);
			}
		}
	}

 	public void InitPanelListener(){
 		
 		//
		qianButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//----�ж��Ƿ��˾�ͷ��û���򷵻���һ��----
				if (textString.length() > 3) {        
					tempString = textString;
					textString = textString.substring(0,textString.lastIndexOf("/"));
					textField.setText(textString);				
				}
				
				Catalog_Function.toDistinationPath(textString);
				readFile(Explorer.getCDB()); 
			}
		});

		houButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempString != "") {
						Catalog_Function.toDistinationPath(tempString);
						textField.setText(tempString);					
						readFile(Explorer.getCDB()); 
						tempString = "";
					}				
				} 
		}); 

		toButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Catalog_Function.toDistinationPath(textField.getText());
				if (num == 2) {
					JOptionPane.showMessageDialog(null, "�ļ������ڣ�");
				}else if (num == 3) {
					JOptionPane.showMessageDialog(null, "��������ȷ���ļ�·���� \n  �� C:\\ ��ͷ");
				}
				readFile(Explorer.getCDB());
			}
		});
		
		createpm = new JPopupMenu();
		
		//----�½� �����˵�-----
		JMenuItem newfileItem = new JMenuItem("�½��ı�");
		newfileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = JOptionPane.showInputDialog("�����ı�����").trim();
				try {
					if(Catalog_Function.createCatalog(nameString, 1,Explorer.getCDB()) == 1){
						fatPanel.removeAll();
						draw();
						fatPanel.setVisible(false);
						fatPanel.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				readFile(Explorer.getCDB());
			}
		});
		
		JMenuItem newDirItem = new JMenuItem("�½��ļ���");
		newDirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = JOptionPane.showInputDialog("������Ŀ¼����");
	//			System.out.println(nameString.length() + "  " + nameString);
				if (nameString != null) {
					try {
						if(Catalog_Function.createCatalog(nameString, 0,Explorer.getCDB()) == 1){
							fatPanel.removeAll();
							draw();
							fatPanel.setVisible(false);
							fatPanel.setVisible(true);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
					readFile(Explorer.getCDB());
				}
			}
		});
		
		createpm.add(newDirItem);
		createpm.add(newfileItem);
				
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					createpm.show(panel, e.getX(), e.getY());
				}
			}
		});
				
	}

 	//----�ı���Ŀ¼ѡ�� �����˵�----
	public void InitDirAndTxtListener(){
		
		txtpm = new JPopupMenu();
		dirpm = new JPopupMenu();
		
		//----Ŀ¼---
		JMenuItem delDir = new JMenuItem("ɾ��Ŀ¼");
		delDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Catalog_Function.delCatalog(chooseName,chooseType) == 1){
						fatPanel.removeAll();
						draw();
						fatPanel.setVisible(false);
						fatPanel.setVisible(true);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readFile(Explorer.getCDB());
			}
		});
		dirpm.add(delDir);
		
		//----�ı�----
		JMenuItem delFileItem = new JMenuItem("ɾ���ļ�");
		delFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Catalog_Function.delCatalog(chooseName,chooseType) == 1){
						fatPanel.removeAll();
						draw();
						fatPanel.setVisible(false);
						fatPanel.setVisible(true);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel.setVisible(false);
				readFile(Explorer.getCDB());
				panel.setVisible(true);
			}
		});

		txtpm.add(delFileItem);

	}
	
	//----�Ӹ����Ĵ��̿��ȡ�����ļ��������������--------
	public void readFile(int CDB){
		panel.setVisible(false);
		panel.removeAll();
		panel.setVisible(true);
		dirIcon = new ImageIcon("image/Finder/dir.png");
		txtIcon = new ImageIcon("image/Finder/TXT.png");
		
		List<Catalog> catalogs = Catalog_Function.getAllCatalogs(CDB);
//		System.out.println(catalogs.size() + "readFile");
		for (int i = 0; i < catalogs.size(); i++) {
			final Catalog catalog = catalogs.get(i);
			if (catalog.getProperty() == 0) {
				final JLabel dirLabel = new JLabel(catalog.getName());
				dirLabel.setIcon(dirIcon);
				dirLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2){
						textString = textString + "/" + catalog.getName().trim();
						textField.setText(textString);
						
						Explorer.addCDB();
						Explorer.setCDB(catalog.getStartBlock());
						readFile(Explorer.getCDB());
					}
					if (e.getButton() == MouseEvent.BUTTON3) {
						chooseName = catalog.getName();
						chooseType = 0;
						dirpm.show(dirLabel, e.getX(), e.getY());
					}
				}
				});;
				
				panel.setVisible(false);
				panel.add(dirLabel);
				panel.setVisible(true);
			}
			if (catalog.getProperty() == 1) {
				final JLabel fileLabel = new JLabel(catalog.getName() +"."+ catalog.getType());
				fileLabel.setIcon(txtIcon);
				fileLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount() == 2){
							readFile(Explorer.getCDB());
							Txt txt = new Txt(catalog);
							txt.setSize(400, 500);
							txt.setVisible(true);
							
						}
						if (e.getButton() == MouseEvent.BUTTON3) {
							chooseName = catalog.getName();
							chooseType = 1;
							txtpm.show(fileLabel,e.getX(),e.getY());
						}
					}
				});
				
				panel.setVisible(false);
				panel.add(fileLabel);
				panel.setVisible(true);
			}
		}
	}
}
