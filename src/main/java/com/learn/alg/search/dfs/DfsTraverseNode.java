package com.learn.alg.search.dfs;


import com.learn.base.tree.TreeNode;

import java.util.LinkedList;

/**
 * 深度优先
 * @author yeyongjun
 * @since 2025/1/15 10:30
 */
public class DfsTraverseNode {

    /**
     * 前序遍历
     * @param treeNode
     */
    public static void dfs(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 遍历节点
        System.out.print(treeNode.data + "，");
        // 遍历左节点
        dfs(treeNode.left);
        // 遍历右节点
        dfs(treeNode.right);
    }

    /**
     * 中序遍历
     * @param treeNode
     */
    public static void dfs2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 遍历左节点
        dfs2(treeNode.left);
        // 遍历节点
        System.out.print(treeNode.data + "，");
        // 遍历右节点
        dfs2(treeNode.right);
    }

    /**
     * 后序遍历
     * @param treeNode
     */
    public static void dfs3(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 遍历左节点
        dfs3(treeNode.left);
        // 遍历右节点
        dfs3(treeNode.right);
        // 遍历节点
        System.out.print(treeNode.data + "，");
    }

    public static void dfsWithStack(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        // 先把根节点压栈
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            // 遍历节点
            System.out.println("value = " + treeNode.data);

            // 先压右节点
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            // 再压左节点
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }
}
