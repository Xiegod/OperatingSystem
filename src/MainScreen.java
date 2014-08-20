import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainScreen extends JFrame{
	private ImageIcon backgroundIcon = new ImageIcon("image/1.jpg");
	private JLabel backgroundLabel = new JLabel(backgroundIcon);
	private JPanel mainPanel,buttonPanel;
	
	private ImageIcon icon1,icon2,icon3,icon4,icon5,icon6,icon7;
	private JButton button1,button2,button3,button4,button5,button6,button7;
	private ButtonListener buttonListener;
	
	MainScreen(){
		mainPanel = new JPanel();
		backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backgroundLabel,new Integer(Integer.MIN_VALUE));
		mainPanel.setOpaque(false);
		this.setContentPane(mainPanel);
		
		InitButtonPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
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
		
		button1.setBorder(null);		//…Ë÷√±ﬂΩÁÕ∏√˜
		button1.setOpaque(false);
		button2.setBorder(null);
		button2.setOpaque(false);
		button3.setBorder(null);
		button3.setOpaque(false);
		button4.setBorder(null);
		button4.setOpaque(false);
		button5.setBorder(null);
		button5.setOpaque(false);
		button6.setBorder(null);
		button6.setOpaque(false);
		button7.setBorder(null);
		button7.setOpaque(false);
		
		
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(button7);
		
		buttonListener = new ButtonListener();
		button1.addActionListener(buttonListener);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainScreen mainScreen = new MainScreen();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		mainScreen.setLocationRelativeTo(null);
		mainScreen.setSize(dimension);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.setResizable(false);
		mainScreen.setVisible(true);
	}

	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				Finder frame = new Finder();
				frame.setSize(300,300);
				frame.setVisible(true);
			}else if (e.getSource() == button2) {
				
			}
		}
		
	}
}
