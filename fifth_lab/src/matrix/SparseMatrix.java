package matrix;

import exceptions.MatrixException;
import java.util.LinkedList;
import java.util.ListIterator;

public class SparseMatrix implements IMatrix {

    private LinkedList<Cell> cells;
    private int rows;
    private int columns;

    public SparseMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new MatrixException("Невозможно создать матрицу с размером " + rows + "x" + columns);
        this.rows = rows;
        this.columns = columns;
        this.cells = new LinkedList<>();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getElement(int row, int column) {
        for (Cell cell : cells) {
            if (cell.row == row && cell.column == column) {
                return cell.value;
            }
        }
        return 0;
    }

    public void setElement(int row, int column, int value) {
        ListIterator<Cell> iterator = cells.listIterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (cell.row == row && cell.column == column) {
                if (value == 0)
                    iterator.remove();
                else
                    cell.value = value;
                return;
            }
        }
        if (value != 0) {
            Cell newCell = new Cell();
            newCell.row = row;
            newCell.column = column;
            newCell.value = value;
            cells.add(newCell);
        }
    }

    public IMatrix sum(IMatrix other) {

        SparseMatrix result = new SparseMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                int val = this.getElement(i, j) + other.getElement(i, j);
                if (val != 0)
                    result.setElement(i, j, val);
            }
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

    private static class Cell {
        int row;
        int column;
        int value;

    }
}