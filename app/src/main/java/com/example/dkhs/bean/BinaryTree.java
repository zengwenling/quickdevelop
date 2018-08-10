package com.example.dkhs.bean;

import android.util.Log;

/**
 * 二叉搜索树
 * Created by lijunjia on 2018/1/10.
 */

public class BinaryTree<T extends Comparable> {
    public TreeNode<T> rootNode;

    public BinaryTree(T[] arrData) {
        for (T item : arrData) {
            addNoteToTree2(item);
        }
    }

    private void addNoteToTree2(T item) {
        if (rootNode == null) {
            rootNode = new TreeNode<>(item, null, null);
            return;
        }
        TreeNode currentNode = rootNode;
        while (currentNode != null) {
            if (item.compareTo(currentNode.getValue()) > 0) {
                TreeNode rightNode = currentNode.getRightNode();
                if (rightNode == null) {
                    rightNode = new TreeNode(item, null, null);
                    currentNode.setRightNode(rightNode);
                    currentNode = null;
                } else {
                    currentNode = rightNode;
                }

            } else {
                TreeNode leftNode = currentNode.getLeftNode();
                if (leftNode == null) {
                    leftNode = new TreeNode(item, null, null);
                    currentNode.setLeftNode(leftNode);
                    currentNode = null;
                } else {
                    currentNode = leftNode;
                }
            }
        }
    }

    private void addNoteToTree(T item) {
        if (rootNode == null) {
            rootNode = new TreeNode<>(item, null, null);
        } else {
            if (item.compareTo(rootNode.getValue()) > 0) {
                addNoteToTree(rootNode, false, rootNode.getRightNode(), item);
            } else {
                addNoteToTree(rootNode, true, rootNode.getLeftNode(), item);
            }
        }
    }

    private void addNoteToTree(TreeNode<T> parentNode, boolean left, TreeNode<T> node, T item) {

        if (node == null) {
            node = new TreeNode<>(item, null, null);
            if (left) {
                parentNode.setLeftNode(node);
            } else {
                parentNode.setRightNode(node);
            }
            return;
        }
        if (item.compareTo(node.getValue()) > 0) {
            if (node.getRightNode() == null) {
                node.setRightNode(new TreeNode<>(item, null, null));
            } else {
                addNoteToTree(node, false, node.getRightNode(), item);
            }
        } else {
            if (node.getLeftNode() == null) {
                node.setLeftNode(new TreeNode<>(item, null, null));
            } else {
                addNoteToTree(node, true, node.getLeftNode(), item);
            }
        }

    }

    public void inOrder(TreeNode<T> node) {
        if (node != null) {
            inOrder(node.getLeftNode());
            Log.e("ljj", "[" + node.getValue().toString() + "]");
            inOrder(node.getRightNode());
        }
    }
}
