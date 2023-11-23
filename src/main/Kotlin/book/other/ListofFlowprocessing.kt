package book.other

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    flowOf(1, 2, 3)
        .flatMapConcat { value ->
            flow {
                emit(value * 2)
                delay(100)
                emit(value * 3)
            }
        }
        .collect { println(it) }

    val list = listOf(1, 2, 3, 4)
    val res = list.scan(1) { acc, i -> acc + i }
    println(res) // [0, 1, 3, 6, 10]
}