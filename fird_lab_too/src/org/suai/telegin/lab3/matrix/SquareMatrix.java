package org.suai.telegin.lab3.matrix;

import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class SquareMatrix extends Matrix {

    public SquareMatrix(int size) {
        super(size, size);
        for (int i = 0; i < size; i++)
            setElement(i, i, 1);
    }

    @Override
    public Matrix sum(Matrix other) {
        if (!(other instanceof SquareMatrix)) {
            throw new MatrixException(
                    "Нельзя сложить SquareMatrix с не-квадратной матрицей через SquareMatrix.sum");
        }
        if (this.getRows() != other.getRows()) {
            throw new MatrixException(
                    "Невозможно сложить матрицы разных размеров: "
                            + this.getRows() + "x" + this.getColumns()
                            + " и " + other.getRows() + "x" + other.getColumns());
        }
        SquareMatrix result = new SquareMatrix(this.getRows());
        for (int i = 0; i < this.getRows(); i++)
            for (int j = 0; j < this.getColumns(); j++)
                result.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
        return result;
    }
}
