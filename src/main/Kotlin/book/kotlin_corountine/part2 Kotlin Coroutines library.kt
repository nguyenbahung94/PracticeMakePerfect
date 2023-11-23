package book.kotlin_corountine

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

/*launch builder
The way launch works is conceptually similar to starting a new thread (thread function). We just start a coroutine,
and it will run independently, like a firework that is launched into the air. This is how we use launch - to start a process.
runBlocking is a very atypical builder. It blocks the thread it has been started on whenever its coroutine is suspended20 (similar to suspending main).
This means that delay(1000L) inside runBlocking will behave like Thread.sleep(1000L)
we often use its successor runTest instead, which makes coroutines operate in virtual time (a very useful feature for testing which we will describe in the Testing coroutines chapter)
How the async builder works is very similar to launch, but it has additional support for returning a value. If all launch functions were replaced with async, the code would still work fine.
But don’t do that! async is about producing a value, so if we don’t need a value, we should use launch.
--Structured Concurrency
A parent provides a scope for its children, and they are called in this scope.
This builds a relationship that is called a structured concurrency
 runBlocking is not an extension function on CoroutineScope. This means that it cannot be a child
--CoroutineContext interface
CoroutineContext is an interface that represents an element or a collection of elements. It is conceptually similar to a map or a set collection:
 it is an indexed set of Element instances like Job, CoroutineName, CoroutineDispatcher, etc. The unusual thing is that each Element is also a CoroutineContext.
 Every element in it has a unique Key that is used to identify it
 So, every element in a collection is a collection in itself.
fun main() {
val name: CoroutineName = CoroutineName("A name")
val element: CoroutineContext.Element = name
val context: CoroutineContext = element
val job: Job = Job()
val jobElement: CoroutineContext.Element = job
val jobContext: CoroutineContext = jobElement
}
-- adding contexts
fun main() {
val ctx1: CoroutineContext = CoroutineName("Name1")
println(ctx1[CoroutineName]?.name) // Name1
println(ctx1[Job]?.isActive) // null

val ctx2: CoroutineContext = Job()
println(ctx2[CoroutineName]?.name) // null
println(ctx2[Job]?.isActive) // true, because "Active" // is the default state of a job created this way
val ctx3 = ctx1 + ctx2
println(ctx3[CoroutineName]?.name) // Name1
println(ctx3[Job]?.isActive) // true
When another element with the same key is added, just like in a map, the new element replaces the previous one.
-------------------------
fun main() = runBlocking(CoroutineName("main")) {
 log("Started") // [main] Started
val v1 = async(CoroutineName("c1")) {
        delay(500)
        log("Running async") // [c1] Running async
        42
    }
    launch(CoroutineName("c2")) {
          delay(1000)
       log("Running launch") // [c2] Running launch
    }
log("The answer is ${v1.await()}")
    // [main] The answer is 42
}
A simplified formula to calculate a coroutine context is:
defaultContext + parentContext + childContext
Since new elements always replace old ones with the same key, the child context always overrides elements with the same key from the parent context.
The defaults are used only for keys that are not specified anywhere else.
-----------------------------------
Jobs and awaiting children
n the Structured Concurrency chapter, we mentioned the following consequences of the parent-child relationship:
• children inherit context from their parent;
• a parent suspends until all the children are finished;
• when the parent is cancelled, its child coroutines are also
cancelled;
• when a child is destroyed,it also destroys the parent.
---------Coroutine builders create their jobs based on their parent job
The type returned by the async function is Deferred<T>, and Deferred<T> also implements the Job interface, so it can also be used in the same way.
There is a very important rule: Job is the only coroutine context that is not inherited by a coroutine from a coroutine.
Every coroutine creates its own Job, and the job from an argument or parent coroutine is used as a parent of this new job.
-------------------Cancellation, suspendCancellableCoroutine
It behaves like suspendCoroutine, but its continuation is wrapped into CancellableContinuation<T>, which provides some additional methods.
 The most important one is invokeOnCancellation, which we use to define what should happen when a coroutine is cancelled.
/* Don't do this, this code not catch exceptions, it's still throwing Exception in thread "main" java.lang.Error: Some error
fun main(): Unit = runBlocking {
// Don't wrap in a try-catch here. It will be ignored.
    try {
    launch {
        delay(1000)
        throw Error("Some error") }
  } catch (e: Throwable) { // nope, does not help here println("Will not be printed")
}
    launch {
        delay(2000)
        println("Will not be printed")
    }
}*/
Don't you like this : withContext(SupervisorJob())
should use SupervisorJob with handle to handle exceptions
 */
