package org.suai.telegin.lab3;

import org.suai.telegin.lab3.matrix.Matrix;
import org.suai.telegin.lab3.matrix.SquareMatrix;
import org.suai.telegin.lab3.matrix.exceptions.MatrixException;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тестирование Matrix и SquareMatrix ===\n");

        // --- Тест 1: Создание Matrix ---
        System.out.println("--- Тест 1: Matrix 2x3 ---");
        Matrix m1 = new Matrix(2, 3);
        m1.setElement(0, 0, 1); m1.setElement(0, 1, 2); m1.setElement(0, 2, 3);
        m1.setElement(1, 0, 4); m1.setElement(1, 1, 5); m1.setElement(1, 2, 6);
        System.out.println("m1:");
        System.out.println(m1);

        // --- Тест 2: Создание SquareMatrix (единичная) ---
        System.out.println("--- Тест 2: SquareMatrix 3x3 (единичная) ---");
        SquareMatrix sq1 = new SquareMatrix(3);
        System.out.println("sq1:");
        System.out.println(sq1);

        // --- Тест 3: Matrix.sum ---
        System.out.println("--- Тест 3: Сложение двух Matrix 2x3 ---");
        Matrix m2 = new Matrix(2, 3);
        m2.setElement(0, 0, 10); m2.setElement(0, 1, 20); m2.setElement(0, 2, 30);
        m2.setElement(1, 0, 40); m2.setElement(1, 1, 50); m2.setElement(1, 2, 60);
        System.out.println("m2:");
        System.out.println(m2);
        System.out.println("m1 + m2:");
        System.out.println(m1.sum(m2));

        // --- Тест 4: Matrix.product ---
        System.out.println("--- Тест 4: Умножение Matrix 2x3 * Matrix 3x2 ---");
        Matrix m3 = new Matrix(3, 2);
        m3.setElement(0, 0, 1); m3.setElement(0, 1, 2);
        m3.setElement(1, 0, 3); m3.setElement(1, 1, 4);
        m3.setElement(2, 0, 5); m3.setElement(2, 1, 6);
        System.out.println("m3 (3x2):");
        System.out.println(m3);
        System.out.println("m1 * m3:");
        System.out.println(m1.product(m3));

        // --- Тест 5: SquareMatrix.sum (переопределённый) ---
        System.out.println("--- Тест 5: Сложение двух SquareMatrix 3x3 ---");
        SquareMatrix sq2 = new SquareMatrix(3);
        sq2.setElement(0, 1, 5);
        sq2.setElement(1, 2, 7);
        System.out.println("sq2:");
        System.out.println(sq2);
        System.out.println("sq1 + sq2:");
        System.out.println(sq1.sum(sq2));

        // --- Тест 6: SquareMatrix.product (наследуется от Matrix) ---
        System.out.println("--- Тест 6: Умножение SquareMatrix 3x3 * SquareMatrix 3x3 ---");
        System.out.println("sq1 * sq2:");
        System.out.println(sq1.product(sq2));

        // --- Тест 7: equals ---
        System.out.println("--- Тест 7: equals ---");
        Matrix a = new Matrix(2, 2);
        a.setElement(0, 0, 1); a.setElement(0, 1, 2);
        a.setElement(1, 0, 3); a.setElement(1, 1, 4);
        Matrix b = new Matrix(2, 2);
        b.setElement(0, 0, 1); b.setElement(0, 1, 2);
        b.setElement(1, 0, 3); b.setElement(1, 1, 4);
        System.out.println("a.equals(b) = " + a.equals(b));
        b.setElement(0, 0, 99);
        System.out.println("После b.setElement(0,0,99): a.equals(b) = " + a.equals(b));
        System.out.println();

        // --- Тест 8: Исключения ---
        System.out.println("--- Тест 8: Исключения ---");

        try {
            new Matrix(0, 3);
        } catch (MatrixException e) {
            System.out.println("Matrix(0,3): " + e.getMessage());
        }

        try {
            new SquareMatrix(-1);
        } catch (MatrixException e) {
            System.out.println("SquareMatrix(-1): " + e.getMessage());
        }

        try {
            m1.sum(m3);
        } catch (MatrixException e) {
            System.out.println("sum разных размеров: " + e.getMessage());
        }

        try {
            m1.product(m2);
        } catch (MatrixException e) {
            System.out.println("product несовместимых: " + e.getMessage());
        }

        try {
            m1.getElement(10, 10);
        } catch (MatrixException e) {
            System.out.println("getElement вне границ: " + e.getMessage());
        }

        try {
            m1.setElement(-1, 0, 5);
        } catch (MatrixException e) {
            System.out.println("setElement вне границ: " + e.getMessage());
        }

        try {
            sq1.sum(m1);
        } catch (MatrixException e) {
            System.out.println("SquareMatrix.sum с не-квадратной: " + e.getMessage());
        }

        System.out.println("\n=== Все тесты завершены! ===");
    }
}
