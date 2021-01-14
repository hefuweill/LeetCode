package `112`

import bean.TreeNode

/**
 * 判断是否存在从根节点到叶子节点总和等于 sum 的路径
 * 如果可以枚举出所有的路径，那么题目也就解开了
 */
class Solution {

    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        return recursion(root, 0, sum)
    }

    private fun recursion(root: TreeNode?, preSum: Int, sum: Int): Boolean {
        if (root == null) {
            return false
        }
        val nextSum = preSum + root.`val`
        return if (root.left == null && root.right == null) {
            nextSum == sum
        } else {
            recursion(root.left, nextSum, sum) || recursion(root.right, nextSum, sum)
        }
    }
}