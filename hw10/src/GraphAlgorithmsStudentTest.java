import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class GraphAlgorithmsStudentTest {
    private Graph directedSize4;
    private Graph undirectedSize6;
    private Graph directedSize5;
    private Graph directedAcyclicSize6;

    @Before
    public void setUp() {
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

    }

    @Test
    public void testDijkstraShortestPathUndirected() {
        Map<Vertex, Integer> map = GraphAlgorithms
                .dijkstraShortestPath(undirectedSize6, new Vertex(0));

        assertEquals(new Integer(0), map.get(new Vertex(0)));
        assertEquals(new Integer(12), map.get(new Vertex(1)));
        assertEquals(new Integer(6), map.get(new Vertex(2)));
        assertEquals(new Integer(10), map.get(new Vertex(3)));
        assertEquals(new Integer(15), map.get(new Vertex(4)));
        assertEquals(new Integer(15), map.get(new Vertex(5)));
    }

    @Test
    public void testDijkstraShortestPathDirected() {
        Map<Vertex, Integer> map = GraphAlgorithms
                .dijkstraShortestPath(directedSize5, new Vertex(0));

        assertEquals(new Integer(0), map.get(new Vertex(0)));
        assertEquals(new Integer(3), map.get(new Vertex(1)));
        assertEquals(new Integer(2), map.get(new Vertex(2)));
        assertEquals(new Integer(5), map.get(new Vertex(3)));
        assertEquals(new Integer(4), map.get(new Vertex(4)));
    }

    @Test
    public void testFloydWarshall() {
        int[][] studentDistances = GraphAlgorithms.floydWarshall(directedSize4);

        int[][] actualDistances = new int[4][4];
        actualDistances[0][0] = 0;
        actualDistances[0][1] = -2;
        actualDistances[0][2] = 0;
        actualDistances[0][3] = 6;

        actualDistances[1][0] = 4;
        actualDistances[1][1] = 0;
        actualDistances[1][2] = 2;
        actualDistances[1][3] = 8;

        actualDistances[2][0] = 11;
        actualDistances[2][1] = 7;
        actualDistances[2][2] = 0;
        actualDistances[2][3] = 12;

        actualDistances[3][0] = 13;
        actualDistances[3][1] = 9;
        actualDistances[3][2] = 11;
        actualDistances[3][3] = 0;

        assertArrayEquals(actualDistances, studentDistances);
    }

    @Test
    public void testTopologicalSort() {
        List<Vertex> studentTopoSort =
                GraphAlgorithms.topologicalSort(directedAcyclicSize6);

        List<Vertex> correctTopoSort = new ArrayList<>();
        correctTopoSort.add(new Vertex(0));
        correctTopoSort.add(new Vertex(3));
        correctTopoSort.add(new Vertex(5));
        correctTopoSort.add(new Vertex(4));
        correctTopoSort.add(new Vertex(1));
        correctTopoSort.add(new Vertex(2));

        assertEquals(correctTopoSort, studentTopoSort);
    }
}
