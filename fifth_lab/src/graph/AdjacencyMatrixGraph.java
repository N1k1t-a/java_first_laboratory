package graph;

public class AdjacencyMatrixGraph implements IGraph {
    private int[][] matrix;
    private int vertexCount;

    public AdjacencyMatrixGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.matrix = new int[vertexCount][vertexCount];
    }

    public void connect(int vertex1, int vertex2) {
        matrix[vertex1][vertex2] = 1;
        matrix[vertex2][vertex1] = 1;
    }

    public void disconnect(int vertex1, int vertex2) {
        matrix[vertex1][vertex2] = 0;
        matrix[vertex2][vertex1] = 0;
    }

    public boolean isConnected(int vertex1, int vertex2) {
        return matrix[vertex1][vertex2] == 1;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Матрица смежности:\n  ");
        for (int i = 0; i < vertexCount; i++)
            sb.append(i).append(" ");
        sb.append("\n");
        for (int i = 0; i < vertexCount; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < vertexCount; j++)
                sb.append(matrix[i][j]).append(" ");
            sb.append("\n");
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
