package com.learn.alg.search;

import com.learn.alg.search.bfs.BfsTraverseNode;
import com.learn.alg.search.dfs.DfsTraverseNode;
import com.learn.base.tree.TreeNode;

/**
 * @author yeyongjun
 * @since 2025/1/15 10:28
 */
public class TreeNodeSearch {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode("1", new TreeNode("2", new TreeNode("4", new TreeNode("8"), new TreeNode("9")), new TreeNode("5", new TreeNode("10"), null)), new TreeNode("3", new TreeNode("6"), new TreeNode("7")));
        DfsTraverseNode.dfs(treeNode);
        System.out.println();
        DfsTraverseNode.dfs2(treeNode);
        System.out.println();
        DfsTraverseNode.dfs3(treeNode);
//        DfsTraverseNode.dfsWithStack(treeNode);
//        BfsTraverseNode.bfs(treeNode);

    }
}
