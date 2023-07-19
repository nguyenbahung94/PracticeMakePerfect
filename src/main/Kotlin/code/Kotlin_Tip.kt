package code

import kotlinx.coroutines.delay

/*
//for with index
fun main() {

    val fruits = listOf<String>("Apple", "Banana", "Cherry", "Durian")
    for ((index, fruit) in fruits.withIndex()) {
         println("$index: $fruit")
     }
     fruits.forEachIndexed { index, fruit ->
         println("$index: $fruit")
     }
}
*/


/*
// deduplication collection
fun main() {

    val fruitBasket = listOf<String>("Apple", "Banana", "Cherry", "Apple", "Apple", "APPLE", "BANANA", "Durian")
    val unique = fruitBasket.distinct()
    println(unique)
    val set = fruitBasket.toSet()
    println(set)

}
*/

/*

//the suspend vs inline

suspend fun main() {
    repeat(5){
        println(getStringSlowly())
    }

    //this isn't work if we add inline before fun "printFiveTimes" it will work
    printFiveTimes {
        getStringSlowly()
    }
}

suspend fun getStringSlowly(): String {

    delay(100)

    return "My String!"
}

inline fun printFiveTimes(generator: () -> String) {
    repeat(5) {
        println(generator())
    }
}
*/

/*

data class Fruit(val name: String, val sugar: Int)

fun main() {

    val fruits = listOf<Fruit>(
        Fruit("banana", 12),
        Fruit("apple", 10),
        Fruit("orange", 9),
        Fruit("apple", 10),
        Fruit("peach", 8),
        Fruit("lemon", 2),
        Fruit("mango", 13),

        ).sortedBy { it.sugar }

    val (sweet, superSweet) = fruits.partition { it.sugar < 10 }
    println(sweet)
    println(superSweet)

    val joinString = fruits.reversed().joinToString(
        separator = " + ",
        prefix = "@ = [",
        postfix = "]",
        limit = 3,
        truncated = "MORE"
    ){it.name}
    println(joinString)

   */
/* the result of a fold operation can be anything
    Takes an initial value (start the accumulation with this value)
    Returns the initial value in case the collection was empty*//*

    val valueFold = fruits.fold(0) { acc, fruit -> acc + fruit.sugar }
    println(valueFold)

*/
/*  another example. reduce operation will always be of the same type
    Doesn't take an initial value (start the accumulation with the collection's first item)
    Throws an UnsupportedOperationException if the collection was empty*//*

    val numbers: List<Int> = listOf(1, 2, 3)
    val sum: Int = numbers.reduce { acc, next -> acc + next }
    println(sum)

}
*/












