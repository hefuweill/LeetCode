package `538`

import utils.bean.TreeNode

/**
 * 二叉搜索树，中序遍历为从小到大有序，而本题需要将每个节点
 * 的值变成该节点的值与大于该节点值累加的结果，正常中序遍历
 * 先遍历到的是小的，较难处理如果采用 右根左 遍历那么就是从
 * 大到小了就好处理
 */
class Solution {

    private var num = 0

    fun convertBST(root: TreeNode?): TreeNode? {
        recursion(root)
        return root
    }

    private fun recursion(root: TreeNode?) {
        if (root == null) {
            return
        }
        recursion(root.right)
        root.`val` += num
        num = root.`val`
        recursion(root.left)
    }
}