import java.util.Scanner;

/**
 * 
 * hw5_problem2_shaikh_dand.java
 * @author Trushank
 * @author Ashpak
 * Date Jan 24, 2013
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
 * @author Ashpak class Stack Provides the functions of a Stack data structure
 */
class Stack {
    AdjacencyList adj;

    /**
     * constructor Uses the linked list (AdjacencyList) and emulates behaviour
     * of stack
     */
    public Stack() {
	// Making an Adjacencylist as it is a linked list and we can add stack
	// functions over it maintaining efficiency and reducing code
	adj = new AdjacencyList();

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

/**
 * @author Trushank
 * @author Ashpak class SAdjacencyList Provides the functions of a linked list
 *         data structure to efficiently store and access the adjacency
 */
class AdjacencyList {
    Node start; // Start of list
    Node end; // End of list

    /**
     * Constructor
     * 
     * @param n
     *            : First node
     */
    public AdjacencyList(Node n) {
	start = n;
	end = n;
    }

    /**
     * Empty Constructor
     */
    public AdjacencyList() {
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
	Node n = find(val); // finding the node with the value to be removed

	// if it is the only node in the list
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
}

/**
 * @author Trushank
 * @author Ashpak class hw5_problem2_shaikh_dand Main class. Takes input and
 *         computes the number of connected nodes.
 */
public class hw5_problem2_shaikh_dand {
    static int numberOfConnectedComponents = 0; // final answer

    /**
     * 
     * findMin: Find the smallest node in the list
     * 
     * @param adj
     *            : List
     * @return Value of the smallest node
     */
    public static int findMin(AdjacencyList adj) {

	if (adj.start != null) { // check if list is empty
	    int min = adj.start.value; // assign first value to be min
	    Node n = adj.start.next; // Start checking from the next node
	    while (n != null) {
		if (n.value < min) { // if current nodes value is less than
				     // current min
		    min = n.value; // assign current nodes value to min
		}
		n = n.next; // move to next node
	    }

	    return min; // return min value
	}
	return 0; // if list empty return 0
    }

    /**
     * 
     * main: takes input and computes number of connected components
     * 
     * @param args
     */
    public static void main(String args[]) {
	Scanner src = new Scanner(System.in);
	int vertices = src.nextInt(); // Number of vertices
	int edges = src.nextInt(); // Number of edges
	AdjacencyList adj[] = new AdjacencyList[vertices]; // Creating an array
							   // of AdjacencyList
							   // based on the
							   // number of vertices

	for (int i = 0; i < vertices; i++) {
	    adj[i] = new AdjacencyList(); // initializing the array of
					  // AdjacencyList
	}
	for (int i = edges; i > 0; i--) {
	    int from = src.nextInt() - 1; // inputting the edges
	    int to = src.nextInt();
	    adj[from].add(to); // adding each entry to appropriate adjacency
			       // list

	}
	src.close(); // closing input

	AdjacencyList left = new AdjacencyList(); // List of nodes yet to be
						  // visited
	for (int i = 1; i <= adj.length; i++) {
	    left.add(i); // Adding all as none visited yet
	}

	DFS(adj, left); // Calling DFS with adjacency list and nodes that
			// haven't been visited yet
	System.out.println(numberOfConnectedComponents); // Outputting answer

    }

    /**
     * 
     * DFS: implements depth first search to find all nodes reachable from a
     * node
     * 
     * @param adj
     *            : Adjacency list
     * @param left
     *            : Nodes that haven't been visited yet. (Ones to consider)
     */
    public static void DFS(AdjacencyList[] adj, AdjacencyList edges) {

	int begin = findMin(edges); // finding smallest value from the current
				    // nodes

	Stack stk = new Stack(); // Making a stack to proccess nodes
	AdjacencyList seen = new AdjacencyList(); // Making a list of visited
						  // nodes

	Node x = edges.start; // Assigning x to start
	if (x == null) { // if there are no nodes left unvisited return;
	    return;
	}
	numberOfConnectedComponents++; // everytime DFS is called it is run on a
				       // different connected component
	seen.add(begin); // add first node to seen list
	edges.remove(begin); // remove it from unvisited nodes list
	stk.push(begin); // add it to stack
	while (!stk.isEmpty()) { // while stack has nodes
	    int parent = stk.pop(); // pop tos
	    Node n = adj[parent - 1].start; // find first child
	    while (n != null) {
		if (seen.find(n.value) == null) { // if child no already visited
		    stk.push(n.value); // push into stack
		    seen.add(edges.remove(n.value)); // add to seen and remove
						     // from unseen
		}
		n = n.next; // move to next child
	    }

	}
	// System.out.println("Left: "+edges+"\nSeen: "+seen);
	DFS(adj, edges); // recurse DFS on the list of unseen nodes.
    }
}
