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
		JButton jbt1 = createBtn("��ʼ");
		JButton jbt2 = createBtn("����");
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
		JLabel label4 = createLbl("�����к�����ɵĽ��̣�",Color.black,Color.white,Color.orange,13,6,SwingConstants.LEFT);
		scrollPane1 = new JScrollPane();
		panel2.add(label4,BorderLayout.NORTH);
		panel2.add(scrollPane1,BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		JLabel label5 = createLbl("�ȴ��еĽ��̣�",Color.black,Color.white,Color.orange,13,6,SwingConstants.LEFT);
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
	
	//Ϊ���������Ƥ��
	public void setLookAndFeel() {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//����һ����������JButton
	public JButton createBtn(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(80, 27));// ���ð�ť��С
        btn.setContentAreaFilled(true);// ���ð�ť͸��
        btn.setBackground(Color.black);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("����", Font.ITALIC, 15));// ��ť�ı���ʽ
        btn.setMargin(new Insets(0, 0, 0, 0));// ��ť������߿����
        return btn;
    }
	
	//����һ����������JLabel
	public JLabel createLbl(String text,Color c1,Color c2,Color c3,int i,int j,int k){
		JLabel lbl = new JLabel(text);
		lbl.setOpaque(true);
		lbl.setBackground(c1);
		lbl.setForeground(c2);
		lbl.setFont(new Font("����",Font.ITALIC,i));
		lbl.setBorder(new OwnBorder(j,c3));
		lbl.setHorizontalAlignment(k);
		return lbl;
	}
	
	//����һ��ȥ���߽��JTextField
	public JTextField createTfd(String text){
		JTextField tfd = new JTextField(text);
		tfd.setOpaque(true);
		tfd.setHorizontalAlignment(SwingConstants.CENTER);
		tfd.setBorder(BorderFactory.createLineBorder(Color.GRAY, 0));
		tfd.setFont(new Font("΢���ź�",Font.PLAIN,20));
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
	
	//�����м���ɵĽ������Ա�������ʾ���Ϸ���JTable��
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
	
	//���ȴ��еĽ������Ա�������ʾ���·���JTable��
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
	
	//Ϊ�Ϸ���JTable����ģ��
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
				return "������";
			else if (col == 1)
				return "A�豸��Ҫ��̨��";
			else if(col == 2)
				return "B�豸��Ҫ��̨��";
			else if(col == 3)
				return "C�豸��Ҫ��̨��";
			else
				return "״̬";
		}

		//��ֹJTable���ݱ��޸�
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	}
	
	//Ϊ�·���JTable����ģ��
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
				return "������";
			else if (col == 1)
				return "A�豸��Ҫ��̨��";
			else if(col == 2)
				return "B�豸��Ҫ��̨��";
			else if(col == 3)
				return "C�豸��Ҫ��̨��";
			else
				return "״̬";
		}

		//��ֹJTable���ݱ��޸�
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	}
	
	//��ʼ��ť�ļ�����
	public class StartListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			new Thread(new Runnable(){
				public void run(){
					manageDevice();
					}
				}).start();
		}
		
	}
	
	//������ť�ļ�����
	public class EndListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
		
	}
	
}

//�豸�࣬����ͳ�Ƶ�ǰ���е��豸����
class Device{
	 
	static int numberOfA = 1;//A�豸��ǰ���е�̨��
	static int numberOfB = 2;//B�豸��ǰ���е�̨��
	static int numberOfC = 2;//C�豸��ǰ���е�̨��
}

//������
class Process{
	
	private int num_a;//A�豸��Ҫ��̨��
	private int num_b;//B�豸��Ҫ��̨��
	private int num_c;//C�豸��Ҫ��̨��
	private String name;//������
	StringBuilder status = new StringBuilder("waiting");//״̬��3�֣�waiting��running��finished
	
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
	
	//��waiting״̬ת����running״̬
	public void turnToRunning(){
		status.replace(0, 7, "running");
	}
	
	//��running״̬ת����finished״̬
	public void turnToFinished(){
		status.replace(0, 7, "finished");
	}
}

class OwnBorder implements Border{
	private int thickness; //�߽������ĺ��
	private Color color; //�߽����ɫ

	public OwnBorder(int thickness,Color color){
	this.thickness=thickness;
	this.color=color;
	}

	/*ʵ��Border �ӿ��еĵ�һ������
	  �÷������ڻ����Զ���ı߽�
	*/
	public void paintBorder(Component c, //�߽��������������
		Graphics g, //�õ�ͼ�����������ã����ڻ���
		int x, int y, //�߽������
		int width, int height) { //�߽�Ŀ�ȡ�����
		g.setColor(this.color); //�趨��ɫ
		g.fill3DRect(x,y,width-thickness,thickness,true); //�����ϱ߽�
		g.fill3DRect(x,y+thickness,thickness,height-thickness,true); //������߽�
		g.fill3DRect(x+thickness,y+height-thickness,width-thickness,thickness,true); //�����±߽�
		g.fill3DRect(x+width-thickness,y,thickness,height-thickness,true); //�����ұ߽�
	}
	/*ʵ��Border �ӿڵĵڶ�������
	  ����һ��Insets ���������
	*/
	public Insets getBorderInsets(Component c){
		return new Insets(thickness,thickness,thickness,thickness);
	}
	/*ʵ��Border �ӿڵĵ���������
	  ���ﷵ��true,���������ƶ�����ɫ��ʾ�߽�
	*/
	public boolean isBorderOpaque(){
		return true;
	}

}
