package `21`

import utils.bean.ListNode

class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) {
            return null
        }
        val result: ListNode = ListNode(-1)
        var node1 = l1
        var node2 = l2
        var resultCur: ListNode = result
        while (node1 != null || node2 != null) {
            if (node1 == null) {
                resultCur.next = node2
                break
            } else if (node2 == null) {
                resultCur.next = node1
                break
            }
            if (node1.`val` < node2.`val`) {
                resultCur.next = node1
                resultCur = node1
                node1 = node1.next
            } else {
                resultCur.next = node2
                resultCur = node2
                node2 = node2.next
            }
        }
        return result.next
    }
}