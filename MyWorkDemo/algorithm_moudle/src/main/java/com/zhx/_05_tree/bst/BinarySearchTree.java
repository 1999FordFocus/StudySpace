package com.zhx._05_tree.bst;

import com.zhx.TestHelper;

public class BinarySearchTree {

    private Node root;

    /**
     * 查找
     * 与当前节点值比较，小则向左，大则向右
     */
    public Node find(int data) {
        Node p = root;
        while (p != null) {
            if (data < p.val) {
                p = p.left;
            } else if (data > p.val) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 查找最大结点
     * 一直向右
     */
    public Node findMax() {
        if (root == null){
            return null;
        }
        Node p = root;
        while (p.right != null){
            p = p.right;
        }
        return p;
    }

    /**
     * 查找最小结点
     * 一直向左
     */
    public Node findMin(){
        if (root == null){
            return null;
        }
        Node p = root;
        while(p.left != null){
            p = p.left;
        }
        return p;
    }

    /**
     * 插入
     * 类似查找，与当前节点比，小于则向左，大于则向右，没有则生成
     * 找到空位（子节点为空），接上去成为叶子。
     */
    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        Node p = root;
        while (p != null) {
            if (data > p.val) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                //继续
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    //删除
    public void delete(int data) {

    }


    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int value) {
            this.val = value;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(11);
        tree.insert(13);
        Node node = tree.find(13);
        TestHelper.print("find ", node.val + "");
    }
}
