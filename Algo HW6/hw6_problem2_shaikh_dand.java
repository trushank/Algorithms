import java.util.Scanner;

/**
 * 
 * hw5_problem2_shaikh_dand.java
 * @author Trushank
 * Date Jan 30, 2013
 * Version 1.0
 * 
 */

/**
 * @author Trushank
 * @author Ashpak class Node Represents a node in the linked list
 */
class Node {
    Node next; // child of current node
    Node prev; // parent of current node
    int value; // value of current node

    /**
     * Constructor
     * 
     * @param value
     *            : value of current node
     * @param next
     *            : child of current node
     * @param prev
     *            : parent of current node
     */
    public Node(int value, Node next, Node prev) {
	this.value = value;
	this.next = next;
	this.prev = prev;
    }
}

/**
 * @author Trushank
 * @author Ashpak class LinkedList Provides the functions of a linked list data
 *         structure to efficiently store and access the adjacency
 */
class LinkedList {
    Node start; // Start of list
    Node end; // End of list

    /**
     * Constructor
     * 
     * @param n
     *            : First node
     */
    public LinkedList(Node n) {
	start = n;
	end = n;
    }

    /**
     * Empty Constructor
     */
    public LinkedList() {
    }

    public LinkedList combine(LinkedList adj) {
	if (adj != null) {
	    adj.start.prev = this.end;
	    this.end.next = adj.start;
	    this.end = adj.end;
	    adj = null;
	}
	return this;
    }

    /**
     * 
     * find: Find value in the list
     * 
     * @param value
     *            : value to be found
     * @return: Node associated with that value. Null if not found
     */
    public Node find(int value) {
	Node traverse = start; // Begin from start
	while (traverse != null) { // As long as current node is not null
	    if (traverse.value == value) {// Check if current value is the value
					  // we want
		return traverse;
	    } else {
		traverse = traverse.next; // go to next node
	    }

	}
	return null; // not found
    }

    /**
     * 
     * add: Add value to the linked list
     * 
     * @param val
     *            : value to be added
     */
    public void add(int val) {
	Node n = new Node(val, null, end);
	// if this is the first node
	if (start == null) {
	    start = n;
	    end = n;
	} else { // if this isn't the first node add normally at end
	    n.prev = end;
	    this.end.next = n;
	    this.end = n;
	}
    }

    /**
     * 
     * remove: remove value from the linked list (used for popping stack)
     * 
     * @param val
     *            : value to be removed
     * @return
     */
    public int remove(int val) {
	Node n = find(val); // finding the node with the value to be removed if
			    // it is the only node in the list
	if (start == n && end == n) {
	    start = null;
	    end = null;
	    return val;
	}
	// if it is the start node
	if (start == n) {
	    start = n.next;
	    n.next.prev = null;
	    return val;
	}
	// if it is the end node
	if (end == n) {
	    end = n.prev;
	    n.prev.next = null;
	    return val;
	}
	// if it is a node in the middle
	n.prev.next = n.next;
	n.next.prev = n.prev;
	return val;
    }

    /**
     * over rides toString to display contents of the list.
     * 
     */
    public String toString() {
	Node traverse = start; // Begin from start
	String ret = "";
	if (traverse == null) { // If list is empty
	    return "Empty";
	}
	while (traverse != null) {
	    ret = ret + " " + traverse.value; // concat value to ret string
	    traverse = traverse.next; // move to next node
	}
	return ret; // return string
    }

    /**
     * 
     * findMin: Find the smallest node in the list
     * 
     * @return Value of the smallest node
     */
    public int findMin() {

	if (start != null) { // check if list is empty
	    int min = start.value; // assign first value to be min
	    Node n = start.next; // Start checking from the next node
	    while (n != null) {
		if (n.value < min) { // if current nodes value is less than
				     // current min
		    min = n.value; // assign current nodes value to min
		}
		n = n.next; // move to next node
	    }

	    return min; // return min value
	}
	return -1; // if list empty return 0
    }

}

/**
 * @author Trushank
 * @author Ashpak class Stack Provides the functions of a Stack data structure
 */
class Stack {
    LinkedList adj;

    /**
     * constructor Uses the linked list and emulates behaviour of stack
     */
    public Stack() {
	// Making an Adjacencylist as it is a linked list and we can add stack
	// functions over it maintaining efficiency and reducing code
	adj = new LinkedList();

    }

    /**
     * 
     * push: pushes value into stack
     * 
     * @param val
     *            : value to be pushed
     */
    public void push(int val) {
	adj.add(val); // adds to the top of the stack (end of the list)
    }

