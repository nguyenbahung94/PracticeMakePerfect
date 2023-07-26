package solve_problem

import java.util.Arrays
import java.util.SimpleTimeZone
import kotlin.math.abs

/*
* N-queens problem
*
* */


fun main() {
val resultPrintln = solveQueens(4)
    resultPrintln.forEach { item->
        println("$item")
    }
    println(resultPrintln.size)
}

val result = ArrayList<ArrayList<String>>()

fun solveQueens(n: Int): ArrayList<ArrayList<String>> {
    val board =Array(n){ CharArray(n) }
    for (i in 0..<n) {
        for (j in 0..<n) {
            board[i][j] = '.'
        }
    }
    val queens = ArrayList<IntArray>()
    dfs(board, 0, queens)
    return result
}

fun dfs(board: Array<CharArray>, r: Int, queens: ArrayList<IntArray>) {

    if (queens.size == board.size) {
        val rows = ArrayList<String>()
        for (item in board) {
           val stringResult =  item.joinToString(separator = ",")
            rows.add(stringResult)
        }
        result.add(rows)
    }

    for (c in board.indices) {
        if (canAddQueen(r, c, queens)) {
            board[r][c] = 'Q'
            queens.add(intArrayOf(r, c))
            dfs(board, r + 1, queens)
            board[r][c] = '.'
            queens.removeAt(queens.size - 1)
        }
    }
}

fun canAddQueen(r: Int, c: Int, queens: ArrayList<IntArray>):Boolean {
    for (item in queens) {
        val dx = abs(r - item[0])
        val dy = abs(c - item[1])

        if (dx == 0 || dy == 0 || dx == dy) return false
    }
    return true
}
