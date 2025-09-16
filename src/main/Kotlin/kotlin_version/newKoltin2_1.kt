package kotlin_version

fun main() {
    // Example of using continue in a nested loop
   // continueExample()
  //  example2()
    testBreakOuter()
}
fun continueExample() {
    val numbers = listOf(1, 2, 3, 4, 5)

    loop@ for (i in numbers) {
        println("Outer loop: $i")
        numbers.forEach {
            if (it == 2) {
                println("Continuing at $it")
                continue@loop  // Now this works! Continues the outer for loop
            }
            println("Inner loop: $it")
        }
    }
}

fun example2() {
    for (i in 1..3) {
        listOf(1, 2, 3).forEach { j ->
            if (j == 2) return@forEach  // skips to next iteration of forEach
            println("i = $i, j = $j")
        }
    }
}

fun testBreakOuter() {
    outer@ for (i in 1..3) {
        listOf(1, 2, 3).forEach lambda@{ j ->
            if (j == 2) {
                println("Breaking out of loop when j = $j")
                break@outer  // New feature in Kotlin 2.1!
            }
            println("i = $i, j = $j")
        }
    }
}

