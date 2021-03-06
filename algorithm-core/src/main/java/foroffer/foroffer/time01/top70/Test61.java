package foroffer.foroffer.time01.top70;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * description:
 *
 * @author liyazhou
 * @create 2017-06-19 8:37
 *
 * 题目：
 *      请实现一个函数按照之字型顺序打印二叉树，即第一行按照从左到右的顺序打印，
 *      第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *      例如：按之字形顺序打印下面的二叉树的结果为：
 *      1
 *      3  2
 *      4  5  6 7
 *      15 14 13 12 11 10 9 8
 *
 *               1
 *           /       \
 *         2          3
 *       /  \        /   \
 *      4    5      6     7
 *    / \   / \    / \   / \
 *   8  9  10 11  12 13 14 15
 *
 * 考查点：
 *      1. 层次遍历（广度优先遍历）不使用递归
 *      2. 栈的应用
 *
 * 思路：
 *      1. 使用两个栈协同工作
 *      2. 当遍历到奇数层时，先保存其右孩子到栈中，再保存其左孩子
 *         当遍历到偶数层时，先保存其左孩子到栈中，再保存其右孩子
 *         （打印后的元素立刻出栈，则刚进入到一个新的层时，其中的一个栈是空的，将新层中的元素的孩子保存到这个空栈中）
 *
 *
 */

class BinTreeNode61{
    int value;
    BinTreeNode61 left;
    BinTreeNode61 right;
    public BinTreeNode61 (int _value){ value = _value; }
    public void setChildren(BinTreeNode61 _left, BinTreeNode61 _right){
        left = _left;
        right = _right;
    }

}

public class Test61 {

    public void snakePrint(BinTreeNode61 root){
        if (root == null) return;

        LinkedList<BinTreeNode61> stack1 = new LinkedList<>();  // 保存奇数层的元素
        LinkedList<BinTreeNode61> stack2 = new LinkedList<>();  // 保存偶数层的元素
        // LinkedList[] stacks = new LinkedList[2]; 分析结合使用泛型和数组可能会带来的问题，为什么不能？
        int current = 1;  // 1 表示奇数层，0 表示偶数层
        // int next = 2;

        stack1.push(root);
        LinkedList<BinTreeNode61> currStack;
        LinkedList<BinTreeNode61> nextStack;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            currStack = current == 1 ? stack1 : stack2;  // 选取奇数层或者偶数层为当前层，以访问该层的元素
            nextStack = current == 1 ? stack2 : stack1;  // 选取奇数层或者偶数层为下一层，以存放当前层元素的孩子

            BinTreeNode61 node = currStack.pop();  // 访问当前层的元素，栈顶元素
            System.out.print(node.value + "\t");   // System.out.print(node.value + '\t');

            if (current == 1){  // 若当前层是奇数层，则先保存左孩子，再保存右孩子
                if (node.left != null) nextStack.push(node.left);
                if (node.right != null) nextStack.push(node.right);
            }
            else{  // 若当前层是偶数层，则先保存右孩子，再保存左孩子
                if (node.right != null) nextStack.push(node.right);
                if (node.left != null) nextStack.push(node.left);
            }

            // 当前层的元素被打印完毕，则换行，
            // 并将当前行设置为下一行，也即是若当前行是奇数层，则设置为偶层数，反之，设置为奇数层。
            if (currStack.isEmpty()){
                System.out.println();
                // int tmp = current;  current = next;  next = tmp;
                current = current == 0 ? 1 : 0;
            }
        }
    }


    @Test
    public void test(){
        BinTreeNode61 node1 = new BinTreeNode61(1);
        BinTreeNode61 node2 = new BinTreeNode61(2);
        BinTreeNode61 node3 = new BinTreeNode61(3);
        BinTreeNode61 node4 = new BinTreeNode61(4);
        BinTreeNode61 node5 = new BinTreeNode61(5);
        BinTreeNode61 node6 = new BinTreeNode61(6);
        BinTreeNode61 node7 = new BinTreeNode61(7);
        BinTreeNode61 node8 = new BinTreeNode61(8);
        BinTreeNode61 node9 = new BinTreeNode61(9);
        BinTreeNode61 node10 = new BinTreeNode61(10);
        BinTreeNode61 node11 = new BinTreeNode61(11);
        BinTreeNode61 node12 = new BinTreeNode61(12);
        BinTreeNode61 node13 = new BinTreeNode61(13);
        BinTreeNode61 node14 = new BinTreeNode61(14);
        BinTreeNode61 node15 = new BinTreeNode61(15);

        node1.setChildren(node2, node3);
        node2.setChildren(node4, node5);
        node3.setChildren(node6, node7);
        node4.setChildren(node8, node9);
        node5.setChildren(node10, node11);
        node6.setChildren(node12, node13);
        node7.setChildren(node14, node15);

        snakePrint(node1);
    }


    // 2017-8-20 08:39:53
    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lines = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) return lines;

        ArrayList<Integer> line = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);

        TreeNode currNode;
        int thisLevel = 1;
        int nextLevel = 0;

        int level = 1;

        LinkedList<Integer> stack = new LinkedList<Integer>();
        while (!queue.isEmpty()) {
            currNode = queue.poll();
            if (currNode.left != null) {
                queue.offer(currNode.left);
                nextLevel++;
            }
            if (currNode.right != null) {
                queue.offer(currNode.right);
                nextLevel++;
            }

            if (level % 2 == 1) line.add(currNode.val);
            else stack.push(currNode.val);

            thisLevel--;
            if (thisLevel == 0) {
                if (level % 2 == 0) {
                    while (!stack.isEmpty()) {
                        line.add(stack.pop());
                    }

                }
                lines.add(line);
                line = new ArrayList<Integer>();
                thisLevel = nextLevel;
                nextLevel = 0;
                level++;
            }
        }
        return lines;
    }
}

