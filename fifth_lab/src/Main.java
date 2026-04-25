import exceptions.MatrixException;
import graph.AdjacencyListGraph;
import graph.AdjacencyMatrixGraph;
import graph.IGraph;
import matrix.IMatrix;
import matrix.SparseMatrix;
import matrix.SquareMatrix;
import matrix.UsualMatrix;

import java.util.Random;

public class Main {

    static void dfs(IGraph graph, int start) {
        boolean[] visited = new boolean[graph.getVertexCount()];
        dfsHelper(graph, start, visited);
    }

    static void dfsHelper(IGraph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.println("  Посещаем вершину: " + vertex);
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.isConnected(vertex, i) && !visited[i])
                dfsHelper(graph, i, visited);
        }
    }

    public static void main(String[] args) {

        // === Тест 1: маленькие матрицы вручную ===
        System.out.println("=== Тест 1: ручное заполнение 3x3 ===");
        UsualMatrix u = new UsualMatrix(3, 3);
        SparseMatrix s = new SparseMatrix(3, 3);
        u.setElement(0, 0, 5);
        u.setElement(1, 2, 3);
        u.setElement(2, 1, 7);
        s.setElement(0, 0, 5);
        s.setElement(1, 2, 3);
        s.setElement(2, 1, 7);
        System.out.println("UsualMatrix:");
        System.out.println(u);
        System.out.println("SparseMatrix:");
        System.out.println(s);
        System.out.println("UsualMatrix.equals(SparseMatrix): " + u.equals(s));
        System.out.println("SparseMatrix.equals(UsualMatrix): " + s.equals(u));

        // === Тест 2: SquareMatrix — единичная матрица ===
        System.out.println("=== Тест 2: SquareMatrix 3x3 (единичная) ===");
        SquareMatrix sq = new SquareMatrix(3);
        System.out.println(sq);

        // === Тест 3: сложение маленьких матриц ===
        System.out.println("=== Тест 3: сложение 3x3 ===");
        UsualMatrix u2 = new UsualMatrix(3, 3);
        SparseMatrix s2 = new SparseMatrix(3, 3);
        u2.setElement(0, 0, 1);
        u2.setElement(0, 2, 2);
        u2.setElement(2, 2, 4);
        s2.setElement(0, 0, 1);
        s2.setElement(0, 2, 2);
        s2.setElement(2, 2, 4);

        IMatrix usualSum = u.sum(u2);
        IMatrix sparseSum = s.sum(s2);
        System.out.println("UsualMatrix + UsualMatrix:");
        System.out.println(usualSum);
        System.out.println("SparseMatrix + SparseMatrix:");
        System.out.println(sparseSum);
        System.out.println("Результаты равны: " + usualSum.equals(sparseSum));

        // === Тест 4: умножение маленьких матриц ===
        System.out.println("=== Тест 4: умножение 3x3 ===");
        IMatrix usualProduct = u.product(u2);
        IMatrix sparseProduct = s.product(s2);
        System.out.println("UsualMatrix * UsualMatrix:");
        System.out.println(usualProduct);
        System.out.println("SparseMatrix * SparseMatrix:");
        System.out.println(sparseProduct);
        System.out.println("Результаты равны: " + usualProduct.equals(sparseProduct));

        // === Тест 5: сложение разных типов (UsualMatrix + SparseMatrix) ===
        System.out.println("=== Тест 5: сложение разных типов ===");
        IMatrix mixedSum = u.sum(s2);
        System.out.println("UsualMatrix + SparseMatrix:");
        System.out.println(mixedSum);
        System.out.println("Равно обычному сложению: " + mixedSum.equals(usualSum));

        // === Тест 6: большие случайные матрицы 1000x1000 ===
        System.out.println("=== Тест 6: случайные матрицы 1000x1000 (1000 ненулевых элементов) ===");
        int size = 1000;
        UsualMatrix bigUsual1 = new UsualMatrix(size, size);
        UsualMatrix bigUsual2 = new UsualMatrix(size, size);
        SparseMatrix bigSparse1 = new SparseMatrix(size, size);
        SparseMatrix bigSparse2 = new SparseMatrix(size, size);

        Random random = new Random(42);
        for (int i = 0; i < 1000; i++) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            int value = random.nextInt(100) + 1;
            bigUsual1.setElement(row, col, value);
            bigSparse1.setElement(row, col, value);
        }
        for (int i = 0; i < 1000; i++) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            int value = random.nextInt(100) + 1;
            bigUsual2.setElement(row, col, value);
            bigSparse2.setElement(row, col, value);
        }

        IMatrix bigUsualSum = bigUsual1.sum(bigUsual2);
        IMatrix bigSparseSum = bigSparse1.sum(bigSparse2);
        System.out.println("Сложение 1000x1000 — результаты равны: " + bigUsualSum.equals(bigSparseSum));

        // === Тест 7: умножение случайных матриц 50x50 ===
        System.out.println("=== Тест 7: случайные матрицы 50x50 — умножение ===");
        int smallSize = 50;
        UsualMatrix smallUsual1 = new UsualMatrix(smallSize, smallSize);
        UsualMatrix smallUsual2 = new UsualMatrix(smallSize, smallSize);
        SparseMatrix smallSparse1 = new SparseMatrix(smallSize, smallSize);
        SparseMatrix smallSparse2 = new SparseMatrix(smallSize, smallSize);

        Random random2 = new Random(99);
        for (int i = 0; i < 20; i++) {
            int row = random2.nextInt(smallSize);
            int col = random2.nextInt(smallSize);
            int value = random2.nextInt(100) + 1;
            smallUsual1.setElement(row, col, value);
            smallSparse1.setElement(row, col, value);
        }
        for (int i = 0; i < 20; i++) {
            int row = random2.nextInt(smallSize);
            int col = random2.nextInt(smallSize);
            int value = random2.nextInt(100) + 1;
            smallUsual2.setElement(row, col, value);
            smallSparse2.setElement(row, col, value);
        }

        IMatrix smallUsualProduct = smallUsual1.product(smallUsual2);
        IMatrix smallSparseProduct = smallSparse1.product(smallSparse2);
        System.out.println("Умножение 50x50 — результаты равны: " + smallUsualProduct.equals(smallSparseProduct));

        // === Тест 8: проверка исключений ===
        System.out.println("=== Тест 8: проверка исключений ===");
        try {
            new UsualMatrix(0, 3);
        } catch (MatrixException e) {
            System.out.println("UsualMatrix(0, 3): " + e.getMessage());
        }
        try {
            new SparseMatrix(-1, 5);
        } catch (MatrixException e) {
            System.out.println("SparseMatrix(-1, 5): " + e.getMessage());
        }
        try {
            UsualMatrix m = new UsualMatrix(3, 3);
            m.getElement(10, 10);
        } catch (MatrixException e) {
            System.out.println("getElement(10, 10): " + e.getMessage());
        }

        // === Тест 9: графы — создание и вывод ===
        System.out.println("\n=== Тест 9: граф на матрице и списке смежности ===");
        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(5);
        AdjacencyListGraph listGraph = new AdjacencyListGraph(5);
        matrixGraph.connect(0, 1); matrixGraph.connect(0, 2);
        matrixGraph.connect(1, 3); matrixGraph.connect(2, 3);
        matrixGraph.connect(3, 4);
        listGraph.connect(0, 1); listGraph.connect(0, 2);
        listGraph.connect(1, 3); listGraph.connect(2, 3);
        listGraph.connect(3, 4);
        System.out.println(matrixGraph);
        System.out.println(listGraph);
        System.out.println("Графы равны: " + matrixGraph.equals(listGraph));

        // === Тест 10: disconnect ===
        System.out.println("=== Тест 10: disconnect(0, 2) ===");
        matrixGraph.disconnect(0, 2);
        listGraph.disconnect(0, 2);
        System.out.println("После удаления ребра 0-2 графы равны: " + matrixGraph.equals(listGraph));

        // === Тест 11: DFS на матрице смежности ===
        System.out.println("\n=== Тест 11: DFS на AdjacencyMatrixGraph с вершины 0 ===");
        AdjacencyMatrixGraph dfsMatrix = new AdjacencyMatrixGraph(5);
        dfsMatrix.connect(0, 1); dfsMatrix.connect(0, 2);
        dfsMatrix.connect(1, 3); dfsMatrix.connect(2, 3);
        dfsMatrix.connect(3, 4);
        dfs(dfsMatrix, 0);

        // === Тест 12: DFS на списке смежности ===
        System.out.println("\n=== Тест 12: DFS на AdjacencyListGraph с вершины 0 ===");
        AdjacencyListGraph dfsList = new AdjacencyListGraph(5);
        dfsList.connect(0, 1); dfsList.connect(0, 2);
        dfsList.connect(1, 3); dfsList.connect(2, 3);
        dfsList.connect(3, 4);
        dfs(dfsList, 0);

        // === Тест 13: DFS на несвязном графе ===
        System.out.println("\n=== Тест 13: DFS на несвязном графе (вершины 3,4 недостижимы) ===");
        AdjacencyMatrixGraph disconnected = new AdjacencyMatrixGraph(5);
        disconnected.connect(0, 1);
        disconnected.connect(1, 2);
        disconnected.connect(3, 4);
        dfs(disconnected, 0);

        System.out.println("\n=== Все тесты завершены! ===");
    }
}
