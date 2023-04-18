import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Memory.getInstance();

        while (true) {
            System.out.println("Program is ready and waiting for user command.");
            String Command = sc.nextLine();

            // System.out.println(Command);

            String[] commandArray = Command.split(" ");
            if (commandArray[0].toUpperCase().equals("CREATE")) {
                Memory.getInstance().buildIndex();
                // for (int i = 0; i < 1000; i++) {
                //     System.out.println("RandomV: " + (i + 1) + " File and Offsets: " + Memory.getInstance().getArrayIndex()[i]);
                // }
                // for (int i = 0; i < 1000; i++) {
                //     System.out.println("RandomV: " + (i + 1) + " File and Offsets: " + Memory.getInstance().getHashIndex().get(i + 1));
                // }
            } else if (commandArray[0].toUpperCase().equals("SELECT")) {

            } else if (commandArray[0].toUpperCase().equals("EXIT")) {
                break;
            }

        }

        sc.close();
    }

}
