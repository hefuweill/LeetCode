package `230`

import utils.bean.TreeNode

/**
 * 二叉搜索树定义了对于二叉树的每个结点其左子树所有元素小于它右子树所有元素大于它
 * 因此其中序遍历得到的结果是有序的
 */
class Solution {

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val list = arrayListOf<Int>()
        recursion(root, list)
        return list[k - 1]
    }

    private fun recursion(node: TreeNode?, list: MutableList<Int>) {
        if (node == null) {
            return
        }
        recursion(node.left, list)
        list.add(node.`val`)
        recursion(node.right, list)
    }
}