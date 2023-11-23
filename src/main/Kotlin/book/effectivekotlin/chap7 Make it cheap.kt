package book.effectivekotlin

/*Avoid unnecessary object creation
Access Requires An Additional Function Call When Elements are encapsulated.That is again a small cost as function
create a object empty and reuse it for case need to pass empty list or LinkedList: object Empty : LinkedList<Nothing>()
--Factory function with a cache
ex: save connections:
private val connections: MutableMap<String, Connection> = mutableMapOf<String, Connection>()
fun getConnection(host: String) = connections.getOrPut(host) { createConnection(host) }
performance for memory. Remember this, and use caches wisely. No-one wants to move from performance issues to lack of memory issues.
Don't
1 fun String.isValidIpAddress(): Boolean {
2 return this.matches("\\A(?:(?:25[0-5]|2[0-4][0-9]
3 |[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]
4 ?[0-9][0-9]?)\\z".toRegex())
5}
Do
private val IS_VALID_EMAIL_REGEX = "\\A(?:(?:25[0-5]
2 |2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4]
3 [0-9]|[01]?[0-9][0-9]?)\\z".toRegex()
4
5 fun String.isValidIpAddress(): Boolean =
6 matches(IS_VALID_EMAIL_REGEX)
we dont need recreate a matches object everytime we run this func
So in short:
Kotlin type
Int
Int? List<Int>
Java type
int
Integer List<Integer>
This is why I suggest this optimization only for performance critical parts of our code and in libraries.
You can find out what is performance critical using a profiler.
fun Iterable<Int>.maxOrNull(): Int?
{ val iterator = iterator()
if (!iterator.hasNext()) return null
var max: Int = iterator.next()
while (iterator.hasNext()) {
 val e = iterator.next()
 if (max < e) max = e
 } return max
 Use inline modifier for functions with parameters of functional types
 --advantages and costs of inline modifier
 A type argument can be reified
 any is List<Int> // Error
 any is List<*> // OK
 Dont
 fun <T> printTypeName() {
   print(T::class.simpleName) // ERROR
 }
 Do
 inline fun <reified T> printTypeName() {
   print(T::class.simpleName)
  }
  <reified T>: This declares a type parameter T and specifies the reified keyword.
   This means that the actual type of T will be available at runtime, and you can use it for reflection purposes,
   such as getting the class name.
 Functions with functional parameters are faster when they are inlined
 Costs of inline modifier
 Inline functions cannot be recursive.
 Inline functions cannot use elements with more restrictive visibility
 crossinline - it means that the function should be inlined but non-local return is not allowed.
 We use it when this function is used in another scope where non-local return is not allowed, for instance in another lambda that is not inlined.
 noinline - it means that this argument should not be inlined at all. It is used mainly when we use this function as an argument to another function that is not inlined.
--Consider using inline classes
 but also objects holding a single value can be replaced with this value
 inline class Name(private val value: String) {
    // code here
 }
 // Code
val name: Name = Name("Marcin")
// During compilation replaced with code similar to:
val name: String = "Marcin"
ex:
inline class Name(private val value: String) {
  fun greet() {
   print("Hello, I am $value")
  }
}
code
val name: Name = Name("Marcin")
name.greet()
// During compilation replaced with code similar to:
14 val name: String = "Marcin"
15 Name.`greet-impl`(name)

--Typealias
Kotlin typealias lets us create another name for a type:



* */