package com.zhx._03_linked_list_;

import java.util.Scanner;

/**
 * 基于单链表实现Lru算法
 */
public class LruLinkedList<T> {

    public static final int DEFAULT_CAPACITY = 5;

    private Node<T> head;

    /**
     * 链表长度
     */
    int size;

    /**
     * 链表容量
     */
    int capacity;


    public LruLinkedList() {
        this.head = new Node<>();
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public LruLinkedList(int capacity) {
        this.head = new Node<>();
        this.capacity = capacity;
        this.size = 0;
    }

    public void add(T data) {
        Node preNode = findPreNode(data);
        //如果已存在，则删除原结点，再插入链表头部
        if (preNode != null) {
            deleteElemOption(preNode);
            insertAtBegin(data);
        }else{
            //判断是否容量已满
            if (size >= this.capacity){
                //删除尾结点
                deleteElemAtEnd();
            }
            insertAtBegin(data);
        }
    }

    private void insertAtBegin(T data) {
        Node next = head.getNext();
        head.setNext(new Node<>(data, next));
        size ++;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd(){
        Node p = head;
        if (p.getNext() ==null){
            return;
        }

        //获取倒数第二个结点，再删除其后继结点（尾结点）
        while(p.getNext().getNext() != null){
            p = p.getNext();
        }
        Node tmp = p.getNext();
        p.setNext(null);
        tmp = null;
        size --;
    }

    private void deleteElemOption(Node preNode) {
        Node temp = preNode.next;
        preNode.next = temp.next;
        temp = null;
        size--;
    }

    /**
     * 查找元素是否存在并返回前驱结点
     *
     * @param data
     * @return
     */
    private Node findPreNode(T data) {
        Node p = head;
        while (p.next != null) {
            if (data.equals(p.next.data)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    private void printAll() {
        Node node = head.getNext();
        while (node != null) {
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }
        System.out.println();
    }


    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node() {
            this.next = null;
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T value, Node<T> next) {
            this.data = value;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
