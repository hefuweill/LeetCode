package `104`

import bean.TreeNode
import kotlin.math.max

/**
 * 最大深度，递归 DFS 解决
 * 如果要求最小深度，虽然也能使用递归 DFS 解决
 * 但是效率明显不行，这时候就得使用迭代（迭代） BFS 解决
 * BFS 空间复杂度较 DFS 高，一般也就用于寻找最短路、
 */
class Solution {

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)
        return max(leftDepth, rightDepth) + 1
    }
}