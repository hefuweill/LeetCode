package `104`

import bean.TreeNode
import kotlin.math.max

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