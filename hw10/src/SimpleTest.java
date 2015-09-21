/**
 * Created by kaijiehuang on 15-4-17.
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class SimpleTest {

    @Before
    public void setUp() throws Exception {

    }
    /* This tests if it throws an IllegalArgumentException
     * When Graph is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullDG() {
        //fail("Not yet implemented");
        Graph graph = null;
        Vertex start = new Vertex(1);
        GraphAlgorithms.dijkstraShortestPath(graph, start);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDV() {
        Graph graph = new Graph(false, "1 2 0 1 5");
        Vertex start = null;
        GraphAlgorithms.dijkstraShortestPath(graph, start);
    }

    /* This test looks at an undirected map and checks a basic working of Djikstras'
     * Algorithm. In Addition there exists one lone Node of value 5
     *
     */
    @Test
    public void testDjikstrasMap() {
        Graph graph = new Graph(false, "7 6" + " 0 1 1" + " 0 2 5" + " 1 2 3" + " 1 3 1"
                + " 3 2 2" + " 3 4 3" + " 4 2 2");
        Vertex start = new Vertex(0);
        Map<Vertex, Integer> finalMap = new HashMap<>();
        finalMap.put(new Vertex(0), 0);
        finalMap.put(new Vertex(2), 4);
        finalMap.put(new Vertex(1), 1);
        finalMap.put(new Vertex(3), 2);
        finalMap.put(new Vertex(4), 5);
        finalMap.put(new Vertex(5), Graph.INF);
        assertEquals(finalMap, GraphAlgorithms.dijkstraShortestPath(graph, start));
    }

	/* Tests Basic Operation of a Topological Sort
	 *
	 */

    @Test
    public void testTopSort() {
        Graph graph = new Graph(true, "9 8" + " 0 1 1" + " 0 2 1" + " 1 2 1" + " 1 3 1" + " 1 6 1"
                + " 2 4 1" + " 4 7 1" + " 6 7 1" + " 5 6 1");
        ArrayList<Vertex> finalList = new ArrayList<>();
        finalList.add(new Vertex(5));
        finalList.add(new Vertex(0));
        finalList.add(new Vertex(1));
        finalList.add(new Vertex(6));
        finalList.add(new Vertex(3));
        finalList.add(new Vertex(2));
        finalList.add(new Vertex(4));
        finalList.add(new Vertex(7));
        try {
            assertEquals(finalList, GraphAlgorithms.topologicalSort(graph));
        } catch (StackOverflowError s) {
            assertFalse("StackOverflowError Detected!!", true);
        }
    }

	/*
	 * This Topological Sort tests whether you can take into consideration Nodes that
	 * have no adjacency list and are not included in any other adjacency lists as well
	 */

    @Test
    public void testTopSort2() {
        Graph graph = new Graph(true, "7 8" + " 3 3 0" + " 0 4 1" + " 4 7 1" + " 1 2 1" + " 2 6 1" + " 1 6 1" + " 1 5 1"
                + " 6 7 1");
        ArrayList<Vertex> finalList = new ArrayList<>();
        finalList.add(new Vertex(3));
        finalList.add(new Vertex(1));
        finalList.add(new Vertex(5));
        finalList.add(new Vertex(2));
        finalList.add(new Vertex(6));
        finalList.add(new Vertex(0));
        finalList.add(new Vertex(4));
        finalList.add(new Vertex(7));
        try {
            assertEquals(finalList, GraphAlgorithms.topologicalSort(graph));
        } catch (StackOverflowError s) {
            assertFalse("StackOverflowError Detected!!", true);
        }
    }


}
