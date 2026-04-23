package list;

import java.util.LinkedList;
import java.util.ListIterator;
import exceptions.IllegalArgumentException;

public class SortedIntegerList {
    private boolean duplicates;
    private LinkedList<Integer> elements;


    public SortedIntegerList(boolean duplicates) {
        this.duplicates = duplicates;
        this.elements = new LinkedList<>();
    }

    public void add(int element){
        ListIterator<Integer> iterator = elements.listIterator();

        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current > element) {
                iterator.previous();
                iterator.add(element);
                return;
            }

            else if (current == element && !this.duplicates) {
                return;
            }
        }
        iterator.add(element);
    }

    public SortedIntegerList combine(SortedIntegerList other) throws exceptions.IllegalArgumentException {
        if (this.duplicates != other.duplicates) {
            throw new exceptions.IllegalArgumentException("Списки имеют разные типы (с дубликатами и без)");
        }

        SortedIntegerList result = new SortedIntegerList(this.duplicates);

        for (int element : this.elements) {
            result.add(element);
        }
        for (int element : other.elements) {
            result.add(element);
        }

        return result;
    }

    public void remove(int element) {
        ListIterator<Integer> iterator = elements.listIterator();

        while (iterator.hasNext()) {
            int current = iterator.next();

            if (current > element) {
                return;
            }

            if (current == element) {
                iterator.remove();
                return;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListIterator<Integer> iterator = elements.listIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SortedIntegerList)){
            return false;
        }

        SortedIntegerList other = (SortedIntegerList) obj;

        if (this.elements.size() != other.elements.size()) {
            return false;
        }

        ListIterator<Integer> it1 = this.elements.listIterator();
        ListIterator<Integer> it2 = other.elements.listIterator();
        

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())){
                return false;
            }
        }
        return true;
    }

}