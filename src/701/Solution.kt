package `701`

import bean.TreeNode

class Solution {

    fun insertIntoBST(root: TreeNode?, value: Int): TreeNode? {
        if (root == null) {
            return TreeNode(value)
        }
        if (value < root.`val`) {
            root.left = insertIntoBST(root.left, value)
        } else {
            root.right = insertIntoBST(root.right, value)
        }
        return root
    }
}
