
import java.util.ArrayList;

public class GenericQueue<T> {
    private ArrayList<T> queue;

    public GenericQueue() {
        queue = new ArrayList<>();
    }

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.remove(0);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.get(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        GenericQueue<Integer> intQueue = new GenericQueue<>();
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);


        while (!intQueue.isEmpty()) {
            System.out.println(intQueue.dequeue());
        }

        GenericQueue<String> stringQueue = new GenericQueue<>();
        stringQueue.enqueue("Hello");
        stringQueue.enqueue("World");

        while (!stringQueue.isEmpty()) {
            System.out.println(stringQueue.dequeue());
        }
    }
}