/*fun main(): Unit = runBlocking {
    val handler = CoroutineExceptionHandler { ctx, exception -> println("Caught $exception")
    }
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        delay(1000)
        throw Error("Some error") }
    scope.launch {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}
// Caught java.lang.Error: Some error
// Will be printed

GlobalScope is just a scope with EmptyCoroutineContext.
// DON'T DO THAT
suspend fun getUserProfile( scope: CoroutineScope
): UserProfileData {
val user = scope.async { getUserData() }
val notifications = scope.async { getNotifications() }
return UserProfileData(
user = user.await(), // (1 sec)
notifications = notifications.await(),
)
 }
if there is an exception in one async, the whole scope will be shut down (assuming it is using Job, not SupervisorJob)
checkout image part2 kotlin builder and more .png
---There are a few important observations you can make:
 When we are just suspending, it doesn’t really matter how many threads we are using.
 When we are blocking, the more threads we are using, the faster all these coroutines will be finished.
 With CPU-intensive operations, Dispatchers.Default is the best option.
 If we are dealing with a memory-intensive problem, more threads might provide some (but not a significant) improvement.
-----CoroutineExceptionHandler
example to how to use it
abstract class BaseViewModel(
private val onError: (Throwable) -> Unit
) : ViewModel() {
private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
           onError(throwable)
       }
private val context = Dispatchers.Main + SupervisorJob() + exceptionHandler
  protected val scope = CoroutineScope(context)
   override fun onCleared() {
    context.cancelChildren()
  }
}
---- useful cases :
val analyticsScope = CoroutineScope(SupervisorJob())
All their exceptions will only be shown in logs; so, if you want to send
them to a monitoring system, use CoroutineExceptionHandler.
Private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
       FirebaseCrashlytics.getInstance()
           .recordException(throwable)
}
val analyticsScope = CoroutineScope( SupervisorJob() + exceptionHandler)
---- atomics
In Kotlin, atomics refers to a set of classes and functions provided by the kotlinx.atomicfu library.
These classes and functions are used for performing atomic operations on shared mutable state,
ensuring thread-safety and preventing data races in concurrent programming scenarios.
The atomics library is useful in scenarios where you need to safely modify shared mutable state in a concurrent environment.
It helps prevent data races and ensures that the state is modified atomically and consistently across multiple threads.
notice: just knowing a single operation will be atomic does not help us when we have a bundle of operations.
-----Mutex
suspend fun main() = coroutineScope {
    repeat(5) {
    launch {
        delayAndPrint()
    } }
}
val mutex = Mutex()
suspend fun delayAndPrint() {
    mutex.lock()
    println("Lock")
    delay(1000)
    println("Done")
    mutex.unlock()
    println("unlock")

}
result
Lock
Done
unlock
Lock
Done
unlock
Lock
Done
unlock
Lock
Done
unlock
Lock
Done
unlock
it executed consequence one by one after the first finished then the second will be executed
The important advantage of mutex over a synchronized block is that we suspend a coroutine instead of blocking a thread.This is a safer and lighter approach
The second problem with mutex is that it is not unlocked when a coroutine is suspended.
-----Semaphore : Regarding Semaphore, we speak of permits, so it has functions acquire, release and withPermit.
Use atomic values when:
You have a simple shared mutable state that requires atomic operations.
You need high performance and low overhead for simple atomic operations.
You want to avoid the potential contention and blocking associated with mutexes.
Use a mutex when:
You have complex shared mutable state that requires more than simple atomic operations.
You need to ensure exclusive access to a shared resource to avoid data races.
You need to perform operations that involve multiple steps and require synchronization.
*/





