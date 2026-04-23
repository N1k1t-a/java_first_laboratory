package org.suai.telegin.lab3.matrix;

import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class AllEqualMatrix extends Matrix {

    public AllEqualMatrix(int rows, int columns, int value) throws MatrixException {
        super(1, 1);
        super.setElement(0, 0, value);

        if (rows <= 0 || columns <= 0) {
            throw new MatrixException(
                    "Невозможно создать матрицу с размером " + rows + " x " + columns);
        }
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int getElement(int row, int column) throws MatrixException {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new MatrixException(
                    "Невозможно получить элемент с индексами " + row + " и " + column);
        }
        return super.getElement(0, 0);
    }

    @Override
    public void setElement(int row, int column, int newValue) throws MatrixException {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new MatrixException(
                    "Невозможно установить элемент с индексами " + row + " и " + column);
        }
        super.setElement(0, 0, newValue);
    }
}
