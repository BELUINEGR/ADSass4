public interface Search<V> {
    boolean hasPathTo(Vertex<V> destination);
    Iterable<Vertex<V>> pathTo(Vertex<V> destination);
}