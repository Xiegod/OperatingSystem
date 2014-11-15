package ProcessManage;
/**  
 * MainThread类  
 * 主线程，调度模拟进程的运行  
 * @author linpeitian  
 */   
   
//import MainUI;
//import SubThread;

import javax.swing.*;   
   
public class MainThread extends Thread{   
   
    /**  
     * 接受传参 MainUI中的控件  
     */   
    public int num = 0;           //进程数目   
    public int block = 0;         //时间片大小   
    public SubThread subThread[] = new SubThread[20];  //进程组   
    public JTextArea area = null;   //运行状态显示域   
    public JLabel timeSys = null;   //时间显示域   
    public JLabel status = null;    //状态显示域   
    public JButton reset = null;    //重置按钮       
       
    public boolean running = true; //主控线程运行控制   
       
    /**  
     * 构造函数  
     * 将MainUI传  
     * @param mainUI  
     */   
    public MainThread(MainUI mainUI){   
        this.num = mainUI.number;         //模拟进程的个数赋值   
        this.block = mainUI.timeblock;    //时间片大小赋值   
        this.subThread = mainUI.subThread;//具体的线程数组赋值   
        this.area = mainUI.area;          //运行显示域赋值   
        this.timeSys = mainUI.timeSys;    //时间显示域赋值   
        this.status = mainUI.status;      //状态显示域赋值   
        this.reset = mainUI.reset;        //重置按钮赋值   
    }   
       
    /**  
     * 主线程的run方法  
     * 线程体  
     */   
    public void run(){   
        int mainTime = 0;   //记录系统运行总时间   
        int curTmp = 0;      
        int runTime = 0;    //记录进程需耗费时间长度   
        int curThread = 0;  //记录当前运行的下个进程   
        //主程序，调度其他进程，一直运行，知道所有进程均完成为止   
        while(running){   
            if(check()){   
                //检测是否进程都运行完之一没运行完情况   
                //启动未启动的进程   
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
                int kk = 0;        //标记是否有进程在占cpu，还是cpu空闲   
                int circle = 0;    //防止死循环   
                int t = curThread; //记录下个将运行的进程   
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
                //CPU处于空闲状态   
                if(kk == 0){   
                    for(int i = 0; i < num; i++){   
                        if(subThread[i].getStart() > mainTime){   
                            runTime = subThread[i].getStart() - mainTime;   
                            break;   
                        }   
                    }    
                }   
                show();   
                //进程的具体操作   
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
                        timeSys.setText("第" + new Integer(mainTime+i+1).toString() + "秒");   
                        show();   
                    }   
                }catch(InterruptedException e){   
                    e.printStackTrace();   
                }   
                //进程时间片用完后的操作   
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
                //检测是否进程都运行完之一运行完情况   
                running = false;   
            }   
        }   
        status.setText("进程运行完成,若要继续，按重置按钮");   
        reset.setEnabled(true);   
    }   
       
    /**  
     * 显示进程的具体信息  
     */   
    public void show(){   
        area.setText("");   
        area.setText("进程名字\t进程状态\t到达时间\tCPU时间\t已运行时间\n");   
        for(int i = 0 ; i < num; i++)   
        area.append(subThread[i].getProcess() + "\t"    
                + subThread[i].getStatus() + "\t"   
                + subThread[i].getStart() + "\t"   
                + subThread[i].getTime() + "\t"   
                + subThread[i].getRun() + "\n");   
    }   
   
    /**  
     * 用于检测是否所有进程都运行完了  
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
