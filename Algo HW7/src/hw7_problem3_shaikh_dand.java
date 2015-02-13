import java.util.Scanner;

/**
 * 
 * hw7_problem3_shaikh_dand.java
 * @author Trushank
 * Date Feb 8, 2013
 * Version 1.0
 * 
 */
/**
 * @author Trushank
 * 
 */
public class hw7_problem3_shaikh_dand {

    /**
     * main
     * 
     * @param args
     */
    public static void main(String[] args) {
	
	Scanner scr = new Scanner(System.in);
	int n = scr.nextInt();		//input number of vertices
	int numOfEdges = scr.nextInt();	//input number of edges
	int S[][][] = new int[n + 1][n + 1][n + 1]; // distance matrix
	int path[][] = new int[n + 1][n + 1]; // number of min paths

	//Initialize array to represent infinity
	for (int i = 1; i < n + 1; i++) {
	    for (int j = 1; j < n + 1; j++) {
		S[i][j][0] = Integer.MAX_VALUE;
	    }
	}
	
	//change value for diagonal elements in the array
	for (int i = 1; i < n + 1; i++) {
	    path[i][i] = 1;
	    S[i][i][0] = 0;
	}
	
	//inout the edges and their weight
	for (int i = 0; i < numOfEdges; i++) {
	    int x = scr.nextInt();
	    int y = scr.nextInt();
	    S[x][y][0] = scr.nextInt();
	    path[x][y] = 1;
	}
	
	//looping through k=1 to n+1 (layers)
	for (int k = 1; k < n + 1; k++) {
	  //looping through i=1 to n+1	(coloumns)
	    for (int i = 1; i < n + 1; i++) {
		//looping through j=1 to n+1	(rows)
		for (int j = 1; j < n + 1; j++) {
		    
		    //don't consider cases to self
		    if (i != j) {
			
			//don't consider if value is infinity (infinity+infinity=error)
			if (S[i][k][k - 1] != Integer.MAX_VALUE
				&& S[k][j][k - 1] != Integer.MAX_VALUE) {

			    //if adding k to the path reduces cost
			    if (S[i][j][k - 1] >= (S[i][k][k - 1] + S[k][j][k - 1])) {
				//update cost to cost of including k
				S[i][j][k] = (S[i][k][k - 1] + S[k][j][k - 1]);
				
				//if cost of previous path and current path is the same, this means there are two paths with same cost. Increment number of paths for path i,j
				if (S[i][j][k - 1] == S[i][k][k - 1]
					+ S[k][j][k - 1]
					&& k != j && k != i)
				    path[i][j]++;
				//else if k is simply being included make of path i,j equal to that of path k,j
				else if (S[i][j][k - 1] > S[i][k][k - 1]
					+ S[k][j][k - 1]
					&& k != j && k != i){
				 //assign current max to path i,j
				    path[i][j] = Math.max(path[k][j],path[i][k]);
				}
			    } 
			    //else don't include k
			    else {
				S[i][j][k] = S[i][j][k - 1];
			    }
			} else {
			    S[i][j][k] = S[i][j][k - 1];
			}
		    }

		}
	    }

	}
	print(S, path, n);	//printing output
    }

    /**
     * 
     * print: Prints the required output
     * @param S
     * @param path
     * @param a
     */
    public static void print(int S[][][], int path[][], int a) {
	for (int i = 1; i < S[0][0].length; i++) {
	    System.out.println();
	    for (int j = 1; j < S[0][0].length; j++) {
		String p = "";
		if (S[i][j][a] == 2147483647)
		    p = "inf";
		else
		    p = Integer.toString(S[i][j][a]);
		System.out.print(p + "/" + path[i][j] + " ");
	    }
	}
    }
}
