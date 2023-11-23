fun main(args: Array<String>) {
    foo()
}
fun foo() {
    run {
        listOf(1, 2, 3, 4, 5).forEach{
          //  if (it == 3) return // non-local return from the lambda passed to run
            println(it)
        }
        val temp = false
        temp.xor(true)
        println(temp.xor(true))
        println(temp.toString())
    }
    val listTemp = StringBuilder()
    listTemp.append('a')
    listTemp.append('b')
    listTemp.clear()
    listTemp.append('c')
    listTemp.append('d')
    println(listTemp.toString())
    println(" done with nested loop")
}