package DevicesManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class Menu extends JFrame {

	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;
	JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	ArrayList runningList = null;
	ArrayList waitingList = null;
	ArrayList aNum = null;
	ArrayList bNum = null;
	ArrayList cNum = null;
	ArrayList aNum2 = null;
	ArrayList bNum2 = null;
	ArrayList cNum2 = null;
	String []name = new String[10];
	String []name2 = new String[10];
	StringBuilder []status = new StringBuilder[10];
	StringBuilder []status2 = new StringBuilder[10];
	
	public Menu(){
	
		setLookAndFeel();
		JPanel panel1 = new JPanel(new GridLayout(1,2,2,2));
		JPanel panel1_left = new JPanel(new GridLayout(1,2,1,1));
		JPanel panel1_right = new JPanel(new GridLayout(1,6,1,1));
		JButton jbt1 = createBtn("开始");
		JButton jbt2 = createBtn("结束");
		JLabel label1 = createLbl("A",Color.LIGHT_GRAY,Color.RED,Color.blue,15,8,SwingConstants.CENTER);
		JLabel label2 = createLbl("B",Color.LIGHT_GRAY,Color.RED,Color.blue,15,8,SwingConstants.CENTER);
		JLabel label3 = createLbl("C",Color.LIGHT_GRAY,Color.RED,Color.blue,15,8,SwingConstants.CENTER);
		jtf1 = createTfd("1");
		jtf2 = createTfd("2");
		jtf3 = createTfd("2");
		panel1_left.add(jbt1);
		panel1_left.add(jbt2);
		panel1_right.add(label1);
		panel1_right.add(jtf1);
		panel1_right.add(label2);
		panel1_right.add(jtf2);
		panel1_right.add(label3);
		panel1_right.add(jtf3);
		panel1.add(panel1_left);
		panel1.add(panel1_right);
		jbt1.addActionListener(new StartListener());
		jbt2.addActionListener(new EndListener());
		
		JPanel panel2 = new JPanel(new BorderLayout());
		JLabel label4 = createLbl("运行中和已完成的进程：",Color.black,Color.white,Color.orange,13,6,SwingConstants.LEFT);
		scrollPane1 = new JScrollPane();
		panel2.add(label4,BorderLayout.NORTH);
		panel2.add(scrollPane1,BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		JLabel label5 = createLbl("等待中的进程：",Color.black,Color.white,Color.orange,13,6,SwingConstants.LEFT);
		scrollPane2 = new JScrollPane();
		panel3.add(label5,BorderLayout.NORTH);
		panel3.add(scrollPane2,BorderLayout.CENTER);
		
		JPanel panel4 = new JPanel(new GridLayout(2,1));
		panel4.add(panel2);
		panel4.add(panel3);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panel1,BorderLayout.SOUTH);
		panel.add(panel4,BorderLayout.CENTER);
		
		scrollPane1.setViewportView(new JTable(new DefaultTableModel()));
		scrollPane2.setViewportView(new JTable(new DefaultTableModel()));
		
		add(panel);
	}
	
	//为主面板设置皮肤
	public void setLookAndFeel() {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//返回一个美化过的JButton
	public JButton createBtn(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(80, 27));// 设置按钮大小
        btn.setContentAreaFilled(true);// 设置按钮透明
        btn.setBackground(Color.black);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("粗体", Font.ITALIC, 15));// 按钮文本样式
        btn.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
        return btn;
    }
	
	//返回一个美化过的JLabel
	public JLabel createLbl(String text,Color c1,Color c2,Color c3,int i,int j,int k){
		JLabel lbl = new JLabel(text);
		lbl.setOpaque(true);
		lbl.setBackground(c1);
		lbl.setForeground(c2);
		lbl.setFont(new Font("粗体",Font.ITALIC,i));
		lbl.setBorder(new OwnBorder(j,c3));
		lbl.setHorizontalAlignment(k);
		return lbl;
	}
	
	//返回一个去掉边界的JTextField
	public JTextField createTfd(String text){
		JTextField tfd = new JTextField(text);
		tfd.setOpaque(true);
		tfd.setHorizontalAlignment(SwingConstants.CENTER);
		tfd.setBorder(BorderFactory.createLineBorder(Color.GRAY, 0));
		tfd.setFont(new Font("微软雅黑",Font.PLAIN,20));
		tfd.setForeground(Color.green);
		return tfd;
	}
	
	public void manageDevice(){
		int devices = 7+(int)(Math.random()*4);
		runningList = new ArrayList();
		waitingList = new ArrayList();
		
		for(int i=0;i<devices;i++){
			
			Process p0 = new Process("process"+i);
			waitingList.add(p0);
		}
		printWaitingList(waitingList);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!(waitingList.isEmpty())){
			
			while(true){
				if(waitingList.isEmpty()) break;
				int i=0;
				while(i<waitingList.size()){
					Process p1 = (Process)waitingList.get(i);
					if((p1.getNum_a()<=Device.numberOfA)&&(p1.getNum_b()<=Device.numberOfB)&&(p1.getNum_c()<=Device.numberOfC))
					{
						p1.turnToRunning();
						Device.numberOfA -= p1.getNum_a();
						Device.numberOfB -= p1.getNum_b();
						Device.numberOfC -= p1.getNum_c();
						runningList.add(p1);
						waitingList.remove(i);
					}
					else
						i++;
				}
				break;
			}
			printWaitingList(waitingList);
			printRunningList(runningList);
			String s1 = String.valueOf(Device.numberOfA);
			String s2 = String.valueOf(Device.numberOfB);
			String s3 = String.valueOf(Device.numberOfC);
			jtf1.setText(s1);
			jtf2.setText(s2);
			jtf3.setText(s3);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<runningList.size();i++){
				Process p2 = (Process)runningList.get(i);
				String s = new String(p2.getStatus());
				if(s.equals("running"))
					p2.turnToFinished();
			}
			printRunningList(runningList);
			jtf1.setText("1");
			jtf2.setText("2");
			jtf3.setText("2");
			Device.numberOfA = 1;
			Device.numberOfB = 2;
			Device.numberOfC = 2;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//将运行及完成的进程线性表数据显示在上方的JTable上
	public void printRunningList(ArrayList list){
		aNum = new ArrayList();
		bNum = new ArrayList();
		cNum = new ArrayList();
		for(int i=0;i<list.size();i++){
			Process p = (Process)list.get(i);
			String s1 = String.valueOf(p.getNum_a());
			String s2 = String.valueOf(p.getNum_b());
			String s3 = String.valueOf(p.getNum_c());
			aNum.add(s1);
			bNum.add(s2);
			cNum.add(s3);
			name[i] = p.getName();
			status[i] = p.getStatus();
		}
		JTable table = new JTable(new TableModel1());
		scrollPane1.setViewportView(table);
		}
	
	//将等待中的进程线性表数据显示在下方的JTable上
	public void printWaitingList(ArrayList list){
		aNum2 = new ArrayList();
		bNum2 = new ArrayList();
		cNum2 = new ArrayList();
		for(int i=0;i<list.size();i++){
			Process p = (Process)list.get(i);
			String s1 = String.valueOf(p.getNum_a());
			String s2 = String.valueOf(p.getNum_b());
			String s3 = String.valueOf(p.getNum_c());
			aNum2.add(s1);
			bNum2.add(s2);
			cNum2.add(s3);
			name2[i] = p.getName();
			status2[i] = p.getStatus();
		}
		JTable table = new JTable(new TableModel2());
		scrollPane2.setViewportView(table);
	}
	
	//为上方的JTable构造模版
	class TableModel1 extends DefaultTableModel {

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return runningList.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			if (col == 0)
				return name[row];
			else if (col == 1)
				return aNum.get(row);
			else if(col == 2)
				return bNum.get(row);
			else if(col == 3)
				return cNum.get(row);
			else
				return status[row];
		}

		public String getColumnName(int col) {
			if (col == 0)
				return "进程名";
			else if (col == 1)
				return "A设备需要的台数";
			else if(col == 2)
				return "B设备需要的台数";
			else if(col == 3)
				return "C设备需要的台数";
			else
				return "状态";
		}

		//禁止JTable内容被修改
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	}
	
	//为下方的JTable构造模版
	class TableModel2 extends DefaultTableModel {

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return waitingList.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			if (col == 0)
				return name2[row];
			else if (col == 1)
				return aNum2.get(row);
			else if(col == 2)
				return bNum2.get(row);
			else if(col == 3)
				return cNum2.get(row);
			else
				return status2[row];
		}

		public String getColumnName(int col) {
			if (col == 0)
				return "进程名";
			else if (col == 1)
				return "A设备需要的台数";
			else if(col == 2)
				return "B设备需要的台数";
			else if(col == 3)
				return "C设备需要的台数";
			else
				return "状态";
		}

		//禁止JTable内容被修改
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	}
	
	//开始按钮的监听器
	public class StartListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			new Thread(new Runnable(){
				public void run(){
					manageDevice();
					}
				}).start();
		}
		
	}
	
	//结束按钮的监听器
	public class EndListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
		
	}
	
}

