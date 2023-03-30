import java.util.*;
import java.io.*;

public class ProcessHandler {
    private File processFile;
    private Scanner reader;
    private ArrayList<Process> processes= new ArrayList<Process>();

    public ProcessHandler(String fileName) throws FileNotFoundException{
        try {
            String[] tmp = new String[3];
            processFile = new File(".\\" + fileName);
            reader = new Scanner(processFile);
            reader.nextLine();

            while (reader.hasNextLine()) {
                tmp = reader.nextLine().split(",");
                processes.add(new Process(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2])));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }
    }
}