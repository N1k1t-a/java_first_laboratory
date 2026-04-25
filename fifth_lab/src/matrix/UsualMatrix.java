package matrix;

import exceptions.MatrixException;

public class UsualMatrix implements IMatrix {
    private int[][] data;
    protected int rows;
    protected int columns;

    public UsualMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new MatrixException("Невозможно создать матрицу с размером " + rows + "x" + columns);
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public void setElement(int row, int column, int value) {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns)
            throw new MatrixException("Индексы за границей матрицы: " + row + ", " + column);
        this.data[row][column] = value;
    }

    public int getElement(int row, int column) {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns)
            throw new MatrixException("Индексы за границей матрицы: " + row + ", " + column);
        return this.data[row][column];
    }

    public IMatrix sum(IMatrix other) {

        UsualMatrix result = new UsualMatrix(rows, columns);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                result.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
        return result;
    }

    public IMatrix product(IMatrix other) {

        UsualMatrix result = new UsualMatrix(this.rows, other.getColumns());
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < other.getColumns(); j++) {
                int sum = 0;
                for (int k = 0; k < this.columns; k++)
                    sum += this.getElement(i, k) * other.getElement(k, j);
                result.setElement(i, j, sum);
            }
        return result;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                sb.append(this.getElement(i, j));
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof IMatrix))
            return false;
        IMatrix other = (IMatrix) obj;
        if (this.rows != other.getRows() || this.columns != other.getColumns())
            return false;

        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                if (this.getElement(i, j) != other.getElement(i, j))
                    return false;

        return true;
    }

}