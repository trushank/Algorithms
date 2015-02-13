/**
 * 
 * hw3_problem1_shaikh_dand.java
 * Finds the best location to open the candy shop
 * @author Trushank
 * @author Ashpak 
 * Date Dec 20, 2012
 * Version 1.0
 *
 * 
 */

import java.util.*;

/**
 * hw3_problem1_shaikh_dand
 * 
 * @author Trushank
 * @author Ashpak Finds the best location to open the candy shop
 */

public class hw3_problem1_shaikh_dand {
    /**
     * main
     * 
     * @param args
     */
    public static void main(String args[]) {

	Scanner src = new Scanner(System.in);
	int n = Integer.parseInt(src.nextLine()); // Taking size of inputs
	int arr[] = new int[n];
	for (int i = 0; i < n; i++) { // Taking inputs
	    arr[i] = src.nextInt();
	}
	src.close();

	int postionK = arr.length / 2; // Median position to be found
	if (arr.length % 2 == 0) { // Median position if array is even
	    postionK--;
	}
	int lookFor = sort(postionK, arr); // looking for median

	int potentialPositionRight = lookFor; // possible candyshop location to
					      // the right of median

	int potentialPositionLeft = lookFor; // possible candyshop location to
					     // the left of median

	boolean checkLeft = true; // check if empty position found at left of
				  // median

	boolean checkRight = true; // check if empty position found at right of
				   // median
	int solution = 0; // position for the candy shop
	int finalSolution = 0; // summation of minimum distance from candy shop

	while (checkLeft) { // finding empty spot to the left
	    potentialPositionLeft--;
	    checkLeft = false;
	    for (int i = 0; i < arr.length; i++) {
		if (arr[i] == potentialPositionLeft) {
		    checkLeft = true;
		}
	    }
	}
	while (checkRight) { // finding empty spot to the right
	    potentialPositionRight++;
	    checkRight = false;
	    for (int i = 0; i < arr.length; i++) {
		if (arr[i] == potentialPositionRight) {
		    checkRight = true;
		}
	    }

	}

	// checking which is closer. Left or Right
	if (lookFor - potentialPositionLeft > potentialPositionRight - lookFor) {
	    solution = potentialPositionRight;
	} else {
	    solution = potentialPositionLeft;

	}
	// Summing distance between candyshop and the houses
	for (int i = 0; i < arr.length; i++) {
	    finalSolution += Math.abs(solution - arr[i]);
	}

	// Printing result
	System.out.println(finalSolution);
    }

    /**
     * bubbleSort Sorts the input array and returns median of the sorted array
     * 
     * @param arr
     *            input arrry
     * @return median
     * 
     */
    public static int bubbleSort(int arr[]) {
	if (arr.length == 1) {
	    return arr[0];
	}
	for (int i = 0; i < arr.length - 1; i++) {
	    for (int j = 1; j < arr.length; j++) {

		if (arr[i] > arr[j]) {
		    int a = arr[i];
		    arr[i] = arr[j];
		    arr[j] = a;
		}
	    }
	}
	return arr[arr.length / 2];
    }

    /**
     * generateSeed Generates Median of Meadian
     * 
     * @param arr
     *            input array
     * @return median of median
     * 
     */
    public static int generateSeed(int arr[]) {
	int len = 0;

	if (arr.length == 1) {
	    return arr[0];
	}
	if (arr.length % 5 == 0) {
	    len = arr.length / 5;
	} else {
	    len = (arr.length / 5) + 1;
	}
	int arrOfMedians[] = new int[len]; // to store the medians of each n/5
					   // size array
	int medianIndex = 0;
	for (int i = 0; i < arr.length;) {
	    int smallArr[] = new int[5];
	    for (int j = 0; j < 5 && i < arr.length; j++) { // storing every 5
							    // elements in temp
							    // array
		smallArr[j] = arr[i++];
	    }
	    arrOfMedians[medianIndex++] = bubbleSort(smallArr); // storing
								// median in
								// median array
	}
	return bubbleSort(arrOfMedians); // Return median of median
    }

    /**
     * sort K-Selection algorithm Returns elmenent at Kth position if array were
     * sorted
     */
    public static int sort(int position, int arr[]) {

	int seed = generateSeed(arr); // getting median of medians
	int smaller[] = new int[arr.length]; // stores numbers smaller than seed
	int larger[] = new int[arr.length]; // stores numbers larger than seed
	int equal[] = new int[arr.length]; // stores numbers equal to seed
	int smallerIndex = 0; // index of smaller array
	int largerIndex = 0; // index of larger array
	int equalIndex = 0; // index of equal array

	// storing elements relative to pivot
	for (int i = 0; i < arr.length; i++) {
	    if (arr[i] > seed) {
		larger[largerIndex++] = arr[i];
	    } else if (arr[i] < seed) {
		smaller[smallerIndex++] = arr[i];
	    } else if (arr[i] == seed) {
		equal[equalIndex++] = arr[i];

	    }
	}

	// Combining the arrays
	int last[] = new int[arr.length];
	int lastIndex = 0;
	for (int i = 0; i < smallerIndex; i++) {
	    last[lastIndex++] = smaller[i];
	}

	for (int i = 0; i < equalIndex; i++) {
	    last[lastIndex++] = equal[i];
	}

	for (int i = 0; i < largerIndex; i++) {
	    last[lastIndex++] = larger[i];
	}

	int j2;
	int j1 = smallerIndex;

	if (equalIndex != 0)
	    j2 = smallerIndex + equalIndex - 1;
	else
	    j2 = smallerIndex + largerIndex - 1;

	if (position >= j1 && position <= j2) { // if pivot is at the position
						// required return it
	    return seed;

	} else if (j1 > position) { // if pivot is more than position than
				    // discard larger array

	    int small[] = new int[smallerIndex];
	    for (int i = 0; i < smallerIndex; i++) {
		small[i] = smaller[i];
	    }
	    return sort(position, small);

	} else {
	    // if pivot is less than position than discard smaller array
	    int large[] = new int[largerIndex];
	    for (int i = 0; i < largerIndex; i++) {
		large[i] = larger[i];
	    }
	    return sort(position - (j2 + 1), large);
	}
    }
}
