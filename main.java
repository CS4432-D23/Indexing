import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        
        HashMap<Integer, String> index = HashBasedIndex.buildIndex();
        System.out.println(index.size());

    }

}

