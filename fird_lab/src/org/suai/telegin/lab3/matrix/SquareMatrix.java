package org.suai.telegin.lab3.matrix;

import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class SquareMatrix extends Matrix {

    public SquareMatrix(int size) throws MatrixException {
        super(size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    this.setElement(i, j, 1);
                } else {
                    this.setElement(i, j, 0);
                }
            }
        }
    }

    public SquareMatrix sum(SquareMatrix other) throws MatrixException {
        int size = this.getRows();

        if (size != other.getRows()) {
            throw new MatrixException("Невозможно сложить матрицы разных размеров: " + size + " и " + other.getRows());
        }

        SquareMatrix result = new SquareMatrix(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = this.getElement(i, j) + other.getElement(i, j);
                result.setElement(i, j, value);
            }
        }

        return result;
    }
}