//设备类，用来统计当前空闲的设备数量
class Device{
	 
	static int numberOfA = 1;//A设备当前空闲的台数
	static int numberOfB = 2;//B设备当前空闲的台数
	static int numberOfC = 2;//C设备当前空闲的台数
}

//进程类
class Process{
	
	private int num_a;//A设备需要的台数
	private int num_b;//B设备需要的台数
	private int num_c;//C设备需要的台数
	private String name;//进程名
	StringBuilder status = new StringBuilder("waiting");//状态有3种：waiting，running，finished
	
	public Process(String newName){
		num_a = (int)(Math.random()*2);
		num_b = (int)(Math.random()*3);
		num_c = (int)(Math.random()*3);
		name = newName;
	}
	
	public int getNum_a() {
		return num_a;
	}
		
	public int getNum_b() {
		return num_b;
	}
	
	public int getNum_c() {
		return num_c;
	}
	
	public String getName(){
		return name;
	}
	
	public StringBuilder getStatus(){
		return status;
	}
	
	//由waiting状态转化成running状态
	public void turnToRunning(){
		status.replace(0, 7, "running");
	}
	
	//由running状态转化成finished状态
	public void turnToFinished(){
		status.replace(0, 7, "finished");
	}
}

class OwnBorder implements Border{
	private int thickness; //边界线条的厚度
	private Color color; //边界的颜色

	public OwnBorder(int thickness,Color color){
	this.thickness=thickness;
	this.color=color;
	}

	/*实现Border 接口中的第一个方法
	  该方法用于绘制自定义的边界
	*/
	public void paintBorder(Component c, //边界所属的组件对象
		Graphics g, //得到图形上下文引用，用于绘制
		int x, int y, //边界的坐标
		int width, int height) { //边界的宽度、长度
		g.setColor(this.color); //设定颜色
		g.fill3DRect(x,y,width-thickness,thickness,true); //绘制上边界
		g.fill3DRect(x,y+thickness,thickness,height-thickness,true); //绘制左边界
		g.fill3DRect(x+thickness,y+height-thickness,width-thickness,thickness,true); //绘制下边界
		g.fill3DRect(x+width-thickness,y,thickness,height-thickness,true); //绘制右边界
	}
	/*实现Border 接口的第二个方法
	  返回一个Insets 对象的引用
	*/
	public Insets getBorderInsets(Component c){
		return new Insets(thickness,thickness,thickness,thickness);
	}
	/*实现Border 接口的第三个方法
	  这里返回true,表明按照制定的颜色显示边界
	*/
	public boolean isBorderOpaque(){
		return true;
	}

}
