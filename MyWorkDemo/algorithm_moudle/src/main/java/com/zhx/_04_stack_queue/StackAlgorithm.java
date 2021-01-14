package com.zhx._04_stack_queue;

import com.zhx.TestHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 栈相关算法
 */
class StackAlgorithm {

    /**
     * 1. 括号匹配
     * 读到左括号则压栈，读到右括号，尝试与栈顶左括号进行匹配，匹配成功则弹出
     * [](){}
     * 判定条件：栈为空
     */
    public static boolean isParenthesesValid(String bracketsStr) {
        Stack<Character> charStack = new Stack<>();
        char c = ' ';
        for (int i = 0; i < bracketsStr.length(); i++) {
            c = bracketsStr.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                charStack.push(c);
            } else {
                if (charStack.empty()) {
                    return false;
                }
                //check top char in stack
                char topChar = charStack.peek();
                //the target char match the top char
                char matchChar = ' ';
                if (topChar == '{') {
                    matchChar = '}';
                } else if (topChar == '[') {
                    matchChar = ']';
                } else {
                    matchChar = ')';
                }

                if (c != matchChar) {
                    break;
                }
                charStack.pop();
            }
        }

        if (charStack.empty()) {
            return true;
        }
        return false;
    }

    /**
     * 基于链表实现栈
     * 栈基本特性：LIFO
     * 栈基本方法：push、pop
     */
    public static class StackBaseOnSingleLinkedList<T> {

        private Node<T> head = null;

        public StackBaseOnSingleLinkedList() {

        }

        //头插
        public void push(T data) {
            Node<T> newNode = new Node<>(data, null);
            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        //头删
        public T pop() {
            if (head == null) {
                return null;
            }
            T data = head.data;
            head = head.next;
            return data;
        }

        static class Node<T> {
            T data;
            Node<T> next;

            public Node(T data, Node<T> next) {
                this.data = data;
                this.next = next;
            }
        }
    }


    /**
     * 二叉树数据结构
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    /**
     * 中序遍历
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderRecurse(root, list);
        TestHelper.print("inorderTraversal", list);
        return list;
    }

    private static void inorderRecurse(TreeNode tree, List<Integer> orderList) {
        if (tree == null) {
            return;
        }
        inorderRecurse(tree.left, orderList);
        orderList.add(tree.val);
        inorderRecurse(tree.right, orderList);

    }

    /**
     * 前序遍历
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderRecurse(root, list);
        TestHelper.print("preorderTraversal", list);
        return list;
    }

    private static void preorderRecurse(TreeNode tree, List<Integer> orderList) {
        if (tree == null) {
            return;
        }
        orderList.add(tree.val);//根结点先打印，再左右
        preorderRecurse(tree.left, orderList);
        preorderRecurse(tree.right, orderList);
    }

    /**
     * 后序遍历
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderRecurese(root, list);
        TestHelper.print("postorderTraversal", list);
        return list;
    }

    private static void postorderRecurese(TreeNode root, List<Integer> orderList) {
        if (root == null) {
            return;
        }
        postorderRecurese(root.left, orderList);
        postorderRecurese(root.right, orderList);
        orderList.add(root.val);
    }

    /**
     * 辅助数据结构 -- 命令栈
     */
    static class Command {
        String cmd;
        TreeNode node;

        public Command(String cmd, TreeNode node) {
            this.cmd = cmd;
            this.node = node;
        }
    }

    public static void inorderTraversalNonRecurse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Command> commandStack = new Stack<>();
        commandStack.push(new Command("go", root));
        while (!commandStack.empty()) {
            Command cmd = commandStack.pop();
            if (cmd.cmd.equals("print")) {
                list.add(cmd.node.val);
            } else {
                assert cmd.cmd.equals("go");

                if (cmd.node.right != null) {
                    commandStack.push(new Command("go", cmd.node.right));
                }
                commandStack.push(new Command("print", cmd.node));

                if (cmd.node.left != null) {
                    commandStack.push(new Command("go", cmd.node.left));
                }
            }
        }
    }

    public static void preorderTraversalNonRecurse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Command> commandStack = new Stack<>();
        commandStack.push(new Command("go", root));
        while (!commandStack.empty()) {
            Command cmd = commandStack.pop();
            if (cmd.cmd.equals("print")) {
                list.add(cmd.node.val);
            } else if (cmd.cmd.equals("go")) {
                if (cmd.node.right != null) {
                    commandStack.push(new Command("go", cmd.node.right));
                }

                if (cmd.node.left != null) {
                    commandStack.push(new Command("go", cmd.node.left));
                }

                commandStack.push(new Command("print", cmd.node));
            }
        }
        TestHelper.print("preorderTraversalNonRecurse", list);
    }

    public static void postorderTraversalNonRecurse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Command> commandStack = new Stack<>();
        commandStack.push(new Command("go", root));
        while (!commandStack.empty()) {
            Command cmd = commandStack.pop();
            if (cmd.cmd.equals("print")) {
                list.add(cmd.node.val);
            } else if (cmd.cmd.equals("go")) {
                commandStack.push(new Command("print", cmd.node));

                if (cmd.node.right != null) {
                    commandStack.push(new Command("go", cmd.node.right));
                }

                if (cmd.node.left != null) {
                    commandStack.push(new Command("go", cmd.node.left));
                }
            }
        }
        TestHelper.print("postorderTraversalNonRecurse", list);
    }
}

