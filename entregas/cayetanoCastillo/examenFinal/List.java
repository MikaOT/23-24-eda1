package ProyectoFinal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T> implements Iterable<T> {
    private Node<T> head;

    public List() {
        this.head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Node<T> getFirst() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head);
    }

    private static class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}