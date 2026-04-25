
package matrix;

public interface IMatrix {
    int getRows();

    int getColumns();

    int getElement(int row, int column);

    void setElement(int row, int column, int value);

    IMatrix sum(IMatrix other);

    IMatrix product(IMatrix other);

}
