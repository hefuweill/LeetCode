package `106`

import bean.TreeNode

/**
 * 观察下中序遍历和后续遍历，由于后续遍历最后一个节点是根节点，
 * 因此可以跟105 题一样用类似的解法解决。
 * [5 2 6 4 7] 1 [8 3 9]
 * [5 6 7 4 2] [8 9 3] 1
 */
class Solution {

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        return recursion(inorder, 0, inorder.lastIndex, postorder, 0, postorder.lastIndex)
    }

    private fun recursion(
            inorder: IntArray,
            inorderStartIndex: Int,
            inorderEndIndex: Int,
            postorder: IntArray,
            postorderStartIndex: Int,
            postorderEndIndex: Int
    ): TreeNode? {
        if (inorderStartIndex > inorderEndIndex || postorderStartIndex > postorderEndIndex) {
            return null
        }
        val rootValue = postorder[postorderEndIndex]
        val rootIndex = findIndex(inorder, inorderStartIndex, inorderEndIndex, rootValue)
        val currentNode = TreeNode(rootValue)
        currentNode.left = recursion(
                inorder = inorder,
                inorderStartIndex = inorderStartIndex,
                inorderEndIndex = rootIndex - 1,
                postorder = postorder,
                postorderStartIndex = postorderStartIndex,
                postorderEndIndex = postorderStartIndex + (rootIndex - inorderStartIndex - 1)
        )
        currentNode.right = recursion(
                inorder = inorder,
                inorderStartIndex = rootIndex + 1,
                inorderEndIndex = inorderEndIndex,
                postorder = postorder,
                postorderStartIndex = postorderStartIndex + (rootIndex - inorderStartIndex),
                postorderEndIndex = postorderEndIndex - 1
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