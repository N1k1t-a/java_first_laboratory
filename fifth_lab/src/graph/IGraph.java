package graph;

public interface IGraph {
    void connect(int vertex1, int vertex2);
    void disconnect(int vertex1, int vertex2);
    boolean isConnected(int vertex1, int vertex2);
    int getVertexCount();
}
