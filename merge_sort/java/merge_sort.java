import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class merge_sort {
    static List<Integer> arrayFromPath(String path) {
        List<Integer> A = new ArrayList<Integer>();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(path));
            while(reader.ready()) {
                A.add(Integer.parseInt(reader.readLine()));
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("file not found");
        }

        return(A);
    }
    
    static void merge(List<Integer> A, int p, int q, int r) {
        int nl = q - p + 1;
        int nr = r - q;
        List<Integer> L = new ArrayList<>(&)
    }

    static void sort(List<Integer> A, int p, int r) {

    }

    public static void main(String[] args) {
        List<Integer> A = arrayFromPath(args[0]);

        System.out.println(A.subList(0, 10).toString());
    }
}