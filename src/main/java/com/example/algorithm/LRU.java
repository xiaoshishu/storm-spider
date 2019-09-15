package com.example.algorithm;

import java.util.HashMap;

/**
 * LRU 算法
 */
public class LRU {

    public class Node{

        Node(String key, String value){
            this.key = key;
            this.value = value;
        }

        public Node next;

        public Node pre;

        public String key;

        public String value;

    }

    private Node head;

    private Node end;
    // 缓存存储上线
    private int limit;

    private HashMap<String, Node> hashMap;

    public LRU(int limit){
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    public String get(String key){
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value){
        Node node = hashMap.get(key);
        if (node == null) {
            if (hashMap.size() >= limit) {
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }

            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(String key){
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    private void refreshNode(Node node){
        if (node == end) {
            return;
        }
        removeNode(node);
        addNode(node);
    }


    private String removeNode(Node node){
        if (node == head && node == end) {
            head = null;
            end = null;
        } else if (node == end) {
            end = end.pre;
            end.next = null;
        } else if (node == head) {
            head.next = head;
            head.pre = null;
        } else {
            node.pre.next = node.next;;
            node.next.pre = node.pre;
        }
        return  node.key;
    }

    private void addNode(Node node){
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }


    public static void main(String[] args){
        LRU lru = new LRU(5);
        lru.put("001", "用户1信息");
        lru.put("002", "用户1信息");
        lru.put("003", "用户1信息");
        lru.put("004", "用户1信息");
        lru.put("005", "用户1信息");
        lru.get("002");
        lru.put("004","用过户信息2更新");
        lru.put("006","用户6信息");
        System.out.println(lru.get("001"));
        System.out.println(lru.get("006"));
        System.out.println(lru.get("004"));
        System.out.println("////////////////");

    }



}
