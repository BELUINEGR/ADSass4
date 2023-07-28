public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");
        Vertex<String> vertexC = new Vertex<>("C");
        Vertex<String> vertexD = new Vertex<>("D");

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        graph.addEdge(vertexA, vertexB, 5.0);
        graph.addEdge(vertexB, vertexC, 3.0);
        graph.addEdge(vertexA, vertexC, 8.0);
        graph.addEdge(vertexB, vertexD, 2.0);
        graph.addEdge(vertexC, vertexD, 4.0);

        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, vertexA);
        System.out.print("BFS traversal starting from A: ");
        for (Vertex<String> vertex : bfs.pathTo(vertexD)) {
            System.out.print(vertex.getData() + " ");
        }
        System.out.println();

        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, vertexA);
        System.out.println("Shortest distances from vertex A using Dijkstra's algorithm:");
        for (Vertex<String> vertex : graph.getVertices()) {
            System.out.println(vertex.getData() + ": " + dijkstra.getShortestDistanceTo(vertex));
        }
    }
}