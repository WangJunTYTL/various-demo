package com.peaceful.collection.demo.tree;

import com.peaceful.common.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 有序二叉树，小于父节点的插入到左边大于父节点的插入到后面，极端情况，比如插入一个有序的列表，二叉树将变为链表
 * <p/>
 * <p/>
 * Created by wangjun on 16/2/23.
 */
public class SortTreeDemo {

    Node root;

    public void insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        while (true) {
            // 往left 走
            if (data < current.data) {
                if (current.left == null) {
                    current.left = node;
                    return;
                } else {
                    current = current.left;
                }
            }
            // 往又走
            else {
                if (current.right == null) {
                    current.right = node;
                    return;
                } else {
                    current = current.right;
                }
            }
        }
    }

    public void find(int data) {
        Node current = root;
        List<Integer> path = new ArrayList<Integer>();
        if (root == null) {
            return;
        }
        while (true) {
            if (current == null) {
                Util.report(data + " NotFound...path is " + path);
                return;
            } else if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else if (current.data == data) {
                Util.report(data + " Find...path is " + path);
                return;
            } else {
                Util.report("What ");
            }
            if (current != null)
                path.add(current.data);
        }
    }

    //中序遍历方法
    public void inOrder(Node root) {
        List<Integer> r = new ArrayList<Integer>();
        if (root != null) {
            inOrder(root.left);//调用自身来遍历左子树
            System.out.print(root.data + "--");//访问这个节点
            inOrder(root.right);//调用自身来遍历右子树
        }
    }

    //先序遍历方法
    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            Util.report(localRoot.data);//访问这个节点
            preOrder(localRoot.left);//调用自身来遍历左子树
            preOrder(localRoot.right);//调用自身来遍历右子树
        }
    }

    public static void main(String[] args) {
        SortTreeDemo demo = new SortTreeDemo();
        for (int i = 0; i < 66; i++) {
            int v = (int) (Math.random() * 100);
            demo.insert(v);
        }
        demo.inOrder(demo.root);
        System.out.println();
        demo.find(66);
        demo.find(55);
        demo.find(11);
    }

}

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data) {
        this.data = data;
    }
}
