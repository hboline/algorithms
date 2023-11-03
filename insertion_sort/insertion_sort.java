import java.util.Random;

public class insertion_sort {
    public int[] test() {
        int[] testArray = {1,2,3,4,5};
        return testArray;
    }
    public static void main(String[] args) {
        insertion_sort instMain = new insertion_sort();
        int[] testArray = instMain.test();
        System.out.print(testArray.toString());
    }
}