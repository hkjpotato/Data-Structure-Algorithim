/**
 * Created by kaijiehuang on 15-4-15.
 */
import org.junit.*;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JGraphTest {
    @Before
    public void setup() {
    }

    @Test
    public void dijkstraNotGreedy() {
        Graph greedy = new Graph(true, "4 4 0 1 1 0 2 3 1 3 10 2 3 2");
        Map<Vertex, Integer> map = new java.util.HashMap<>();
        map.put(new Vertex(0), 0);
        map.put(new Vertex(1), 1);
        map.put(new Vertex(2), 3);
        map.put(new Vertex(3), 5);
        assertEquals(map, GraphAlgorithms.dijkstraShortestPath(greedy,
                new Vertex(0)));
    }

    @Test
    public void dijkstraUnreachable() {
        Graph isolated = new Graph(false, "3 4 0 1 1 1 2 2 2 0 0");
        Map<Vertex, Integer> map = new java.util.HashMap<>();
        map.put(new Vertex(0), 0);
        map.put(new Vertex(1), 1);
        map.put(new Vertex(2), 0);
        map.put(new Vertex(3), Graph.INF);
        assertEquals(map, GraphAlgorithms.dijkstraShortestPath(isolated,
                new Vertex(0)));
    }

    @Test
    public void cyclicFloyd() {
        Graph cyclic = new Graph(true, "3 3 0 1 1 1 2 1 2 0 -4");
        assertEquals(null, GraphAlgorithms.floydWarshall(cyclic));
    }

    @Test
    public void topoEdgeless() {
        Graph edgeless = new Graph(true, "8 10 0 1 1 1 2 2 2 3 3 3 4 4 4 5 5 " +
                "6 7 7 7 8 8 8 9 9");
        List<Vertex> ans = new java.util.ArrayList<>();
        for (int i = 6; i < 10; i++) {
            ans.add(new Vertex(i));
        }
        for (int i = 0; i <= 5; i++) {
            ans.add(new Vertex(i));
        }
        assertEquals(ans, GraphAlgorithms.topologicalSort(edgeless));
    }
}