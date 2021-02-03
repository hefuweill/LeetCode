package `700`

import utils.bean.TreeNode
import java.util.*

/**
 * 二叉搜索树，搜索一个值
 * 1. 中序遍历一遍，速度过慢
 * 2. 利用根节点介于左子树和最大值和右子树之间的特性
 */
class Solution {

    fun searchBST(root: TreeNode?, target: Int): TreeNode? {
        return solution2(root, target)
    }

    private fun solution2(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) {
            return null
        }
        return when {
            target == root.`val` -> {
                root
            }
            target < root.`val` -> {
                solution2(root.left, target)
            }
            else -> {
                solution2(root.right, target)
            }
        }
    }

    private fun solution1(root: TreeNode?, target: Int): TreeNode? {
        val stack = Stack<TreeNode>()
        var current = root
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.push(current)
                current = current.left
            }
            val node = stack.pop()
            if (node.`val` == target) {
                return node
            } else if (node.`val` > target) {
                return null
            }
            current = node?.right
        }
        return null
    }
}

fun main() {
    println(Solution().searchBST(TreeNode(2).apply {
        left = TreeNode(1)
        right = TreeNode(3)
    }, 1))
}