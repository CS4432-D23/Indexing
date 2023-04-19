import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Memory.getInstance();

        while (true) {
            System.out.println("\nProgram is ready and waiting for user command.");
            String Command = sc.nextLine();
            System.out.println("");

            // System.out.println(Command);

            String[] commandArray = Command.split(" ");

            // for (String s : commandArray) {
            //     System.out.println(s);
            // }
            if (commandArray[0].toUpperCase().equals("CREATE")) {
                Memory.getInstance().buildIndex();
                System.out.println("Index built");
                // for (int i = 0; i < 1000; i++) {
                //     System.out.println("RandomV: " + (i + 1) + " File and Offsets: " + Memory.getInstance().getArrayIndex()[i]);
                // }
                // for (int i = 0; i < 1000; i++) {
                //     System.out.println("RandomV: " + (i + 1) + " File and Offsets: " + Memory.getInstance().getHashIndex().get(i + 1));
                // }
            } else if (commandArray[0].toUpperCase().equals("SELECT")) { // if the command is SELECT
                ArrayList<String> records = new ArrayList<>();

                if (commandArray[6].equals("=")) { // if the operator is =
                    String findRandomV = commandArray[7];
                    int findKey = Integer.parseInt(findRandomV);

                    if(Memory.getInstance().isIndexerBuilt()) {
                        System.out.println("Search using hash index...");
                        long startTime = System.currentTimeMillis();

                        if (Memory.getInstance().getHashIndex().containsKey(findKey)) {
                            // System.out.println(Memory.getInstance().getHashIndex().get(findKey));
                            ArrayList<String> values = Memory.getInstance().getHashIndex().get(findKey);
                            int count = 0;
                            records.add(Integer.toString(count));

                            for (String s : values) {
                                String[] valueArr = s.split("\\|");
                                String filenumber = valueArr[0].trim();
                                String offset = valueArr[1].trim();
                                // System.out.println("File Number: " + filenumber + " Offset: " + offset);
                                String record = Memory.getInstance().getRecordFromOffset(filenumber, offset);
                                records.add(record);
                                count++;
                            }

                            records.set(0, Integer.toString(count));

                        }
                        else { // if the key is not found
                            System.out.println("No record found.");
                            records.add("0");
                        }
                        long endTime = System.currentTimeMillis() - startTime;
                        System.out.println("Time taken: " + endTime + "ms");
                    }

                    else { // if the index is not built, use table scan
                        System.out.println("Search using table scan...");
                        long startTime = System.currentTimeMillis();
                        for (String record : Memory.getInstance().getRecord(findKey)) {
                            records.add(record);
                        }
                        long endTime = System.currentTimeMillis() - startTime;
                        System.out.println("Time taken: " + endTime + "ms");
                    }

                    int numFiles = Integer.parseInt(records.get(0));
                    System.out.println("Number of data files read: " + numFiles);

                    if (records.size() < 2) { // if the records is empty
                        System.out.println("No record found.");
                    }
                    
                    else { // print out the records

                        Boolean isFirst = true;

                        for (String record : records) { 
                            if (!isFirst) {
                                System.out.println(record);
                            }
                            else {
                                isFirst = false;
                            }
                        }
                    }
                }
                
                else if (commandArray[6].equals(">")) {
                    // section 5

                    int findRandomV1 = Integer.parseInt(commandArray[7]);
                    int findRandomV2 = Integer.parseInt(commandArray[11]);

                    if (Memory.getInstance().isIndexerBuilt()) { // if the array index is built
                        System.out.println("Search using array index...");
                        long startTime = System.currentTimeMillis();

                        records = (Memory.getInstance().getRecord(findRandomV1, findRandomV2));

                        long endTime = System.currentTimeMillis() - startTime;
                        System.out.println("Time taken: " + endTime + "ms");
                        
                        

                    }

                    else { // if the index is not built, use table scan
                        System.out.println("Search using table scan...");

                        long startTime = System.currentTimeMillis();

                        records = Memory.getInstance().getRecordTableScan(findRandomV1, findRandomV2);
                            
                        long endTime = System.currentTimeMillis() - startTime;
                        System.out.println("Time taken: " + endTime + "ms");

                    }
                    int numFiles = Integer.parseInt(records.get(0));
                    System.out.println("Number of data files read: " + numFiles);

                    if (records.size() < 2) { // if the records is empty
                        System.out.println("No record found.");
                    }
                    else { // print out the records

                        Boolean isFirst = true;
                        for (String record : records) { 
                            if (!isFirst) {
                                System.out.println(record);
                            }
                            else {
                                isFirst = false;
                            }
                        }
                    }
                    
                }
                else if (commandArray[6].equals("!=")) {
                    // section 6

                    System.out.println("Search using table scan...");

                    long startTime = System.currentTimeMillis();

                    int RandomV = Integer.parseInt(commandArray[7]);
                    records = (Memory.getInstance().getRecordInequality(RandomV));

                    long endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Time taken: " + endTime + "ms");

                    int numFiles = Integer.parseInt(records.get(0));
                    System.out.println("Number of data files read: " + numFiles);

                    if (records.size() < 2) { // if the records is empty
                        System.out.println("No record(s) found.");
                    }
                    else { // print out the records

                        Boolean isFirst = true;
                        
                        for (String record : records) { 
                            if (!isFirst) {
                                System.out.println(record);
                            }
                            else {
                                isFirst = false;
                            }
                        }
                    }
                    
                }


            } else if (commandArray[0].toUpperCase().equals("EXIT")) {
                break;
            }

            else { // if the command is not recognized
                System.out.println("Command not recognized.");}

        }

        sc.close();
    }

}
