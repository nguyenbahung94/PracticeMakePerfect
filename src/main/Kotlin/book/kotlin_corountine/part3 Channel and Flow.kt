package book.kotlin_corountine

/*
* Channel is a powerful inter-coroutine communication primitive. It supports any number of senders and receivers,
* and every value that is sent to a channel is received once. We often create a channel using the produce builder.
* select is a useful function that lets us await the result of the first coroutine that completes,
* or send to or receive from the first from multiple channels. It is mainly used to implement different patterns for operating on channels, but it can also be used to implement async coroutine races.
*----Flow is synchronous
* Notice that Flow is synchronous by nature, just like suspending functions: the collect call is suspended until the flow is completed.
* This also means that a flow doesn’t start any new coroutines. Its concrete steps can do it, just like suspending functions can start coroutines, but this is not the default behavior for suspending functions.
*  Most flow processing steps are executed synchronously.
* Flow can be considered a bit more complicated than a suspending lambda expression with a receiver, and its processing functions just decorate it with new operations
* example use on start and complete
* fun updateNews() {
*           scope.launch { newsFlow()
            .onStart { showProgressBar() }
            .onCompletion { hideProgressBar() }
            .collect { view.showNews(it) }
} }
* Notice that using catch does not protect us from an exception in the terminal operation (because catch cannot be placed after the last operation).
* if there is an exception in the collect, it won’t be caught, and an error will be thrown.
* should use operator follow this example
* val flow = flow {
* emit("Message1")
* emit("Message2")
}
suspend fun main(): Unit {
* flow.onStart { println("Before") }
      .onEach { throw MyError() }
      .catch { println("Caught $it") }
      .collect()
}
*
// Before
// Caught MyError: My error
SharedFlow
MutableSharedFlow, which is like a broadcast channel: everyone can send (emit) messages which will be received by every coroutine that is listening (collecting).
* MutableSharedFlow is conceptually similar to RxJava Subjects.
* When the replay parameter is set to 0, it is similar to a PublishSubject.
* When replay is 1, it is similar to a BehaviorSubject.
* When replay is Int.MAX_VALUE, it is similar to ReplaySubject.
* shareIn
* the easiest way to turn a Flow into a SharedFlow is by using the shareIn function.
* example real case:
* class LocationService( locationDao: LocationDao, scope: CoroutineScope){
    private val locations = locationDao.observeLocations()
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
        )
        *
   fun observeLocations(): Flow<List<Location>> = locations

 }
* StateFlow is an extension of the SharedFlow concept. It works similarly to SharedFlow when the replay parameter is set to 1.
* It always stores one value, which can be accessed using the value property.
* stateIn
* stateIn is a function that transforms Flow<T> into StateFlow<T>, It can only be called with a scope, but it is a suspending function
* We typically use stateIn when we want to observe a value from one source of changes. On the way, these changes can be processed, and in the end they can be observed by our views.
* example callback Flow :
* fun EditText.listenTextChange(): Flow<String> = callbackFlow { val watcher = doAfterTextChanged {
        trySendBlocking(it.toString())
    }
    awaitClose { removeTextChangedListener(watcher) }
}
*
* */

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Create a simple flow of integers
    val flowOfNumbers = (1..5).asFlow()

    // Use shareIn to share the flow among multiple collectors
    val sharedFlow = flowOfNumbers
        .onEach { delay(100) } // Simulate some processing time between emissions
        .shareIn(this, SharingStarted.WhileSubscribed(replayExpirationMillis = 0))

    // Collect values from two different coroutines
    launch {
        sharedFlow.collect {
            println("Collector 1: $it")
        }
    }

    launch {
        sharedFlow.collect {
            println("Collector 2: $it")
        }

    }

    // Introduce a delay to allow both collectors to receive values
    delay(500)
}
