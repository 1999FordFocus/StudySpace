package com.zhx._04_stack_queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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


    //Binary tree BFS using queue
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    /**
     * Binary tree BFS base on queue
     *
     * @param root
     */
    public static List<List<Integer>> bfsBaseOnQueue(TreeNode root) {
        //using mutable tow-dimensional array to save result
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //using queue to traversal the tree by level-order,using Pair<K,V> to save node and the level number the node in.
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        List<Integer> level;
        while (!queue.isEmpty()) {
            int count = queue.size();
            level = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                //cause of FIFO , left child node first
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                level.add(node.val);
            }
            res.add(level);
        }
        return res;
    }

    //"之"字/锯齿形打印二叉树
    public static List<List<Integer>> bfsBaseOnQueueWithZhi(TreeNode root) {
        //using mutable tow-dimensional array to save result
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //using queue to traversal the tree by level-order,using Pair<K,V> to save node and the level number the node in.
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        List<Integer> level;
        while (!queue.isEmpty()) {
            int count = queue.size();
            level = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                //cause of FIFO , left child node first
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                level.add(node.val);
            }
            //calculate now finished levels
            if (res.size() % 2 == 1){
                Collections.reverse(level);
            }
            res.add(level);
        }
        return res;
    }
}






















