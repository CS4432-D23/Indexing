import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * This class is used to build the index for the Project2Dataset directory.
 */
public class Memory {

    private static Memory instance; // the instance of the Memory class
    private HashMap<Integer, ArrayList<String>> hashIndex = null;
    private String[] arrayIndex = null;

    /*
     * This is a private constructor to prevent the creation of new instances of the
     */
    private Memory() {
    }

    /**
     * This method returns the instance of the Memory class.
     * @return the instance of the Memory class
     */
    public static Memory getInstance() {
        if (instance == null) {
            instance = new Memory();
        }
        return instance;
    }

    
    /**
     * This method builds a hash-based and array-based index for the Project2Dataset directory.
     * 
     * @return a hash map of the index
     */
    public void buildIndex() {
        HashMap<Integer, ArrayList<String>> hashIndex = new HashMap<>();
        String[] arrayIndex = new String[5000];


        File directory = new File("Project2Dataset");

        for (File file : directory.listFiles()) {
            String fileNumber = file.getName().substring(1, file.getName().lastIndexOf('.'));
            // System.out.println(fileNumber);

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                String[] recordArray = line.split("\\.\\.\\."); // split the line into records
                for (String record : recordArray) {
                    String[] fieldArray = record.split(","); // split the record into fields
                    String offset = fieldArray[0].substring(fieldArray[0].length() - 3); // extract the offset
                    // System.out.println(offset);
                    int RandomV = Integer.parseInt(fieldArray[3].replaceAll("\\s", "")); // extract the RandomV
        

                    if (hashIndex.containsKey(RandomV)) {
                        ArrayList<String> fileAndOffsetList = new ArrayList<>();
                        fileAndOffsetList.add(fileNumber + " | " + offset);
                        hashIndex.get(RandomV).addAll(fileAndOffsetList);

                    } else {
                        ArrayList<String> fileAndOffsetList = new ArrayList<>();
                        fileAndOffsetList.add(fileNumber + " | " + offset);
                        hashIndex.put(RandomV, fileAndOffsetList);
                    }

                    if (arrayIndex[RandomV - 1] == null) {
                        arrayIndex[RandomV - 1] = fileNumber + " | " + offset;
                    } else {
                        arrayIndex[RandomV - 1] += ", " + fileNumber + " | " + offset;
                    }

                }
                br.close();
            } catch (Exception e) {
                System.out.println("Error reading file: " + fileNumber);
                e.printStackTrace();
            }

        }

        this.hashIndex = hashIndex;
        this.arrayIndex = arrayIndex;
    }

    /**
     * This method checks if the index is built.
     * @return true if the index is built, false otherwise
     */
    public Boolean isIndexerBuilt() {
        if (hashIndex == null || arrayIndex == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method returns the hash-based index.
     * @return the hash-based index
     */
    public HashMap<Integer, ArrayList<String>> getHashIndex() {
        return hashIndex;
    }

    /**
     * This method returns the array-based index.
     * @return the array-based index
     */
    public String[] getArrayIndex() {
        return arrayIndex;
    }


}