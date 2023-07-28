import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Boolean> marked;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Vertex<V> source;

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> source) {
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, Vertex<V> startVertex) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        marked.put(startVertex, true);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            for (WeightedGraph.Edge<V> edge : graph.getAdjacentEdges(currentVertex)) {
                Vertex<V> adjacentVertex = edge.getDest();
                if (!marked.containsKey(adjacentVertex)) {
                    marked.put(adjacentVertex, true);
                    edgeTo.put(adjacentVertex, currentVertex);
                    queue.add(adjacentVertex);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Vertex<V> destination) {
        return marked.containsKey(destination);
    }

    @Override
    public Iterable<Vertex<V>> pathTo(Vertex<V> destination) {
        if (!hasPathTo(destination)) return null;

        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> vertex = destination; vertex != source; vertex = edgeTo.get(vertex)) {
            path.addFirst(vertex);
        }
        path.addFirst(source);
        return path;
    }
}