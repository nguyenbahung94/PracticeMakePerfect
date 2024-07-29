package data_structures_algorithm

fun main() {
        val stringWithColon = "hello:world"
        val stringWithoutColon = "hello world"

        val resultWithColon = stringWithColon.substringAfter(':')
        val resultWithoutColon = stringWithoutColon.substringAfter(':')

        println(resultWithColon) // Output: "world"
        println(resultWithoutColon) // Output: "hello world"

   // println(countingValleys(8, "DDUUUUDD"))
    // println(pageCount(6, 2))
    // println(sockMerchant(9, arrayOf(10, 20, 20, 10, 10, 30, 50, 10, 20)))
    // bonAppetit(arrayOf(3,10,2,9), 1, 12)
   // println(migratoryBirds(arrayOf(1,2,3,4,5,4,3,2,1,3,4)))
}

/*fun getMoneySpent(keyboards: Array<Int>, drives: Array<Int>, b: Int): Int {
    *//*
     * Write your code here.
     *//*

}*/

fun countingValleys(steps: Int, path: String): Int {
   var sum = 0
   var previous = 0
   var count  = 0

   for (i in path.indices) {
       if (path[i] == 'U') {
           sum++
       } else {
           sum--
       }
       if (sum == 0 && previous == -1) {
           count++
       }
       previous = sum
    }
    return count
}


fun pageCount(n: Int, p: Int): Int {
    // Write your code here
    val fromPage1 = 1
    if(p == fromPage1 || p == n){
        return 0
    } else if(n % 2 != 0 && n-1 == p) {
        return 0
    }else {
        val first = flipFromFirstPage(n, p)
        val second = flipFromLastPage(n, p)
        val result = minOf(first, second)
        return result
    }

}

fun flipFromFirstPage(n: Int, p: Int): Int {
    val temp1 = p%2
    return if (temp1 == 0){
        p/2
    } else {
        (p-1)/2
    }
}

fun flipFromLastPage(n: Int, p: Int): Int {
    // do flip from the first page
    return if (p%2==0){
        (n-p)/2
    }else{
        ( n- p + 1)/2
    }

}


fun sockMerchant(n: Int, ar: Array<Int>): Int {
    val sock = HashMap<Int,Int>()
    var count = 0
    for (i in ar) {
        val value = sock.getOrDefault(i, 0) + 1
        sock[i] = value
        if (value % 2 == 0) {
            count++
        }
    }
   return count
}

fun bonAppetit(bill: Array<Int>, k: Int, b: Int): Unit {
    val sum = bill.sum() - bill[k]
    val annaShare = sum /2
    if (annaShare == b) {
        println("Bon Appetit")
    } else {
        b - annaShare
    }

}



fun migratoryBirds(arr: Array<Int>): Int {
    val frequency = HashMap<Int, Int>()
    var maxCount = 0
    var maxValue = 0
    for (i in arr) {
        val count = frequency.getOrDefault(i, 0) + 1
        frequency[i] = count
        if (count > maxCount) {
            maxCount = count
            maxValue = i
        }
    }
    return maxValue
}