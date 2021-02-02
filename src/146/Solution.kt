package `146`

/**
 * O(1) 时间复杂度完成 LruCache
 * 解决方案为学习 LinkedHashMap 使用 Hash 表加双向链表
 * LinkedHashMap 每个节点包含 before、next、after 三个指针
 * 最近最少使用的是越靠近 tail，新节点加入链表首部
 * 注意：虚拟头尾节点，并且做成环形链表，避免判断相邻节点是否存在
 */
class LRUCache(private val capacity: Int) {

    private val map = hashMapOf<Int, Node>()
    private var head: Node = Node()
    private var tail: Node = Node()

    init {
        require(capacity > 0)
        head.after = tail
        tail.before = head
    }

    fun get(key: Int): Int {
        val preNode = map[key] ?: return -1
        moveToHead(preNode)
        return preNode.value
    }

    fun put(key: Int, value: Int) {
        val preNode = map[key]
        if (preNode != null) {
            preNode.value = value
            moveToHead(preNode)
        } else {
            Node(key, value).apply {
                map[key] = this
                addToHead(this)
            }
            if (map.size > capacity) {
                val tempNode = tail.before!!
                removeNode(tempNode)
                map.remove(tempNode.key)
            }
        }
    }

    private fun removeNode(node: Node) {
        node.before?.after = node.after
        node.after?.before = node.before
    }

    private fun addToHead(node: Node) {
        node.after = head.after
        node.after?.before = node
        head.after = node
        node.before = head
    }

    private fun moveToHead(node: Node) {
        removeNode(node)
        addToHead(node)
    }

    override fun toString(): String {
        return map.toString()
    }

    data class Node(var key: Int = 0, var value: Int = 0) {
        var before: Node? = null
        var after: Node? = null
    }

}

fun main() {
    val cache = LRUCache(2)
    cache.put(1, 1)
    println(cache)
    cache.put(2, 2)
    println(cache)
    println("get" + cache.get(1))
    cache.put(3, 3)
    println(cache)
}