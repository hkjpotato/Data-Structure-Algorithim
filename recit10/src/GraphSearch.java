import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Graph Search
 * Recitation 11
 * 
 * Also, you do not have to run CheckStyle on this assignment.
 * But make it look pretty.  Pretty please. With a cherry on top.
 * 
 * If you don't know what to return for something, look at the JUnits.
 * They should tell you what they expect if null is passed in, etc.
 * If there's not a test case for it, then don't worry about it! :)
 * 
 * @author KJ
 */

//  ****   Cherry  ******

public class GraphSearch {

	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using General Graph Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * The structure(struct) passed in is an empty structure may behave as a Stack or Queue and the
	 * correspondingly search function should execute DFS(Stack) or BFS(Queue) on the graph.
	 *
	 * We've written the stack and queue for you.  Your mission, should you choose to accept it (and you should),
	 * is to finish the graph search algorithm.
	 * 
	 * @param start
	 * @param struct
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean generalGraphSearch(T start, Structure<T> struct, Map<T, List<T>> adjList, T goal) {
		// TODO Implement General Graph Search here

		if (adjList == null) {
			throw new IllegalArgumentException("The input map is null");
		}

		//check if start is in the in the map
		if (!adjList.containsKey(start)) {
			return false;
		}

		//use HashSet to store the visited value
		//thus the big O for checking if visited is 1 (totally [E])
		HashSet<T> visited = new HashSet<>();

		//first add the start to the struct
		struct.add(start);

		//loop the struct (candidate for traverse) until is empty
		//or found
		while (!struct.isEmpty()) {
			//check the next candidate
			T visitedNode = struct.remove();

			//if it is target, return true
			if (visitedNode.equals(goal)) {
				return true;
			}

			//else, mark it as "visited"
			visited.add(visitedNode);

			//add its adjacent node (new one) to the struct
			for (T element : adjList.get(visitedNode)) {
				if (!visited.contains(element)) {
					struct.add(element);
				}
			}
		}


		return false;
	}
	
	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Bredth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return true if path exists, false otherwise
	 */
	public static <T> boolean breadthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		// TODO Implement Breadth First Search here
		// Hint: This can be accomplished in one line.

		return generalGraphSearch(start, new StructureQueue<T>(), adjList, goal);
	}
	
	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Depth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return true if path exists, false otherwise
	 */
	public static <T> boolean depthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		// TODO Implement Depth First Search here
		// Hint: This can be accomplished in one line.
		return generalGraphSearch(start, new StructureStack<T>(), adjList, goal);
	}
	
}
