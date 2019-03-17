package com.csu.algoritm.tree;

/**
 * 最近公共祖先
 */
public class LowestCommonAncestorTest {

    /**
     注意p,q必然存在树内, 且所有节点的值唯一!!!
     递归思想, 对以root为根的(子)树进行查找p和q, 如果root == null || p || q 直接返回root
     表示对于当前树的查找已经完毕, 否则对左右子树进行查找, 根据左右子树的返回值判断:
     1. 左右子树的返回值都不为null, 由于值唯一左右子树的返回值就是p和q, 此时root为LCA
     2. 如果左右子树返回值只有一个不为null, 说明只有p和q存在与左或右子树中, 最先找到的那个节点为LCA
     3. 左右子树返回值均为null, p和q均不在树中, 返回null
     **/
    /**
     * 方法1： 找出node p和node q的所有路径再比较
     * 方法2： 递归，查找。（本题采用方法2，时间复杂度o（n））
     **/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // root为空
        if (root == null || p == null || q == null) {
            return null;
        }

        // 如果p或者q为root（返回条件）
        if (root == p || root == q) {
            return root;
        }

        // 递归左子树，找到左子树中的p或者q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归右子树，找到右子树中的p或者q
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果左子树找不到任何一个，返回右子树
        if (left == null) {
            return right;
        }
        // 如果右子树也找不到任何一个，返回左子树
        if (right == null) {
            return left;
        }
        // 否则，左右字数都能找到任何一个，说明当前root为祖先节点
        return root;
    }

}
