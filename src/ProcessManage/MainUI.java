package ProcessManage;

/**  
 * MainUI��  
 * ��main()��ڷ���  
 * @author linpeitian  
 */   
   
//import SubThread;

import java.awt.*;   
import java.awt.event.*;   

import javax.swing.*;   
   
public class MainUI extends JFrame implements ActionListener{   
       
    /**  
     * UI���  
     */   
    public JButton confirm = null;  //���ܽ�����Ŀ��ʱ��Ƭ��Сȷ�ϰ�ť   
    public JTextField processNumField = null; //���������Ŀ��   
    public JTextField timeField = null;       //����ʱ��Ƭ��С��   
       
    public JTextField processNameField = null;   //�����������   
    public JTextField processStartField = null;  //������̿�ʼʱ����   
    public JTextField processLenField = null;    //������̺�ʱ��    
       
    public JButton reset = null;            //���ð�ť   
    public JButton submit = null;   //�����ύ��ť   
    public JButton runSubmit = null; //���̵������а�ť  
    public JButton randomSubmit=null; //������
       
    public JTextArea area = null;    //���̾��������ʾ��   
    public JLabel timeSys = null;    //�����ʱ��ʾ   
    public JLabel status = null;     //����״̬��ʾ   
       
    private JSeparator separator = null;   
       
    /**  
     * �߼�����  
     */   
    public int number = 0;           //������   
    public int timeblock = 0;         //ʱ��Ƭ   
    public int threadNum = 0;   
    public SubThread subThread[] = new SubThread[20]; //�߳�   
    public MainThread mainThread = null;          //���߳�   
    public int count=1;   
    /**  
     * ���캯��  
     * ��Ҫ����ͼ�ν���Ĺ���   
     */   
    public MainUI(){   
        super("���̵���ģ��");   
        JFrame.setDefaultLookAndFeelDecorated(true);   
        JDialog.setDefaultLookAndFeelDecorated(true);    
           
        //���岼�� GridBagLayout���ַ�ʽ   
        GridBagConstraints cst = null;   
        GridBagLayout layout = new GridBagLayout();   
        getContentPane().setLayout(layout);   
           
        confirm = new JButton("ȷ��");   
        confirm.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.gridwidth = 1;   
        cst.gridheight = 2;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(confirm, cst);   
        getContentPane().add(confirm);   
           
        JLabel processNumLabel = new JLabel("������Ŀ:");   
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
           
        JLabel timeLabel = new JLabel("ʱ��Ƭֵ:");   
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
           
        JLabel processNameLabel = new JLabel("��������:");   
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
           
        JLabel processStartLabel = new JLabel("����ʱ��:");   
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
           
        JLabel processLenLabel = new JLabel("����ʱ��:");   
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
           
        reset = new JButton("����");   
        reset.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.anchor = GridBagConstraints.EAST;   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(reset, cst);   
        getContentPane().add(reset);   
           
        submit = new JButton("�ύ����");   
        submit.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(submit, cst);   
        getContentPane().add(submit); 
        
        randomSubmit = new JButton("������ɽ���");   
        randomSubmit.addActionListener(this);   
        cst = new GridBagConstraints();   
        cst.insets = new Insets(5, 5, 5, 5);   
        layout.setConstraints(randomSubmit, cst);   
        getContentPane().add(randomSubmit);
           
        runSubmit = new JButton("���н���");   
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
           
        //��λ����   
        resetDone();   
    }   
       
