/***************************************
 * CSCI 330 M03 Spring 2023
 * Ivan Yu, Jerry Weng
 * CPU Scheduling Programming Project
 * 4/23/2023
****************************************/

// Performs all process execution and tracks the time of process execution

import java.io.FileNotFoundException;
import java.util.*;

public class CPU {
    private static LinkedList<Process> queue = new LinkedList<Process>();
    private static ArrayList<Process> finProcess = new ArrayList<Process>();
    private static int clock, quantum, conSwitch, avgWait, avgTurnaround, idleTime;
    private static final int conSwitchTime = 2;
    private static double cpuUtil = 1.0;

    public static void main(String args[]) throws FileNotFoundException{
        try {
            ProcessCreator pc = new ProcessCreator(args[0]);
            queue = pc.queueProcesses();
            quantum = Integer.parseInt(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }
        
        System.out.println("\n----------Process Execution---------- \n");

        while (queue.size() != 0) {
            System.out.println(clock + "ms: ");
            System.out.println(" PID: " + queue.peek().getPid() + "\n Arrival Time: " + queue.peek().getArrival() + "\n Burst Time: " + queue.peek().getRemBurst() + "\n");
            queue.peek().decreaseBurst(quantum);
            if (queue.peek().getRemBurst() < 0) {
                clock += quantum + queue.peek().getRemBurst();
                queue.peek().setRemBurst(0);
            } else {
                clock += quantum;
            }

            System.out.println(clock + "ms: ");
            System.out.println(" PID: " + queue.peek().getPid() + "\n Arrival Time: " + queue.peek().getArrival() + "\n Burst Time: " + queue.peek().getRemBurst() + "\n");
            
            if (queue.peek().getRemBurst() <= 0) {
                queue.peek().setCompletion(clock);
                queue.peek().calcTurnaround();
                queue.peek().calcWaiting();
                queue.peek().calcResponse(clock);
                finProcess.add(queue.pop());
            } else if (queue.peek().getRemBurst() != 0) {
                queue.offer(queue.pop());
            }
            
            if (queue.size() != 0) {
                conSwitch++;
                idleTime += conSwitchTime;
                clock += conSwitchTime;
            }
        }

        System.out.println("\n----------Process Info---------- \n");

        for (int i = 0; i < finProcess.size(); i++) {
            avgWait += finProcess.get(i).getWaiting();
            avgTurnaround += finProcess.get(i).getTurnaround();
            avgWait /= finProcess.size();
            avgTurnaround /= finProcess.size();
            System.out.println(finProcess.get(i));
        }

        cpuUtil -= (double)idleTime / (double)clock;
        cpuUtil = Math.round(cpuUtil * 100.0);

        System.out.println("Clock: " + clock + "ms");
        System.out.println("# of Context Switches: " + conSwitch);
        System.out.println("Average Waiting Time: " + avgWait + "ms");
        System.out.println("Average Turnaround Time: " + avgTurnaround + "ms");
        System.out.println("Idle Time: " + idleTime + "ms");
        System.out.println("CPU Utilization: " + cpuUtil + "%\n");
    }
}