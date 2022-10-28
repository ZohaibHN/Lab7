// ArrayQueue.java

import java.util.NoSuchElementException;

// a queue based on an array
class Queue {
    private int[] array;
    private int start;
    private int end;
    private int size;

    // constructor
    public Queue(int capacity) {
        array = new int[capacity];
        start = -1;
        end = -1;
        size = 0;
    }
    public void resize() { //resize method for when array is full
        int[] newArray = new int[(array.length * 2)]; //new array declared twice the size of the original array
        int original = start; //int value to keep track of old array
        int now = 0; //int value to keep track of new array
        while (now < array.length) { //while loop to access new array
            newArray[now] = array[original]; //copies old array to new array
            now++; //increments now to access next index of new array
            original++; //increments to access next index of old array
            if (original == array.length) { //if statement to change original when it equals the old array length
                original = 0;
            }
        }
        start = 0;
        end = (now -1);
        array = newArray; //sets array equal to the new array with updated values
    }

    // enqueue - add at end
    public void enqueue(int number) {
        if (full() == true) { //checking to see if array is full before going through the method
            resize(); //calls resize method if array is full
        }
        end++;
        if (end == array.length) {
            end = 0;
        }

        array[end] = number;
        size++;

        if (start == -1) {
            start = end;
        }
    }

    // dequeue - remove from start
    public int dequeue() throws NoSuchElementException{ //throws exception
        if (empty() == true) {
            throw new NoSuchElementException(); //throws Exception when array is empty
        }
        int returnVal = array[start];
        start++;
        if (start == array.length) {
            start = 0;
        }

        size--;
        if (size == 0) {
            start = -1;
            end = -1;
        }
        return returnVal;
    }

    // get the size
    public int getSize() {
        return size;
    }

    // return whether or not we're empty
    public boolean empty() {
        return size == 0;
    }

    // return whether or not we're full
    public boolean full() {
        return size == array.length;
    }
}

public class ArrayQueue {
    public static void main(String args[]) {
        Queue queue = new Queue(5);

        // add some things to the queue (causing wraparound)
        queue.enqueue(99);
        queue.enqueue(99);
        queue.enqueue(99);
        queue.enqueue(99);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(99);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4); // causes resize

        // add some more stuff
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11); // causes resize

        // add some more
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);

        // print it all out
        while (!queue.empty()) {
            System.out.println(queue.dequeue());
        }

        // test the exception
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Exception thrown correctly!");
        }
    }
}
