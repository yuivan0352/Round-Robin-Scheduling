public class Process {
    private int pid, arrive, burst;
    
    public Process(int pid, int arrive, int burst) {
        this.pid = pid;
        this.arrive = arrive;
        this.burst = burst;
    }

    public int getPid()                    {return pid;}
    public void setPid(int newPid)         {pid = newPid;}
    public int getArrive()                 {return arrive;}
    public void setArrive(int newArrive)   {arrive = newArrive;}
    public int getBurst()                  {return burst;}
    public void setBurst(int newBurst)     {burst = newBurst;}
}
