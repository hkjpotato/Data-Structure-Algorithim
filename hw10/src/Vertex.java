public class Vertex implements Comparable<Vertex> {

    private int id;

    /**
     * A simple vertex class
     *
     * @param id the int identifier of this vertex
     */
    public Vertex(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Vertex) {
            return id == ((Vertex) o).id;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Vertex v2) {
        return (id < v2.getId() ? -1 : id > v2.getId() ? 1 : 0);
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Returns the ID of this vertex.
     *
     * @return the int id of this vertex
     */
    public int getId() {
        return id;
    }

}
