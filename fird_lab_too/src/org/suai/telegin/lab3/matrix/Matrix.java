package org.suai.telegin.lab3.matrix;

import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class Matrix {
    private final int[][] data;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new MatrixException(
                    "Невозможно создать матрицу с размером " + rows + " x " + columns);
        }
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public final int getRows() {
        return rows;
    }

    public final int getColumns() {
        return columns;
    }

    public int getElement(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new MatrixException(
                    "Индексы [" + row + "][" + column + "] выходят за границы матрицы "
                            + rows + "x" + columns);
        }
        return data[row][column];
    }

    public void setElement(int row, int column, int value) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new MatrixException(
                    "Индексы [" + row + "][" + column + "] выходят за границы матрицы "
                            + rows + "x" + columns);
        }
        data[row][column] = value;
    }

    public Matrix sum(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new MatrixException(
                    "Невозможно сложить матрицы разных размеров: "
                            + this.rows + "x" + this.columns
                            + " и " + other.rows + "x" + other.columns);
        }
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
        return result;
    }

    public Matrix product(Matrix other) {
        if (this.columns != other.rows) {
            throw new MatrixException(
                    "Невозможно перемножить матрицы: "
                            + this.rows + "x" + this.columns
                            + " и " + other.rows + "x" + other.columns);
        }
        Matrix result = new Matrix(this.rows, other.columns);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < other.columns; j++) {
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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(getElement(i, j));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (this.rows != other.rows || this.columns != other.columns) return false;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (this.getElement(i, j) != other.getElement(i, j))
                    return false;
        return true;
    }
}
