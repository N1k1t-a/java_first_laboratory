package org.suai.telegin.lab3;

import org.suai.telegin.lab3.matrix.AllEqualMatrix;
import org.suai.telegin.lab3.matrix.Matrix;
import org.suai.telegin.lab3.matrix.SquareMatrix;
import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Тестирование Matrix, SquareMatrix, AllEqualMatrix ===\n");

        System.out.println("--- Тест 1: AllEqualMatrix 3x3 со значением 5 ---");
        AllEqualMatrix ae = new AllEqualMatrix(3, 3, 5);
        System.out.println(ae);
        System.out.println("ae.getElement(0,0) = " + ae.getElement(0, 0));
        System.out.println("ae.getElement(2,2) = " + ae.getElement(2, 2));

        ae.setElement(0, 0, 7);
        System.out.println("После ae.setElement(0,0,7) — все элементы стали 7:");
        System.out.println(ae);

        System.out.println("--- Тест 2: Сложение Matrix 2x3 и AllEqualMatrix 2x3 ---");
        Matrix m1 = new Matrix(2, 3);
        m1.setElement(0, 0, 1);
        m1.setElement(0, 1, 2);
        m1.setElement(0, 2, 3);
        m1.setElement(1, 0, 4);
        m1.setElement(1, 1, 5);
        m1.setElement(1, 2, 6);
        System.out.println("m1 (Matrix 2x3):");
        System.out.println(m1);

        AllEqualMatrix ae2 = new AllEqualMatrix(2, 3, 10);
        System.out.println("ae2 (AllEqualMatrix 2x3, все = 10):");
        System.out.println(ae2);

        Matrix sumResult = m1.sum(ae2);
        System.out.println("m1 + ae2 =");
        System.out.println(sumResult);

        System.out.println("--- Тест 3: Умножение AllEqualMatrix 2x3 * Matrix 3x2 ---");
        Matrix m2 = new Matrix(3, 2);
        m2.setElement(0, 0, 1);
        m2.setElement(0, 1, 2);
        m2.setElement(1, 0, 3);
        m2.setElement(1, 1, 4);
        m2.setElement(2, 0, 5);
        m2.setElement(2, 1, 6);
        System.out.println("m2 (Matrix 3x2):");
        System.out.println(m2);

        AllEqualMatrix ae3 = new AllEqualMatrix(2, 3, 2);
        System.out.println("ae3 (AllEqualMatrix 2x3, все = 2):");
        System.out.println(ae3);

        Matrix productResult = ae3.product(m2);
        System.out.println("ae3 * m2 =");
        System.out.println(productResult);

        System.out.println("--- Тест 4: Сложение SquareMatrix 3x3 + AllEqualMatrix 3x3 ---");
        SquareMatrix sq = new SquareMatrix(3);
        System.out.println("sq (единичная SquareMatrix 3x3):");
        System.out.println(sq);

        AllEqualMatrix ae4 = new AllEqualMatrix(3, 3, 1);
        System.out.println("ae4 (AllEqualMatrix 3x3, все = 1):");
        System.out.println(ae4);

        Matrix mixed = sq.sum(ae4);
        System.out.println("sq + ae4 =");
        System.out.println(mixed);

        System.out.println("--- Тест 5: Исключения ---");

        try {
            new AllEqualMatrix(0, 3, 5);
        } catch (MatrixException e) {
            System.out.println("✓ AllEqualMatrix(0,3): " + e.getMessage());
        }

        try {
            ae2.getElement(10, 10);
        } catch (MatrixException e) {
            System.out.println("✓ getElement(10,10): " + e.getMessage());
        }

        try {
            m1.sum(m2);
        } catch (MatrixException e) {
            System.out.println("✓ sum разных размеров: " + e.getMessage());
        }

        try {
            m1.product(m1);
        } catch (MatrixException e) {
            System.out.println("✓ product несовместимых: " + e.getMessage());
        }

        System.out.println("\n--- Тест 6: Matrix — базовые операции get/set ---");
        Matrix m3 = new Matrix(2, 2);
        m3.setElement(0, 0, 3);
        m3.setElement(0, 1, 6);
        m3.setElement(1, 0, 9);
        m3.setElement(1, 1, 12);
        System.out.println("m3 (Matrix 2x2):");
        System.out.println(m3);
        System.out.println("m3.getElement(1,1) = " + m3.getElement(1, 1));

        System.out.println("--- Тест 7: Matrix equals ---");
        Matrix m4 = new Matrix(2, 2);
        m4.setElement(0, 0, 3);
        m4.setElement(0, 1, 6);
        m4.setElement(1, 0, 9);
        m4.setElement(1, 1, 12);
        System.out.println("m3.equals(m4) = " + m3.equals(m4));
        m4.setElement(0, 0, 99);
        System.out.println("После m4.setElement(0,0,99): m3.equals(m4) = " + m3.equals(m4));

        System.out.println("--- Тест 8: Matrix умножение 2x2 * 2x2 ---");
        Matrix product2 = m3.product(m4);
        System.out.println("m3 * m4 =");
        System.out.println(product2);

        System.out.println("--- Тест 9: SquareMatrix — единичная матрица и get/set ---");
        SquareMatrix sq2 = new SquareMatrix(2);
        System.out.println("sq2 (единичная 2x2):");
        System.out.println(sq2);
        System.out.println("sq2.getElement(0,0) = " + sq2.getElement(0, 0));
        System.out.println("sq2.getElement(0,1) = " + sq2.getElement(0, 1));
        sq2.setElement(0, 1, 5);
        System.out.println("После sq2.setElement(0,1,5):");
        System.out.println(sq2);

        System.out.println("--- Тест 10: SquareMatrix.sum(SquareMatrix) ---");
        SquareMatrix sq3 = new SquareMatrix(2);
        System.out.println("sq3 (единичная 2x2):");
        System.out.println(sq3);
        SquareMatrix sqSum = sq2.sum(sq3);
        System.out.println("sq2 + sq3 =");
        System.out.println(sqSum);

        System.out.println("--- Тест 11: SquareMatrix.product (наследуется от Matrix) ---");
        Matrix sqProduct = sq2.product(sq3);
        System.out.println("sq2 * sq3 =");
        System.out.println(sqProduct);

        System.out.println("--- Тест 12: Исключения SquareMatrix и Matrix ---");
        try {
            new SquareMatrix(0);
        } catch (MatrixException e) {
            System.out.println("✓ SquareMatrix(0): " + e.getMessage());
        }

        try {
            sq2.sum(new SquareMatrix(3));
        } catch (MatrixException e) {
            System.out.println("✓ sq2.sum разных размеров: " + e.getMessage());
        }

        try {
            m3.setElement(5, 5, 1);
        } catch (MatrixException e) {
            System.out.println("✓ setElement(5,5) out of bounds: " + e.getMessage());
        }

        System.out.println("\n=== Все тесты завершены! ===");
    }
}