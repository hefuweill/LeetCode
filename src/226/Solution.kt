package `226`

import utils.bean.TreeNode

/**
 * 左右翻转二叉树，总结规律就是每个节点的左右节点互换
 */
class Solution {

    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        val left = root.left
        val right = root.right
        root.left = right
        root.right = left
        invertTree(left)
        invertTree(right)
        return root
    }
}

fun main() {
    println(Solution().invertTree(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}