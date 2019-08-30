package com.example.algorithm;

public class MyLinkedList {

    private static class Node{
        int data;

        Node next;

        Node(int data){
            this.data = data;
        }
    }

    private Node head;

    private Node last;

    private int size;


    public void insert(int data, int index) throws Exception{

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出范围");
        }

        Node insertdNode = new Node(data);

        if (size == 0) {
            head = insertdNode;
            last = insertdNode;
        } else if (size == index) {
            last.next = insertdNode;
            last = insertdNode;
        } else {
            Node preNode = get(index - 1);
            if (preNode != null) {
                Node nextNode = preNode.next;
                preNode.next = insertdNode;
                insertdNode.next = nextNode;
            }
        }
        size ++;


    }


    public Node remove(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        Node removeNode = null;
        if (index == 0) {
            removeNode = head;
            head = head.next;
        } else if (index == size) {
            Node preNode = get(index - 1);
            removeNode = preNode.next;
            preNode.next = null;
            last = preNode;
        } else {
            Node preNode = get(index - 1);
            removeNode = preNode.next;
            Node nextNode = removeNode.next;
            preNode.next = nextNode;
        }
        size --;
        return  removeNode;
    }

    private Node get(int index){
        if (index < 0 || index > size) {
            return  null;
        }

        Node temp = head;
        for (int i = 0; i < index; i ++) {
            temp = temp.next;;
        }
        size --;
        return  temp;

    }


    public void output() {
        Node temp = head;
        while (temp != null){
            System.out.println(temp.next);
            temp = temp.next;
        }
    }


    public static void main(String[] args) throws  Exception{
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insert(3,0);
        linkedList.insert(7,1);
        linkedList.insert(9,2);
        linkedList.insert(5,2);
        linkedList.insert(6,1);
        linkedList.remove(0);
        linkedList.output();
    }



}
