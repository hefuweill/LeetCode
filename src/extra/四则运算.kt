package extra

import java.util.*
import kotlin.test.assertEquals

class TreeNode(var value: Any) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    var flag: Boolean = false
}

fun main() {
    assertEquals(calculate("1 + 2 - ( 6 / 2 )"), 0)
    assertEquals(calculate("99 - 9 + 10 * 2 - 3"), 107)
    assertEquals(calculate("2 / 3"), 0)
    assertEquals(calculate("1 - 9"), -8)
    assertEquals(calculate("-1 / 2"), 0)

    assertEquals(calculate("3 * ( 2 + 4 * ( 1 - 2 ) / 2 ) + 1"), 1)
    assertEquals(calculate("4 / 2 + 1 + 2 * ( 3 - 2 * ( 1 + 3 ) )"), -7)
    assertEquals(calculate("9 + ( ( 10 - 2 ) * 3 + 3 * 3 ) * 4 + 10 / 2"), 146)
}

fun calculate(expression: String): Int {
    val characters = expression.split(" ")
    val treeNode = createBinaryTree(characters, 0, characters.lastIndex)
    val sequence = postOrder(treeNode, mutableListOf())
    val stack = Stack<Int>()
    for (any in sequence) {
        if (any is Int) {
            stack.push(any)
        } else {
            val op2 = stack.pop()
            val op1 = stack.pop()
            stack.push(when (any) {
                '+' -> op1 + op2
                '-' -> op1 - op2
                '*' -> op1 * op2
                '/' -> op1 / op2
                else -> {
                    throw RuntimeException()
                }
            })
        }
    }
    return stack.pop()
}

private fun createBinaryTree(characters: List<String>, start: Int, end: Int): TreeNode? {
    var rootNode: TreeNode? = null
    var currentIndex = start
    while (currentIndex <= end) {
        val character = characters[currentIndex]
        if (characters[currentIndex] == "(") {
            // 构建子树，然后拼接
            val endIndex = findCloseBrace(characters, start, end)
            val treeNode = createBinaryTree(characters, currentIndex + 1, endIndex - 1)
            if (rootNode == null) {
                rootNode = treeNode
            } else {
                findRightestNode(rootNode).right = treeNode
            }
            currentIndex = endIndex
        } else if (isOperator(character)) {
            if (rootNode == null) {
                throw IllegalArgumentException()
            }
            val node = TreeNode(character.toCharArray()[0])
            if (rootNode.value is Int || character == "+" || character == "-") {
                node.left = rootNode
                rootNode = node
            } else {
                var currentNode = rootNode
                var previewNode: TreeNode? = null
                while (currentNode != null) {
                    if (currentNode.flag || currentNode.value == '*' || currentNode.value == '/') {
                        // 前面是乘除，那我肯定在其后面计算，我需要插入
                        if (previewNode == null) {
                            node.left = rootNode
                            rootNode = node
                        } else {
                            node.left = previewNode.right
                            previewNode.right = node
                        }
                        break
                    } else if (currentNode.value is Int) {
                        node.left = previewNode!!.right
                        previewNode.right = node
                        break
                    }
                    previewNode = currentNode
                    currentNode = currentNode.right
                }
            }
        } else if (isNumber(character)) {
            if (rootNode == null) {
                rootNode = TreeNode(character.toInt())
            } else {
                findRightestNode(rootNode).right = TreeNode(character.toInt())
            }
        } else {
            // 遇到非法的右括号了，报错
            throw IllegalArgumentException()
        }
        currentIndex++
    }
    return rootNode.apply {
        this?.flag = true
    }
}

private fun postOrder(node: TreeNode?, list: MutableList<Any>): List<Any> {
    if (node == null) {
        return list
    }
    postOrder(node.left, list)
    postOrder(node.right, list)
    list.add(node.value)
    return list
}

private fun isOperator(character: String): Boolean {
    return character == "+" || character == "-" || character == "*" || character == "/"
}

private fun isNumber(character: String): Boolean {
    return character.toIntOrNull() != null
}

private fun findRightestNode(rootNode: TreeNode?): TreeNode {
    var currentNode = rootNode
    while (currentNode!!.right != null) {
        currentNode = currentNode.right
    }
    return currentNode
}

private fun findCloseBrace(characters: List<String>, start: Int, end: Int): Int {
    for (index in end downTo start) {
        if (characters[index] == ")") {
            return index
        }
    }
    return -1
}
