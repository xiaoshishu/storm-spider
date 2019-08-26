package com.example.algorithm;

public class MyQueue {

    private int[] array;

    private int front;

    private int rear;

    public MyQueue(int cap){
        this.array = new int[cap];
    }

    public void enQueue(int element){
        // 入队列
        if ((rear + 1) % array.length == front ) {
            return;
        }

        array[rear] = element;
        rear = (rear + 1) % array.length;
    }

    public int deQueue() {
        // 出对列
        if (rear == front) {
            return 0;
        }

        int deQueueElemennt = array[front];
        front = (front + 1) % array.length;
        return deQueueElemennt;


    }


    public void output() {
        for (int i = front ; i != rear; i = (i+1) % array.length) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args){
        MyQueue myQueue = new MyQueue(6);
        myQueue.enQueue(3);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(8);
        myQueue.enQueue(1);
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.enQueue(2);
        myQueue.enQueue(4);
        myQueue.enQueue(9);
        myQueue.output();
    }

}
