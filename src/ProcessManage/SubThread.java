package ProcessManage;
/**  
 * SubThread类  
 * 模拟各个进程的线程  
 * @author linpeitian  
 */   
   
public class SubThread extends Thread{   
       
    public static final String READY = "Ready";   
    public static final String RUNNING = "Running";   
    public static final String STOP = "Stop";   
    public static final String NOTREADY = "Not Ready";   
       
    public String process = null;     //模拟进程名字   
    public int start = 0;             //模拟进程的开始运行时间   
    public int time = 0;              //模拟进程运行时间   
    public int run = 0;               //模拟进程已运行时间   
    public String status = NOTREADY;           //模拟进程运行状态   
       
    /**  
     * 线程的构造函数  
     * 不带参数 vs 带参数  
     */   
    public SubThread(){ }   
    public SubThread(String process, int start, int time){   
        this.process = process;   
        this.start = start;   
        this.time = time;   
    }   
       
    /**  
     * 线程的具体操作实习内容  
     * 线程体  
     */   
    public void run(){   
        ;   
        //具体的操作   
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