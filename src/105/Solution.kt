package `105`

import utils.bean.TreeNode

/**
 * 前序遍历 preorder = 1 [2 5 4 6 7] [3 8 9]
 * 中序遍历 inorder = [5 2 6 4 7] 1 [8 3 9]
 * 发现规律 前序遍历根节点在起点，右边前面是其左子树，右边后面是其右子树，
 * 中序遍历根节点在中间前面是其左子树后面是其右子树。两种遍历顺序右子树
 * 都是在最后遍历的，于是可以根据前序遍历获取到根节点，然后结合中序遍历
 * 获取到前序遍历中左子树和右子树的分割点。
 * 前提是二叉树各个元素值都不同，与 654 题类似
 */
class Solution {

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return recursion(
                preorder, 0, preorder.lastIndex,
                inorder, 0, inorder.lastIndex)
    }

    private fun recursion(
            preorder: IntArray,
            preorderStartIndex: Int,
            preorderEndIndex: Int,
            inorder: IntArray,
            inorderStartIndex: Int,
            inorderEndIndex: Int): TreeNode? {
        if (preorderStartIndex > preorderEndIndex || inorderStartIndex > inorderEndIndex) {
            return null
        }
        val rootValue = preorder[preorderStartIndex]
        val rootIndex = findIndex(inorder, inorderStartIndex, inorderEndIndex, rootValue)
        val currentNode = TreeNode(rootValue)
        currentNode.left = recursion(
                preorder = preorder,
                preorderStartIndex = preorderStartIndex + 1,
                preorderEndIndex = preorderStartIndex + (rootIndex - inorderStartIndex),
                inorder = inorder,
                inorderStartIndex = inorderStartIndex,
                inorderEndIndex = rootIndex - 1
        )
        currentNode.right = recursion(
                preorder = preorder,
                preorderStartIndex = preorderStartIndex + (rootIndex - inorderStartIndex) + 1,
                preorderEndIndex = preorderEndIndex,
                inorder = inorder,
                inorderStartIndex = rootIndex + 1,
                inorderEndIndex = inorderEndIndex
        )
        return currentNode
    }

    /**
     * 从中序遍历中寻找根节点的索引值
     */
    private fun findIndex(array: IntArray, start: Int, end: Int, target: Int): Int {
        for (index in start..end) {
            if (array[index] == target) {
                return index
            }
        }
        return -1
    }
}