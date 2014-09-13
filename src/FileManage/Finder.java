package FileManage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

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


public class Finder extends JFrame{
	ImageIcon qianIcon;
	ImageIcon houIcon;
	ImageIcon txtIcon;
	ImageIcon dirIcon;
	JButton qianButton;
	JButton houButton;
	JButton toButton;
	JTextField textField;
	JScrollPane JSP;
	JPanel panel;
	
	JPopupMenu createpm,txtpm,dirpm;
	String chooseName;
	
	public static Disk disk;

	public Finder() throws IOException{
		disk = new Disk();
		InitGUI();
		InitPanelListener();
		InitDirAndTxtListener();
	}
	public void InitGUI(){
		qianIcon = new ImageIcon("image/Finder/qian.png");
		houIcon  = new ImageIcon("image/Finder/hou.png");
		qianButton = new JButton(qianIcon);
		houButton  = new JButton(houIcon);
		toButton = new JButton("ת��");
		JLabel label = new JLabel("��ַ��");
		textField = new JTextField(60);

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
		panel.setPreferredSize(new Dimension(760,490));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		readFile(2);
		JSP = new JScrollPane(panel);
		
		setLayout(new BorderLayout());
		add(toolPanel,BorderLayout.NORTH);
		add(JSP,BorderLayout.CENTER);
		
	}

	public void InitPanelListener(){
		qianButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

		houButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		toButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("This is a toBotton");
			}
		});
		
		createpm = new JPopupMenu();
		txtpm = new JPopupMenu();
		dirpm = new JPopupMenu();
		
		//----�½� �����˵�-----
		JMenuItem newfileItem = new JMenuItem("�½��ı�");
		newfileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = JOptionPane.showInputDialog("�����ı�����");
				try {
					Catalog_Function.createCatalog(nameString, 1,Explorer.getCDB());
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
				try {
					Catalog_Function.createCatalog(nameString, 0,Explorer.getCDB());
				} catch (IOException e1) {
					e1.printStackTrace();
				}		
				panel.setVisible(false);
				readFile(Explorer.getCDB());
				panel.setVisible(true);
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
	
	public void InitDirAndTxtListener(){
		//----�ı���Ŀ¼ѡ�� �����˵�----
		JMenuItem copyDirItem = new JMenuItem("����");
		copyDirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		JMenuItem delDir = new JMenuItem("ɾ��Ŀ¼");
		delDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Catalog_Function.delCatalog(chooseName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readFile(Explorer.getCDB());
			}
		});
		dirpm.add(delDir);
		dirpm.add(copyDirItem);
		
		JMenuItem copyFileItem = new JMenuItem("����");
		JMenuItem delFileItem = new JMenuItem("ɾ���ļ�");
		delFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Catalog_Function.delCatalog(chooseName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel.setVisible(false);
				readFile(Explorer.getCDB());
				panel.setVisible(true);
			}
		});
		JMenuItem writeFileItem = new JMenuItem("д��");
		writeFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		txtpm.add(delFileItem);
		txtpm.add(copyFileItem);
		txtpm.add(writeFileItem);
	}
	
	//----�Ӹ����Ĵ��̿��ȡ�����ļ�--------
	public void readFile(int CDB){
		panel.setVisible(false);
		panel.removeAll();
		panel.setVisible(true);
		dirIcon = new ImageIcon("image/Finder/dir.png");
		txtIcon = new ImageIcon("image/Finder/TXT.png");
		
		List<Catalog> catalogs = Catalog_Function.getAllCatalogs(CDB);
		System.out.println(catalogs.size() + "readFile");
		for (int i = 0; i < catalogs.size(); i++) {
			final Catalog catalog = catalogs.get(i);
			if (catalog.getProperty() == 0) {
				final JLabel dirLabel = new JLabel(catalog.getName());
				dirLabel.setIcon(dirIcon);
				dirLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2){
						
					}
					if (e.getButton() == MouseEvent.BUTTON3) {
						chooseName = catalog.getName();
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
							
						}
						if (e.getButton() == MouseEvent.BUTTON3) {
							chooseName = catalog.getName();
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
