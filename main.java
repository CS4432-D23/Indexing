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
            if (commandArray[0].toUpperCase().equals("CREATE")) {
                Memory.getInstance().buildIndex();
                System.out.println("Index built");
                // for (int i = 0; i < 1000; i++) {
                //     System.out.println("RandomV: " + (i + 1) + " File and Offsets: " + Memory.getInstance().getArrayIndex()[i]);
                // }
                // for (int i = 0; i < 1000; i++) {
                //     System.out.println("RandomV: " + (i + 1) + " File and Offsets: " + Memory.getInstance().getHashIndex().get(i + 1));
                // }
            } else if (commandArray[0].toUpperCase().equals("SELECT")) {
                ArrayList<String> records = new ArrayList<>();

                if (commandArray[6].equals("=")) {
                    String findRandomV = commandArray[7];
                    int findKey = Integer.parseInt(findRandomV);

                    if(Memory.getInstance().isIndexerBuilt()) {
                        System.out.println("Search using hash index...");
                        long startTime = System.currentTimeMillis();

                        if (Memory.getInstance().getHashIndex().containsKey(findKey)) {
                            // System.out.println(Memory.getInstance().getHashIndex().get(findKey));
                            ArrayList<String> values = Memory.getInstance().getHashIndex().get(findKey);
                            int count = 0;

                            for (String s : values) {
                                String[] valueArr = s.split("\\|");
                                String filenumber = valueArr[0].trim();
                                String offset = valueArr[1].trim();
                                // System.out.println("File Number: " + filenumber + " Offset: " + offset);
                                String record = Memory.getInstance().getRecord(filenumber, offset);
                                records.add(record);
                                count++;
                            }
                            System.out.println("Number of data files read: " + count);

                        }
                        else { // if the key is not found
                            System.out.println("No record found.");
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

                    if (records.isEmpty()) { // if the records is empty
                        System.out.println("No record found.");
                    }
                    
                    else { // print out the records

                        for (String record : records) { 
                            System.out.println(record);
                        }
                    }
                }

                else if (commandArray[6].equals(">")) {
                    // section 5
                }
                else if (commandArray[6].equals("!=")) {
                    // section 6
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
