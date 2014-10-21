package MemoryManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class MemoryManager extends JFrame {
	//定义使用的进程
	int test;	
	int influence=0;
	int influentialKey=0;
	boolean reStart=true;
	ButtonListener buttonListener;
	JPanel visableJPanel;
	JPanel memoryJPanel;
	JPanel pcbJPanel;	
	JPanel pcb2JPanel;
	JPanel pcb3JPanel;
	JPanel pcb4JPanel;
	JPanel containJPanel;
	JPanel littlecontainJPanel;
	JPanel buttonJPanel;
	JPanel buttonJPanel1;
	JPanel buttonJPanel2;
	JPanel conveyJPanel;
	JPanel conveyJPanel1;
	JPanel conveyJPanel2;
	JButton worseMethodButton;
	JButton firstMethodButton;
	JButton secondMethodButton;
	JButton startButton;
	JButton shotDownButton;
	JButton stopButton;
	Thread r;
	Thread s;
	Thread k;
	Thread v;
	Thread l;
    //少数参数的初始化
    int CurrentProcess =0;	    
    int number;   
    //JLabel的初始化
    JLabel pcbJLabel;
    JLabel memoryJLabel;  
    JLabel pro1;
    JLabel pro2;
    JLabel pro3;
    JLabel pro4;
    JLabel pro5;
    JLabel pro6;    
    JLabel pro7;
    JLabel pro8;
    JLabel pro9;
    JLabel pro10;
    JLabel proSize1;
    JLabel proSize2;
    JLabel proSize3;
    JLabel proSize4;
    JLabel proSize5;
    JLabel proSize6;
    JLabel proSize7;
    JLabel proSize8;
    JLabel proSize9;
    JLabel proSize10;
    JLabel blankJLabel1;
    JLabel blankJLabel2;
    JLabel blankJLabel3;
    JLabel blankJLabel4;
    JLabel blankJLabel5;
    JLabel blankJLabel6;
    JLabel blankJLabel7;
    JLabel blankJLabel8;
    JLabel blankJLabel9;
    JLabel blankJLabel10;
    JLabel colorJLabel1;
    JLabel colorJLabel2;
    JLabel colorJLabel3;
    JLabel colorJLabel4;
    JLabel imaJLabel1;
    JLabel imaJLabel2;
    JLabel imaJLabel3;
    JLabel imaJLabel4;
    JLabel imaJLabel5;
    JLabel imaJLabel6;
    JLabel imaJLabel7;
    JLabel imaJLabel8;
    JLabel imaJLabel9;
    JLabel imaJLabel10;
    JLabel titleJLabel;
    JLabel startIndex;
    JLabel endIndex;
    JLabel firAreaJLabel1;
    JLabel lasAreaJLabel1;
    JLabel firAreaJLabel2;
    JLabel lasAreaJLabel2;
    JLabel firAreaJLabel3;
    JLabel lasAreaJLabel3;
    JLabel firAreaJLabel4;
    JLabel lasAreaJLabel4;
    JLabel waitJLabel;
    JLabel waitSizeJLabel;
    JLabel waitJLabel1;
    JLabel waitSizeJLabel1;
    JLabel waitJLabel2;
    JLabel waitSizeJLabel2;
    JLabel waitJLabel3;
    JLabel waitSizeJLabel3;
    JLabel waitJLabel4;
    JLabel waitSizeJLabel4;    
    
    Processexample process1;
	Processexample process2;
	Processexample process3;
	Processexample process4;
	Processexample process5;
	Processexample process6;
	Processexample process7;
	Processexample process8;
	Processexample process9;
	Processexample process10;
	Processexample process11=null;
	
	drawMemory dM;

	judging judge;    
	
    ComputerResponse cmprsp;
    
    BlankSearching bS;
    
    BlockListSearching BLS;
    
    Repeater rp;
    
	public  MemoryManager() {
		initialprocess();
	
	}

	void instantiateProcess()
	{
		Font  largeFont = new Font("TimesRoman",Font.BOLD,20);
		Border lineBorder = new LineBorder(Color.black,2);
		

		startButton=new JButton("启动");
		shotDownButton=new JButton("终止");
		stopButton=new JButton("暂停");
		
		process1=new Processexample();
		process2=new Processexample();
		process3=new Processexample();
		process4=new Processexample();
		process5=new Processexample();
		process6=new Processexample();
		process7=new Processexample();
		process8=new Processexample();
		process9=new Processexample();
		process10=new Processexample();
		process11=new Processexample();
		
		pro1=new JLabel("      程序1");
		pro2=new JLabel("      程序2");
		pro3=new JLabel("      程序3");
		pro4=new JLabel("      程序4");
		pro5=new JLabel("      程序5");
		pro6=new JLabel("      程序6");
		pro7=new JLabel("      程序7");
		pro8=new JLabel("      程序8");
		pro9=new JLabel("      程序9");
		pro10=new JLabel("      程序10");
		
		process1.selfColor=Color.black;
		process2.selfColor=Color.blue;
		process3.selfColor=Color.cyan;
		process4.selfColor=Color.red;
		process5.selfColor=Color.gray;
		process6.selfColor=Color.green;
		process7.selfColor=Color.LIGHT_GRAY;
		process8.selfColor=Color.magenta;
		process9.selfColor=Color.orange;
		process10.selfColor=Color.pink;
		process11.selfColor=Color.yellow;
		
		pro1.setForeground(process1.selfColor);
		pro2.setForeground(process2.selfColor);
		pro3.setForeground(process3.selfColor);
		pro4.setForeground(process4.selfColor);
		pro5.setForeground(process5.selfColor);
		pro6.setForeground(process6.selfColor);
		pro7.setForeground(process7.selfColor);
		pro8.setForeground(process8.selfColor);
		pro9.setForeground(process9.selfColor);
		pro10.setForeground(process10.selfColor);
		
		process1.prostaJLabel.setForeground(process1.selfColor);
		process2.prostaJLabel.setForeground(process2.selfColor);
		process3.prostaJLabel.setForeground(process3.selfColor);
		process4.prostaJLabel.setForeground(process4.selfColor);
		process5.prostaJLabel.setForeground(process5.selfColor);
		process6.prostaJLabel.setForeground(process6.selfColor);
		process7.prostaJLabel.setForeground(process7.selfColor);
		process8.prostaJLabel.setForeground(process8.selfColor);
		process9.prostaJLabel.setForeground(process9.selfColor);
		process10.prostaJLabel.setForeground(process10.selfColor);

		process1.prosize=(int)(Math.random()*50+25);
		process2.prosize=(int)(Math.random()*50+25);
		process3.prosize=(int)(Math.random()*50+30);
		process4.prosize=(int)(Math.random()*50+30);
		process5.prosize=(int)(Math.random()*50+50);
		process6.prosize=(int)(Math.random()*50+50);
		process7.prosize=(int)(Math.random()*50+75);
		process8.prosize=(int)(Math.random()*50+75);
		process9.prosize=(int)(Math.random()*50+100);
		process10.prosize=(int)(Math.random()*50+100);
		
		proSize1=new JLabel("  0  ");
		proSize2=new JLabel("  0  ");
		proSize3=new JLabel("  0  ");
		proSize4=new JLabel("  0  ");
		proSize5=new JLabel("  0  ");
		proSize6=new JLabel("  0  ");
		proSize7=new JLabel("  0  ");
		proSize8=new JLabel("  0  ");
		proSize9=new JLabel("  0  ");
		proSize10=new JLabel("  0  ");
	}

	void initialStringArray() {
		// TODO Auto-generated method stub
		for(int l=0;l<512;l++)
		{
			cmprsp.memoryArrays[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process1.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process2.selfMemory[l]="free";
		}		
		for(int l=0;l<512;l++)
		{
			process3.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process4.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process5.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process6.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process7.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process8.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process9.selfMemory[l]="free";
		}
		for(int l=0;l<512;l++)
		{
			process10.selfMemory[l]="free";
		}
	
		
	}

	void instantiateImageIcon()
	{
		int width=30;
		int hight=30;
		 ImageIcon image1 = new ImageIcon("image/cycxtb 001.png"); 
		 image1.setImage(image1.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel1=new JLabel(image1);
//		
		 ImageIcon image2 = new ImageIcon("image/cycxtb 002.png"); 
		 image2.setImage(image2.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel2=new JLabel(image2);
		 
		 ImageIcon image3 = new ImageIcon("image/cycxtb 003.png"); 
		 image3.setImage(image3.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel3=new JLabel(image3);
		 
		 ImageIcon image4 = new ImageIcon("image/cycxtb 006.png"); 
		 image4.setImage(image4.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel4=new JLabel(image4);
		 
		 ImageIcon image5 = new ImageIcon("image/cycxtb 012.png"); 
		 image5.setImage(image5.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel5=new JLabel(image5);
		 
		 ImageIcon image6 = new ImageIcon("image/cycxtb 017.png"); 
		 image6.setImage(image6.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel6=new JLabel(image6);
		 
		 ImageIcon image7 = new ImageIcon("image/cycxtb 091.png"); 
		 image7.setImage(image7.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel7=new JLabel(image7);
		 
		 ImageIcon image8 = new ImageIcon("image/cycxtb 093.png"); 
		 image8.setImage(image8.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel8=new JLabel(image8);
		 
		 ImageIcon image9 = new ImageIcon("image/cycxtb 095.png"); 
		 image9.setImage(image9.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel9=new JLabel(image9);
		 
		 ImageIcon image10 = new ImageIcon("image/cycxtb 096.png"); 
		 image10.setImage(image10.getImage().getScaledInstance(width,hight,Image.SCALE_DEFAULT)); 
		 imaJLabel10=new JLabel(image10);
	}

	void instantiateJLabel()
	{
		imaJLabel1=new JLabel();
		imaJLabel2=new JLabel();
		imaJLabel3=new JLabel();
		imaJLabel4=new JLabel();
		imaJLabel5=new JLabel();
		imaJLabel6=new JLabel();
		imaJLabel7=new JLabel();
		imaJLabel8=new JLabel();
		imaJLabel9=new JLabel();
		imaJLabel10=new JLabel();
		
	    waitJLabel=new JLabel("就绪等待");
	    waitSizeJLabel=new JLabel("程序大小");
	    waitJLabel1=new JLabel("0");
	    waitSizeJLabel1=new JLabel("0");
	    waitJLabel2=new JLabel("0");
	    waitSizeJLabel2=new JLabel("0");
	    waitJLabel3=new JLabel("0");
	    waitSizeJLabel3=new JLabel("0");
	    waitJLabel4=new JLabel("0");
	    waitSizeJLabel4=new JLabel("0");
		
		startIndex=new JLabel("起始");
		endIndex=new JLabel("终止");
		firAreaJLabel1=new JLabel("0");
		lasAreaJLabel1=new JLabel("0");
		firAreaJLabel2=new JLabel("0");
		lasAreaJLabel2=new JLabel("0");
		firAreaJLabel3=new JLabel("0");
		lasAreaJLabel3=new JLabel("0");
		firAreaJLabel4=new JLabel("0");
		lasAreaJLabel4=new JLabel("0");
		
		titleJLabel=new JLabel("程序控制块");

		blankJLabel1=new JLabel("    ");	
		blankJLabel1.setOpaque(true);
		blankJLabel1.setBackground(process1.selfColor);
		
		blankJLabel2=new JLabel("    ");
		blankJLabel2.setOpaque(true);
		blankJLabel2.setBackground(process2.selfColor);

		blankJLabel3=new JLabel("    ");
		blankJLabel3.setOpaque(true);
		blankJLabel3.setBackground(process3.selfColor);

		blankJLabel4=new JLabel("    ");
		blankJLabel4.setOpaque(true);
		blankJLabel4.setBackground(process4.selfColor);

		blankJLabel5=new JLabel("    ");
		blankJLabel5.setOpaque(true);
		blankJLabel5.setBackground(process5.selfColor);

		blankJLabel6=new JLabel("    ");
		blankJLabel6.setOpaque(true);
		blankJLabel6.setBackground(process6.selfColor);

		blankJLabel7=new JLabel("    ");
		blankJLabel7.setOpaque(true);
		blankJLabel7.setBackground(process7.selfColor);

		blankJLabel8=new JLabel("    ");
		blankJLabel8.setOpaque(true);
		blankJLabel8.setBackground(process8.selfColor);

		blankJLabel9=new JLabel("    ");
		blankJLabel9.setOpaque(true);
		blankJLabel9.setBackground(process9.selfColor);

		blankJLabel10=new JLabel("    ");	
		blankJLabel10.setOpaque(true);
		blankJLabel10.setBackground(process10.selfColor);

		colorJLabel1=new JLabel("    ");
		colorJLabel2=new JLabel("    ");
		colorJLabel3=new JLabel("    ");
		colorJLabel4=new JLabel("    ");
		
		colorJLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
		colorJLabel2.setBorder(BorderFactory.createLineBorder(Color.black));
		colorJLabel3.setBorder(BorderFactory.createLineBorder(Color.black));
		colorJLabel4.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	void initialprocess()
	{
		Font  largeFont = new Font("TimesRoman",Font.BOLD,20);
		Border lineBorder = new LineBorder(Color.black,2);
		buttonListener=new ButtonListener();
		//绘图面板定义
		pcbJPanel=new JPanel();
		pcb2JPanel=new JPanel();
		visableJPanel=new JPanel();
		memoryJPanel=new JPanel();
		pcb3JPanel=new JPanel();
		pcb4JPanel=new JPanel();
		buttonJPanel=new JPanel();
		buttonJPanel1=new JPanel();
		buttonJPanel2=new JPanel();
		buttonJPanel.setLayout(new GridLayout(2,1));
		buttonJPanel2.setLayout(new GridLayout(6,1));
		conveyJPanel=new JPanel();
		conveyJPanel1=new JPanel();
		conveyJPanel2=new JPanel();
		containJPanel=new JPanel(new GridLayout(4,1));
		littlecontainJPanel=new JPanel(new GridLayout(5,1));
		
		cmprsp=new ComputerResponse();
		
		instantiateProcess();
		
		judge=new judging();
		
        dM=new drawMemory();
		dM.setBorder(new TitledBorder("内存分配图"));
		
		bS=new BlankSearching();

		BLS=new BlockListSearching();
		firstMethodButton=new JButton("首次适配法");
		secondMethodButton=new JButton("再次适配法");
		worseMethodButton=new JButton("最差适配法");
		firstMethodButton.addActionListener(buttonListener);
		worseMethodButton.addActionListener(buttonListener);
		secondMethodButton.addActionListener(buttonListener);
		startButton.addActionListener(buttonListener);
		shotDownButton.addActionListener(buttonListener);
		stopButton.addActionListener(buttonListener);

		startButton.setBackground(Color.WHITE);
		startButton.setForeground(Color.GREEN);
		shotDownButton.setBackground(Color.WHITE);
		shotDownButton.setForeground(Color.BLUE);
		stopButton.setBackground(Color.white);
		stopButton.setForeground(Color.pink);
		firstMethodButton.setBackground(Color.WHITE);
		firstMethodButton.setForeground(Color.yellow);
		secondMethodButton.setBackground(Color.white);
		secondMethodButton.setForeground(Color.cyan);
		worseMethodButton.setBackground(Color.white);
		worseMethodButton.setForeground(Color.ORANGE);
		
		shotDownButton.setEnabled(false);
		stopButton.setEnabled(false);
			
        rp=new Repeater();
        
        instantiateJLabel();
        
        instantiateImageIcon();
        
		initialStringArray();
		
		setPcbJPanel();
		
		conveyJPanel1.setLayout(new GridLayout(5,2));
		conveyJPanel1.add(startIndex);
		conveyJPanel1.add(endIndex);
		conveyJPanel1.add(firAreaJLabel1);
		conveyJPanel1.add(lasAreaJLabel1);
		conveyJPanel1.add(firAreaJLabel2);
		conveyJPanel1.add(lasAreaJLabel2);
		conveyJPanel1.add(firAreaJLabel3);
		conveyJPanel1.add(lasAreaJLabel3);
		conveyJPanel1.add(firAreaJLabel4);
		conveyJPanel1.add(lasAreaJLabel4);
		
		conveyJPanel2.setLayout(new GridLayout(5,2));
		conveyJPanel2.add(waitJLabel);
		conveyJPanel2.add(waitSizeJLabel);
		conveyJPanel2.add(waitJLabel1);
		conveyJPanel2.add(waitSizeJLabel1);
		conveyJPanel2.add(waitJLabel2);
		conveyJPanel2.add(waitSizeJLabel2);
		conveyJPanel2.add(waitJLabel3);
		conveyJPanel2.add(waitSizeJLabel3);
		conveyJPanel2.add(waitJLabel4);
		conveyJPanel2.add(waitSizeJLabel4);
		
		conveyJPanel.setLayout(new GridLayout(2,1));
		conveyJPanel.add(conveyJPanel1);
		conveyJPanel.add(conveyJPanel2);
		
		buttonJPanel2.add(startButton);
		buttonJPanel2.add(stopButton);
		buttonJPanel2.add(shotDownButton);
		buttonJPanel2.add(firstMethodButton);
		buttonJPanel2.add(worseMethodButton);
		buttonJPanel2.add(secondMethodButton);
		
		buttonJPanel.add(buttonJPanel2);
		buttonJPanel.add(conveyJPanel);
		
		this.add(containJPanel,BorderLayout.WEST);
		this.add(dM,BorderLayout.CENTER);
		this.add(buttonJPanel,BorderLayout.EAST);
		this.setVisible(true);
		
	}

	void StartThread() 
	{
		r=new Thread(rp);
		r.start();
		s=new Thread(cmprsp);
		s.start();
		k=new Thread(judge);
		k.start();
		v=new Thread(bS);
		v.start();
		l=new Thread(BLS);
		l.start();
	}
	
	void ThreadTerminator()
	{
		r.destroy();
		v.destroy();
		l.destroy();
		k.destroy();
		s.destroy();
	}

	void cleanProcess()
	{
		process1.selfprosition="未运行";
		process2.selfprosition="未运行";
		process3.selfprosition="未运行";
		process4.selfprosition="未运行";
		process5.selfprosition="未运行";
		process6.selfprosition="未运行";
		process7.selfprosition="未运行";
		process8.selfprosition="未运行";
		process9.selfprosition="未运行";
		process10.selfprosition="未运行";
		
		process1.firstIndex=0;
		process1.lastIndex=0;
		process2.firstIndex=0;
		process2.lastIndex=0;
		process3.firstIndex=0;
		process3.lastIndex=0;
		process4.firstIndex=0;
		process4.lastIndex=0;
		process5.firstIndex=0;
		process5.lastIndex=0;
		process6.firstIndex=0;
		process6.lastIndex=0;
		process7.firstIndex=0;
		process7.lastIndex=0;
		process8.firstIndex=0;
		process8.lastIndex=0;
		process9.firstIndex=0;
		process9.lastIndex=0;
		process10.firstIndex=0;
		process10.lastIndex=0;
	}
	
	void cleanMemory()
	{
		for(int i=0;i<512;i++)
		{
			cmprsp.memoryArrays[i]="free";
		}
	}
	
	void cleanEveryJLabel()
	{
		process1.prostaJLabel.setText("未运行");
		process2.prostaJLabel.setText("未运行");		
		process3.prostaJLabel.setText("未运行");
		process4.prostaJLabel.setText("未运行");
		process5.prostaJLabel.setText("未运行");
		process6.prostaJLabel.setText("未运行");
		process7.prostaJLabel.setText("未运行");
		process8.prostaJLabel.setText("未运行");
		process9.prostaJLabel.setText("未运行");
		process10.prostaJLabel.setText("未运行");

		waitJLabel1.setText("0");
		waitSizeJLabel1.setText("0");
		waitJLabel2.setText("0");
		waitSizeJLabel2.setText("0");
		waitJLabel3.setText("0");
		waitSizeJLabel3.setText("0");
		waitJLabel4.setText("0");
		waitSizeJLabel4.setText("0");
		
		blankJLabel1.setText("0");
		blankJLabel2.setText("0");
		blankJLabel3.setText("0");
		blankJLabel4.setText("0");
		blankJLabel5.setText("0");
		blankJLabel6.setText("0");
		blankJLabel7.setText("0");
		blankJLabel8.setText("0");
		blankJLabel9.setText("0");
		blankJLabel10.setText("0");
		
		proSize1.setText("  0  ");
		proSize2.setText("  0  ");
		proSize3.setText("  0  ");
		proSize4.setText("  0  ");
		proSize5.setText("  0  ");
		proSize6.setText("  0  ");
		proSize7.setText("  0  ");
		proSize8.setText("  0  ");
		proSize9.setText("  0  ");
		proSize10.setText("  0  ");
	}

	void setPcbJPanel()
	{
		pcb2JPanel.add(titleJLabel);
		pcbJPanel.setLayout(new GridLayout(3,4));
		pcb3JPanel.setLayout(new GridLayout(3,4));
		pcb4JPanel.setLayout(new GridLayout(4,4));
		
		pcbJPanel.add(process1.prostaJLabel);
		pcbJPanel.add(pro1);
		pcbJPanel.add(proSize1);
		pcbJPanel.add(imaJLabel1);
		
		pcbJPanel.add(process2.prostaJLabel);
		pcbJPanel.add(pro2);
		pcbJPanel.add(proSize2);
		pcbJPanel.add(imaJLabel2);

		pcbJPanel.add(process3.prostaJLabel);
		pcbJPanel.add(pro3);
		pcbJPanel.add(proSize3);
		pcbJPanel.add(imaJLabel3);

		pcb3JPanel.add(process4.prostaJLabel);
		pcb3JPanel.add(pro4);
		pcb3JPanel.add(proSize4);
		pcb3JPanel.add(imaJLabel4);

		pcb3JPanel.add(process5.prostaJLabel);
		pcb3JPanel.add(pro5);		
		pcb3JPanel.add(proSize5);
		pcb3JPanel.add(imaJLabel5);

		pcb3JPanel.add(process6.prostaJLabel);
		pcb3JPanel.add(pro6);
		pcb3JPanel.add(proSize6);
		pcb3JPanel.add(imaJLabel6);

		pcb4JPanel.add(process7.prostaJLabel);
		pcb4JPanel.add(pro7);		
		pcb4JPanel.add(proSize7);
		pcb4JPanel.add(imaJLabel7);

		pcb4JPanel.add(process8.prostaJLabel);
		pcb4JPanel.add(pro8);
		pcb4JPanel.add(proSize8);
		pcb4JPanel.add(imaJLabel8);

		pcb4JPanel.add(process9.prostaJLabel);
		pcb4JPanel.add(pro9);
		pcb4JPanel.add(proSize9);
		pcb4JPanel.add(imaJLabel9);

		pcb4JPanel.add(process10.prostaJLabel);
		pcb4JPanel.add(pro10);
		pcb4JPanel.add(proSize10);
		pcb4JPanel.add(imaJLabel10);
		
		memoryJPanel.add(process11);
		setLayout(new BorderLayout());
		
		pcbJPanel.setBorder(new TitledBorder("小程序"));
		pcb2JPanel.setBorder(new TitledBorder("PCB列表"));
		pcb3JPanel.setBorder(new TitledBorder("中程序"));
		pcb4JPanel.setBorder(new TitledBorder("大程序"));
//		littlecontainJPanel.add(pcbJPanel);
//		littlecontainJPanel.add(pcb3JPanel);
//		littlecontainJPanel.add(pcb4JPanel);
		containJPanel.add(pcb2JPanel);
		containJPanel.add(pcbJPanel);
		containJPanel.add(pcb3JPanel);
		containJPanel.add(pcb4JPanel);
	}
	
	void fillMemory(int pfi,int pli)
	{
		int f=0;
		int l=0;
		for(f=pfi,l=pli;f<=pli;f++)
			cmprsp.memoryArrays[f]="notfree";
		
	}
	
	int worseFindLasIndex(int pfi,int pli,int ps)
	{
		int fir=0;
	    int las=0;
	    int i=0;
	    int t=50;
	    int maxFirIndex=0;
	    int maxLasIndex=0;
	    int currentCount=0;
	    int maxCount=0;
	    int e;
	    for(int k=0;k<512;k++)
	    {
	    	currentCount=0;
	    	e=k;
	    	if(cmprsp.memoryArrays[k]=="free")
	    	{
	    		for(int l=e+1;l<512;l++)
	    		{
	    			if(l==51&&cmprsp.memoryArrays[l]=="free")
	    			{
//	    				System.out.println("find");
	    			}
	    			
	    				
	    			currentCount++;
	    			if(l==511)
	    			{	
//	    				System.out.println("ddddd");	    	
	    				if(maxCount<currentCount)
	    				{
	    					maxCount=currentCount;
	    					maxFirIndex=k;
	    					maxLasIndex=l;
	    				}
	    				k=512;
	    				break;
	    			}
	    			if(cmprsp.memoryArrays[l]=="notfree")
	    			{
	    				if(maxCount<currentCount)
	    				{
	    					maxCount=currentCount;
	    					maxFirIndex=k;
	    					maxLasIndex=l-1;
	    				}
	    				k=l+1;
	    				break;
	    			}	
	    		
	    		}
	    	}
	    }
	    
	    if(maxLasIndex-maxFirIndex<ps)
	    {
             return 0;
	   	}
		return maxFirIndex+ps-1;
	}
 	
	int worseFindFirIndex(int pfi,int pli,int ps)
	{
		int fir=0;
	    int las=0;
	    int i=0;
	    int t=50;
	    int maxFirIndex=0;
	    int maxLasIndex=0;
	    int currentCount=0;
	    int maxCount=0;
	    int e;
	    for(int k=0;k<512;k++)
	    {
	    	currentCount=0;
	    	e=k;
	    	if(cmprsp.memoryArrays[k]=="free")
	    	{
	    		for(int l=e+1;l<512;l++)
	    		{
	    			if(l==51&&cmprsp.memoryArrays[l]=="free")
	    			{
//	    				System.out.println("find");
	    			}
	    			
	    				
	    			currentCount++;
	    			if(l==511)
	    			{	
//	    				System.out.println("ddddd");	    	
	    				if(maxCount<currentCount)
	    				{
	    					maxCount=currentCount;
	    					maxFirIndex=k;
	    					maxLasIndex=l;
	    				}
	    				k=512;
	    				break;
	    			}
	    			if(cmprsp.memoryArrays[l]=="notfree")
	    			{
	    				if(maxCount<currentCount)
	    				{
	    					maxCount=currentCount;
	    					maxFirIndex=k;
	    					maxLasIndex=l-1;
	    				}
	    				k=l+1;
	    				break;
	    			}	
	    		
	    		}
	    	}
	    }
	    if(maxLasIndex-maxFirIndex<ps)
	    	{return 0;}
		return maxFirIndex;
	    
	}

	int findfirindex(int pfi,int pli,int ps)
	{
		int fir=0;
		int las;
		if(pli!=0)
			fir=pli-ps+1;
		return fir;

	}

	int findlasindex(int pfi,int pli,int ps)
	{
		int i=0;
		int fir=0;
		int las=0;
		int finish=0;
		for(int k=i;k<512;k++)
		{
			if(finish==1)
			{
				for(int e=k+ps-2;e>=k-1;e--)
					cmprsp.memoryArrays[e]="notfree";
				break;
				
			}
			if(cmprsp.memoryArrays[k]=="free")
			{
				fir=k;
				for(int l=k;l<=k+ps-1;l++)
				{
					if(l>=512)
					{
						fir=0;
						las=0;
						finish=0;
						k=512;
						break;
					}
					if(cmprsp.memoryArrays[l]=="notfree")
					{
						fir=0;
						las=0;
						k=l+1;
						finish=0;
						break;
					}
						fir=k;
						las=k+ps-1;
						finish=1;
				}
				
			}
		}		
		return las;
	}
  
	int secondFindFirIndex(int pfi,int pli,int ps)
	{
		int i=0;
		int j=0;
		int fir=0;
		int countBlock=0;
		int countNum=0;
		int countAgain=0;
		int finish=0;
		int las=0;
		for(int k=0;k<512;k++)
		{
			countNum=0;
			if(finish==1)
				break;
			if(cmprsp.memoryArrays[k]=="free")
			{
				for(int l=k+1;l<512;l++)
				{
					countNum++;
					if(l==511)
					{
						if(countNum>=ps)
						{
							fir=k;
							finish=1;
							break;
						}
					}
					if(cmprsp.memoryArrays[l]=="notfree")
					{
						if(countNum>=ps)
						{
							countBlock++;
							if(countBlock==2)
							{
								finish=1;
								fir=k;
								return fir;
							
							}
							fir=k;
							k=l+1;
							l=512;
					
						}
						if(countNum<ps)
						{
							
							k=l+1;
							l=512;
						}
						
					}
				}
				
			}
			
		}
		
		return fir;
	}

	int secondFindLasIndex(int pfi,int pli,int ps)
	{
		int i=0;
		int j=0;
		int fir=0;
		int countBlock=0;
		int countNum=0;
		int countAgain=0;
		int finish=0;
		int las=0;
		for(int k=0;k<512;k++)
		{
			countNum=0;
			if(finish==1)
				break;
			if(cmprsp.memoryArrays[k]=="free")
			{
				for(int l=k+1;l<512;l++)
				{
					countNum++;
					if(l==511)
					{
						if(countNum>=ps)
						{
							fir=k;
							finish=1;
							las=fir+ps-1;
							break;
						}
					}
					if(cmprsp.memoryArrays[l]=="notfree")
					{
						if(countNum>=ps)
						{
							countBlock++;
							if(countBlock==2)
							{
								finish=1;
								fir=k;
								las=fir+ps-1;
								return fir;
							
							}
							fir=k;
							las=fir+ps-1;
							k=l+1;
							l=512;
					
						}
						if(countNum<ps)
						{
							
							k=l+1;
							l=512;
						}
						
					}
				}
				
			}
			
		}
		
		return las;
	}

	void findBlankIndexArea()
	{
		int k=0;
		int i=0;
		int fir;
		int las;
		int num=1;
		String o=new String();
		String kString=new String();
		firAreaJLabel1.setText("0");
		lasAreaJLabel1.setText("0");
		firAreaJLabel2.setText("0");
		lasAreaJLabel2.setText("0");
		firAreaJLabel3.setText("0");
		lasAreaJLabel3.setText("0");
		firAreaJLabel4.setText("0");
		lasAreaJLabel4.setText("0");
		for(k=0;k<512;k++)
		{
			if(cmprsp.memoryArrays[k]=="free")
			{
				for(int l=k+1;l<512;l++)
				{
					if(l==511)
					{
						if(num==1)
						{
							fir=k;
							las=l;
							kString=o.valueOf(fir);
							firAreaJLabel1.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel1.setText(kString);							
							num++;
							k=las+1;
						}
						else
						if(num==2)
						{
							fir=k;
							las=l;
							kString=o.valueOf(fir);
							firAreaJLabel2.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel2.setText(kString);							
							num++;
							k=las+1;
						}
						else if(num==3)
						{
							fir=k;
							las=l;
							kString=o.valueOf(fir);
							firAreaJLabel3.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel3.setText(kString);							
							num++;
							k=las+1;
						}
						else if(num==4)
						{
							fir=k;
							las=l;
							kString=o.valueOf(fir);
							firAreaJLabel4.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel4.setText(kString);
							k=las+1;
						}
					}
					if(cmprsp.memoryArrays[l]=="notfree")
					{
						fir=k;
						las=l-1;
						k=l+1;
						l=512;
						if(num==1)
						{							
							kString=o.valueOf(fir);
							firAreaJLabel1.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel1.setText(kString);							
							num++;
						}
						else if(num==2)
						{
							kString=o.valueOf(fir);
							firAreaJLabel2.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel2.setText(kString);
							num++;
						}
						else if(num==3)
						{
							kString=o.valueOf(fir);
							firAreaJLabel3.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel3.setText(kString);
							num++;
						}
						else if(num==4)
						{
							kString=o.valueOf(fir);
							firAreaJLabel4.setText(kString);
							kString=o.valueOf(las);
							lasAreaJLabel4.setText(kString);
						}
					}
				}
			}
		}
	}
	
	void findAtWaitingListProcess()
	{
		int i=1;
		int num=1;
		String oString=new String();
		String kString=new String();
		String[] listStrings=new String[10];
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitJLabel1.setText("0");
		waitSizeJLabel1.setText("0");
		waitJLabel2.setText("0");
		waitSizeJLabel2.setText("0");
		waitJLabel3.setText("0");
		waitSizeJLabel3.setText("0");
		waitJLabel4.setText("0");
		waitSizeJLabel4.setText("0");
		for(int j=0;j<=9;j++)
		{
			listStrings[j]="unblocked";
		}
		if(process1.selfprosition=="就绪挂起")
		{
			listStrings[0]="blocked";
		}
		if(process2.selfprosition=="就绪挂起")
		{
			listStrings[1]="blocked";
		}
		if(process3.selfprosition=="就绪挂起")
		{
			listStrings[2]="blocked";
		}
		if(process4.selfprosition=="就绪挂起")
		{
			listStrings[3]="blocked";
		}
		if(process5.selfprosition=="就绪挂起")
		{
			listStrings[4]="blocked";
		}
		if(process6.selfprosition=="就绪挂起")
		{
			listStrings[5]="blocked";
		}
		if(process7.selfprosition=="就绪挂起")
		{
			listStrings[6]="blocked";
		}
		if(process8.selfprosition=="就绪挂起")
		{
			listStrings[7]="blocked";
		}
		if(process9.selfprosition=="就绪挂起")
		{
			listStrings[8]="blocked";
		}
		if(process10.selfprosition=="就绪挂起")
		{
			listStrings[9]="blocked";
		}
		
		if(listStrings[0]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process1.prosize);
				waitJLabel1.setText("1");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process1.prosize);
				waitJLabel2.setText("1");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process1.prosize);
				waitJLabel3.setText("1");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process1.prosize);
				waitJLabel4.setText("2");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[1]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process2.prosize);
				waitJLabel1.setText("2");
				waitSizeJLabel1.setText(kString);
				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process2.prosize);
				waitJLabel2.setText("2");
				waitSizeJLabel2.setText(kString);
				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process2.prosize);
				waitJLabel3.setText("2");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process2.prosize);
				waitJLabel4.setText("2");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[2]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process3.prosize);
				waitJLabel1.setText("3");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process3.prosize);
				waitJLabel2.setText("3");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process3.prosize);
				waitJLabel3.setText("3");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process3.prosize);
				waitJLabel4.setText("3");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[3]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process4.prosize);
				waitJLabel1.setText("4");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process4.prosize);
				waitJLabel2.setText("4");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process4.prosize);
				waitJLabel3.setText("4");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process4.prosize);
				waitJLabel4.setText("4");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[4]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process5.prosize);
				waitJLabel1.setText("5");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process5.prosize);
				waitJLabel2.setText("5");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process5.prosize);
				waitJLabel3.setText("5");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process5.prosize);
				waitJLabel4.setText("5");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[5]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process6.prosize);
				waitJLabel1.setText("6");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process6.prosize);
				waitJLabel2.setText("6");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process6.prosize);
				waitJLabel3.setText("6");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process6.prosize);
				waitJLabel4.setText("6");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[6]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process7.prosize);
				waitJLabel1.setText("7");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process7.prosize);
				waitJLabel2.setText("7");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process7.prosize);
				waitJLabel3.setText("7");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process7.prosize);
				waitJLabel4.setText("7");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[7]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process8.prosize);
				waitJLabel1.setText("8");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process8.prosize);
				waitJLabel2.setText("8");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process8.prosize);
				waitJLabel3.setText("8");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process8.prosize);
				waitJLabel4.setText("8");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[8]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process9.prosize);
				waitJLabel1.setText("9");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process9.prosize);
				waitJLabel2.setText("9");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process9.prosize);
				waitJLabel3.setText("9");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process9.prosize);
				waitJLabel4.setText("9");
				waitSizeJLabel4.setText(kString);			}
		}
		if(listStrings[9]=="blocked")
		{
			if(num==1)
			{
				kString=oString.valueOf(process10.prosize);
				waitJLabel1.setText("10");
				waitSizeJLabel1.setText(kString);				num++;
			}else if(num==2)
			{
				kString=oString.valueOf(process10.prosize);
				waitJLabel2.setText("10");
				waitSizeJLabel2.setText(kString);				num++;
			}else if(num==3)
			{
				kString=oString.valueOf(process10.prosize);
				waitJLabel3.setText("10");
				waitSizeJLabel3.setText(kString);				num++;
			}else if(num==4)
			{
				kString=oString.valueOf(process10.prosize);
				waitJLabel4.setText("10");
				waitSizeJLabel4.setText(kString);			
			}
		}
		
	}
	
	void freeindex(int pfi,int pli)
	{
		int i,j;
		for(i=pfi,j=pli;i<=j;i++)
				cmprsp.memoryArrays[i]="free";
	}

	class judging extends Thread
	{
		int i=0;
		int fir=0;
		int las = 0;
		int h=0;
		String hString=new String();
		public void run() {
				
			while(true)
				{
				while(reStart==false)
				{
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					if(cmprsp.randomnum==1)
					{
						if(process1.selfprosition=="未运行          "||process1.selfprosition=="就绪挂起")
						{
							process1.prosize=(int)(Math.random()*50+25);
							hString=hString.valueOf(process1.prosize);
							proSize1.setText(hString);
							try {
								this.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(influence==0)
							{
							process1.lastIndex=findlasindex(process1.firstIndex, process1.lastIndex, process1.prosize);
							process1.firstIndex=findfirindex(process1.firstIndex, process1.lastIndex, process1.prosize);
							}else
							if(influence==1)
							{
								process1.firstIndex=worseFindFirIndex(process1.firstIndex, process1.lastIndex, process1.prosize);
								process1.lastIndex=worseFindLasIndex(process1.firstIndex, process1.lastIndex, process1.prosize);
								fillMemory(process1.firstIndex,process1.lastIndex);
							}
							else
							if(influence==2)
							{
								process1.firstIndex=secondFindFirIndex(process1.firstIndex, process1.lastIndex, process1.prosize);
								process1.lastIndex=secondFindLasIndex(process1.firstIndex, process1.lastIndex, process1.prosize);
								fillMemory(process1.firstIndex,process1.lastIndex);
							}
							
							if(process1.lastIndex!=0)
							{
							process1.selfprosition="运行准备";
							process1.prostaJLabel.setText(process1.selfprosition);
//							dM.repaint();
							try {
								this.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							process1.selfprosition="正在运行";
							process1.prostaJLabel.setText(process1.selfprosition);
							}else {
//								System.out.println("程序分配的地址"+process1.lastIndex);
//								System.out.print("程序一的大小是"+process1.prosize);
								process1.selfprosition="就绪挂起";
								process1.prostaJLabel.setText(process1.selfprosition);
							}
						}
						else if(process1.selfprosition=="正在运行")
						{
							try {
								this.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							process1.selfprosition="运行完毕";
							process1.prostaJLabel.setText(process1.selfprosition);
							freeindex(process1.firstIndex, process1.lastIndex);
							process1.firstIndex=0;
							process1.lastIndex=0;
//							dM.repaint();
							try {
								this.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							process1.selfprosition="未运行          ";
							process1.prostaJLabel.setText(process1.selfprosition);
							proSize1.setText("  0  ");
						}
					}
				
			else if(cmprsp.randomnum==2)
			{
				if(process2.selfprosition=="未运行          "||process2.selfprosition=="就绪挂起")
				{
					process2.prosize=(int)(Math.random()*50+25);
					hString=hString.valueOf(process2.prosize);
					proSize2.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process2.lastIndex=findlasindex(process2.firstIndex, process2.lastIndex, process2.prosize);
					process2.firstIndex=findfirindex(process2.firstIndex, process2.lastIndex, process2.prosize);
					}else
					if(influence==1)
					{
						process2.firstIndex=worseFindFirIndex(process2.firstIndex, process2.lastIndex, process2.prosize);
						process2.lastIndex=worseFindLasIndex(process2.firstIndex, process2.lastIndex, process2.prosize);
						fillMemory(process2.firstIndex,process2.lastIndex);
					}else
					if(influence==2)
					{
						process2.firstIndex=secondFindFirIndex(process2.firstIndex, process2.lastIndex, process2.prosize);
						process2.lastIndex=secondFindLasIndex(process2.firstIndex, process2.lastIndex, process2.prosize);
						fillMemory(process2.firstIndex,process2.lastIndex);
					}
					if(process2.lastIndex!=0)
					{
					process2.selfprosition="运行准备";
					process2.prostaJLabel.setText(process2.selfprosition);
//					dM.repaint();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process2.selfprosition="正在运行";
					process2.prostaJLabel.setText(process2.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process2.lastIndex);
//						System.out.print("程序一的大小是"+process2.prosize);
						process2.selfprosition="就绪挂起";
						process2.prostaJLabel.setText(process2.selfprosition);
					}

				}
				else if(process2.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process2.selfprosition="运行完毕";
					process2.prostaJLabel.setText(process2.selfprosition);
					freeindex(process2.firstIndex, process2.lastIndex);
					process2.firstIndex=0;
					process2.lastIndex=0;
//					dM.repaint();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process2.selfprosition="未运行          ";
					process2.prostaJLabel.setText(process2.selfprosition);
					proSize2.setText("  0  ");

				}
		     }
			else if(cmprsp.randomnum==3)
			{
				if(process3.selfprosition=="未运行          "||process3.selfprosition=="就绪挂起")
				{
					process3.prosize=(int)(Math.random()*50+30);
					hString=hString.valueOf(process3.prosize);
					proSize3.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process3.lastIndex=findlasindex(process3.firstIndex, process3.lastIndex, process3.prosize);
					process3.firstIndex=findfirindex(process3.firstIndex, process3.lastIndex, process3.prosize);
					}else
					if(influence==1)
					{
						process3.firstIndex=worseFindFirIndex(process3.firstIndex, process3.lastIndex, process3.prosize);
						process3.lastIndex=worseFindLasIndex(process3.firstIndex, process3.lastIndex, process3.prosize);
						fillMemory(process3.firstIndex,process3.lastIndex);
					}else
					if(influence==2)
					{
						process3.firstIndex=worseFindFirIndex(process3.firstIndex, process3.lastIndex, process3.prosize);
						process3.lastIndex=worseFindLasIndex(process3.firstIndex, process3.lastIndex, process3.prosize);
						fillMemory(process3.firstIndex,process3.lastIndex);
					}
					if(process3.lastIndex!=0)
					{
					process3.selfprosition="运行准备";
					process3.prostaJLabel.setText(process3.selfprosition);
//					dM.repaint();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process3.selfprosition="正在运行";
					process3.prostaJLabel.setText(process3.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process3.lastIndex);
//						System.out.print("程序一的大小是"+process3.prosize);
						process3.selfprosition="就绪挂起";
						process3.prostaJLabel.setText(process3.selfprosition);
					}

				}
				else if(process3.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process3.selfprosition="运行完毕";
					process3.prostaJLabel.setText(process3.selfprosition);
					freeindex(process3.firstIndex, process3.lastIndex);
					process3.firstIndex=0;
					process3.lastIndex=0;
//					dM.repaint();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process3.selfprosition="未运行          ";
					process3.prostaJLabel.setText(process3.selfprosition);
					proSize3.setText("  0  ");

				}
			}
		
			else if(cmprsp.randomnum==4)
			{
				if(process4.selfprosition=="未运行          "||process4.selfprosition=="就绪挂起")
				{
					process4.prosize=(int)(Math.random()*50+30);
					hString=hString.valueOf(process4.prosize);
					proSize4.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process4.lastIndex=findlasindex(process4.firstIndex, process4.lastIndex, process4.prosize);
					process4.firstIndex=findfirindex(process4.firstIndex, process4.lastIndex, process4.prosize);
					}else
					if(influence==1)
					{
						process4.firstIndex=worseFindFirIndex(process4.firstIndex, process4.lastIndex, process4.prosize);
						process4.lastIndex=worseFindLasIndex(process4.firstIndex, process4.lastIndex, process4.prosize);
						fillMemory(process4.firstIndex,process4.lastIndex);
					}else
					if(influence==2)
					{
						process4.firstIndex=secondFindFirIndex(process4.firstIndex, process4.lastIndex, process4.prosize);
						process4.lastIndex=secondFindLasIndex(process4.firstIndex, process4.lastIndex, process4.prosize);
						fillMemory(process4.firstIndex,process4.lastIndex);
					}
					if(process4.lastIndex!=0)
					{
					process4.selfprosition="运行准备";
					process4.prostaJLabel.setText(process4.selfprosition);
//					dM.repaint();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process4.selfprosition="正在运行";
					process4.prostaJLabel.setText(process4.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process4.lastIndex);
//						System.out.print("程序一的大小是"+process4.prosize);
						process4.selfprosition="就绪挂起";
						process4.prostaJLabel.setText(process4.selfprosition);
					}

				}
				else if(process4.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process4.selfprosition="运行完毕";
					process4.prostaJLabel.setText(process4.selfprosition);
					freeindex(process4.firstIndex, process4.lastIndex);
					process4.firstIndex=0;
					process4.lastIndex=0;
//					dM.repaint();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process4.selfprosition="未运行          ";
					process4.prostaJLabel.setText(process4.selfprosition);
					proSize4.setText("  0  ");

				}				
			}
			else if(cmprsp.randomnum==5)
			{
				if(process5.selfprosition=="未运行          "||process5.selfprosition=="就绪挂起")
				{
					process5.prosize=(int)(Math.random()*50+50);
					hString=hString.valueOf(process5.prosize);
					proSize5.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process5.lastIndex=findlasindex(process5.firstIndex, process5.lastIndex, process5.prosize);
					process5.firstIndex=findfirindex(process5.firstIndex, process5.lastIndex, process5.prosize);
					}else
					if(influence==1)
					{
						process5.firstIndex=worseFindFirIndex(process5.firstIndex, process5.lastIndex, process5.prosize);
						process5.lastIndex=worseFindLasIndex(process5.firstIndex, process5.lastIndex, process5.prosize);
						fillMemory(process5.firstIndex,process5.lastIndex);
					}else 
					if(influence==2)
					{
						process5.firstIndex=secondFindFirIndex(process5.firstIndex, process5.lastIndex, process5.prosize);
						process5.lastIndex=secondFindLasIndex(process5.firstIndex, process5.lastIndex, process5.prosize);
						fillMemory(process5.firstIndex,process5.lastIndex);
					}
					
					if(process5.lastIndex!=0)
					{
					process5.selfprosition="运行准备";
					process5.prostaJLabel.setText(process5.selfprosition);
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process5.selfprosition="正在运行";
					process5.prostaJLabel.setText(process5.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process5.lastIndex);
//						System.out.print("程序一的大小是"+process5.prosize);
						process5.selfprosition="就绪挂起";
						process5.prostaJLabel.setText(process5.selfprosition);
					}

				}
				else if(process5.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process5.selfprosition="运行完毕";
					process5.prostaJLabel.setText(process5.selfprosition);
					freeindex(process5.firstIndex, process5.lastIndex);
					process5.firstIndex=0;
					process5.lastIndex=0;
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process5.selfprosition="未运行          ";
					process5.prostaJLabel.setText(process5.selfprosition);
					proSize5.setText("  0  ");

				}				
			}
			else if(cmprsp.randomnum==6)
			{
				if(process6.selfprosition=="未运行          "||process6.selfprosition=="就绪挂起")
				{
					process6.prosize=(int)(Math.random()*50+50);
					hString=hString.valueOf(process6.prosize);
					proSize6.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process6.lastIndex=findlasindex(process6.firstIndex, process6.lastIndex, process6.prosize);
					process6.firstIndex=findfirindex(process6.firstIndex, process6.lastIndex, process6.prosize);
					}else
					if(influence==1)
					{
						process6.firstIndex=worseFindFirIndex(process6.firstIndex, process6.lastIndex, process6.prosize);
						process6.lastIndex=worseFindLasIndex(process6.firstIndex, process6.lastIndex, process6.prosize);
						fillMemory(process6.firstIndex,process6.lastIndex);
					}else
					if(influence==2)
					{
						process6.firstIndex=secondFindFirIndex(process6.firstIndex, process6.lastIndex, process6.prosize);
						process6.lastIndex=secondFindLasIndex(process6.firstIndex, process6.lastIndex, process6.prosize);
						fillMemory(process6.firstIndex,process6.lastIndex);
					}
					if(process6.lastIndex!=0)
					{
					process6.selfprosition="运行准备";
					process6.prostaJLabel.setText(process6.selfprosition);
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process6.selfprosition="正在运行";
					process6.prostaJLabel.setText(process6.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process6.lastIndex);
//						System.out.print("程序一的大小是"+process6.prosize);
						process6.selfprosition="就绪挂起";
						process6.prostaJLabel.setText(process6.selfprosition);
					}

				}
				else if(process6.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process6.selfprosition="运行完毕";
					process6.prostaJLabel.setText(process6.selfprosition);
					freeindex(process6.firstIndex, process6.lastIndex);
					process6.firstIndex=0;
					process6.lastIndex=0;
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process6.selfprosition="未运行          ";
					process6.prostaJLabel.setText(process6.selfprosition);
					proSize6.setText("  0  ");

				}				
			}
			else if(cmprsp.randomnum==7)
			{
				if(process7.selfprosition=="未运行          "||process7.selfprosition=="就绪挂起")
				{
					process7.prosize=(int)(Math.random()*50+75);
					hString=hString.valueOf(process7.prosize);
					proSize7.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process7.lastIndex=findlasindex(process7.firstIndex, process7.lastIndex, process7.prosize);
					process7.firstIndex=findfirindex(process7.firstIndex, process7.lastIndex, process7.prosize);
					}else
					if(influence==1)
					{
						process7.firstIndex=worseFindFirIndex(process7.firstIndex, process7.lastIndex, process7.prosize);
						process7.lastIndex=worseFindLasIndex(process7.firstIndex, process7.lastIndex, process7.prosize);
						fillMemory(process7.firstIndex,process7.lastIndex);
					}else
					if(influence==2)
					{
						process7.firstIndex=secondFindFirIndex(process7.firstIndex, process7.lastIndex, process7.prosize);
						process7.lastIndex=secondFindLasIndex(process7.firstIndex, process7.lastIndex, process7.prosize);
						fillMemory(process7.firstIndex,process7.lastIndex);
					}
					if(process7.lastIndex!=0)
					{
					process7.selfprosition="运行准备";
					process7.prostaJLabel.setText(process7.selfprosition);
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process7.selfprosition="正在运行";
					process7.prostaJLabel.setText(process7.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process7.lastIndex);
//						System.out.print("程序一的大小是"+process7.prosize);
						process7.selfprosition="就绪挂起";
						process7.prostaJLabel.setText(process7.selfprosition);
					}

				}
				else if(process7.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process7.selfprosition="运行完毕";
					process7.prostaJLabel.setText(process7.selfprosition);
					freeindex(process7.firstIndex, process7.lastIndex);
					process7.firstIndex=0;
					process7.lastIndex=0;
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process7.selfprosition="未运行          ";
					process7.prostaJLabel.setText(process7.selfprosition);
					proSize7.setText("  0  ");

				}				
			}
			else if(cmprsp.randomnum==8)
			{
				if(process8.selfprosition=="未运行          "||process8.selfprosition=="就绪挂起")
				{
					process8.prosize=(int)(Math.random()*50+75);
					hString=hString.valueOf(process8.prosize);
					proSize8.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process8.lastIndex=findlasindex(process8.firstIndex, process8.lastIndex, process8.prosize);
					process8.firstIndex=findfirindex(process8.firstIndex, process8.lastIndex, process8.prosize);
					}else
					if(influence==1)
					{
						process8.firstIndex=worseFindFirIndex(process8.firstIndex, process8.lastIndex, process8.prosize);
						process8.lastIndex=worseFindLasIndex(process8.firstIndex, process8.lastIndex, process8.prosize);
						fillMemory(process8.firstIndex,process8.lastIndex);
					}else
					if(influence==2)
					{
						process8.firstIndex=secondFindFirIndex(process8.firstIndex, process8.lastIndex, process8.prosize);
						process8.lastIndex=secondFindLasIndex(process8.firstIndex, process8.lastIndex, process8.prosize);
						fillMemory(process8.firstIndex,process8.lastIndex);
					}
					if(process8.lastIndex!=0)
					{
					process8.selfprosition="运行准备";
					process8.prostaJLabel.setText(process8.selfprosition);
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process8.selfprosition="正在运行";
					process8.prostaJLabel.setText(process8.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process8.lastIndex);
//						System.out.print("程序一的大小是"+process8.prosize);
						process8.selfprosition="就绪挂起";
						process8.prostaJLabel.setText(process8.selfprosition);
					}

				}
				else if(process8.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process8.selfprosition="运行完毕";
					process8.prostaJLabel.setText(process8.selfprosition);
					freeindex(process8.firstIndex, process8.lastIndex);
					process8.firstIndex=0;
					process8.lastIndex=0;
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process8.selfprosition="未运行          ";
					process8.prostaJLabel.setText(process8.selfprosition);
					proSize8.setText("  0  ");

				}				
			}
			else if(cmprsp.randomnum==9)
			{
				if(process9.selfprosition=="未运行          "||process9.selfprosition=="就绪挂起")
				{
					process9.prosize=(int)(Math.random()*50+100);
					hString=hString.valueOf(process9.prosize);
					proSize9.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process9.lastIndex=findlasindex(process9.firstIndex, process9.lastIndex, process9.prosize);
					process9.firstIndex=findfirindex(process9.firstIndex, process9.lastIndex, process9.prosize);
					}else
					if(influence==1)
					{
						process9.firstIndex=worseFindFirIndex(process9.firstIndex, process9.lastIndex, process9.prosize);
						process9.lastIndex=worseFindLasIndex(process9.firstIndex, process9.lastIndex, process9.prosize);
						fillMemory(process9.firstIndex,process9.lastIndex);
					}else
					if(influence==2)
					{
						process9.firstIndex=secondFindFirIndex(process9.firstIndex, process9.lastIndex, process9.prosize);
						process9.lastIndex=secondFindLasIndex(process9.firstIndex, process9.lastIndex, process9.prosize);
						fillMemory(process9.firstIndex,process9.lastIndex);
					}
					if(process9.lastIndex!=0)
					{
					process9.selfprosition="运行准备";
					process9.prostaJLabel.setText(process9.selfprosition);
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process9.selfprosition="正在运行";
					process9.prostaJLabel.setText(process9.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process9.lastIndex);
//						System.out.print("程序一的大小是"+process9.prosize);
						process9.selfprosition="就绪挂起";
						process9.prostaJLabel.setText(process9.selfprosition);
					}

				}
				else if(process9.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process9.selfprosition="运行完毕";
					process9.prostaJLabel.setText(process9.selfprosition);
					freeindex(process9.firstIndex, process9.lastIndex);
					process9.firstIndex=0;
					process9.lastIndex=0;

					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process9.selfprosition="未运行          ";
					process9.prostaJLabel.setText(process9.selfprosition);
					proSize9.setText("  0  ");

				}				
			}
			else if(cmprsp.randomnum==10)
			{
				if(process10.selfprosition=="未运行          "||process10.selfprosition=="就绪挂起")
				{
					process10.prosize=(int)(Math.random()*50+100);
					hString=hString.valueOf(process10.prosize);
					proSize10.setText(hString);
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(influence==0)
					{
					process10.lastIndex=findlasindex(process10.firstIndex, process10.lastIndex, process10.prosize);
					process10.firstIndex=findfirindex(process10.firstIndex, process10.lastIndex, process10.prosize);
					}else
					if(influence==1)
					{
						process10.firstIndex=worseFindFirIndex(process10.firstIndex, process10.lastIndex, process10.prosize);
						process10.lastIndex=worseFindLasIndex(process10.firstIndex, process10.lastIndex, process10.prosize);
						fillMemory(process10.firstIndex,process10.lastIndex);
					}else
					if(influence==2)
					{
						process10.firstIndex=secondFindFirIndex(process10.firstIndex, process10.lastIndex, process10.prosize);
						process10.lastIndex=secondFindLasIndex(process10.firstIndex, process10.lastIndex, process10.prosize);
						fillMemory(process10.firstIndex,process10.lastIndex);
					}
					if(process10.lastIndex!=0)
					{
					process10.selfprosition="运行准备";
					process10.prostaJLabel.setText(process10.selfprosition);
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process10.selfprosition="正在运行";
					process10.prostaJLabel.setText(process10.selfprosition);
					}else {
//						System.out.println("程序分配的地址"+process10.lastIndex);
//						System.out.print("程序一的大小是"+process10.prosize);
						process10.selfprosition="就绪挂起";
						process10.prostaJLabel.setText(process10.selfprosition);
					}

				}
				else if(process10.selfprosition=="正在运行")
				{
					try {
						this.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process10.selfprosition="运行完毕";
					process10.prostaJLabel.setText(process10.selfprosition);
					freeindex(process10.firstIndex, process10.lastIndex);
					process10.firstIndex=0;
					process10.lastIndex=0;

					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					process10.selfprosition="未运行          ";
					process10.prostaJLabel.setText(process10.selfprosition);
					proSize10.setText("  0  ");

				}				
			}

		}
		}

	}

	class BlockListSearching extends Thread
	{
		public void run()
		{
			while(true)
			{
				while(reStart==false)
				{
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				findAtWaitingListProcess();
			}
		}
	}
	
    class Repeater extends Thread
    {
    	public void run()
    	{
    		while(true)
    		{
    			while(reStart==false)
				{
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
    			dM.repaint();
    		}
    	}
    }

	class ComputerResponse extends Thread

	{
		int responsenum=0;
		String[] memoryArrays=new String[512];
		String[] colorArrays=new String[512];
		int randomnum;
		
		public void run() {
			
			while(true)
			{
				while(reStart==false)
				{
					randomnum=0;
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				randomnum=(int)((Math.random()*10)+1);
//				System.out.println("随机到的是"+randomnum);
				try {
					this.sleep(2500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			}
			
		
	}

	class BlankSearching extends Thread
	{
		public void run()
		{
			while(true){
				while(reStart==false)
				{
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			findBlankIndexArea();
		}
		}
	}
	
	class Processexample extends JPanel
	{
		String selfprosition="未运行          ";
		JLabel prostaJLabel=new JLabel(selfprosition);
		String[] selfMemory=new String[512];
		boolean computer=false;
		int prosize;
		boolean MemoryEnough=false;
		boolean selffunction=false;
		int firstIndex=0;
		int lastIndex=0;
		boolean isAllocated=false;
		String colorString;
		Color selfColor=Color.black;
		public Processexample()
		{
			prostaJLabel=new JLabel(selfprosition);
		}
		
        public void paintComponent(Graphics g){ 
            super.paintComponent(g);
            g.setColor(selfColor);

        	g.fill3DRect(0,10+lastIndex-firstIndex,70,70,true);
        }
	}
    
    class drawMemory extends JPanel
    {
    	public void paintComponent(Graphics g)
    	{
    		super.paintComponent(g);
    		
    		//给每个进程绘制一个小长方体，来作为颜色的标签。
    		
    		g.setColor(Color.black);
    		g.draw3DRect(29, 29, 121, 513, true);
    		
    		if(process1.lastIndex!=0)
    		{
    			g.setColor(process1.selfColor);
    			g.fill3DRect(30, 30+process1.firstIndex, 120, process1.lastIndex-process1.firstIndex+1, true);
    		}
    		if(process2.lastIndex!=0)
    		{
    			g.setColor(process2.selfColor);
    			g.fill3DRect(30, 30+process2.firstIndex, 120, process2.lastIndex-process2.firstIndex+1, true);
    		}
    		if(process3.lastIndex!=0)
    		{
    			g.setColor(process3.selfColor);    		
    			g.fill3DRect(30, 30+process3.firstIndex, 120, process3.lastIndex-process3.firstIndex+1, true);

    		}
    		if(process4.lastIndex!=0)
    		{
    			g.setColor(process4.selfColor);
    			g.fill3DRect(30, 30+process4.firstIndex, 120, process4.lastIndex-process4.firstIndex+1, true);
    		}
    		if(process5.lastIndex!=0)
    		{
    			g.setColor(process5.selfColor);
    			g.fill3DRect(30, 30+process5.firstIndex, 120, process5.lastIndex-process5.firstIndex+1, true);
    		}
    		if(process6.lastIndex!=0)
    		{
    			g.setColor(process6.selfColor);
    			g.fill3DRect(30, 30+process6.firstIndex, 120, process6.lastIndex-process6.firstIndex+1, true);
    		}if(process7.lastIndex!=0)
    		{
    			g.setColor(process7.selfColor);
    			g.fill3DRect(30, 30+process7.firstIndex, 120, process7.lastIndex-process7.firstIndex+1, true);
    		}if(process8.lastIndex!=0)
    		{
    			g.setColor(process8.selfColor);
    			g.fill3DRect(30, 30+process8.firstIndex, 120, process8.lastIndex-process8.firstIndex+1, true);
    		}if(process9.lastIndex!=0)
    		{
    			g.setColor(process9.selfColor);
    			g.fill3DRect(30, 30+process9.firstIndex, 120, process9.lastIndex-process9.firstIndex+1, true);
    		}
    		if(process10.lastIndex!=0)
    		{
    			g.setColor(process10.selfColor);
    			g.fill3DRect(30, 30+process10.firstIndex, 120, process10.lastIndex-process10.firstIndex+1, true);
    		}
    	}
    }

	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == firstMethodButton) {
				firstMethodButton.setEnabled(false);
				worseMethodButton.setEnabled(true);
				secondMethodButton.setEnabled(true);
				System.out.println("a");
				influence=0;
			}
			if (e.getSource() == worseMethodButton) {
				firstMethodButton.setEnabled(true);
				worseMethodButton.setEnabled(false);
				secondMethodButton.setEnabled(true);
				System.out.println("b");
				influence=1;
			}
			if(e.getSource()==secondMethodButton)
			{
				firstMethodButton.setEnabled(true);
				worseMethodButton.setEnabled(true);
				secondMethodButton.setEnabled(false);
				System.out.println("c");
				influence=2;
			}
			if(e.getSource()==startButton)
			{
				startButton.setEnabled(false);
				shotDownButton.setEnabled(true);
				if(influentialKey==0)
				{
					StartThread();
					reStart=true;
					startButton.setEnabled(false);
					stopButton.setEnabled(true);
					shotDownButton.setEnabled(true);
				}
				if(influentialKey==1)
				{
					reStart=true;
					stopButton.setEnabled(true);
					shotDownButton.setEnabled(true);
					startButton.setEnabled(false);
				}
				if(influentialKey==2)
				{
					MemoryManager reOne=new MemoryManager();
				    dispose();
				    int width = 580; 
					int height = 600; 
					Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
					reOne.setSize(width,height);//设置程序的大小 
					reOne.setLocation((int)(dm.getWidth()-width)/2,(int)(dm.getHeight()-height)/2);
					reOne.setSize(580,600);
					reOne.setVisible(true);
					reOne.startButton.setEnabled(false);
					reOne.stopButton.setEnabled(true);
					reOne.shotDownButton.setEnabled(true);
					reOne.StartThread();
					reStart=true;
				}
			}
			if(e.getSource()==shotDownButton)
			{
				startButton.setEnabled(true);
				shotDownButton.setEnabled(false);
				stopButton.setEnabled(false);
				cmprsp.randomnum=0;
				influentialKey=2;
				reStart=false;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cleanEveryJLabel();
				cleanMemory();
				cleanProcess();
			}
			if(e.getSource()==stopButton)
			{
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
				influentialKey=1;
				reStart=false;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}

