import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class GraphSearchTest {

	Map<String, List<String>> adjList;
	Map<Integer, List<Integer>> adjListConnected, adjListFullyDisconnected,
    adjListSingly;
	public final int TIMEOUT = 200;

    @Before
    public void setUp() {
        adjList = new HashMap<>();
 
        LinkedList<String> aList = new LinkedList<>();
        aList.add("B");
        aList.add("D");
        aList.add("F");
        adjList.put("A", aList);
 
        LinkedList<String> bList = new LinkedList<>();
        bList.add("C");
        bList.add("D");
        adjList.put("B", bList);
 
        LinkedList<String> cList = new LinkedList<>();
        adjList.put("C", cList);
 
        LinkedList<String> dList = new LinkedList<>();
        dList.add("A");
        adjList.put("D", dList);
 
        LinkedList<String> fList = new LinkedList<>();
        fList.add("G");
        adjList.put("F", fList);
 
        LinkedList<String> gList = new LinkedList<>();
        gList.add("H");
        adjList.put("G", gList);
 
        LinkedList<String> hList = new LinkedList<>();
        adjList.put("H", hList);

        LinkedList<String> zList = new LinkedList<>();
        adjList.put("Z", zList);
 
 
        /**  graph
        * 	   F<-A->B->C
        *      |  |  |
        *      v  |  v
        *   H<-G  <> D
        *   
        *
        *  Z
        */
		
        // Additional General Tests
        setupConnectedGraph();
        setupFullyDisconnectedGraph();
        setupSinglyGraph();
    }
	
    /**
     * http://homepages.ius.edu/rwisman/C455/html/notes/AppendixB4/B4-2.gif
     */
    private void setupConnectedGraph() {
        adjListConnected = new HashMap<>();
        final LinkedList<Integer> one = new LinkedList<>();
        final LinkedList<Integer> two = new LinkedList<>();
        final LinkedList<Integer> three = new LinkedList<>();
        final LinkedList<Integer> four = new LinkedList<>();
        final LinkedList<Integer> five = new LinkedList<>();

        one.add(2);
        one.add(5);

        two.add(1);
        two.add(3);
        two.add(4);
        two.add(5);

        three.add(2);
        three.add(4);

        four.add(2);
        four.add(3);
        four.add(5);

        five.add(1);
        five.add(2);
        five.add(4);

        adjListConnected.put(1, one);
        adjListConnected.put(2, two);
        adjListConnected.put(3, three);
        adjListConnected.put(4, four);
        adjListConnected.put(5, five);
    }

    private void setupFullyDisconnectedGraph() {
        adjListFullyDisconnected = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            adjListFullyDisconnected.put(i, new LinkedList<>());
        }
    }

    /*
     * Graph that looks like 1 -> 2 -> 3 -> ... -> k
     */
    private void setupSinglyGraph() {
        adjListSingly = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            final LinkedList<Integer> value = new LinkedList<>();
            value.add(i + 1);
            adjListSingly.put(i, value);
        }
        adjListSingly.put(adjListSingly.size(), new LinkedList<>());
    }
	
    @Test(timeout = TIMEOUT)
    public void testDepthFirstSearch1() {
        assertTrue(GraphSearch.depthFirstSearch("A", adjList, "H"));
    }

    @Test(timeout = TIMEOUT)
    public void testDepthFirstSearch2() {
    	assertFalse(GraphSearch.depthFirstSearch("A", adjList, "Z"));
    }

    @Test(timeout = TIMEOUT)
    public void testDepthFirstSearch3() {
    	assertFalse(GraphSearch.depthFirstSearch("C", adjList, "H"));
    }

    @Test(timeout = TIMEOUT)
    public void testDepthFirstSearch4() {
    	assertTrue(GraphSearch.depthFirstSearch("D", adjList, "H"));
    }

    @Test(timeout = TIMEOUT)
    public void testBreadthFirstSearch1() {
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList, "H"));
    }

     @Test(timeout = TIMEOUT)
    public void testBreadthFirstSearch2() {
    	assertFalse(GraphSearch.breadthFirstSearch("A", adjList, "Z"));
    }

    @Test(timeout = TIMEOUT)
    public void testBreadthFirstSearch3() {
    	assertFalse(GraphSearch.breadthFirstSearch("C", adjList, "H"));
    }

    @Test(timeout = TIMEOUT)
    public void testBreadthFirstSearch4() {
    	assertTrue(GraphSearch.breadthFirstSearch("D", adjList, "H"));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFSNullMap() {
    	GraphSearch.depthFirstSearch("A", null, "H");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBFSNullMap() {
    	GraphSearch.breadthFirstSearch("A", null, "H");
    }

    @Test(timeout = TIMEOUT)
    public void testDFSBadStart() {
    	assertFalse(GraphSearch.depthFirstSearch("X", adjList, "A"));
    }

    @Test(timeout = TIMEOUT)
    public void testBFSBadStart() {
    	assertFalse(GraphSearch.breadthFirstSearch("X", adjList, "A"));
    }


    @Test(timeout = TIMEOUT)
    public void testBFSBadStartWithZ() {
        assertFalse(GraphSearch.breadthFirstSearch("Z", adjList, "A"));
    }


    @Test(timeout = TIMEOUT)
    public void testBFSBadStartWithY() {
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList, "H"));
    }

    @Test(timeout = TIMEOUT)
    public void testConnectedGraphFullSearch() {

        // Tests every single vertex to every other vertex.
        // Should all assert to true, because it's a connected graph.
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                assertTrue(GraphSearch.breadthFirstSearch(i, adjListConnected,
                        j));
                assertTrue(GraphSearch.depthFirstSearch(i, adjListConnected, j));
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSinglyFullSearch() {

        // Attempts all vertices for "start".
        // "goal" vertices are always right of the "start" vertices.
        for (int i = 0; i < adjListSingly.size(); i++) {
            for (int j = i; j < adjListSingly.size(); j++) {
                assertTrue(GraphSearch.breadthFirstSearch(i, adjListSingly, j));
                assertTrue(GraphSearch.depthFirstSearch(i, adjListSingly, j));
            }
        }

        // Attempts all vertices for "start".
        // "goal" vertices are always left of the "start" vertices.
        for (int i = adjListSingly.size() - 1; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                // Vertex to itself should be return true, and we have tested
                // that in above nested loop, so skip (i != j).
                if (i != j) {
                    assertFalse(GraphSearch.breadthFirstSearch(i,
                            adjListSingly, j));
                    assertFalse(GraphSearch.depthFirstSearch(i, adjListSingly,
                            j));
                }
            }
        }

    }

    @Test(timeout = TIMEOUT)
    public void testFullyDisconnectedGraphFullSearch() {
        final int graphSize = adjListFullyDisconnected.size();

        // Tests every single vertex to every other vertex.
        // Should all assert to false except a vertex to itself, because it's a
        // fully disconnected graph.
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                if (i == j) {
                    assertTrue(GraphSearch.breadthFirstSearch(i,
                            adjListFullyDisconnected, j));
                    assertTrue(GraphSearch.depthFirstSearch(i,
                            adjListFullyDisconnected, j));
                } else {
                    assertFalse(GraphSearch.breadthFirstSearch(i,
                            adjListFullyDisconnected, j));
                    assertFalse(GraphSearch.depthFirstSearch(i,
                            adjListFullyDisconnected, j));
                }
            }
        }
    }
	
    @After
    public void tearDown() {
        adjList = null;
    }

}
