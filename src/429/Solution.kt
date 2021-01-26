package `429`

import java.util.*

class Node(var `val`: Int) {
    var children: List<Node> = listOf()
}

class Solution {
    fun levelOrder(root: Node?): List<List<Int>> {
        var q1 = LinkedList<Node>()
        var q2 = LinkedList<Node>()
        val result = arrayListOf<MutableList<Int>>()
        if (root == null) {
            return result
        }
        var level = 1
        q1.offer(root)
        while (q1.isNotEmpty()) {
            if (result.size < level) {
                result.add(arrayListOf())
            }
            val node = q1.poll()
            result[level - 1].add(node.`val`)
            for (child in node.children) {
                q2.offer(child)
            }
            if (q1.isEmpty()) {
                val temp = q1
                q1 = q2
                q2 = temp
                level++
            }
        }
        return result
    }
}