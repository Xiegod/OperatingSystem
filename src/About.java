import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FileManage.Finder;


public class About extends JFrame{
	JPanel contentPanel;
	JLabel label,label2,label3;
	JButton initDiskButton;
	
	About(){
		contentPanel = new JPanel();
		label  = new JLabel("12软件学院:\n");
		label2 = new JLabel("组长：谢晋叶");
		label3 = new JLabel("组员：吴海彬   毕键华   冯耀中  叶柯棋  陈远旋");
		initDiskButton = new JButton("磁盘初始化");
		
		add(contentPanel);
		
		contentPanel.setLayout(new GridLayout(4,1));
		contentPanel.add(label);
		contentPanel.add(label2);
		contentPanel.add(label3);
		contentPanel.add(initDiskButton);
		
		initDiskButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Finder.disk.init();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
