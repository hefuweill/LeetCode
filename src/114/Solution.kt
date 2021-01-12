package `114`

import bean.TreeNode

/**
 * 二叉树原地展开为链表，按照前序遍历方式
 * 1. 前序遍历一遍，将结果保存到一个 List 中，然后构建链表即可
 * 2. 使用递归将左右子树分别展开，然后合并两条展开的子树即可
 * 递归的一个非常重要的点就是：不去管函数的内部细节是如何处理的，我们只看其函数作用以及输入与输出
 * 作用：展开二叉树
 * 输入：根节点
 * 输出：无
 */
class Solution {

    fun flatten(root: TreeNode?) {
        if (root == null) {
            return
        }
        flatten(root.left) // 左子树展开
        flatten(root.right) // 右子树展开
        // 两个子树都已经展开了，那么只需将右子树拼到左子数链表的最后一个就行
        val right = root.right
        root.right = root.left
        root.left = null
        var current: TreeNode = root
        while (current.right != null) {
            current = current.right!!
        }
        current.right = right
    }
}