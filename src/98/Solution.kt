package `98`

import utils.bean.TreeNode

/**
 * 直接中序遍历判断是否递增就能解决
 */
class Solution {

    /**
     * 递归
     * 作用：判断一颗二叉树是否二叉搜索树
     * 输入：该二叉树的根节点
     * 输出：是否是二叉搜索树
     * 必须左右子树是二叉搜索树，同时根节点必须位于左子树最大值和右子树最小值之间
     */
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        val isChildLegal = isValidBST(root.left) && isValidBST(root.right)
        if (isChildLegal) {
            return root.`val` in ((getMaxElement(root.left) + 1) until getMinElement(root.right))
        }
        return false
    }

    private fun getMaxElement(node: TreeNode?): Long {
        return if (node == null) {
            Long.MIN_VALUE
        } else {
            var rightNode = node
            while (rightNode!!.right != null) {
                rightNode = rightNode.right
            }
            rightNode.`val`.toLong()
        }
    }

    private fun getMinElement(node: TreeNode?): Long {
        return if (node == null) {
            Long.MAX_VALUE
        } else {
            var leftNode = node
            while (leftNode!!.left != null) {
                leftNode = leftNode.left
            }
            leftNode.`val`.toLong()
        }
    }
}

fun main() {
    val result = Solution().isValidBST(TreeNode(5).apply {
        left = TreeNode(1)
        right = TreeNode(4).apply {
            left = TreeNode(3)
            right = TreeNode(6)
        }
    })
//    val result = Solution().isValidBST(TreeNode(2).apply {
//        left = TreeNode(1)
//        right = TreeNode(3)
//    })
    println(result)
}