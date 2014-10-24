package ProcessManage;
/**  
 * SubThread��  
 * ģ��������̵��߳�  
 * @author linpeitian  
 */   
   
public class SubThread extends Thread{   
       
    public static final String READY = "Ready";   
    public static final String RUNNING = "Running";   
    public static final String STOP = "Stop";   
    public static final String NOTREADY = "Not Ready";   
       
    public String process = null;     //ģ���������   
    public int start = 0;             //ģ����̵Ŀ�ʼ����ʱ��   
    public int time = 0;              //ģ���������ʱ��   
    public int run = 0;               //ģ�����������ʱ��   
    public String status = NOTREADY;           //ģ���������״̬   
       
    /**  
     * �̵߳Ĺ��캯��  
     * �������� vs ������  
     */   
    public SubThread(){ }   
    public SubThread(String process, int start, int time){   
        this.process = process;   
        this.start = start;   
        this.time = time;   
    }   
       
    /**  
     * �̵߳ľ������ʵϰ����  
     * �߳���  
     */   
    public void run(){   
        ;   
        //����Ĳ���   
    }   
       
    public String getProcess(){   
        return this.process;   
    }   
    public void setProcess(String process){   
        this.process = process;   
    }   
       
    public int getStart(){   
        return this.start;   
    }   
    public void setStart(int start){   
        this.start = start;   
    }   
       
    public int getTime(){   
        return this.time;   
    }   
    public void setTime(int time){   
        this.time = time;   
    }   
       
    public int getRun(){   
        return this.run;   
    }   
    public void setRun(int run){   
        this.run = run;   
    }   
       
    public String getStatus(){   
        return this.status;   
    }   
    public void setStatus(String status){   
        this.status = status;   
    }   
       
}   