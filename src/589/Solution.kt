package `589`

import java.util.*

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

class Solution {

    fun preorder(root: Node?): List<Int> {
        val list = arrayListOf<Int>()
        if (root == null) {
            return list
        }
        val stack = Stack<Node>().apply {
            push(root)
        }
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            list.add(node.`val`)
            for (index in node.children.lastIndex downTo 0) {
                stack.push(node.children[index])
            }
        }
        return list
    }
}