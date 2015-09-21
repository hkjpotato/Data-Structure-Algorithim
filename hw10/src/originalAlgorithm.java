import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;


public class originalAlgorithm {
    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph.
     * You should use the adjacency list representation of the graph.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be visited. There are no negative edge weights in the
     * graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph or start vertex is null
     * @param graph the graph to search
     * @param start the starting vertex
     * @return map of the shortest distance between start and all other vertices
     */
    public static Map<Vertex, Integer> dijkstraShortestPath(Graph graph,
                                                            Vertex start) {
        if (graph == null) {
            throw new IllegalArgumentException("The input graph is null.");
        }

        if (start == null) {
            throw new IllegalArgumentException("The input start is null.");
        }

        //the distanceMap is to be returned
        Map<Vertex, Integer> distanceMap = new HashMap<>();


        for (Vertex vertex : graph.getVertices()) {
            if (start.equals(vertex)) {
                distanceMap.put(vertex, 0);

            } else {
                distanceMap.put(vertex, Graph.INF);
            }
        }

        //VertexDistancePair records the vertex and its current distance from start
        PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>();

        //need sth to record the vertex being visited
        Set<Vertex> visited = new HashSet<>();

        queue.add(new VertexDistancePair(start, 0));


        while(!queue.isEmpty()) {
            VertexDistancePair currentPair = queue.remove();
            Vertex currentVertex = currentPair.getVertex();
            Integer currentDistance = currentPair.getDistance();
            if (!visited.contains(currentVertex)) {
                visited.add(currentVertex);
                distanceMap.put(currentVertex, currentDistance);
//                if (graph.getAdjacencyList().containsKey(currentVertex)) {
                if (graph.getAdjacencies(currentVertex) != null) {
                    for (Map.Entry<Vertex, Integer> entry : graph.getAdjacencies(currentVertex).entrySet()) {
                        VertexDistancePair newPair =
                                new VertexDistancePair(entry.getKey(), entry.getValue() + currentDistance);
                        queue.add(newPair);
                    }
                }

            }
        }



        return distanceMap;



    }


    /**
     * Run Floyd Warshall on the given graph to find the length of all of the
     * shortest paths between vertices.
     *
     * You will also detect if there are negative cycles in the
     * given graph.
     *
     * You should use the adjacency matrix representation of the graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the graph
     * @return the distances between each vertex. For example, given {@code arr}
     * represents the 2D array, {@code arr[i][j]} represents the distance from
     * vertex i to vertex j. Return {@code null} if there is a negative cycle.
     */
    public static int[][] floydWarshall(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("The input graph is null.");
        }

        int vertexNum = graph.getAdjacencyMatrix().length;
        int[][] retMatrix = new int[vertexNum][vertexNum];

        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                retMatrix[i][j] = graph.getAdjacencyMatrix()[i][j];
            }
        }

        for (int k = 0; k < vertexNum; k++) {
            //use kth vertex as intermediate node
            //update all the node
            for (int i = 0; i < vertexNum; i++) {
                for (int j = 0; j < vertexNum; j++) {

                    int originalPath = retMatrix[i][j];
                    int newPath = retMatrix[i][k] + retMatrix[k][j];

                    if (newPath < originalPath) {
                        retMatrix[i][j] = newPath;

                    }

                    if (i == j && retMatrix[i][j] < 0) {
                        return null;
                    }
                }
            }
        }

        return retMatrix;
    }

    /**
     * A topological sort is a linear ordering of vertices with the restriction
     * that for every edge uv, vertex u comes before v in the ordering.
     *
     * You should use the adjacency list representation of the graph.
     * When considering which vertex to visit next while exploring the graph,
     * choose the next vertex in the adjacency list.
     *
     * You should start your topological sort with the smallest vertex
     * and if you need to select another starting vertex to continue
     * with your sort (like with a disconnected graph),
     * you should choose the next smallest applicable vertex.
     *
     * @throws IllegalArgumentException if the graph is null
     * @param graph a directed acyclic graph
     * @return a topological sort of the graph
     */
    public static List<Vertex> topologicalSort(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("The input graph is null.");
        }

        //the list to be returned
        List<Vertex> sorted = new LinkedList<>();

        Set<Vertex> vertexes = graph.getVertices();

        //a vertex set to store the visited vertex
        Set<Vertex> visited = new HashSet<>();

        for (Vertex vertex : vertexes) {
            topoSortHelper(vertex, graph, visited, sorted);
        }

        return sorted;
    }

    private static void topoSortHelper(Vertex vertex, Graph graph, Set<Vertex> visited, List<Vertex> sorted) {
//        if (graph.getAdjacencyList().containsKey(vertex)) {
//            for (Map.Entry<Vertex, Integer> entry : graph.getAdjacencies(vertex).entrySet()) {
//                //FIXME not sure if is ok to put !visited here
//                if(!visited.contains(entry.getKey())) {
//                    topoSortHelper(entry.getKey(), graph, visited, list);
//                }
//            }
//        }
//
//        if (!visited.contains(vertex)) {
//            visited.add(vertex);
//            list.add(0, vertex);
//        }



        if (!visited.contains(vertex)) {
            if (graph.getAdjacencies(vertex)!= null) {
                for (Map.Entry<Vertex, Integer> entry
                        : graph.getAdjacencies(vertex).entrySet()) {
                    topoSortHelper(entry.getKey(), graph, visited, sorted);
                }
            }
            visited.add(vertex);
            sorted.add(0, vertex);
        }

    }


    /**
     * A class that pairs a vertex and a distance. Hint: might be helpful
     * for Dijkstra's.
     */
    private static class VertexDistancePair implements
            Comparable<VertexDistancePair> {
        private Vertex vertex;
        private int distance;

        /**
         * Creates a vertex distance pair
         * @param vertex the vertex to store in this pair
         * @param distance the distance to store in this pair
         */
        public VertexDistancePair(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * Gets the vertex stored in this pair
         * @return the vertex stored in this pair
         */
        public Vertex getVertex() {
            return vertex;
        }

        /**
         * Sets the vertex to be stored in this pair
         * @param vertex the vertex to be stored in this pair
         */
        public void setVertex(Vertex vertex) {
            this.vertex = vertex;
        }

        /**
         * Gets the distance stored in this pair
         * @return the distance stored in this pair
         */
        public int getDistance() {
            return distance;
        }

        /**
         * Sets the distance to be stored in this pair
         * @param distance the distance to be stored in this pair
         */
        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistancePair v) {
            return (distance < v.getDistance() ? -1 : distance > v.getDistance() ? 1 : 0);
        }
    }
}