    /**
     * 
     * pop: pops top of stack
     * 
     * @return top of stack
     */
    public int pop() {
	int val = adj.end.value;
	adj.remove(val); // pops tos (last element of list)
	return val; // return popped value
    }

    /**
     * 
     * isEmpty: checks if stack is empty
     * 
     * @return boolean isEmpty
     */
    public boolean isEmpty() {
	if (adj.start == null) {
	    return true;
	}
	return false;
    }
}

/********************************* Problem begins here *******************************/
/**
 * @author Trushank
 * @author Ashpak
 * hw6_problem2_shaikh_dand: finds the longest path in a given DAG
 */
public class hw6_problem2_shaikh_dand {

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {

	Scanner src = new Scanner(System.in);
	int vertices = src.nextInt(); // Number of vertices
	int edges = src.nextInt(); // Number of edges

	// Creating an array of AdjacencyList based on the number of vertices
	LinkedList adj[] = new LinkedList[vertices];

	for (int i = 0; i < vertices; i++) {
	    // initializing the array of AdjacencyList
	    adj[i] = new LinkedList();
	}

	for (int i = edges; i > 0; i--) {
	    int from = src.nextInt(); // inputting the edges
	    int to = src.nextInt();

	    // adding each entry to appropriate adjacency list
	    adj[from].add(to);

	}
	src.close(); // closing input
	// List of nodes yet to be visited
	LinkedList left = new LinkedList();
	for (int i = 0; i < adj.length; i++) {
	    left.add(i); // Adding all as none visited yet
	}

	// finding topological ordering of the graph
	LinkedList topologicalOrder = findTopologicalOrdering(adj, left);

	// passing the topological ordering and the adjacency list to find the
	// longest path
	findMaxLength(topologicalOrder, adj);
    }

    /**
     * 
     * findMaxLength: finds the longest path in the given graph
     * 
     * @param topologicalOrder
     *            : topological ordering of the graph
     * @param adj
     *            : adjacency matrix of the graph
     */
    public static void findMaxLength(LinkedList topologicalOrder,
	    LinkedList[] adj) {

	// to maintain (longest) distance to each node
	int[] length = new int[adj.length];
	int max = 0; // Max distance up till now

	Node n = topologicalOrder.end;
	while (n != null) { // Iterate through each vertex
	    Node x = adj[n.value].start;
	    while (x != null) { // Consider each edge of that vertex
		// If taking that vertex causes the distance to increase
		if (length[x.value] < length[n.value] + 1) {
		    length[x.value] = length[n.value] + 1; // modify distance
		}
		if (length[x.value] > max) { // if new max found
		    max = length[x.value]; // update max
		}
		x = x.next; // move to next edge
	    }
	    n = n.prev; // move to next vertex
	}
	System.out.println(max); // print result
    }

    /**
     * 
     * findTopologicalOrdering: implements depth first search to compute
     * topological ordering of nodes
     * 
     * @param adj
     *            : Adjacency list
     * @param left
     *            : Nodes that haven't been visited yet. (Ones to consider)
     */
    public static LinkedList findTopologicalOrdering(LinkedList[] adj,
	    LinkedList edges) {
	LinkedList toReturn = new LinkedList();
	int begin = edges.findMin(); // finding smallest value
	Stack stk = new Stack(); // Making a stack to process nodes
	LinkedList seen = new LinkedList(); // Making a list of visited
					    // nodes
	if (edges.start == null) { // if there are no nodes left unvisited
				   // return;
	    return null;
	}
	seen.add(begin); // add first node to seen list
	edges.remove(begin); // remove it from unvisited nodes list
	stk.push(begin); // add it to stack
	while (!stk.isEmpty()) { // while stack has nodes

	    int parent = stk.adj.end.value;
	    Node n = adj[parent].start; // find first child
	    int childCount = 0;
	    while (n != null) {
		//if child not already visited
		if (seen.find(n.value) == null && edges.find(n.value) != null) { 
		    stk.push(n.value); // push into stack
		    seen.add(edges.remove(n.value)); // add to seen and remove
						     // from unseen
		    childCount++;
		}
		n = n.next; // move to next child
	    }
	    
	  //once all its children are dealt with pop it from stack
	    if (childCount == 0) {	
		toReturn.add(stk.pop());
	    }
	}
	// try to find topological ordering of the remaining nodes and combine
	// with previous ordering
	return toReturn.combine(findTopologicalOrdering(adj, edges));
    }

}
