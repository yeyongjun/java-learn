package com.learn.base.tree;


/**
 * @author yeyongjun
 * @since 2024/8/19 22:57
 */
public class TreeOrder {
    /**
     * 先序遍历
     * @param root
     */
    public void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(TreeNode root) {
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrder(TreeNode root) {
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }
}
