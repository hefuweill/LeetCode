package `20`

import java.util.*

class Solution {

    private val map = mutableMapOf('(' to ')', '[' to ']', '{' to '}')

    fun isValid(string: String): Boolean {
        if (string.length and 0x1 != 0) {
            return false
        }
        val stack = Stack<Char>()
        for (char in string) {
            if (map.containsKey(char)) {
                stack.push(char)
            } else if (stack.isEmpty() || map[stack.peek()] != char) {
                return false
            } else {
                stack.pop()
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    println(Solution().isValid("()"))
}