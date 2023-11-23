package book.effectivekotlin

import java.awt.Color

/*
chap 1
-Prefer an immutable property over a mutable one.
-Prefer val over var
-If you need them to change, consider making them immutable
data classes, and using copy.
-When you hold state, prefer read-only over mutable collections.
-we should prefer to define variables for the closest possible scope. Also, we prefer val over var also for local variables
-The general rule is that if we are not sure about the type, we should specify it. It is important information  we should not hide it
-The require function is used when we specify requirements on arguments.
-The check function works similarly to require,
but it throws an IllegalStateException when the stated expectation is not met. It checks if a state is correct.
* */

data class Point(val x: Int, val y: Int)

fun main() {
    val startPoint = Point(0, 0)
    val endPoint = startPoint.copy(x = 5, y = 10) // Create a new Point with modified values
    println("Start Point: $startPoint")
    println("End Point: $endPoint")
    updateWeather(20)
}


// Better code
 fun updateWeather(degrees: Int) {
     val (description, color) = when {
         degrees < 5 -> "cold" to Color.BLUE
         degrees < 23 -> "mild" to Color.YELLOW
         else -> "hot" to Color.RED
         }
     // ...
    println(description)
    println(color)
 }

/*
-Specify your expectations to:
• Make them more visible.
• Protect your application stability. • Protect your code correctness.
• Smart cast variables.
Four main mechanisms we use for that are:
• require block - a universal way to specify expectations on arguments.
• check block - a universal way to specify expectations on the state.
• assert block - a universal way to test in testing mode if something is true.
• Elvis operator with return or throw.
---------
Prefer standard errors to custom ones
---------
we should prefer returning null or Failure when an error is expected, and throwing an exception when an error is not expected
classJsonParsingException:Exception()
* */




