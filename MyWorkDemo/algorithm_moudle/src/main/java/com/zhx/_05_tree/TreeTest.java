package com.zhx._05_tree;
import com.zhx.TestHelper;
import com.zhx._05_tree.TreeAlgorithm.TreeNode;

import java.nio.ByteBuffer;
import java.util.List;

public class TreeTest {

    public static void main(String[] args) {
        /**
         *       8
         *    6    10
         *  5  7  9  11
         */
        TreeNode treeNode = new TreeNode(8);
//        treeNode.left = new TreeNode(6);
//        treeNode.left.left = new TreeNode(5);
//        treeNode.left.right = new TreeNode(7);
        treeNode.right = new TreeNode(10);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(11);
//        TreeAlgorithm.traversalBinaryTreeByLevel(treeNode);

        List<Integer> list = null;
        list = TreeAlgorithm.preorderNonRecurse(treeNode);
        TestHelper.print("preorderNonRecurse",list);
        list = TreeAlgorithm.preorderNonRecurse2(treeNode);
        TestHelper.print("preorderNonRecurse2",list);
    }

}
