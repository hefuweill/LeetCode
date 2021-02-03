package `652`

import utils.bean.TreeNode

/**
 * 后续遍历框架，牢记如果递归计算二叉树节点数量
 * 1,2,3,#,4,5,6
 */
class Solution {

    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        val resultMap = hashMapOf<String, TreeNode?>()
        val resultList = arrayListOf<TreeNode?>()
        recursion(root, resultMap)
        resultMap.values.forEach {
            if (it != null) {
                resultList.add(it)
            }
        }
        return resultList
    }

    /**
     * 用于给以 root 为节点的子树生成标志
     */
    private fun recursion(root: TreeNode?, map: HashMap<String, TreeNode?>): String {
        if (root == null) {
            return "#"
        }
        val left = recursion(root.left, map)
        val right = recursion(root.right, map)
        // 由于按根左右拼的所以最后就是前序遍历
        val identifier =  "${root.`val`},$left,$right"
        if (map.contains(identifier)) {
            map[identifier] = root
        } else {
            map[identifier] = null
        }
        return identifier
    }
}

fun main() {
    val result = Solution().findDuplicateSubtrees(TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
        }
        right = TreeNode(3).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
            }
            right = TreeNode(4)
        }
    })
    println(result)
}