package solve_problem

fun main(): Unit {
    val calculator = Solution().lengthOfLongestSubstring("abcabcbb")
    println("result = $calculator")
    val calculator2 = Solution2().lengthOfLongestSubstring("abcabcbb")
    println("result2 = $calculator2")
    val calculator3 = Solution3().lengthOfLongestSubstring("abcabcbb")
    println("result2 = $calculator3")
}

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 0
        for (i in 0..<s.length) {
            var finalResult = ""
            for(j in i..<s.length) {
                if (finalResult.contains(s[j])) {
                    if (maxLength >= finalResult.length) {
                        break
                    } else {
                        maxLength = finalResult.length
                    }
                } else {
                    finalResult += s[j]
                }
            }
        }

        return maxLength
    }
}
//aabb
class Solution2 {
    fun lengthOfLongestSubstring(s: String): Int {
        val seen = mutableMapOf<Char, Int>()
        var left = 0
        var maxLength = 0
        for (right in s.indices) {
            val char = s[right]

            if (seen.contains(char) && seen[char]!! >= left) {
                left = seen[char]!! + 1
            }
            seen[char] = right
           maxLength = maxOf(maxLength, right - left + 1)
        }
        return maxLength
    }
}

class Solution3 {
    fun lengthOfLongestSubstring(s: String): Int {
        val seen = mutableSetOf<Char>()
        var left = 0
        var maxLength = 0

        for (right in s.indices) {
            while (s[right] in seen) {
                seen.remove(s[left])
                left++
            }
            seen.add(s[right])
            maxLength = maxOf(maxLength, right - left + 1)
        }
        return maxLength
    }
}

