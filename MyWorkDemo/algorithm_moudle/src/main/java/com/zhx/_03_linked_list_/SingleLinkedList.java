package com.zhx._03_linked_list_;

import java.util.HashMap;

/**
 * 单链表
 * 1. 插入、删除、查找
 * 2. 方便起见，链表中存储int类型数据
 */
public class SingleLinkedList {

    private Node head = null;

    //逆序插入 -- 头插
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        // 头结点为空，newNode直接登基成为头节点。
        // 不为空，则将目前的头节点赶下台成为newNode的后继结点，newNode再登基成为头结点
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    //顺序插入 -- 尾插
    public void insertToTail(int value) {
        Node newNode = new Node(value, null);
        insertToTail(newNode);
    }

    public void insertToTail(Node newNode) {
        // 头结点为空，newNode直接登基成为头节点。
        // 遍历链表，找到next为空的结点（尾结点），狗尾续貂
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            newNode.next = null;
            q.next = newNode;
        }
    }

    //在p结点前插入
    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
    }

    public void insertBefore(Node p, Node newNode) {
        //判空
        if (p == null || newNode == null) {
            return;
        }
        //如果p结点是头结点，那就是头插
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        //找到p结点的前驱结点，改变其next指针指向newNode；同时将newNode的next指针指向p结点
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        //直到找到p的前驱，或者找不到
        if (q == null) {
            return;
        }

        newNode.next = p;
        q.next = newNode;
    }

    //在p结点之后插入新结点
    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    //删除结点
    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }
        //找到p的前驱结点
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        //p结点后继直接接到前驱
        q.next = q.next.next;

    }

    //按值删除结点
    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }

        Node p = head;
        Node q = null;//记录当前遍历结点的前驱结点
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null) {
            //未找到与目标值相等的结点
            return;
        }
        if (q == null) {
            //前驱结点为空，表示p是head结点，将head结点后移一位
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }

    //按index查找
    public Node findByIndex(int index) {
        Node p = head;
        int position = 0;
        while (p != null && position != index) {
            p = p.next;
            ++position;
        }
        return p;
    }

    //按值查找
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data + "");
            p = p.next;
        }
        System.out.println();
    }

    //生成结点
    public static Node createNode(int value) {
        return new Node(value, null);
    }

    //定义结点类
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
