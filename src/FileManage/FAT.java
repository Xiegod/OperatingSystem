package FileManage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class FAT extends JPanel{
	ImageIcon redIcon = new ImageIcon("image/Finder/red.png");
	ImageIcon greenIcon = new ImageIcon("image/Finder/green.png");
	int num[] = new int[128];
	JPanel panel;
	
	public FAT(){
//		titleLabel = new JLabel("----------------------F A T----------------------");

		panel = new JPanel(new GridLayout(8, 16, 5, 5));
		panel.setBackground(Color.WHITE);	
		panel.setBorder(new TitledBorder("磁盘分配表（FAT）"));
		
		for (int i = 0; i < 128; i++) {
			if (i < 64) {
				num[i] = Finder.disk.block[0][i];
			}else {
				num[i] = Finder.disk.block[1][i - 64];
			}
		}
		
		this.setLayout(new BorderLayout());
//		add(titleLabel,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		
		draw();
	}
	
	public void draw() {
		for (int i = 0; i < num.length; i++) {
			if (num[i] == (byte)255) {
				JLabel label = new JLabel();
				label.setIcon(redIcon);
				label.setToolTipText("第 " + i + " 块 下一连接块为 " + num[i]);
				panel.add(label);
				
			}else {
				JLabel label = new JLabel();
				label.setIcon(greenIcon);
				panel.add(label);
			}
		}
	}
	
}
