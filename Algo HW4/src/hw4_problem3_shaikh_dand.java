import java.sql.Time;
import java.util.Random;

/**
* 
* hw4_problem3_shaikh_dand.java
* @author Trushank
* Date Dec 26, 2012
* Version 1.0
* 
 * 
 */

/**
 * @author Trushank
 *
 */
public class hw4_problem3_shaikh_dand {
public static void main(String args[]){
    int arr[][]= generateArray(10);
   System.out.println("\nMax Block: "+findBlock(arr));
}
public static int findBlock(int arr[][]){
    int hori[][]=logHorizontal(arr);
    int verti[][]=logVertical(arr);
    System.out.println("Horizontal:");
    printArray(hori);
    System.out.println("Vertical:");
    printArray(verti);
    
    int max=0;
    for(int i=0;i<arr.length;i++){
	for(int j=0;j<arr.length;j++){
	    if(hori[i][j]==verti[i][j] && hori[i][j]>max){
		System.out.println("i: "+i+" j: "+j+" max: "+hori[i][j]);
		max=hori[i][j];
	    }
	}
    }
    return max;
}
public static void printArray(int arr[][]){
    for(int i=0;i<arr.length;i++){
	System.out.println();
	for(int j=0;j<arr.length;j++){
	    System.out.print(arr[i][j]+" ");
	}
	
    }
    System.out.println();
}
public static int[][] logHorizontal(int arr[][]){
    int log[][]=new int[arr.length][arr.length];
    for(int i=0;i<arr.length;i++){
	int max=0;
   	for(int j=0;j<arr.length;j++){
   	 
   	    if(arr[i][j]==1){
   		log[i][j]=++max;
   	    }
   	    else{
   		max=0;
   	    }
   	}
   	
       }
    return log;
}
public static int[][] logVertical(int arr[][]){
    int log[][]=new int[arr.length][arr.length];
    for(int i=0;i<arr.length;i++){

	int max=0;
   	for(int j=0;j<arr.length;j++){
   	    
   	    if(arr[j][i]==1){
   		log[j][i]=++max;
   	    }
   	    else{
   		max=0;
   	    }
   	
   	}
   	
       }
    return log;
}
public static int[][] generateArray(int n){
    Random r=new Random(System.currentTimeMillis());
    int arr[][]=new int[n][n];
    System.out.println("Generating array");
    for(int i=0;i<n;i++){
	System.out.println();
	for(int j=0;j<n;j++){
	    arr[i][j]=r.nextInt(2);
	    System.out.print(arr[i][j]+" ");
	}
    }
    return arr;
}
}
