/*The class Queue makes a linked list so that word ladders are easily created and stored.*/
public class Queue<E>{
    /*The private class QueueNode makes nodes for the Queue. The value will contain WordInfo variables.*/
    private class QueueNode<E> {
        public E value;
        public QueueNode<E> next;

        public QueueNode() {
        }

        public QueueNode(E o) { this.value = o;}
    }

    private QueueNode<E> head = new QueueNode<>();
    private QueueNode<E> tail;
    private int enqueuedSize;

    public Queue() {this.enqueuedSize = 0; tail = head;}

    /*The method getEnqueuedSize returns the size of the Queue. This is important for returning the total enqueued after a
    * word ladder is created.*/
    public int getEnqueuedSize() {return this.enqueuedSize;}

    /*The method enqueue places new values at the end of the queue.*/
    public void enqueue(E newValue) {

        QueueNode<E> node = new QueueNode<>(newValue);
        QueueNode<E> current = head.next;
        QueueNode<E> previous = head;

        while (current != null) {
            previous = current;
            current = current.next;
        }

        previous.next = node;
        node.next = current;
        tail = node;

        this.enqueuedSize++;

    }

    /*The method dequeue deletes the first queue node from the queue.*/
    public void dequeue() {

        head.next = head.next.next;
        this.enqueuedSize--;
    }

    /*The method isEmpty returns if the queue is empty. This is used to ensure that a word ladder is impossible.*/
    public boolean isEmpty() {
        return head.next == null;
    }

    /*The method getFirstValue returns the first queue node from the queue.*/
    public E getFirstValue() {
        return head.next.value;
    }

    public E getLastValue() {return tail.value;}

}
