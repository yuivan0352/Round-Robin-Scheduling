import java.io.FileNotFoundException;
import java.util.*;

public class CPU {
    private static LinkedList<Process> queue = new LinkedList<Process>();
    private static ArrayList<Process> finProcess = new ArrayList<Process>();
    private static int clock = 0;
    private static int quantum, conSwitch;

    public static void main(String args[]) throws FileNotFoundException{
        try {
            ProcessCreator pc = new ProcessCreator("processes.csv");
            queue = pc.queueProcesses();
            quantum = Integer.parseInt("2");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }

        while (queue.size() != 0) {
            queue.peek().decreaseBurst(quantum);
            if (queue.peek().getRemBurst() < 0) {
                clock += quantum + queue.peek().getRemBurst();
                queue.peek().setRemBurst(0);
            } else {
                clock += quantum;
            }
            System.out.println(clock + ": " + queue.peek().getPid() + ", " + queue.peek().getRemBurst());
            if (queue.peek().getRemBurst() <= 0) {
                queue.peek().setCompletion(clock);
                queue.peek().calcTurnaround();
                queue.peek().calcWaiting();
                queue.peek().calcResponse(clock);
                finProcess.add(queue.pop());
            } else if (queue.peek().getRemBurst() != 0) {
                queue.offer(queue.pop());
            }
            conSwitch++;
        }

        System.out.println("");

        for (int i = 0; i < finProcess.size(); i++) {
            System.out.println(finProcess.get(i));
        }

        System.out.println("Clock: " + clock);
        System.out.println("# of Context Switches: " + conSwitch + "\n");
    }
}