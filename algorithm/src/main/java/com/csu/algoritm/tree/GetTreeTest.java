package com.csu.algoritm.tree;

/**
 * 构造一个二叉树
 */
public class GetTreeTest {

    public static void main(String[] args) {
        Object[] objs = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode binTree = tree(objs);
        binTree.preorder(binTree.getParent()); //先序遍历
        System.out.println();
        binTree.inorder(binTree.getParent()); //中序遍历
        System.out.println();
        binTree.afterorder(binTree.getParent()); //后序遍历
        System.out.println();
    }


    public static TreeNode tree(Object[] objs) {
        TreeNode binTree = new TreeNode();
        binTree.createTree(objs);
        return binTree;
    }

}
