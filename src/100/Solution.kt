package `100`

import utils.bean.TreeNode

/**
 * 遍历一次如果都相同那么就是相同树，使用递归 DFS
 */
class Solution {

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return if (p != null && q != null) {
            p.`val` == q.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        } else p == null && q == null
    }
}

fun main() {
    println(Solution().isSameTree(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }, TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}