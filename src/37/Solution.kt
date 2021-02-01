package `37`

/**
 * 解数独
 * 对于每个位置，无非就是填入 1 - 9，每个都试过来就行了，不合法的剔除
 * 不同于全排列，全排列是以任意顺序从数组中取出元素，取出元素值是固定的（数组指定位置元素）
 * 而对于数独，其并非是任意顺序就是按 r * c 格子来取的，而每个格子的元素值也是不同的，可能为 1 - 9
 */
class Solution {

    fun solveSudoku(board: Array<CharArray>) {
        if (backtrack(board, 0, 0)) {
            println("成功解出答案")
        } else {
            println("失败此题无解")
        }
    }

    private fun backtrack(board: Array<CharArray>, row: Int, column: Int): Boolean {
        if (column == 9) {
            // 换行
            return backtrack(board, row + 1, 0)
        }
        if (row == 9) {
            // 结束
            return true
        }
        if (board[row][column] != '.') {
            // 已经有数字了忽略
            return backtrack(board, row, column + 1)
        }
        for (number in '1' .. '9') {
            if (isLegal(number, row, column, board)) {
                board[row][column] = number
                if (backtrack(board, row, column + 1)) {
                    return true
                }
                board[row][column] = '.'
            }
        }
        return false
    }

    /**
     * 判断 board[row][column] 填入 number 是否合法
     * 检测是还未填入
     */
    private fun isLegal(number: Char, row: Int, column: Int, board: Array<CharArray>): Boolean {
        for (i in 0 until 9) {
            // 行
            if (board[row][i] == number) {
                return false
            }
            // 列
            if (board[i][column] == number) {
                return false
            }
            // 九宫格
            // 行 0 ~ 2 => 0、0、0、1、1、1、2、2、2 ，3 ~ 5 => 3、3、3、4
            // 列 0 ~ 2 => 0、1、2、0、1、2、0、1、2 ，3 ~ 5 => 3、4、5、3
            if (board[row / 3 * 3 + i / 3][column / 3 * 3 + i % 3] == number) {
                return false
            }
        }
        return true
    }
}