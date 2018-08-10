package com.example.dkhs.bean;

/**
 * Created by lijunjia on 2018/1/10.
 */

public class TreeNode<T extends Comparable> {
    private T value;
    private TreeNode<T> leftNode;
    private TreeNode<T> rightNode;

    public TreeNode(T value, TreeNode<T> leftNode, TreeNode<T> rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }
}
