import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distance;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private PriorityQueue<Vertex<V>> minHeap;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> source) {
        distance = new HashMap<>();
        edgeTo = new HashMap<>();
        minHeap = new PriorityQueue<>(Comparator.comparingDouble(vertex -> distance.getOrDefault(vertex, Double.POSITIVE_INFINITY)));
        dijkstra(graph, source);
    }

    private void dijkstra(WeightedGraph<V> graph, Vertex<V> source) {
        distance.put(source, 0.0);
        minHeap.offer(source);

        while (!minHeap.isEmpty()) {
            Vertex<V> currentVertex = minHeap.poll();
            double currentDist = distance.getOrDefault(currentVertex, Double.POSITIVE_INFINITY);

            for (WeightedGraph.Edge<V> edge : graph.getAdjacentEdges(currentVertex)) {
                Vertex<V> adjacentVertex = edge.getDest();
                double newDist = currentDist + edge.getWeight();

                if (newDist < distance.getOrDefault(adjacentVertex, Double.POSITIVE_INFINITY)) {
                    distance.put(adjacentVertex, newDist);
                    edgeTo.put(adjacentVertex, currentVertex);
                    minHeap.offer(adjacentVertex);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<V> destination) {
        return distance.containsKey(destination);
    }

    @Override
    public Iterable<Vertex<V>> pathTo(Vertex<V> destination) {
        if (!hasPathTo(destination)) return null;

        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> vertex = destination; vertex != null; vertex = edgeTo.get(vertex)) {
            path.addFirst(vertex);
        }
        return path;
    }

    public double getShortestDistanceTo(Vertex<V> destination) {
        return distance.getOrDefault(destination, Double.POSITIVE_INFINITY);
    }
}