import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DevicesManager.Menu;
import FileManage.Disk;
import FileManage.Explorer;
import FileManage.Finder;
import FileManage.Terminal;
import MemoryManager.MemoryManager;
import ProcessManage.MainThread;
import ProcessManage.MainUI;
import ProcessManage.SubThread;


public class MainScreen extends JFrame{
	private ImageIcon backgroundIcon = new ImageIcon("image/1.jpg");
	private JLabel backgroundLabel = new JLabel(backgroundIcon);
	private JPanel mainPanel,buttonPanel;
	
	private ImageIcon icon1,icon2,icon3,icon4,icon5,icon6,icon7;
	private JButton button1,button2,button3,button4,button5,button6,button7;
	private ButtonListener buttonListener;
	
	static Disk disk;
	
	MainScreen(){
		//----设置主面板透明，使其可以显示背景图片-----
		mainPanel = new JPanel();
		backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backgroundLabel,new Integer(Integer.MIN_VALUE));
		mainPanel.setOpaque(false);
		this.setContentPane(mainPanel);
		
		//----初始化软件按钮--------
		InitButtonPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
		
		//----进行软件使用必要的初始化（读取磁盘，初始化磁盘块号追踪器）------
		try {
			disk = new Disk();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Explorer explorer = new Explorer();
	}
	
	private void InitButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		
		icon1 = new ImageIcon("image/Finder.png");
		icon2 = new ImageIcon("image/process.png");
		icon3 = new ImageIcon("image/notes.png");
		icon4 = new ImageIcon("image/memoryManager.png");
		icon5 = new ImageIcon("image/devicesManager.png");
		icon6 = new ImageIcon("image/terminal.png");
		icon7 = new ImageIcon("image/preference2.png");
		
		button1 = new JButton(icon1);
		button2 = new JButton(icon2);
		button3 = new JButton(icon3);
		button4 = new JButton(icon4);
		button5 = new JButton(icon5);
		button6 = new JButton(icon6);
		button7 = new JButton(icon7);
		
		button1.setBorder(null);		//设置边界透明
		button1.setContentAreaFilled(false);
		button1.setOpaque(false);
		button2.setBorder(null);
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button3.setBorder(null);
		button3.setContentAreaFilled(false);
		button3.setOpaque(false);
		button4.setBorder(null);
		button4.setContentAreaFilled(false);
		button4.setOpaque(false);
		button5.setBorder(null);
		button5.setContentAreaFilled(false);
		button5.setOpaque(false);
		button6.setBorder(null);
		button6.setContentAreaFilled(false);
		button6.setOpaque(false);
		button7.setBorder(null);
		button7.setContentAreaFilled(false);
		button7.setOpaque(false);
		
		
		buttonPanel.add(button1);
		buttonPanel.add(button2);
//		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(button7);
		
		buttonListener = new ButtonListener();
		button1.addActionListener(buttonListener);
		button2.addActionListener(buttonListener);
		button3.addActionListener(buttonListener);
		button4.addActionListener(buttonListener);
		button5.addActionListener(buttonListener);
		button6.addActionListener(buttonListener);
		button7.addActionListener(buttonListener);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainScreen mainScreen = new MainScreen();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		mainScreen.setSize(1200,800);
		mainScreen.setLocation(0, 50);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.setResizable(false);
		mainScreen.setVisible(true);
	}

	//----所有软件入口button按钮公用一个listener类-----
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				Finder frame = null;
				try {
					frame = new Finder();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setSize(640,700);
				frame.setVisible(true);
			}else if (e.getSource() == button2) {
				MainUI frame = new MainUI();
				frame.setSize(550,600);
				frame.setVisible(true);
			}else if (e.getSource() == button3) {
			
			}else if (e.getSource() == button4) {
				MemoryManager Mframe =new MemoryManager();
				Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
				Mframe.setLocation((int)(dm.getWidth()-580)/2,(int)(dm.getHeight()-600)/2);
				Mframe.setSize(580,600);
				Mframe.setVisible(true);
			}else if (e.getSource() == button5) {
				Menu a = new Menu();
				a.setLocationByPlatform(true);
//				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.setSize(600, 400);
				a.setVisible(true);	
				
			}else if (e.getSource() == button6) {
				Terminal frame = null;
				try {
					frame = new Terminal();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setSize(600,500);
				frame.setVisible(true);
				frame.setTitle("Terminal");
			}else if (e.getSource() == button7) {
				About frame = new About();
				frame.setSize(300,140);
				Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((int)(dm.getWidth()-580)/2,(int)(dm.getHeight()-600)/2);
				frame.setVisible(true);
			}
		}
		
	}
}
