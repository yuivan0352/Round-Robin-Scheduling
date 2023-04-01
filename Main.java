import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static LinkedList<Process> queue = new LinkedList<Process>();
    private static int clock = 0;
    private static int quantum, conSwitch;

    public static void main(String args[]) throws FileNotFoundException{
        try {
            ProcessCreator pc = new ProcessCreator(args[0]);
            queue = pc.queueProcesses();
            quantum = Integer.parseInt(args[1]);
            System.out.println(quantum);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }
    }
}
