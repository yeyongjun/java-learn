package com.learn.alg.search.bfs;

import com.learn.base.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先
 * @author yeyongjun
 * @since 2025/1/15 10:30
 */
public class BfsTraverseNode {

    public static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            System.out.println("value = " + node.data);
            TreeNode left = node.left;
            if (left != null) {
                stack.add(left);
            }
            TreeNode right = node.right;
            if (right != null) {
                stack.add(right);
            }
        }
    }

}
