package com.zhx._04_stack_queue;

import com.zhx.TestHelper;

import java.util.List;
import java.util.Scanner;

public class TestStackAndQueue {

    public static void main(String[] args) {
        //1. ����ƥ��
//        String testParentheses = "{{}}{}}";
//        boolean isValid = StackAlgorithm.isParenthesesValid(testParentheses);
//        TestHelper.print("check string", testParentheses, "result = "+isValid);

        //2. ��������ʵ��ջ
//        StackAlgorithm.StackBaseOnSingleLinkedList<String> stack = new StackAlgorithm.StackBaseOnSingleLinkedList<>();
//        stack.push("Jan");
//        stack.push("Feb");
//
//        TestHelper.print("pop stack", stack.pop());
//        TestHelper.print("pop stack2", stack.pop());
//        TestHelper.print("pop stack3", stack.pop());

        //3. ������ǰ�к������
//        StackAlgorithm.TreeNode treeNode = new StackAlgorithm.TreeNode(1);
//        treeNode.left = new StackAlgorithm.TreeNode(2);
//        treeNode.left.left = new StackAlgorithm.TreeNode(4);
//        treeNode.left.right = new StackAlgorithm.TreeNode(5);
//        treeNode.right = new StackAlgorithm.TreeNode(3);
//        treeNode.right.left = new StackAlgorithm.TreeNode(6);
//        treeNode.right.right = new StackAlgorithm.TreeNode(7);
//
//        StackAlgorithm.preorderTraversal(treeNode);
//        StackAlgorithm.inorderTraversal(treeNode);
//        StackAlgorithm.postorderTraversal(treeNode);
//        System.out.println("");
//        StackAlgorithm.preorderTraversalNonRecurse(treeNode);
//        StackAlgorithm.inorderTraversalNonRecurse(treeNode);
//        StackAlgorithm.postorderTraversalNonRecurse(treeNode);

        //Queue base on Stack
        QueueAlgorithm.QueueBaseOnStack queue = new QueueAlgorithm.QueueBaseOnStack();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.pop();
        TestHelper.print("elements in queue :");
        queue.printAll();
    }
}
