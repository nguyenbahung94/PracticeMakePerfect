package solve_problem

fun main() {
   val num1 = intArrayOf(1,3)
   val num2 = intArrayOf(4,5)
    println("result ${findMedianSortedArrays(num1, num2)}")
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val merge = (nums1 + nums2).sorted()
    val n = merge.size
    return if (n % 2 == 1) {
        merge[n / 2].toDouble()
    } else {
        ((merge[n / 2 - 1] + merge[n / 2]) / 2.0)
    }
}
