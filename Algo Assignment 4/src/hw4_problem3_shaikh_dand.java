import java.util.Scanner;

/**
 * 
 * hw4_problem3_shaikh_dand.java
 * @author Trushank
 * @author Ashpak
 * Date Jan 11, 2013
 * Version 1.0
 * 
 * 
 */
/**
 * class hw4_problem3_shaikh_dand
 * 
 * @author Trushank
 * @author Ashpak
 */
public class hw4_problem3_shaikh_dand {
    /**
     * @param args
     */
    public static void main(String[] args) {

	Scanner src = new Scanner(System.in);
	int length = src.nextInt(); // Input length of matrix
	int matrix[][] = new int[length][length]; // initializing array
	int result[][] = new int[length + 1][length + 1]; // initializing
							  // processing array

	// Inputting elements
	for (int i = 0; i < length; i++) {
	    for (int j = 0; j < length; j++) {
		matrix[i][j] = src.nextInt();
		result[i + 1][j + 1] = matrix[i][j]; // first row and coloumn
						     // set to zero
	    }
	}

	int max = result[1][1]; // initializing max to [1][1]

	// Traverse through the entire array
	for (int i = 1; i < length + 1; i++) {
	    for (int j = 1; j < length + 1; j++) {
		// if element is 1
		if (result[i][j] != 0) { // Current element is 1 + min(previous,
					 // bottom, bottom left)
		    result[i][j] = 1 + Math.min(result[i][j - 1],
			    Math.min(result[i - 1][j], result[i - 1][j - 1]));
		}

		// Checking if new element is bigger than current max
		if (max < result[i][j]) {
		    max = result[i][j];
		}
	    }

	}
	System.out.println(max);

    }

}
