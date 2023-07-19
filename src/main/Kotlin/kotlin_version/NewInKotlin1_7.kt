package kotlin_version

import kotlin.time.TimeSource

// stable deep recursive function
/*
class Tree(val left: Tree?, val right:Tree?)

val calculateDepth = DeepRecursiveFunction<Tree?,Int>{t->
    if (t==null) 0 else maxOf(
        callRecursive(t.left),
        callRecursive(t.right)
    )+1
}

fun main(){
    // Generate a tree with a depth of 100_000
    val deepTree = generateSequence(Tree(null, null)){prev->
        Tree(prev,null)
    }.take(1000).last()
    println(calculateDepth(deepTree))
}*/


// Returned 'TimeMark' is inline class
/*
fun main(){

    val mark = TimeSource.Monotonic.markNow()
    val elapsedDuration = mark.elapsedNow()
    println(elapsedDuration)

}*/

// A new approach to incremental compilation
/*
gradle.properties:
kotlin.incremental.useClasspathSnapshot=true
*/

//build reports for kotlin compiler tasks
/*
gradle.properties:
kotlin.build.report.output = file
*/

// named capturing groups in regular expression for JS and Native

fun main(){
    val regex = "\\b(?<corrdinate>[A-Za-z\\s]+):\\s(?<city>[A-Za-z\\s]+),\\s(?<state>[A-Z]+):\\s(?<areaCode>[0-9]{3})\\b".toRegex()
    val input = "Coordinates: Austin, TX: 123"
    val match  = regex.find(input)
    println(match?.groups?.get("corrdinate")?.value)
    println(match?.groups?.get("city")?.value)
    println(match?.groups?.get("state")?.value)
    println(match?.groups?.get("areaCode")?.value)

    //////////////////
    val regex2 ="(?<title>\\w+), yes \\k<title>".toRegex()
    val match2 = regex2.find("Do you copy? Sir, yes Sir!")
    println(match2?.value)
    println(match2?.groups?.get("title")?.value)
    ///////////////
    val dateRegex = Regex("(?<dd>\\d{2})-(?<mm>\\d{2})-(?<yyyy>\\d{4})")
    val inputDate = "Date of birth: 27-04-2022"
    println(dateRegex.replace(inputDate,"\${yyyy}-\${mm}-\${dd}"))
    println(dateRegex.replace(inputDate,"\$3-\$2-\$1"))
}
