package book.kotlin_corountine

/*
* Asynchronous map
suspend fun <T, R> List<T>.mapAsync(
 transformation: suspend (T) -> R
): List<R> = coroutineScope {
this@mapAsync.map { async { transformation(it) } }.awaitAll()
}
*
 suspend fun <T, R> List<T>.mapAsync( concurrencyLimit: Int = Int.MAX_VALUE,
    transformation: suspend (T) -> R
 ): List<R> = coroutineScope {
 val semaphore = Semaphore(concurrencyLimit)
 this@mapAsync.map {
       async {
           semaphore.withPermit {
               transformation(it)
           }
       }
   }.awaitAll()

* suspendLazy
*
fun <T> suspendLazy( initializer: suspend () -> T
    ): suspend () -> T {
var initializer: (suspend () -> T)? = initializer
val mutex = Mutex()
var holder: Any? = Any()
return {
 if (initializer == null) holder as T
    else mutex.withLock {
            initializer?.let {
              holder = it()
              initializer = null }
              holder as T }
} }
}

*
* Reusing connections
* class LocationService( locationDao: LocationDao, scope: CoroutineScope){
    private val locations = locationDao.observeLocations()
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
        )
    fun observeLocations(): Flow<List<Location>> = locations
}
*
*
Recipe 5: Retrying a suspending process

* */