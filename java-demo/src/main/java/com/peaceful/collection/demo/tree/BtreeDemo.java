package com.peaceful.collection.demo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 每个叶结点具有相同的深度。树的高度h 树的度为t
 * 每个结点的关键字  非根至少t-1个，至多2t-1 个,根至少1个
 * 非根非叶子结点最少t子女，最多2t个子女
 * 根至少2两个子女,最多2t个子女
 * Created by wangjun on 16/3/22.
 */
public class BtreeDemo {

    // m阶的Btree最多含有m个子节点,对于非根节点至少含有m/2~m-1个关键字,根节点至少含有m-1个节点
    public int m;
    // 节点的根
    public BTNode root;


    public void split(BTNode node) {
        if (node.keySize < m - 1) {
            return;
        } else {
            // 分裂
        }
    }

    public void merge(BTNode node) {
        if (node.keySize > m / 2) {
            return;
        } else {
            // 分裂
        }
    }

    public void add(Integer key) {
        if (root == null) {
            root = new BTNode();
            root.keySize = 1;
            root.father = null;
            root.keys.add(key);

        }
    }


    /**
     * Btree节点特点
     * <p>
     * 1、本结点所含关键字的个数；
     * 2、指向父结点的指针；
     * 3、关键字；
     * 4、指向子结点的指针；
     * <p>
     * 对于一棵m阶B-tree，每个结点至多可以拥有m个子结点。各结点的关键字和可以拥有的子结点数都有限制，规定m阶B-tree中，根结点至少有2个子
     * 结点，除非根结点为叶子节点，相应的，根结点中关键字的个数为1~m-1；非根结点至少有[m/2]（[]，向上取整）个子结点，相应的，关键字个数为[m/2]-1~m-1。
     */
    class BTNode {

        // 含有关键字的个数
        public int keySize;
        // 父节点指针
        public BTNode father;
        // 关键字 要进行升序排序 k0 < k1 <k2 <km-1
        public List<Integer> keys = new ArrayList<Integer>();
        // 指向子节点的指针 c0.XX < k0  < c2.xx < k2 ...km-1<cm.xx
        public LinkedList<BTNode> sons = new LinkedList<BTNode>();


    }


}

