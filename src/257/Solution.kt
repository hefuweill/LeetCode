package `257`

import utils.bean.TreeNode

class Solution {
    fun binaryTreePaths(root: TreeNode?): List<String> {
        val result = arrayListOf<String>()
        if (root == null) {
            return result
        }
        return recursion(root)
    }

    private fun recursion(root: TreeNode?): List<String> {
        if (root == null) {
            return arrayListOf()
        }
        val leftPaths = recursion(root.left)
        val rightPaths = recursion(root.right)
        return mutableListOf<String>().apply {
            if (leftPaths.isEmpty() && rightPaths.isEmpty()) {
                add("${root.`val`}")
            } else {
                addAll(leftPaths)
                addAll(rightPaths)
                for (index in 0 ..lastIndex) {
                    set(index, "${root.`val`}->${get(index)}")
                }
            }
        }
    }
}