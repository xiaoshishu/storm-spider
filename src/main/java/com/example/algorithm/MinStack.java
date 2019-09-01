package com.example.algorithm;

import java.util.Stack;

/**
 * 算法题， 实现一个栈，该栈有入栈，出栈，和获取最小值。时间复杂度都是o(1)
 */
public class MinStack {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int element){
        mainStack.push(element);

        if (minStack.isEmpty() || element < minStack.peek()){
            minStack.push(element);
        }
    }

    public Integer pop(){
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return  mainStack.pop();

    }


    public Integer min(){
        if (minStack.isEmpty()) {
            return null;
        }
        return  minStack.peek();
    }

    public static void main(String[] args){
        MinStack minStack = new MinStack();
        minStack.push(4);
        minStack.push(9);
        minStack.push(7);
        minStack.push(3);
        minStack.push(8);
        minStack.push(5);
        minStack.push(4);
        System.out.println(minStack.min());

        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        System.out.println(minStack.min());

    }


}
