import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * This class builds a hash-based index for the Project2Dataset directory.
 */
public class HashBasedIndex {

    /**
     * This method builds a hash-based index for the Project2Dataset directory.
     * 
     * @return a hash map of the index
     */
    public static HashMap<Integer, String> buildIndex() {
        HashMap<Integer, String> index = new HashMap<Integer, String>();

        File directory = new File("Project2Dataset");

        for (File file : directory.listFiles()) {
            String fileNumber = file.getName().substring(1, file.getName().lastIndexOf('.'));
            // System.out.println(fileNumber);

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                String[] recordArray = line.split("\\.\\.\\."); // split the line into records
                for (String record : recordArray) {
                    // System.out.println(record);
                    String[] fieldArray = record.split(","); // split the record into fields
                    int RandomV = Integer.parseInt(fieldArray[3].trim());
                    // System.out.println(RandomV);
                    if (index.containsKey(RandomV)) {
                        System.out.println("Duplicate key: " + RandomV + " in file: " + index.get(RandomV)
                                + " and file: " + fileNumber);
                    }
                    index.put(RandomV, fileNumber);
                }
            } catch (Exception e) {
                System.out.println("Error reading file: " + fileNumber);
                e.printStackTrace();
            }

        }

        return index;
    }

}
