package data_structures_algorithm

import java.util.Stack
import kotlin.math.max

fun main() {
    // Longest substring without repeating characters
  //  mainLongestSubString()
  //  mainTwoSum()
  //  mainBestTimeToBuyAndSell()
  //  mainContainsDuplicate()
  //  reverseString()
    reverseWords()
}

fun reverseWords() {
    val sentence = "I'm the best developer in the world!"
    var result =""
    var previousSpace = 0
    var startFrom = 0
    for (i in 0 until sentence.length) {
        if (sentence[i] == ' ') {
            startFrom = i - 1
            while (startFrom >= previousSpace) {

                result += sentence[startFrom]

                startFrom--
            }
            result += " "
            previousSpace = i +1
        } else if (i == sentence.length -1) {
            startFrom = i
            while (startFrom >= previousSpace) {

                result += sentence[startFrom]

                startFrom--
            }
        }
    }
    println("reverseWords = $result")
}

fun reverseString() {
    val listString = mutableListOf<String>("a", "b", "c", "d", "e", "f")
    val stack = Stack<String>()
    for (i in 0 until listString.size) {
        stack.push(listString[i])
    }
    var i = 0
    while (!stack.isEmpty()) {
       listString[i] = stack.pop()
        i++
    }
    println("listString = $listString")
}

fun mainContainsDuplicate() {
    val result = containsDuplicate(arrayOf(1,2,5,7,8,9,10,11,12,2))
    val result2 = containsDuplicate(arrayOf(1,2,5,7,8,9,10,11,12,))
    println("contain duplicate1 = $result")
    println("contain duplicate2 = $result2")
}

fun containsDuplicate(numbers: Array<Int>): Boolean {

    val containsNumber = hashSetOf<Int>()

    for (i in 0 until numbers.size) {
        if (containsNumber.contains(numbers[i])) {
            return true
        } else {
            containsNumber.add(numbers[i])
        }
    }
    return false
}

fun mainBestTimeToBuyAndSell() {
   bestTimeToBuyAndSell(arrayOf(7,1,5,3,6,4))
}

fun bestTimeToBuyAndSell(prices: Array<Int>): Int {
    var left = 0 // buy
    var right = 0 // sell
    var bestProfit = 0
    while (right < prices.size) {
        if (prices[left] < prices[right]) {
            val price = prices[right] - prices[left]
            bestProfit = max(bestProfit, price)
        } else {
            left = right
        }
        right++
    }
    println("bestProfit = $bestProfit")
    return bestProfit
}

fun mainTwoSum() {
    twoSum(arrayOf(1, 2, 3,5,6,67,7,78), 7)
}

fun twoSum(numbers: Array<Int>, target: Int) {
    val complements = hashMapOf<Int, Int>()

    for (i in 0 until numbers.size) {
       val complementIndex = complements[numbers[i]]
        if (complementIndex != null) {
            println("index = $i vs $complementIndex")
        }
        complements.put(target - numbers[i], i)
    }
}

private fun mainLongestSubString() {
    // first solution
    longestSubString("abcdefabdew")

    // second solution faster
    longestSubString2("abcdefabdew")
}

fun longestSubString2(s: String): Int {
    var maxLength = 0
    var leftPoint = 0

    for (right in 0 until s.length) {
        val indexOfFirstAppearanceInSubString = s.indexOf(s[right], leftPoint)
        if (indexOfFirstAppearanceInSubString != right) {
            leftPoint =  indexOfFirstAppearanceInSubString + 1
        }
        maxLength = max(maxLength, right - leftPoint + 1 )
    }

    print("max length2: $maxLength")
    return maxLength
}

fun longestSubString(s: String): Int {
    var maxLength = 0
    var leftPoint = 0

    val visitedCharacters = hashMapOf<Char, Int>()

    for (right in 0 until s.length) {
        val currentCharacter = s[right]
        if (visitedCharacters.containsKey(currentCharacter) && visitedCharacters.get(currentCharacter)!! >= leftPoint) {
            leftPoint = visitedCharacters.get(currentCharacter)!!.plus(1)
        }

        maxLength = max(maxLength, right - leftPoint + 1)
        visitedCharacters.put(currentCharacter, right)
    }

    print("max length: $maxLength")
    return maxLength
}
