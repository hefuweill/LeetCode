package `337`

import bean.TreeNode
import java.lang.Integer.max

/**
 * 打家劫舍
 * 规则是父节点如果偷了，那么子节点就不能偷
 * 如果当前根节点可以偷：
 * 如果偷了该节点，那么该树最大值为 f(root.left, false) + f(root.right, false) + root.val
 * 如果没有偷该节点，那么该树最大值为 f(root.left, true) + f(root.right, true)
 * 如果当前根节点不能偷：
 * 那么该树最大值为 max(f(root.left, true), f(root.right, true))
 * 不过需要考虑重用，不然效率过低
 * 执行用时：1276 ms, 在所有 Kotlin 提交中击败了 21.43% 的用户
 * 内存消耗：36.5 MB, 在所有 Kotlin 提交中击败了 42.86% 的用户
 */
class Solution {

    private val map1 = mutableMapOf<TreeNode?, Int>()
    private val map2 = mutableMapOf<TreeNode?, Int>()

    fun rob(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        return recursion(root, 0, true)
    }

    private fun recursion(root: TreeNode?, currentMoney: Int, canRob: Boolean): Int {
        if (root == null) {
            return 0
        }
        val m1 = recursion(root.left, currentMoney, true)
        val m2 = recursion(root.right, currentMoney, true)
        return if (canRob) {
            val m3 = recursion(root.left, currentMoney, false)
            val m4 = recursion(root.right, currentMoney, false)
            max((m3 + m4 + root.`val`), (m1 + m2))
        } else {
            m1 + m2
        }
    }
}

fun main() {
    val root = TreeNode(4).apply {
        left = TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(3)
        }
    }
    println(Solution().rob(root))
}