package com.example.algorithm;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;

public class MyTree {

    public static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data){
            this.data = data;
        }
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> integers){
        TreeNode node = null;
        if (integers == null || integers.isEmpty()) {
            return  null;
        }

        Integer data = integers.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(integers);
            node.rightChild = createBinaryTree(integers);
        }
        return node;
    }


    public static void preOrderTraveral(TreeNode node){
        if (node == null) {
            return;
        }
        // 前序遍历
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);

    }

    public static void inOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }

        // 中序遍历
        inOrderTraveral(node.leftChild);
        System.out.println(node.data);
        inOrderTraveral(node.rightChild);
    }

    public static void postOrderTrveral(TreeNode node){
        if (node == null) {
            return;
        }

        postOrderTrveral(node.leftChild);
        postOrderTrveral(node.rightChild);
        System.out.println(node.data);

    }


    public static void main(String[] args){
        LinkedList<Integer> integers = new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode node = createBinaryTree(integers);

        System.out.println("前");
        preOrderTraveral(node);
        System.out.println("中");
        inOrderTraveral(node);
        System.out.println("后");
        postOrderTrveral(node);
    }



}
