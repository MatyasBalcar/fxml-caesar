import java.util.NoSuchElementException;

public class MyPriorityQueue<T extends Prioritizable> {
    private final MyQueue<T> highQueue;
    private final MyQueue<T> normalQueue;
    private final MyQueue<T> lowQueue;

    public MyPriorityQueue(int capacityPerPriority) {
        highQueue = new MyQueue<>(capacityPerPriority);
        normalQueue = new MyQueue<>(capacityPerPriority);
        lowQueue = new MyQueue<>(capacityPerPriority);
    }

    public void add(T item) {
        switch (item.getPriority()) {
            case HIGH -> highQueue.add(item);
            case NORMAL -> normalQueue.add(item);
            case LOW -> lowQueue.add(item);
            default -> throw new IllegalArgumentException("Neznámá priorita");
        }
    }

    public T remove() {
        if (!highQueue.isEmpty()) {
            return highQueue.remove();
        } else if (!normalQueue.isEmpty()) {
            return normalQueue.remove();
        } else if (!lowQueue.isEmpty()) {
            return lowQueue.remove();
        } else {
            throw new NoSuchElementException("Fronta je prázdná");
        }
    }

    public int size() {
        return highQueue.size() + normalQueue.size() + lowQueue.size();
    }

    public boolean isEmpty() {
        return highQueue.isEmpty() && normalQueue.isEmpty() && lowQueue.isEmpty();
    }
}

class Task implements Prioritizable {
    private final String name;
    private final Priority priority;

    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + "}";
    }
}

