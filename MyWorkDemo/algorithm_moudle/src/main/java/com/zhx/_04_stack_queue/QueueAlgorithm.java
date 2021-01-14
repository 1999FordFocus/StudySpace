package com.zhx._04_stack_queue;

import java.util.Stack;

class QueueAlgorithm {
    //数组实现队列
    static class QueueBaseOnArray {
        private String[] items;
        int n;
        private int head = 0;
        private int tail = 0;

        public QueueBaseOnArray(int capacity) {
            items = new String[capacity];
            n = capacity;
        }

        //enqueue
        public boolean enqueue(String item) {
            //tail = n 表示队尾没有空间了，需要把数据整体迁移
            if (tail == n) {
                if (head == 0) {
                    //表示确实满了，数组头部也没有空间
                    return false;
                }
                //头部有空间，执行数组迁移,整体前移head位
                for (int i = head; i < tail; i++) {
                    items[i - head] = items[i];
                }
                //update head and tail
                tail -= head;
                head = 0;
            }
            items[tail] = item;
            tail++;
            return true;
        }

        public String dequeue() {
            if (head == tail) {
                return null;
            }
            String ret = items[head];
            ++head;
            return ret;
        }
    }

    static class QueueBaseOnStack {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;

        public QueueBaseOnStack() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }

        public void push(int x) {
            inputStack.push(x);
        }

        public int peek() {
            if (outputStack.isEmpty()) {
                while (!inputStack.empty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }

        public int pop() {
            if (outputStack.isEmpty()) {
                while (!inputStack.empty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop();
        }

        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }

    //Binary tree DFS using Queue


    //"之"

}






















