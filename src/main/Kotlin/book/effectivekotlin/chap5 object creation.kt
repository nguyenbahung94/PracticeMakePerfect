package book.effectivekotlin

/*Consider factory functions instead of constructors
Unlike constructors, functions have names.
Unlike constructors,functions can return an object of any subtype of their return type
Unlike constructors, functions are not required to create a new object each time they’re invoked.
Factory functions can provide objects that might not yet Object creation
When we define a factory function out side of an object, we can control its visibility exist
Factory functions can be inline and so their type parame- ters can be reified.
inline fun <reified T> createObject(): T {
    return T::class.java.newInstance()
}
example: val person = createObject<Person>() // Create an instance of Person
val car = createObject<Car>()
Kotlin factory functions:
1. Companion object factory function 2. Extension factory function
3. Top-level factory functions
4. Fake constructors
5. Methods on a factory classes
--
Companion Object Factory Function
class MyLinkedList<T>( val head: T,val tail: MyLinkedList<T>?){
companion object {
fun <T> of(vararg elements: T): MyLinkedList<T>? {
        // do something
  }
 }

//Usage
val list=MyLinkedList.of(1,2)
--

* */