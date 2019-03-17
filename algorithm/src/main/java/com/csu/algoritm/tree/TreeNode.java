package com.csu.algoritm.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public TreeNode left;//左孩子
    public TreeNode right;//右孩子
    public TreeNode parent;//根节点
    public Object data; //数据域

    public TreeNode(TreeNode lChild, TreeNode rChild, Object data) {
        super();
        this.left = lChild;
        this.right = rChild;
        this.data = data;
    }

    public TreeNode(Object data) {
        this(null, null, data);
    }

    public TreeNode() {
        super();
    }

    public void createTree(Object[] objs) {
        List<TreeNode> datas = new ArrayList<TreeNode>();
        for (Object object : objs) {
            datas.add(new TreeNode(object));
        }
        parent = datas.get(0);//将第一个作为根节点
        for (int i = 0; i < objs.length / 2; i++) {
            datas.get(i).left = datas.get(i * 2 + 1);
            if (i * 2 + 2 < datas.size()) {//避免偶数的时候 下标越界
                datas.get(i).right = datas.get(i * 2 + 2);
            }
        }
    }

    //先序遍历
    public void preorder(TreeNode root) {
        if (root != null) {
            visit(root.getData());
            preorder(root.left);
            preorder(root.right);
        }

    }

    //中序遍历
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            visit(root.getData());
            inorder(root.right);
        }

    }

    //后序遍历
    public void afterorder(TreeNode root) {
        if (root != null) {
            afterorder(root.left);
            afterorder(root.right);
            visit(root.getData());
        }

    }

    private void visit(Object obj) {
        System.out.print(obj + " ");
    }

    public Object getData() {
        return data;
    }

    public TreeNode getParent() {
        return parent;
    }
}