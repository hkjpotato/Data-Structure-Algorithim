import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * A class representing a graph, can be built from an edge list.
 *
 * @author CS-1332 TAs
 * @version 1.0
 */
public class Graph {

    public static final int INF = 9999;
    //the value representing no connection between vertices in an
    //adjacency matrix

    private Set<Vertex> vertices = new HashSet<>();
    private Map<Vertex, Map<Vertex, Integer>> adjacencies = new HashMap<>();
    private int[][] adjacencyMatrix;

    /**
     * A class representing a graph, can be built from an edge list.
     *
     * The first int is the number of edges. The second is the number of
     * vertices. Following this are triples of integers for each edge
     * representing vertex u, vertex v, and the weight.
     *
     * Each vertex must be named a number between 0 and the number of
     * vertices - 1.
     *
     * For example, a triangle graph with all weight being 1 is as follows:
     * 3 3 0 1 1 0 2 1 1 2 1
     *
     * @param directed determines whether or not the graph is directed
     * @param input the graph to make as per the format above
     */
    public Graph(boolean directed, String input) {
        Scanner scan = new Scanner(input);

        int numEdges = scan.nextInt();
        int numVertices = scan.nextInt();

        adjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (i != j) {
                    adjacencyMatrix[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            vertices.add(new Vertex(i));
        }
        //vertex set

        for (int i = 0; i < numEdges; i++) {
            Vertex u = new Vertex(scan.nextInt());
            Vertex v = new Vertex(scan.nextInt());
            int weight = scan.nextInt();


            if (!adjacencies.containsKey(u)) {
                adjacencies.put(u, new TreeMap<Vertex, Integer>());
            }
            adjacencies.get(u).put(v, weight);

            if (!directed) {
                if (!adjacencies.containsKey(v)) {
                    adjacencies.put(v, new TreeMap<Vertex, Integer>());
                }
                adjacencies.get(v).put(u, weight);
            }
            //adjacency list

            adjacencyMatrix[u.getId()][v.getId()] = weight;
            if (!directed) {
                adjacencyMatrix[v.getId()][u.getId()] = weight;
            }
            //adjacency matrix

        }
        scan.close();
    }

    /**
     * Returns the vertices of this graph as a Set of Vertex.
     *
     * @return the vertices of the graph
     */
    public Set<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Returns the adjacencies of a given vertex as a Map of Vertex to Integer.
     *
     * @param u the vertex to ge the adjacencies of
     * @return the adjacencies of a vertex
     */
    public Map<Vertex, Integer> getAdjacencies(Vertex u) {
        return adjacencies.get(u);
    }

    /**
     * Returns the adjacencies of the entire graph as a
     * Map of Vertex to a Map of Vertex to Integer.
     *
     * @return the adjacencies of this graph
     */
    public Map<Vertex, Map<Vertex, Integer>> getAdjacencyList() {
        return adjacencies;
    }

    /**
     * Returns the adjacency matrix of the graph as a 2D array of ints.
     *
     * @return the adjacency matrix of this graph
     */
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
