package ProcessManage;
/**  
 * MainThread��  
 * ���̣߳�����ģ����̵�����  
 * @author linpeitian  
 */   
   
//import MainUI;
//import SubThread;

import javax.swing.*;   
   
public class MainThread extends Thread{   
   
    /**  
     * ���ܴ��� MainUI�еĿؼ�  
     */   
    public int num = 0;           //������Ŀ   
    public int block = 0;         //ʱ��Ƭ��С   
    public SubThread subThread[] = new SubThread[20];  //������   
    public JTextArea area = null;   //����״̬��ʾ��   
    public JLabel timeSys = null;   //ʱ����ʾ��   
    public JLabel status = null;    //״̬��ʾ��   
    public JButton reset = null;    //���ð�ť       
       
    public boolean running = true; //�����߳����п���   
       
    /**  
     * ���캯��  
     * ��MainUI��  
     * @param mainUI  
     */   
    public MainThread(MainUI mainUI){   
        this.num = mainUI.number;         //ģ����̵ĸ�����ֵ   
        this.block = mainUI.timeblock;    //ʱ��Ƭ��С��ֵ   
        this.subThread = mainUI.subThread;//������߳����鸳ֵ   
        this.area = mainUI.area;          //������ʾ��ֵ   
        this.timeSys = mainUI.timeSys;    //ʱ����ʾ��ֵ   
        this.status = mainUI.status;      //״̬��ʾ��ֵ   
        this.reset = mainUI.reset;        //���ð�ť��ֵ   
    }   
       
    /**  
     * ���̵߳�run����  
     * �߳���  
     */   
    public void run(){   
        int mainTime = 0;   //��¼ϵͳ������ʱ��   
        int curTmp = 0;      
        int runTime = 0;    //��¼������ķ�ʱ�䳤��   
        int curThread = 0;  //��¼��ǰ���е��¸�����   
        //�����򣬵����������̣�һֱ���У�֪�����н��̾����Ϊֹ   
        while(running){   
            if(check()){   
                //����Ƿ���̶�������֮һû���������   
                //����δ�����Ľ���   
                for(int i = 0; i < num; i++){   
                    if(subThread[i].getStart() <= mainTime && subThread[i].getStatus().equals(SubThread.NOTREADY)){   
                        subThread[i].start();   
                        subThread[i].setStatus(SubThread.READY);   
                        subThread[i].suspend();   
                        curTmp ++;   
                    }   
                    if(subThread[i].getStart() > mainTime)   
                        break;   
                }   
                show();   
                int kk = 0;        //����Ƿ��н�����ռcpu������cpu����   
                int circle = 0;    //��ֹ��ѭ��   
                int t = curThread; //��¼�¸������еĽ���   
                for( ; t < curTmp; t++){   
                    if(circle == curTmp){   
                        break;   
                    }   
                    circle ++;   
                    if(subThread[t].getStatus().equals(SubThread.STOP)){   
                        if(t == curTmp-1)   
                            t = -1;   
                        continue;   
                    }else{   
                        kk = 1;   
                        runTime = subThread[t].getTime() - subThread[t].getRun();   
                        if(runTime > block)    
                            runTime = block;   
                        subThread[t].setStatus(SubThread.RUNNING);   
                        subThread[t].resume();   
                        curThread = t+1;   
                        if(curThread == curTmp){   
                            if(curTmp == num ||subThread[curTmp].getStart() > runTime + mainTime)   
                                curThread = 0;   
                        }   
                        break;   
                    }   
                }   
                //CPU���ڿ���״̬   
                if(kk == 0){   
                    for(int i = 0; i < num; i++){   
                        if(subThread[i].getStart() > mainTime){   
                            runTime = subThread[i].getStart() - mainTime;   
                            break;   
                        }   
                    }    
                }   
                show();   
                //���̵ľ������   
                try{   
                    for(int i = 0; i < runTime; i++){   
                        for(int tt = curTmp; tt < num; tt++){   
                            if(subThread[tt].getStart() <= mainTime + i && subThread[tt].getStatus().equals(SubThread.NOTREADY)){   
                                subThread[tt].start();   
                                subThread[tt].setStatus(SubThread.READY);   
                                subThread[tt].suspend();   
                                curTmp ++;   
                            }   
                            if(subThread[tt].getStart() > mainTime)   
                                break;   
                        }   
                        sleep(1000);   
                        if(kk == 1){   
                            subThread[t].setRun(subThread[t].getRun() + 1);   
                        }   
                        timeSys.setText("��" + new Integer(mainTime+i+1).toString() + "��");   
                        show();   
                    }   
                }catch(InterruptedException e){   
                    e.printStackTrace();   
                }   
                //����ʱ��Ƭ�����Ĳ���   
                mainTime = mainTime + runTime;   
                if( kk == 1){   
                    if((subThread[t].getTime() - subThread[t].getRun()) == 0){   
                        subThread[t].setStatus(SubThread.STOP);   
                        subThread[t].stop();   
                    }else{   
                        subThread[t].setStatus(SubThread.READY);   
                        subThread[t].suspend();   
                    }   
                }   
                show();   
            }else {   
                //����Ƿ���̶�������֮һ���������   
                running = false;   
            }   
        }   
        status.setText("�����������,��Ҫ�����������ð�ť");   
        reset.setEnabled(true);   
    }   
       
    /**  
     * ��ʾ���̵ľ�����Ϣ  
     */   
    public void show(){   
        area.setText("");   
        area.setText("��������\t����״̬\t����ʱ��\tCPUʱ��\t������ʱ��\n");   
        for(int i = 0 ; i < num; i++)   
        area.append(subThread[i].getProcess() + "\t"    
                + subThread[i].getStatus() + "\t"   
                + subThread[i].getStart() + "\t"   
                + subThread[i].getTime() + "\t"   
                + subThread[i].getRun() + "\n");   
    }   
   
    /**  
     * ���ڼ���Ƿ����н��̶���������  
     * @return  
     */   
    public boolean check(){   
        for(int i = 0; i < num; i++){   
            if(!subThread[i].getStatus().equals(SubThread.STOP))   
                return true;   
        }   
        return false;   
    }   
   
}  
