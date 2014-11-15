package ProcessManage;

/**  
 * MainUI类  
 * 带main()入口方法  
 * @author linpeitian  
 */   
   
//import SubThread;

import java.awt.*;   
import java.awt.event.*;   

import javax.swing.*;   
   
public class MainUI extends JFrame implements ActionListener{   
       
    /**  
     * UI组件  
     */   
    public JButton confirm = null;  //接受进程数目及时间片大小确认按钮   
    public JTextField processNumField = null; //输入进程数目域   
    public JTextField timeField = null;       //输入时间片大小域   
       
    public JTextField processNameField = null;   //输入进程名域   
    public JTextField processStartField = null;  //输入进程开始时间域   
    public JTextField processLenField = null;    //输入进程耗时域    
       
    public JButton reset = null;            //重置按钮   
    public JButton submit = null;   //进程提交按钮   
    public JButton runSubmit = null; //进程调度运行按钮  
    public JButton randomSubmit=null; //随机添加
       
    public JTextArea area = null;    //进程具体调度显示屏   
    public JLabel timeSys = null;    //具体耗时显示   
    public JLabel status = null;     //具体状态显示   
       
    private JSeparator separator = null;   
       
    /**  
     * 逻辑控制  
     */   
    public int number = 0;           //进程数   
    public int timeblock = 0;         //时间片   
    public int threadNum = 0;   
    public SubThread subThread[] = new SubThread[20]; //线程   
    public MainThread mainThread = null;          //主线程   
    public int count=1;   
    /**  
     * 构造函数  
     * 主要负责图形界面的构建   
     */   
    public MainUI(){   
        super("进程调度模拟");   
        JFrame.setDefaultLookAndFeelDecorated(true);   
        JDialog.setDefaultLookAndFeelDecorated(true);    
           
        //具体布局 GridBagLayout布局方式   
        GridBagConstraints cst = null;   
        GridBagLayout layout = new GridBagLayout();   
        getContentPane().setLayout(layout);   
           
        confirm = new JButton("确定");   
        confirm.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.gridwidth = 1;   
        cst.gridheight = 2;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(confirm, cst);   
        getContentPane().add(confirm);   
           
        JLabel processNumLabel = new JLabel("进程数目:");   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.CENTER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processNumLabel, cst);   
        getContentPane().add(processNumLabel);   
           
