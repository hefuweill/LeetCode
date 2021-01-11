package `144`

import bean.TreeNode
import java.util.*

/**
 * 前序遍历 中左右
 */
class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
//        return iteration(root)
        return recursion(root)
    }

    private fun iteration(root: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        if (root == null) {
            return resultList
        }
        val stack = Stack<TreeNode>()
        stack.push(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            resultList.add(node.`val`)
            if (node.right != null) {
                stack.push(node.right)
            }
            if (node.left != null) {
                stack.push(node.left)
            }
        }
        return resultList
    }

    private fun recursion(node: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        realRecursion(node, resultList)
        return resultList
    }

    private fun realRecursion(node: TreeNode?, resultList: MutableList<Int>) {
        if (node == null) {
            return
        }
        resultList.add(node.`val`)
        realRecursion(node.left, resultList)
        realRecursion(node.right, resultList)
    }
}

fun main() {
    println(Solution().preorderTraversal(TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }))
}