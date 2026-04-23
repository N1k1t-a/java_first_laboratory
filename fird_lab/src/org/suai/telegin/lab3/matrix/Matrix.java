package org.suai.telegin.lab3.matrix;

import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class Matrix {
    private int[][] data;
    protected int rows;
    protected int columns;

    public Matrix(int rows, int columns) throws MatrixException {
        if (rows <= 0 || columns <= 0) {
            throw new MatrixException(
                    "Невозможно создать матрицу с размером " + rows + " x " + columns);
        }
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public Matrix sum(Matrix other) throws MatrixException {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new MatrixException(
                    "Невозможно сложить матрицы разных размеров: "
                            + this.rows + "x" + this.columns
                            + " и " + other.rows + "x" + other.columns);
        }
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                result.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
        return result;
    }

    public Matrix product(Matrix other) throws MatrixException {
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

    public int getElement(int row, int column) throws MatrixException {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            throw new MatrixException(
                    "Невозможно получить элемент с индексами " + row + " и " + column);
        }
        return this.data[row][column];
    }

    public void setElement(int row, int column, int value) throws MatrixException {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            throw new MatrixException(
                    "Невозможно установить элемент с индексами " + row + " и " + column);
        }
        this.data[row][column] = value;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    sb.append(this.getElement(i, j));
                    sb.append(" ");
                }
                sb.append("\n");
            }
        } catch (MatrixException e) {
            throw new RuntimeException(e);
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
        if (obj == null || !(obj instanceof Matrix))
            return false;
        Matrix other = (Matrix) obj;
        if (this.rows != other.rows || this.columns != other.columns)
            return false;
        try {
            for (int i = 0; i < this.rows; i++)
                for (int j = 0; j < this.columns; j++)
                    if (this.getElement(i, j) != other.getElement(i, j))
                        return false;
        } catch (MatrixException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}