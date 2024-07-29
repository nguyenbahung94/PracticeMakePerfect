package data_structures_algorithm

fun main() {
    println("Factorial of 3 is ${factorial(3)}")
    println("Factorial of 3 is ${factorial(4)}")
    println("sumOfNumbers of 3 is ${sumOfNumbers(5)}")

    val arrayList = arrayListOf("apple", "banana", "cherry")

    // Replace element at index 1 with "grape" using set method
    arrayList[1] = "grape"

    // Alternatively, you can use the index assignment operator
    arrayList[2] = "orange"

    println(arrayList)  // O
}

fun factorial(n: Int): Int {
    //base case
    if (n == 1) return 1
    //recursive case
    return n * factorial(n - 1)
}

fun sumOfNumbers(n: Int): Int {
    //base case
    if (n <= 0) return 0
    //recursive case
    return n + sumOfNumbers(n - 1)
}

fun binarySearch(array: IntArray, target: Int, left: Int, right: Int): Int {
    //base case
    if (left > right) return -1
    //recursive case
    val mid = (left + right) / 2
    return when {
        array[mid] == target -> mid
        target < array[mid] -> binarySearch(array, target, left, mid - 1)
        else -> binarySearch(array, target, mid + 1, right)
    }
}