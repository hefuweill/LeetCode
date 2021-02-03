package `116`

import utils.bean.Node
import java.util.*

/**
 * 1. 层次遍历，每次遍历一层连接即可
 * 2. 从上到下递归，由于父节点的临近节点可以通过 root.next 获取，
 * 因此只需递归的连接 root.left、root.right、root.next.left 即可
 */
class Solution {

    fun connect(root: Node?): Node? {
        solution2(root)
        return root
    }

    private fun solution1(root: Node?): Node? {
        if (root == null) {
            return null
        }
        var fromQueue = LinkedList<Node>()
        var toQueue = LinkedList<Node>()
        fromQueue.add(root)
        while (fromQueue.isNotEmpty()) {
            val node = fromQueue.poll()
            if (node.left != null) {
                toQueue.offer(node.left)
            }
            if (node.right != null) {
                toQueue.offer(node.right)
            }
            if (fromQueue.isNotEmpty()) {
                node.next = fromQueue.peek()
            } else {
                val temp = fromQueue
                fromQueue = toQueue
                toQueue = temp
            }
        }
        return root
    }

    private fun solution2(root: Node?) {
        if (root?.left == null) {
            return
        }
        root.left?.next = root.right
        if (root.next != null) {
            root.right?.next = root.next?.left
        }
        solution2(root.left)
        solution2(root.right)
    }
}