package com.zhx._05_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class TreeAlgorithm {

    /**
     * 构建二叉树
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 从上到下，从左到右打印二叉树（不需分层）
     * 场景是从左到右依次遍历，队列特性是先进先出，所以就从根结点开始，放入队列，出队、打印后，再将其左右孩子入队
     */
    public static void traversalBinaryTreeByLevel(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode lastNode = queue.poll();
            //print node
            System.out.print(lastNode.val + " ");

            if (lastNode.left != null) {
                queue.add(lastNode.left);
            }
            if (lastNode.right != null) {
                queue.add(lastNode.right);
            }
        }
    }

    /**
     * 非递归前序遍历二叉树
     */
    public static List<Integer> preorderNonRecurse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                result.add(treeNode.val);
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
        return result;
    }

    public static List<Integer> preorderNonRecurse2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmpNode = stack.pop();
            if (tmpNode != null) {
                result.add(tmpNode.val);
                stack.push(tmpNode.right);
                stack.push(tmpNode.left);
            }
        }
        return result;
    }

    /**
     * 非递归遍历二叉树
     */
    public List<Integer> inorderNonRecurse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            treeNode = stack.pop();
            result.add(treeNode.val);
            treeNode = treeNode.right;
        }
        return result;
    }

    /**
     * 非递归遍历二叉树
     */
//    public List<Integer> postorderNonRecurse(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//        Deque<TreeNode> stack = new LinkedList<>();
//        TreeNode prev = null;
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (root.right == null || root.right == prev) {
//                result.add(root.val);
//                prev = root;
//                root = null;
//            } else {
//                stack.push(root);
//                root = root.right;
//            }
//        }
//        return result;
//    }
    public List<Integer> postorderNonRecurse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                result.add(treeNode.val);
                stack.push(treeNode);
                treeNode = treeNode.right;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.left;
            }
        }
        Collections.reverse(result);
        return result;
    }

    int maxd;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxd;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        maxd = Math.max(left + right, maxd);
        return Math.max(left, right) + 1;
    }

    public static TreeNode inverseTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode inverseLeft = inverseTree(root.right);
        TreeNode inverseRight = inverseTree(root.left);
        root.left = inverseLeft;
        root.right = inverseRight;
        return root;
    }

    public static TreeNode mirrorTreeNonRecurse(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }


    /**
     * sum of path
     */
    public static boolean findPathWithSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            //没有左右子树了，说明是叶子结点，递归终止
            return root.val == sum;
        }
        return findPathWithSum(root.left, sum - root.val)
                || findPathWithSum(root.right, sum - root.val);
    }
}
