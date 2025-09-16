package solve_problem

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("last return = ${longestPalindrome("babad")}")
    println("last return2 = ${longestPalindrome("cbbd")}")
    main2()
}
fun main2() = runBlocking {
    launch(Dispatchers.Unconfined) {
        println("Before delay: ${Thread.currentThread().name}")
        delay(100)
        println("After delay: ${Thread.currentThread().name}")
    }
}

fun longestPalindrome(s: String): String {
}