/**
 * Created by kaijiehuang on 15-4-17.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by Brian on 4/16/2015.
 */
public class ToPoSort {
    Graph graph;
    ArrayList<NodeVertex[]> nodeMatrix;

    @Before
    public void setUp(){
        if (graph != null) {
            return;
        }

        nodeMatrix = new ArrayList<>();

        //for assigning incrementing ids//
        int nodeIdCounter = 0;

        NodeVertex[] firstLayer = new NodeVertex[20];
        firstLayer[0] = new NodeVertex(nodeIdCounter++);
        int edgeCount = -1;

        nodeMatrix.add(firstLayer);

        for (int layerCount = 0; layerCount < 20; layerCount++) {

            Randomizer rand = new Randomizer();
            NodeVertex[] newLayer = new NodeVertex[20];

            nodeMatrix.add(newLayer);

            //generate new Layer//
            for (int i = 0; i < newLayer.length; i++) {
                if (rand.shouldPlaceVertex()) {
                    newLayer[i] = new NodeVertex(nodeIdCounter++);

                    ConnectChoice choice = rand.getConnectionChoice();
                    if (choice == ConnectChoice.CONNECT_NEW) {
                        NodeVertex connection = null;
                        int tries = -1;

                        //get random downstream node//
                        while (connection == null && tries++ < 6) {
                            int row = (int) (Math.random() * (nodeMatrix.size() - 1));
                            int col = (int) (Math.random() * (20 - i)) + i;


                            connection = nodeMatrix.get(row)[col];
                        }

                        if (connection != null) {
                            edgeCount++;
                            connection.parents.add(newLayer[i]);
                            newLayer[i].adjacencies.add(connection);
                        }

                    } else if (choice == ConnectChoice.CONNECT_OLD) {
                        NodeVertex connection = null;
                        int tries = -1;

                        //get random upstream node//
                        while (connection == null && tries < 10) {
                            int row = (int) (Math.random() * (nodeMatrix.size() + (i == 0 ? -1 : 0)));
                            int col = (int) (Math.random() * i);

                            connection = nodeMatrix.get(row)[col];
                        }

                        if (connection != null) {
                            edgeCount++;
                            connection.adjacencies.add(newLayer[i]);
                            newLayer[i].parents.add(connection);
                        }
                    }
                }
            }
        }

        //alright, now we've gotta input it into the graph somehow//

        StringBuilder sb = new StringBuilder();
        sb.append(edgeCount)
                .append(" ")
                .append(nodeIdCounter);

        for (NodeVertex[] vertexes : nodeMatrix) {
            for (int i = 0; i < 20; i++) {
                if (vertexes[i] != null) {
                    for (NodeVertex v : vertexes[i].adjacencies) {
                        sb.append(" ")
                                .append(vertexes[i].getId())
                                .append(" ")
                                .append(v.getId())
                                .append(" ")
                                .append(1);
                    }
                }
            }
        }

        graph = new Graph(true, sb.toString());
    }

    @Test(timeout = 5000)
    public void tryIt() {
        List<Vertex> toposort = GraphAlgorithms.topologicalSort(graph);
//        for (Vertex v : toposort) {
//            System.out.print(" ");
//            System.out.print(v.getId());
//        }
    }

    private class NodeVertex extends Vertex {

        private LinkedList<NodeVertex> adjacencies;
        private LinkedList<NodeVertex> parents;


        public NodeVertex(int id) {
            super(id);

            adjacencies = new LinkedList<>();
            parents = new LinkedList<>();
        }

        public LinkedList<NodeVertex> getParents() {
            LinkedList<NodeVertex> parentCopy = new LinkedList<>();
            Collections.copy(parentCopy, parents);
            return parentCopy;
        }

        public LinkedList<NodeVertex> getAdjacencies() {
            LinkedList<NodeVertex> childrenCopy = new LinkedList<>();
            Collections.copy(childrenCopy, adjacencies);
            return childrenCopy;
        }

        public void print() {
            System.out.println("Vertex " + getId() + ":");
            System.out.println("Parents:");
            System.out.println(parents.toString());
            System.out.println("Adjacencies/children (the connections that are in the graph):");
            System.out.println(adjacencies.toString());
        }
    }

    /**
     * a class used to randomize location of the connections, but keep
     * the same number and type of connection constant
     */
    private class Randomizer {
        private ArrayList<ConnectChoice> choices;

        private int numVerteces;
        private int spots;

        public Randomizer() {
            choices = new ArrayList<>(20);
            for (int i = 0; i < 4; i++) {
                choices.add(ConnectChoice.NO_CONNECTION);
            }
            for (int i = 0; i < 8; i++) {
                choices.add(ConnectChoice.CONNECT_NEW);
            }
            for (int i = 0; i < 8; i++) {
                choices.add(ConnectChoice.CONNECT_OLD);
            }

            numVerteces = 16;
            spots = 20;
        }

        /**
         * @return gets random ConnectionChoice without replacement
         */
        public ConnectChoice getConnectionChoice() {
            return choices.remove((int)(Math.random() * choices.size()));
        }

        public boolean shouldPlaceVertex() {
            if (Math.random() > ((double) numVerteces / (double) spots--)) {
                return false;
            } else {
                numVerteces--;
                return true;
            }
        }
    }

    /**
     * enum to determine connection action in randomizing directed graph
     */
    private enum ConnectChoice {
        CONNECT_NEW, CONNECT_OLD, NO_CONNECTION
    }
}