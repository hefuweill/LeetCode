package `2`

import utils.bean.ListNode

class Solution {

    fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode? {
        var node1: ListNode? = l1
        var node2: ListNode? = l2
        var currentNode: ListNode? = null
        var resultNode: ListNode? = null
        var needCarry = false
        while (node1 != null || node2 != null) {
            val value1 = node1?.`val` ?: 0
            val value2 = node2?.`val` ?: 0
            var result = value1 + value2 + if (needCarry) {
                1
            } else {
                0
            }
            if (result > 9) {
                needCarry = true
                result -= 10
            } else {
                needCarry = false
            }
            if (currentNode == null) {
                currentNode = ListNode(result)
                resultNode = currentNode
            } else {
                currentNode.next = ListNode(result)
                currentNode = currentNode.next
            }
            if (node1 != null) {
                node1 = node1.next
            }
            if (node2 != null) {
                node2 = node2.next
            }
        }
        if (needCarry) {
            currentNode!!.next = ListNode(1)
        }
        return resultNode
    }
}