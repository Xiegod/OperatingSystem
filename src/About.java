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
		label  = new JLabel("12���ѧԺ:\n");
		label2 = new JLabel("�鳤��л��Ҷ");
		label3 = new JLabel("��Ա���⺣��   �ϼ���   ��ҫ��  Ҷ����  ��Զ��");
		initDiskButton = new JButton("���̳�ʼ��");
		
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
