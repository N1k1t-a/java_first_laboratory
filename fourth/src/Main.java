import list.SortedIntegerList;

public class Main {
    public static void main(String[] args) {

        // 1. Добавление в список без дубликатов
        System.out.println("=== Список без дубликатов ===");
        SortedIntegerList list1 = new SortedIntegerList(false);
        list1.add(5);
        list1.add(3);
        list1.add(7);
        list1.add(1);
        list1.add(9);
        System.out.println("Добавили 5, 3, 7, 1, 9:");
        System.out.println(list1); // [1, 3, 5, 7, 9]

        list1.add(5);
        System.out.println("Добавили дубликат 5 (должен игнорироваться):");
        System.out.println(list1); // [1, 3, 5, 7, 9]

        // 2. Добавление в список с дубликатами
        System.out.println("\n=== Список с дубликатами ===");
        SortedIntegerList list2 = new SortedIntegerList(true);
        list2.add(5);
        list2.add(3);
        list2.add(5);
        list2.add(1);
        list2.add(5);
        System.out.println("Добавили 5, 3, 5, 1, 5:");
        System.out.println(list2); // [1, 3, 5, 5, 5]

        // 3. Удаление
        System.out.println("\n=== Удаление ===");
        list2.remove(5);
        System.out.println("Удалили 5 (первое вхождение):");
        System.out.println(list2); // [1, 3, 5, 5]

        list2.remove(1);
        System.out.println("Удалили 1 (из начала):");
        System.out.println(list2); // [3, 5, 5]

        list2.remove(99);
        System.out.println("Удалили 99 (нет в списке, ничего не должно измениться):");
        System.out.println(list2); // [3, 5, 5]

        // 4. Equals
        System.out.println("\n=== Equals ===");
        SortedIntegerList a = new SortedIntegerList(false);
        a.add(1); a.add(2); a.add(3);

        SortedIntegerList b = new SortedIntegerList(false);
        b.add(1); b.add(2); b.add(3);

        SortedIntegerList c = new SortedIntegerList(false);
        c.add(1); c.add(2); c.add(4);

        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("C: " + c);
        System.out.println("A.equals(B): " + a.equals(b)); // true
        System.out.println("A.equals(C): " + a.equals(c)); // false
        System.out.println("A.equals(null): " + a.equals(null)); // false
        System.out.println("A.equals(A): " + a.equals(a)); // true
        SortedIntegerList d = new SortedIntegerList(true);
        d.add(1); d.add(2); d.add(3);
        System.out.println("D (с дубликатами): " + d);
        System.out.println("A.equals(D) разные политики: " + a.equals(d)); // false

        // 5. Пустой список
        System.out.println("\n=== Пустой список ===");
        SortedIntegerList empty = new SortedIntegerList(false);
        System.out.println("Пустой список: " + empty); // []
        empty.remove(5);
        System.out.println("Удаление из пустого списка: " + empty); // []

        // 6. Combine
        System.out.println("\n=== Combine ===");
        try {
            // Оба без дубликатов
            SortedIntegerList c1 = new SortedIntegerList(false);
            c1.add(1); c1.add(3); c1.add(5);

            SortedIntegerList c2 = new SortedIntegerList(false);
            c2.add(3); c2.add(5); c2.add(7);

            SortedIntegerList c3 = c1.combine(c2);
            System.out.println("c1: " + c1);
            System.out.println("c2: " + c2);
            System.out.println("c1.combine(c2) без дубликатов: " + c3); // [1, 3, 5, 7]

            // Оба с дубликатами
            SortedIntegerList c4 = new SortedIntegerList(true);
            c4.add(1); c4.add(3); c4.add(3);

            SortedIntegerList c5 = new SortedIntegerList(true);
            c5.add(3); c5.add(5);

            SortedIntegerList c6 = c4.combine(c5);
            System.out.println("c4: " + c4);
            System.out.println("c5: " + c5);
            System.out.println("c4.combine(c5) с дубликатами: " + c6); // [1, 3, 3, 3, 5]

            // Разные типы — должно бросить исключение
            SortedIntegerList c7 = new SortedIntegerList(false);
            c7.add(1);
            SortedIntegerList c8 = new SortedIntegerList(true);
            c8.add(2);
            c7.combine(c8); // ожидаем исключение
            System.out.println("Этого не должно быть напечатано");

        } catch (exceptions.IllegalArgumentException e) {
            System.out.println("Поймали исключение (ожидаемо): " + e.getMessage());
        }
    }
}
