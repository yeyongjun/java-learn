package com.learn.base.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yeyongjun
 * @since 2024/8/19 22:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public String data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(String data) {
        this.data = data;
    }
}
