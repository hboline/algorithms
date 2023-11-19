import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<Integer> L = new ArrayList<>(A.subList(p, q+1));
        List<Integer> R = new ArrayList<>(A.subList(q+1, r+1));
        int i = 0;
        int j = 0;
        int k = p;
        while(i < nl && j < nr) {
            if(L.get(i) <= R.get(j)) {
                A.set(k, L.get(i));
                i ++;
            } else {
                A.set(k, R.get(j));
                j ++;
            }
            k ++;
        }
        while(i < nl) {
            A.set(k, L.get(i));
            i ++;
            k ++;
        }
        while(j < nr) {
            A.set(k, R.get(j));
            j ++;
            k ++;
        }
    }

    static void merging_sort(List<Integer> A, int p, int r) {
        if(p != r) {
            int q = (p + r)/2;
            merging_sort(A, p, q);
            merging_sort(A, q+1, r);
            merge(A, p, q, r);
        }
    }

    static String timeFormatter(long time) {
        double nano = 1000000000.0;
        if(time > nano) {
            return(String.format("%.3fs", time/nano));
        } else {
            return(String.format("%.1fms", time/1000000.0));
        }
    }

    public static void main(String[] args) {
        List<Integer> A = arrayFromPath(args[0]);
        List<Integer> A_correct = arrayFromPath(args[1]);

        int p = 0;
        int r = A.size()-1;

        long exectime = System.nanoTime();
        merging_sort(A,p,r);
        exectime = System.nanoTime() - exectime;

        System.out.println(String.format("Array \"A\" was%ssorted correctly", (A.equals(A_correct)) ? " " : " not "));
        System.out.println(String.format("Runtime: %s\n", timeFormatter(exectime)));
    }
}