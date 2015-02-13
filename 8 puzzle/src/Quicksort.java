import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Quicksort  {
  private static int[] numbers;
  private int number;
public static void main(String args[]){
    Random r=new Random();
    int len=10000000;
    int[] num=new int[len];
    File f=new File("test.txt");
   // PrintWriter out= new PrintWriter(f);
    for(int i=0;i<len;i++)
   num[i]=r.nextInt();
    
    Quicksort q= new Quicksort();
    q.sort(num);
    System.out.println(Arrays.toString(numbers));
}
  public void sort(int[] values) {
    // Check for empty or null array
    if (values ==null || values.length==0){
      return;
    }
    numbers = values;
    number = values.length;
    quicksort(0, number - 1);
  }

  private void quicksort(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = numbers[low + (high-low)/2];

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (numbers[i] < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (numbers[j] > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(int i, int j) {
    int temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
  }
} 