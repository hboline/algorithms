import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class insertion_sort {
    // static method ArrGet returns an array from specified file
    static List<Integer> ArrGet(String path) {
        List<Integer> array = new ArrayList<Integer>();
        
        // handle I/O exception for reader, next line keeps it in scope
        Scanner reader = null;
        try {
            reader = new Scanner(new File(path));
        } catch (IOException e) {
            System.err.println("file not found");
        }
        
        int step;
        while(reader.hasNextLine()) {
            try {
                step = reader.nextInt();
            } catch (NoSuchElementException e) {
                continue;
            }
            array.add(step);
        }
        reader.close();
        
        return array;
    }

    // insertion sort method -- arrayList version
    static List<Integer> sort(List<Integer> A) {
        int n = A.size();
        for (int i = 1; i < n; i++) {
            int key = A.get(i);
            int j = i - 1;
            while (j >= 0 && A.get(j) > key) {
                A.set(j+1,A.get(j));
                j -= 1;
            }
            A.set(j+1,key);
        }
        return A;
    }

    public static void main(String[] args) {
        if(args.length > 0) {
            List<Integer> A = ArrGet(args[0]);
            List<Integer> A_correct = ArrGet(args[1]);
            
            long exectime = System.currentTimeMillis();
            List<Integer> A_sorted = sort(new ArrayList<>(A));
            exectime = System.currentTimeMillis() - exectime;

            String s_exectime = new String();
            if(exectime >= 1000) {
                s_exectime = String.format("%.3fs", (float) exectime/1000L);
            } else {
                s_exectime = String.format("%.1fms", (float) exectime);
            }
            
            System.out.println(
                String.format(
                    "Array \"A\" was%ssorted correctly",
                    (A_sorted.equals(A_correct)) ? " " : " not "
                )
            );
            System.out.println(String.format("Runtime: %s", s_exectime));
        } else {
            Random rand = new Random();
            List<Integer> A = new ArrayList<Integer>();
            for(int i = 0; i < 20; i++) {
                A.add(rand.nextInt(100));
            }
            
            List<Integer> A_sorted = sort(new ArrayList<>(A));
    
            System.out.println("Original: " + A.toString());
            System.out.println("  Sorted: " + A_sorted.toString());
        }
    }
}