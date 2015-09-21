/**
 * Created by kaijiehuang on 15-4-15.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class grazyTest {
    private Graph randomDirected;
    private Graph randomUndirected;
    private Graph randomDirectedDisconnected;
    private Graph randomUndirectedDisconnected;
    private Graph directedSize4;
    private Graph undirectedSize6;
    private Graph directedSize5;
    private Graph directedAcyclicSize6;
    private List<Integer> arrayConnected;
    private List<Integer> arrayDisconnected;
    private List<Graph> student;
    private static final int disconnectedComponent = 15;
    private static final int minVertex = 600;
    private static final int maxVertex = 1000;


    @Before
    public void setUp() {

        String randomConnectedInput = randomConnected(minVertex, maxVertex);

        String randomDisconnectedInput = randomDisconnected(minVertex, maxVertex, disconnectedComponent);

        randomDirected = new Graph(true, randomConnectedInput);
        randomUndirected = new Graph(false, randomConnectedInput);
        randomDirectedDisconnected = new Graph(true, randomDisconnectedInput);
        randomUndirectedDisconnected = new Graph(false, randomDisconnectedInput);

        directedSize4 = new Graph(true, "7 4" + " 0 1 -2" + " 1 0 4" + " 1 2 2"
                + " 2 1 7" + " 2 3 12" + " 3 1 9" + " 1 3 8");

        undirectedSize6 = new Graph(false, "8 6" + " 0 1 13" + " 0 2 6"
                + " 2 3 4" + " 1 3 2" + " 1 4 3"
                + " 2 5 9" + " 3 5 9" + " 4 5 1");

        directedSize5 = new Graph(true, "9 5" + " 0 1 4" + " 0 2 2" + " 2 1 1"
                + " 1 3 3" + " 1 4 1" + " 4 3 7"
                + " 3 0 1" + " 3 2 1" + " 2 3 3");

        directedAcyclicSize6 = new Graph(true, "8 6" + " 0 1 1" + " 0 2 4"
                + " 0 3 9" + " 1 2 2" + " 3 2 4"
                + " 3 4 8" + " 3 5 4" + " 5 4 3");
        student = new ArrayList<>();
        student.add(directedSize4);
        student.add(undirectedSize6);
        student.add(directedSize5);
        student.add(directedAcyclicSize6);
    }

    @Test
    public void studentTest() {
        for (Graph g : student) {
            int loopSource = g.getVertices().size();
            for (int i = 0; i < 0; i++) {
                Map<Vertex, Integer> myMap = DijkstraUsingMatrix
                        .dijkstra(g.getAdjacencyMatrix(), loopSource);
                Map<Vertex, Integer> map = GraphAlgorithms
                        .dijkstraShortestPath(g, new Vertex(loopSource));

                for (Map.Entry<Vertex, Integer> a : myMap.entrySet()) {

                    for (Map.Entry<Vertex, Integer> b : map.entrySet()) {
                        if (a.getKey().equals(b.getKey())) {

                            assertEquals(a.getValue(), b.getValue());
                        }
                    }
                }
            }

        }
    }


    @Test
    public void testDijkstraUndirectedCrossCheckDisconnected() {
        Random rn = new Random(System.currentTimeMillis());
        int source = rn.nextInt(arrayDisconnected.get(1) - 1 - 0 + 1) + 0;

        System.out.println("=============================================");
        System.out.println("Testing Dijkstra Undirected (Disconnected)");
        System.out.println("Number of Vertex: " + arrayDisconnected.get(1));
        System.out.println("Number of Edge: " + arrayDisconnected.get(0));
        System.out.println("Source Vertex ID: " + source);
        System.out.println("Disconnected Components: " + disconnectedComponent);
        System.out.println("=============================================");

        Map<Vertex, Integer> myMap = DijkstraUsingMatrix
                .dijkstra(randomUndirectedDisconnected.getAdjacencyMatrix(), source);

        Map<Vertex, Integer> map = GraphAlgorithms
                .dijkstraShortestPath(randomUndirectedDisconnected, new Vertex(source));
        int comparisonMade = 0;
        for (Map.Entry<Vertex, Integer> a : myMap.entrySet()) {

            for (Map.Entry<Vertex, Integer> b : map.entrySet()) {
                if (a.getKey().equals(b.getKey())) {

                    comparisonMade++;

                    assertEquals(a.getValue(), b.getValue());
                }

            }
        }
        assertEquals(comparisonMade, (int) arrayDisconnected.get(1));

    }


    @Test
    public void testDijkstraDirectedCrossCheckDisconnected() {
        Random rn = new Random(System.currentTimeMillis());
        int source = rn.nextInt(arrayDisconnected.get(1) - 1 - 0 + 1) + 0;

        System.out.println("=============================================");
        System.out.println("Testing Dijkstra Directed (Disconnected)");
        System.out.println("Number of Vertex: " + arrayDisconnected.get(1));
        System.out.println("Number of Edge: " + arrayDisconnected.get(0));
        System.out.println("Source Vertex ID: " + source);
        System.out.println("Disconnected Components: " + disconnectedComponent);
        System.out.println("=============================================");

        Map<Vertex, Integer> myMap = DijkstraUsingMatrix
                .dijkstra(randomDirectedDisconnected.getAdjacencyMatrix(), source);

        Map<Vertex, Integer> map = GraphAlgorithms
                .dijkstraShortestPath(randomDirectedDisconnected, new Vertex(source));

        int comparisonMade = 0;
        for (Map.Entry<Vertex, Integer> a : myMap.entrySet()) {
            for (Map.Entry<Vertex, Integer> b : map.entrySet()) {
                if (a.getKey().equals(b.getKey())) {
                    comparisonMade++;
                    assertEquals(a.getValue(), b.getValue());
                }

            }
        }
        assertEquals(comparisonMade, (int) arrayDisconnected.get(1));
    }

    @Test
    public void testDijkstraDirectedCrossCheck() {
        Random rn = new Random(System.currentTimeMillis());
        int source = rn.nextInt(arrayConnected.get(1) - 1 - 0 + 1) + 0;

        System.out.println("=============================================");
        System.out.println("Testing Dijkstra Directed (Connected)");
        System.out.println("Number of Vertex: " + arrayConnected.get(1));
        System.out.println("Number of Edge: " + arrayConnected.get(0));
        System.out.println("Source Vertex ID: " + source);
        System.out.println("=============================================");

        Map<Vertex, Integer> myMap = DijkstraUsingMatrix
                .dijkstra(randomDirected.getAdjacencyMatrix(), source);

        Map<Vertex, Integer> map = GraphAlgorithms
                .dijkstraShortestPath(randomDirected, new Vertex(source));

        int comparisonMade = 0;
        for (Map.Entry<Vertex, Integer> a : myMap.entrySet()) {
            for (Map.Entry<Vertex, Integer> b : map.entrySet()) {
                if (a.getKey().equals(b.getKey())) {
                    comparisonMade++;
                    assertEquals(a.getValue(), b.getValue());
                }

            }
        }
        assertEquals(comparisonMade, (int) arrayConnected.get(1));

    }

    @Test
    public void testDijkstraUndirectedCrossCheck() {

        Random rn = new Random(System.currentTimeMillis());
        int source = rn.nextInt(arrayConnected.get(1) - 1 - 0 + 1) + 0;

        System.out.println("=============================================");
        System.out.println("Testing Dijkstra Undirected (Connected)");
        System.out.println("Number of Vertex: " + arrayConnected.get(1));
        System.out.println("Number of Edge: " + arrayConnected.get(0));
        System.out.println("Source Vertex ID: " + source);
        System.out.println("=============================================");

        Map<Vertex, Integer> myMap = DijkstraUsingMatrix
                .dijkstra(randomUndirected.getAdjacencyMatrix(), source);

        Map<Vertex, Integer> map = GraphAlgorithms
                .dijkstraShortestPath(randomUndirected, new Vertex(source));

        int comparisonMade = 0;
        for (Map.Entry<Vertex, Integer> a : myMap.entrySet()) {
            for (Map.Entry<Vertex, Integer> b : map.entrySet()) {
                if (a.getKey().equals(b.getKey())) {
                    comparisonMade++;
                    assertEquals(a.getValue(), b.getValue());
                }

            }
        }
        assertEquals(comparisonMade, (int) arrayConnected.get(1));
    }

    private String randomConnected(int min, int max) {
        arrayConnected = new ArrayList<>();
        String input = "";
        Random rn = new Random(System.currentTimeMillis());
        int vertex = rn.nextInt(max - min + 1) + min;


        arrayConnected.add((int) Math.pow(vertex, 2));
        arrayConnected.add(vertex);
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                arrayConnected.add(i);
                arrayConnected.add(j);
                arrayConnected.add(rn.nextInt(9998 - 1 + 1) + 1);
            }
        }

        input = arrayConnected.toString();
        input = input.replace("[", "");
        input = input.replace("]", "");
        input = input.replace(",", "");
        return input;

    }


    private String randomDisconnected(int min, int max, int disconnectedNo) {
        arrayDisconnected = new ArrayList<>();
        Random rn = new Random(System.currentTimeMillis());
        int vertex = rn.nextInt(max - min + 1) + min;
        int oneComponent = (int) Math.ceil((float) vertex / (float) disconnectedNo);
        int temp = vertex;
        String input = "";
        int count = 0;
        int edge = 0;
        arrayDisconnected.add(vertex);
        while (temp > 0) {
            for (int i = count; i < count + oneComponent; i++) {
                for (int j = count; j < count + oneComponent; j++) {
                    arrayDisconnected.add(i);
                    arrayDisconnected.add(j);
                    arrayDisconnected.add(rn.nextInt(9998 - 1 + 1) + 1);
                    edge++;
                }
            }
            count = count + oneComponent;
            temp = temp - oneComponent;
            oneComponent = (int) Math.ceil((float) temp / (float) disconnectedNo);

        }

        arrayDisconnected.add(0, edge);

        input = arrayDisconnected.toString();
        input = input.replace("[", "");
        input = input.replace("]", "");
        input = input.replace(",", "");
        return input;

    }

    public static class DijkstraUsingMatrix {

        private static Map<Vertex, Integer> ret = new HashMap<>();

        public static Map<Vertex, Integer> dijkstra(int[][] graph, int src) {
            int[] dist = new int[graph.length];

            boolean[] sptSet = new boolean[graph.length];

            for (int i = 0; i < graph.length; i++) {
                dist[i] = Graph.INF;
                sptSet[i] = false;
            }

            dist[src] = 0;

            for (int count = 0; count < graph.length - 1; count++) {

                int u = minDistance(dist, sptSet);
                sptSet[u] = true;
                for (int v = 0; v < graph.length; v++) {
                    if (!sptSet[v] && graph[u][v] != Graph.INF && dist[u] != 9999
                            && dist[u] + graph[u][v] < dist[v])
                        dist[v] = dist[u] + graph[u][v];
                }


            }

            //printSolution(dist);
            for (int i = 0; i < dist.length; i++) {
                ret.put(new Vertex(i), dist[i]);
            }
            return ret;
        }

        public static int minDistance(int dist[], boolean sptSet[]) {
            int min = 9999;
            int min_index = 0;

            for (int v = 0; v < dist.length; v++) {
                if (sptSet[v] == false && dist[v] <= min) {
                    min = dist[v];
                    min_index = v;
                }
            }

            return min_index;
        }

        public static void printSolution(int dist[]) {
            System.out.println("Vertex   Distance from Source\n");
            for (int i = 0; i < dist.length; i++) {
                System.out.println("i:" + i + "    " + dist[i]);
            }
        }
    }
}