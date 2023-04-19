// Creates Processes based on file input and sorts them based on arrival time

import java.util.*;
import java.io.*;

public class ProcessCreator {
    private File processFile;
    private Scanner reader;
    private HashMap<Integer, ArrayList<Process>> processes = new HashMap<Integer, ArrayList<Process>>();

    public ProcessCreator(String fileName) throws FileNotFoundException{
        try {
            String[] tmp = new String[3];
            processFile = new File("./" + fileName);
            reader = new Scanner(processFile);
            reader.nextLine();
            int pid, arrival, burst;

            while (reader.hasNextLine()) {
                tmp = reader.nextLine().split(",");
                pid = Integer.parseInt(tmp[0]);
                arrival = Integer.parseInt(tmp[1]);
                burst = Integer.parseInt(tmp[2]);

                if (!processes.containsKey(arrival)) {
                    processes.put(arrival, new ArrayList<Process>());
                }

                processes.get(arrival).add(new Process(pid, arrival, burst));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }
    }

    public LinkedList<Process> queueProcesses() {
        LinkedList<Process> tmp = new LinkedList<Process>();
        for (Integer key : processes.keySet()) {
            tmp.addAll(processes.get(key));
        }

        return tmp;
    }
}