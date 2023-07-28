import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<V>>> map;

    public WeightedGraph() {
        map = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        map.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        Edge<V> edge = new Edge<>(source, dest, weight);
        map.get(source).add(edge);
    }

    public List<Edge<V>> getAdjacentEdges(Vertex<V> vertex) {
        return map.get(vertex);
    }

    public Iterable<Vertex<V>> getVertices() {
        return map.keySet();
    }

    public static class Edge<V> {
        private Vertex<V> source;
        private Vertex<V> dest;
        private Double weight;

        public Edge(Vertex<V> source, Vertex<V> dest, Double weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }

        public Vertex<V> getSource() {
            return source;
        }

        public Vertex<V> getDest() {
            return dest;
        }

        public Double getWeight() {
            return weight;
        }
    }
}