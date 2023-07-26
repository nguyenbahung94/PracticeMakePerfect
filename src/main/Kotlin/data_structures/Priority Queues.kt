package data_structures

import java.util.Collections
import java.util.PriorityQueue

fun main(){
    val queue = PriorityQueue<Int>(Collections.reverseOrder())

    queue.add(1)
    queue.add(5)
    queue.add(3)
    queue.add(5)
    queue.add(6)
    queue.add(2)

    while (!queue.isEmpty()){
        println(queue.poll())
    }
}