        processNumField = new JTextField(15);   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processNumField, cst);   
        getContentPane().add(processNumField);   
           
        JLabel timeLabel = new JLabel("时间片值:");   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.CENTER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(timeLabel, cst);   
        getContentPane().add(timeLabel);   
           
        timeField = new JTextField(15);   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(timeField, cst);   
        getContentPane().add(timeField);   
           
        separator = new JSeparator();   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(separator, cst);   
        getContentPane().add(separator);   
           
        JLabel processNameLabel = new JLabel("进程名称:");   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.EAST;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processNameLabel, cst);   
        getContentPane().add(processNameLabel);   
           
        processNameField = new JTextField();   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processNameField, cst);   
        getContentPane().add(processNameField);   
           
        JLabel processStartLabel = new JLabel("到达时间:");   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.EAST;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processStartLabel, cst);   
        getContentPane().add(processStartLabel);   
           
        processStartField = new JTextField();   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processStartField, cst);   
        getContentPane().add(processStartField);   
           
        JLabel processLenLabel = new JLabel("运行时间:");   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.EAST;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processLenLabel, cst);   
        getContentPane().add(processLenLabel);   
           
        processLenField = new JTextField();   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(processLenField, cst);   
        getContentPane().add(processLenField);   
           
        reset = new JButton("重置");   
        reset.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.EAST;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(reset, cst);   
        getContentPane().add(reset);   
           
        submit = new JButton("提交进程");   
        submit.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(submit, cst);   
        getContentPane().add(submit); 
        
        randomSubmit = new JButton("随机生成进程");   
        randomSubmit.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(randomSubmit, cst);   
        getContentPane().add(randomSubmit);
           
        runSubmit = new JButton("运行进程");   
        runSubmit.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(runSubmit, cst);   
        getContentPane().add(runSubmit);   
           
        separator = new JSeparator();   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(separator, cst);   
        getContentPane().add(separator);   
           
        area = new JTextArea(10, 40);   
        area.setEditable(false);   
        cst = new GridBagConstraints();   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(area, cst);   
        getContentPane().add(area);   
           
        timeSys = new JLabel("");   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(timeSys, cst);   
        getContentPane().add(timeSys);   
           
        status = new JLabel("");   
        cst = new GridBagConstraints();   
        cst.fill = GridBagConstraints.HORIZONTAL;   
        cst.gridwidth = GridBagConstraints.REMAINDER;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(status, cst);   
        getContentPane().add(status);   
           
        setSize(550, 510);   
        setResizable(false);   
        setVisible(true);   
           
        //复位操作   
        resetDone();   
    }   
       
    /**  
     * ActionEvent 事件处理  
     */   
    public void actionPerformed(ActionEvent e){   
        Object oj = e.getSource();   
        if(oj == confirm){   
            //确认输入进程数以及时间片大小按钮事件   
            if(processNumField.getText().trim().equals("")||timeField.getText().trim().equals("")){   
                //输入为空处理   
                JOptionPane.showMessageDialog(this,   
                        "请输入进程数目或者时间片为空!!",   
                        "出错",   
                        JOptionPane.ERROR_MESSAGE);   
            }else{   
                try{   
                    number = Integer.parseInt(processNumField.getText());   
                    timeblock = Integer.parseInt(timeField.getText());   
                    //逻辑合法性验证   
                    if(number <= 0 || number > 10){   
                        JOptionPane.showMessageDialog(this,   
                                "请输入进程数应该在1<=n<=10!!",   
                                "出错",   
                                JOptionPane.ERROR_MESSAGE);   
                    }else if(timeblock <= 0){   
                        JOptionPane.showMessageDialog(this,   
                                "请输入时间片大小应该t>=0!!",   
                                "出错",   
                                JOptionPane.ERROR_MESSAGE);   
                    }else{   
                        confirmDone();   
                        threadNum = 0;   
                    }   
                }catch(NumberFormatException ee){   
                    //输入进程数以及时间片为空处理   
                    JOptionPane.showMessageDialog(this,   
                            "请输入进程数目或时间片大小格式有误",   
                            "出错",   
                            JOptionPane.ERROR_MESSAGE);   
                }   
            }   
        }else if(oj == reset){   
            //重置按钮事件   
            resetDone();   
        }else if(oj == submit){   
            //提交进程按钮事件   
            if(processNameField.getText().trim().equals("")||processStartField.getText().trim().equals("")||processLenField.getText().trim().equals("")){   
                //输入为空状态处理   
                JOptionPane.showMessageDialog(this,   
                        "输入进程信息有误，请验证!!",   
                        "出错",   
                        JOptionPane.ERROR_MESSAGE);   
            }else{   
                String pname = null;   
                int start = 0;   
                int len = 0;   
                try{   
                    pname = processNameField.getText();   
                    start = Integer.parseInt(processStartField.getText());   
                    len = Integer.parseInt(processLenField.getText());   
                    if(start < 0 || len <= 0){   
                        //逻辑错误处理   
                        JOptionPane.showMessageDialog(this,   
                                "输入进程信息有误，可能存在负数!!",   
                                "出错",   
                                JOptionPane.ERROR_MESSAGE);   
                    }else{   
                        //合法,建立一个相应的线程来模拟进程   
                        subThread[threadNum] = new SubThread(pname, start, len);   
                        threadNum++;   
                        if(threadNum == number)   
                            submitDone();   
                        display();   
                        processNameField.setText("");   
                        processStartField.setText("");   
                        processLenField.setText("");   
                    }   
                }catch(NumberFormatException ee){   
                    //输入信息非法情况处理   
                    JOptionPane.showMessageDialog(this,   
                            "输入进程信息格式错误!!",   
                            "出错",   
                            JOptionPane.ERROR_MESSAGE);   
                }   
            }   
        }else if(oj == runSubmit){   
            mainThread = new MainThread(this);   
            mainThread.start();   
            reset.setEnabled(false);   
            runSubmit.setEnabled(false);   
        }
        else if(oj==randomSubmit){
        	if(count < number + 1)
        	{
	        	String pname="Process"+count;
	        	int start=(int)(Math.random()*10)+(int)(Math.random());
	        	int len=(int)(Math.random()*10) + 1;
	        	subThread[threadNum] = new SubThread(pname, start, len);   
	            threadNum++;   
	            if(threadNum == number)   
	                submitDone();   
	            display();   
	            processNameField.setText("");   
	            processStartField.setText("");   
	            processLenField.setText("");
	            count++;
        	}
        }
    }   
       
    /**  
     * submitDone()  
     * 相应submit按钮事件  
     */   
    public void submitDone(){   
        //窗口界面显示刷新   
        processNameField.setText("");   
        processNameField.setEditable(false);   
        processStartField.setText("");   
        processStartField.setEditable(false);   
        processLenField.setText("");   
        processLenField.setEditable(false);   
        submit.setEnabled(false);   
        runSubmit.setEnabled(true);   
        status.setText("按重置按钮返回初始状态，按运行进程开始进程调度");   
    }   
       
    /**  
     * confirmDone()  
     * 相当于响应confirm按钮事件  
     */   
    public void confirmDone(){   
        //窗口界面显示刷新   
        confirm.setEnabled(false);   
        processNumField.setEditable(false);   
        timeField.setEditable(false);   
        processNameField.setText("");   
        processNameField.setEditable(true);   
        processStartField.setText("");   
        processStartField.setEditable(true);   
        processLenField.setText("");   
        processLenField.setEditable(true);   
        submit.setEnabled(true);   
        status.setText("请输入各个进程的相关信息");   
    }   
       
    /**  
     * resetDone()  
     * 相当于激发reset按钮事件  
     */   
    public void resetDone(){   
        //窗口界面显示刷新   
        confirm.setEnabled(true);   
        processNumField.setText("");   
        processNumField.setEditable(true);   
        timeField.setText("");   
        timeField.setEditable(true);   
        processNameField.setText("");   
        processNameField.setEditable(false);   
        processStartField.setText("");   
        processStartField.setEditable(false);   
        processLenField.setText("");   
        processLenField.setEditable(false);   
        submit.setEnabled(false);   
        runSubmit.setEnabled(false);   
        timeSys.setText("第0秒");   
        status.setText("请输入进程数目以及时间片大小");   
        //释放资源   
        for(int i = 0;i < number; i++)   
            subThread[i] = null;   
        number = 0;   
        timeblock = 0;   
        threadNum = 0;   
        mainThread = null;   
        //area显示线程状态信息   
        display(); 
        count=1;
    }   
       
    /**  
     * display()  
     * 相当于刷新area中的具体内容  
     */   
    public void display(){   
        //各个线程按到达时间的先后排序   
        SubThread tmp = null;   
        for(int i = 0; i < threadNum; i++){   
            for(int j = i+1; j < threadNum; j++){   
                if(subThread[i].start > subThread[j].start){   
                    tmp = subThread[i];   
                    subThread[i] = subThread[j];   
                    subThread[j] = tmp;   
                }   
            }   
        }   
        //刷新area中的具体内容   
        area.setText("");   
        area.setText("进程名字\t进程状态\t到达时间\tCPU时间\t已运行时间\n");   
        for(int i = 0 ; i < threadNum; i++)   
        area.append(subThread[i].getProcess() + "\t"    
                + subThread[i].getStatus() + "\t"   
                + subThread[i].getStart() + "\t"   
                + subThread[i].getTime() + "\t"   
                + subThread[i].getRun() + "\n");   
    }   
       
    /**  
     * 程序入口方法main  
     * @param args  
     */   
  
   
}   