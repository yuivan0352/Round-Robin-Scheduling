// Process object class that holds all process information

public class Process {
    private int pid, arrival, burst, remBurst, completion, turnaround, waiting, response;
    
    public Process(int pid, int arrival, int burst) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.remBurst = burst;
    }

    public void calcTurnaround() {
        turnaround = completion - arrival;
    }

    public void calcWaiting() {
        waiting = turnaround - burst;
    }

    public void calcResponse(int firstClock) {
        response = firstClock - arrival;
    }

    public void decreaseBurst(int quantum) {
        remBurst -= quantum;
    }

    public int getPid()                         {return pid;}
    public void setPid(int newPid)              {pid = newPid;}
    public int getArrival()                     {return arrival;}
    public void setArrival(int newArrival)      {arrival = newArrival;}
    public int getBurst()                       {return burst;}
    public void setBurst(int newBurst)          {burst = newBurst;}
    public int getRemBurst()                    {return remBurst;}
    public void setRemBurst(int newRem)         {remBurst = newRem;}
    public int getCompletion()                  {return completion;}
    public void setCompletion(int newComplete)  {completion = newComplete;}
    public int getTurnaround()                  {return turnaround;}
    public void setTurnaround(int newTurnaround){turnaround = newTurnaround;}
    public int getWaiting()                     {return waiting;}
    public void setWaiting(int newWaiting)      {waiting = newWaiting;}
    public int getResponse()                    {return response;}
    public void setResponse(int newResponse)    {response = newResponse;}
    

    public String toString() {
        String tmp = "";
        tmp += "Process ID: " + pid + "\n";
        tmp += "Arrival Time: " + arrival + "\n";
        tmp += "Burst Time: " + burst + "ms\n";
        tmp += "Completion Time: " + completion + "ms\n";
        tmp += "Turnaround Time: " + turnaround + "ms\n";
        tmp += "Waiting Time: " + waiting + "ms\n";
        tmp += "Response Time: " + response + "ms\n";
        return tmp;
    }
}
