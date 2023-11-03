import java.util.Arrays;
import java.util.Random;

public class insertion_sort {
    // insertion sort method
    static int[] sort(int[] A_in) {
        int[] A  = A_in.clone();
        int n = A.length;
        for (int i = 1; i < n; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j+1] = A[j];
                j -= 1;
            }
            A[j+1] = key;
        }
        return(A);
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        int[] A = new int[25];
        for(int i = 0; i < A.length; i++) {
            A[i] = rand.nextInt(100);
        }
        
        int[] A_sorted = sort(A);

        System.out.println("Original: " + Arrays.toString(A));
        System.out.println("  Sorted: " + Arrays.toString(A_sorted));
    }
}