    /**  
     * ActionEvent �¼�����  
     */   
    public void actionPerformed(ActionEvent e){   
        Object oj = e.getSource();   
        if(oj == confirm){   
            //ȷ������������Լ�ʱ��Ƭ��С��ť�¼�   
            if(processNumField.getText().trim().equals("")||timeField.getText().trim().equals("")){   
                //����Ϊ�մ���   
                JOptionPane.showMessageDialog(this,   
                        "�����������Ŀ����ʱ��ƬΪ��!!",   
                        "����",   
                        JOptionPane.ERROR_MESSAGE);   
            }else{   
                try{   
                    number = Integer.parseInt(processNumField.getText());   
                    timeblock = Integer.parseInt(timeField.getText());   
                    //�߼��Ϸ�����֤   
                    if(number <= 0 || number > 10){   
                        JOptionPane.showMessageDialog(this,   
                                "�����������Ӧ����1<=n<=10!!",   
                                "����",   
                                JOptionPane.ERROR_MESSAGE);   
                    }else if(timeblock <= 0){   
                        JOptionPane.showMessageDialog(this,   
                                "������ʱ��Ƭ��СӦ��t>=0!!",   
                                "����",   
                                JOptionPane.ERROR_MESSAGE);   
                    }else{   
                        confirmDone();   
                        threadNum = 0;   
                    }   
                }catch(NumberFormatException ee){   
                    //����������Լ�ʱ��ƬΪ�մ���   
                    JOptionPane.showMessageDialog(this,   
                            "�����������Ŀ��ʱ��Ƭ��С��ʽ����",   
                            "����",   
                            JOptionPane.ERROR_MESSAGE);   
                }   
            }   
        }else if(oj == reset){   
            //���ð�ť�¼�   
            resetDone();   
        }else if(oj == submit){   
            //�ύ���̰�ť�¼�   
            if(processNameField.getText().trim().equals("")||processStartField.getText().trim().equals("")||processLenField.getText().trim().equals("")){   
                //����Ϊ��״̬����   
                JOptionPane.showMessageDialog(this,   
                        "���������Ϣ��������֤!!",   
                        "����",   
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
                        //�߼�������   
                        JOptionPane.showMessageDialog(this,   
                                "���������Ϣ���󣬿��ܴ��ڸ���!!",   
                                "����",   
                                JOptionPane.ERROR_MESSAGE);   
                    }else{   
                        //�Ϸ�,����һ����Ӧ���߳���ģ�����   
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
                    //������Ϣ�Ƿ��������   
                    JOptionPane.showMessageDialog(this,   
                            "���������Ϣ��ʽ����!!",   
                            "����",   
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
     * ��Ӧsubmit��ť�¼�  
     */   
    public void submitDone(){   
        //���ڽ�����ʾˢ��   
        processNameField.setText("");   
        processNameField.setEditable(false);   
        processStartField.setText("");   
        processStartField.setEditable(false);   
        processLenField.setText("");   
        processLenField.setEditable(false);   
        submit.setEnabled(false);   
        runSubmit.setEnabled(true);   
        status.setText("�����ð�ť���س�ʼ״̬�������н��̿�ʼ���̵���");   
    }   
       
    /**  
     * confirmDone()  
     * �൱����Ӧconfirm��ť�¼�  
     */   
    public void confirmDone(){   
        //���ڽ�����ʾˢ��   
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
        status.setText("������������̵������Ϣ");   
    }   
       
    /**  
     * resetDone()  
     * �൱�ڼ���reset��ť�¼�  
     */   
    public void resetDone(){   
        //���ڽ�����ʾˢ��   
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
        timeSys.setText("��0��");   
        status.setText("�����������Ŀ�Լ�ʱ��Ƭ��С");   
        //�ͷ���Դ   
        for(int i = 0;i < number; i++)   
            subThread[i] = null;   
        number = 0;   
        timeblock = 0;   
        threadNum = 0;   
        mainThread = null;   
        //area��ʾ�߳�״̬��Ϣ   
        display(); 
        count=1;
    }   
       
    /**  
     * display()  
     * �൱��ˢ��area�еľ�������  
     */   
    public void display(){   
        //�����̰߳�����ʱ����Ⱥ�����   
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
        //ˢ��area�еľ�������   
        area.setText("");   
        area.setText("��������\t����״̬\t����ʱ��\tCPUʱ��\t������ʱ��\n");   
        for(int i = 0 ; i < threadNum; i++)   
        area.append(subThread[i].getProcess() + "\t"    
                + subThread[i].getStatus() + "\t"   
                + subThread[i].getStart() + "\t"   
                + subThread[i].getTime() + "\t"   
                + subThread[i].getRun() + "\n");   
    }   
       
    /**  
     * ������ڷ���main  
     * @param args  
     */   
  
   
}   