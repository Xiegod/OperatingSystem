import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class Finder extends JFrame{
	ImageIcon qianIcon;
	ImageIcon houIcon;
	JButton qianButton;
	JButton houButton;
	JButton toButton;
	JTextField textField;
	JScrollPane JSP;
	JPanel panel;
	
	JPopupMenu createpm,txtpm,dirpm;
	
	Disk disk;
	
	public Finder(){
		try {
			disk = new Disk();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InitGUI();
		InitListener();
		
	}
	public void InitGUI(){
		qianIcon = new ImageIcon("image/Finder/qian.png");
		houIcon  = new ImageIcon("image/Finder/hou.png");
		qianButton = new JButton(qianIcon);
		houButton  = new JButton(houIcon);
		toButton = new JButton("转到");
		JLabel label = new JLabel("地址栏");
		textField = new JTextField(60);

		//----前进后退功能键和地址栏布局------
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
		
		//----在可滑动面板中添加内容面板------
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(760,490));
		JSP = new JScrollPane(panel);
		
		setLayout(new BorderLayout());
		add(toolPanel,BorderLayout.NORTH);
		add(JSP,BorderLayout.CENTER);
		
	}

	public void InitListener(){
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
		
		//----新建 弹出菜单-----
		JMenuItem newfileItem = new JMenuItem("新建文本");
		newfileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建文本");
			}
		});
		JMenuItem newDirItem = new JMenuItem("新建文件夹");
		newDirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		createpm.add(newfileItem);
		createpm.add(newDirItem);
		
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					createpm.show(panel, e.getX(), e.getY());
				}
			}
		});
		
		//----文本及目录选择 弹出菜单----
		JMenuItem delDir = new JMenuItem("删除目录");
		delDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		dirpm.add(delDir);
		
		JMenuItem delFileItem = new JMenuItem("删除文件");
		delFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		JMenuItem writeFileItem = new JMenuItem("写入");
		writeFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		txtpm.add(delFileItem);
		txtpm.add(writeFileItem);
				
	}
}
