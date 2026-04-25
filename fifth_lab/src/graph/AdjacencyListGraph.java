package graph;

import java.util.LinkedList;

public class AdjacencyListGraph implements IGraph {
    private LinkedList<Integer>[] adjacency;
    private int vertexCount;

    @SuppressWarnings("unchecked")
    public AdjacencyListGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacency = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++)
            adjacency[i] = new LinkedList<>();
    }

    public void connect(int vertex1, int vertex2) {
        if (!adjacency[vertex1].contains(vertex2))
            adjacency[vertex1].add(vertex2);
        if (!adjacency[vertex2].contains(vertex1))
            adjacency[vertex2].add(vertex1);
    }

    public void disconnect(int vertex1, int vertex2) {
        adjacency[vertex1].remove(Integer.valueOf(vertex2));
        adjacency[vertex2].remove(Integer.valueOf(vertex1));
    }

    public boolean isConnected(int vertex1, int vertex2) {
        return adjacency[vertex1].contains(vertex2);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список смежности:\n");
        for (int i = 0; i < vertexCount; i++) {
            sb.append(i).append(" → ").append(adjacency[i]).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IGraph)) return false;
        IGraph other = (IGraph) obj;
        if (this.vertexCount != other.getVertexCount()) return false;
        for (int i = 0; i < vertexCount; i++)
            for (int j = 0; j < vertexCount; j++)
                if (this.isConnected(i, j) != other.isConnected(i, j))
                    return false;
        return true;
    }
}
