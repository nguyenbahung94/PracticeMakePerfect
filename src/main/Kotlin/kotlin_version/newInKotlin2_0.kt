package kotlin_version

/*
* In Kotlin 2.0.0, the new Kotlin K2 compiler is used by default, faster Kotlin compiler
* */

class Cat {
    fun purr() {
        println("Purr purr")
    }
}

fun petAnimal(animal: Any) {
    val isCat = animal is Cat
    if (isCat) {
        // In Kotlin 2.0.0, the compiler can access
        // information about isCat, so it knows that
        // animal was smart-cast to the type Cat.
        // Therefore, the purr() function can be called.
        // In Kotlin 1.9.20, the compiler doesn't know
        // about the smart cast, so calling the purr()
        // function triggers an error.
        animal.purr()
    }
}
fun main() {
    //Smart cast improvements
   // val kitty = Cat()
   // petAnimal(kitty)
    ////////////////////////////////////////////////////////////////
    convertUnit()
}

fun convertUnit() {
    // Define conversion factors
    val kilometerToMeter = 1000.0
    val meterToAstronomicalUnit = 1.495978707e11

    // 1 kilometer in meters
    val km = 1.0
    val meters = km * kilometerToMeter

    // Convert meters to Astronomical Units (AU)
    val astronomicalUnits = meters / meterToAstronomicalUnit

    // Print the result
    println("$km kilometer is equal to $astronomicalUnits AU")
    println("1 kilometer ≈ ${"%.10f".format(astronomicalUnits)} AU")
    //test result
}



////////////////////////////////
// more notes
/*
Kotlin 2.0 Explicit Backing Fields (field)
before Kotlin 2.0.0
* class MyViewModel {
    private val _title = MutableStateFlow<String>("Placeholder")
    val title: StateFlow<String> get() = _title
}
after Kotlin 2.0.0
class MyViewModel {
    val title: StateFlow<String>
        field = MutableStateFlow<String>("Placeholder")
}
var count: Int = 0
    get() = field // Access the backing field using 'field'
    set(value) {
        field = value // Modify the backing field using 'field'
    }
///////////////////////////
Combination Of Operators And Numeric Conversions
fun foo(longs: MutableList<Long>) {
    longs[0] += 1 // This results in an error in Kotlin 1.x, but works normally in Kotlin 2.0
}
In Kotlin 2.0, this works normally because longs[0] += 1 is desugared into longs.set(0, longs.get(0).plus(1)).
no need using 1L

///////////////////////////
Combination of Nullable Operator Calls
class Box(val longs: MutableList<Long>)
fun foo(box: Box?) {
    box?.longs[0] += 1 // Gives an error in Kotlin 1.x
    box?.longs[0] += 1L // Also gives an error in Kotlin 1.x
}
In Kotlin 2.0, however, you can use box?.longs[0] += 1 without any problems
since it is desugared into box?.run { longs.set(0, longs.get(0).plus(1)) }.

///////////////////////////
More Smart Casts'
example in kotlin 2.0
class Card(val holderName: String?)

fun foo(card: Any): String {
    val hasHolderName = card is Card && !card.holderName.isNullOrBlank()
    return when {
        hasHolderName -> card.holderName
        else -> "Unknown"
    }
}
more examples
interface Action {
    fun execute()
}

interface None : Action
interface Send : Action
interface Receive : Action

fun demo(action: Any) {
    if (action is Send || action is Receive) {
        action.execute() // This won't work in Kotlin 1.x
    }
}

///////////////////////////
Smart Casts inside the Closures of Inline Lambdas
fun indexOfMax(numbers: IntArray): Int? {
    var maxIndex: Int? = null
    numbers.forEachIndexed { i, number ->
        // In Kotlin 2.0, we can remove the explicit !!
        if (maxIndex == null || numbers[maxIndex] <= number) {
            maxIndex = i
        }
    }
    return maxIndex
}

///////////////////////////
Guard Conditions in When Statements
before
fun SearchResultListItem(searchResult: SearchResult) {
    when {
        searchResult is SearchResult.Person && !searchResult.isBlocked -> { /* ... */ }
        searchResult is SearchResult.Post -> { /* ... */ }
        searchResult is SearchResult.Place -> { /* ... */ }
    }
}
after
fun SearchResultItem(searchResult: SearchResult) {
    when (searchResult) {
        is SearchResult.Person if !searchResult.isBlocked -> { /* ... */ }
        ...
    }
}
// Guarded Conditions (if inside when) : introduced in Kotlin 2.1 Beta

fun guardedConditions() {
    val numbers = listOf(2, 5, 8, 12, 15)

    for (x in numbers) {
        when (x) {
            in 1..10 if x % 2 == 0 -> println("$x is an even number between 1 and 10")
            else -> println("$x is not an even number between 1 and 10")
        }
    }
}
When guards are coming as beta in Kotlin 2.1.0

///////////////////////////
Context-sensitive resolution is coming in Kotlin 2.2 as an experimental feature.
Context-sensitive resolution allows us to make the code even more concise by removing the repeated SearchResult
fun SearchResultItem(searchResult: SearchResult) {
    when (searchResult) {
        is Person if !searchResult.isBlocked -> { /* ... */ }
        is Post -> { /* ... */ }
        is Place -> { /* ... */ }
    }
}

// #3. Context-Sensitive Resolution for Sealed Classes and Enums
sealed class Result {
    object Success : Result()
    data class Error(val message: String) : Result()
}

fun handleResult(result: Result) {
    when (result) {
        is Success -> println("Success!") // No need to specify Result.Success in Kotlin 2.1+
        is Error -> println("Error: ${result.message}") // Accessing data class property
    }
}

fun main() {
    val successResult = Result.Success
    val errorResult = Result.Error("Something went wrong")

    handleResult(successResult)
    handleResult(errorResult)
}
///////////////////////////
Name-Based Destructuring
///////////////////////////
Extensible Data Arguments
dataarg class LazyColumnSettings(
    val contentPadding: PaddingValues = PaddingValues(0.dp),
    val reverseLayout: Boolean = false,
    val verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    val horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    val flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    val userScrollEnabled: Boolean = true,
)
then use it like this
@Composable
fun LazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    dataarg settings: LazyColumnSettings,
    content: LazyListScope.() -> Unit
)
no need to pass each parameter just group it in one dataarg class
Extensible data arguments are coming in Kotlin 2.2 as an experimental feature.
more example
// #1. Extensible Data Arguments (data objects)
// Before (lots of parameters)
fun myFunction(param1: Int = 0, param2: String = "", param3: Boolean = false) {
    // ...
}

// After (using data object)
data object MyFunctionOptions {
    var param1: Int = 0
    var param2: String = ""
    var param3: Boolean = false
}

fun myFunction(options: MyFunctionOptions = MyFunctionOptions) {
    // Access options.param1, options.param2, etc.
}

fun MyCallerFunction(){
    // Calling the function
    myFunction(param1 = 10, param3 = true) // No need to create MyFunctionOptions object
}
///////////////////////////
Union Types for Errors
private error object NotFound

inline fun <T> Sequence<T>.last(predicate: (T) -> Boolean): T {
    var result: T | NotFound = NotFound // Union types for errors.
    for (element in this) {
        if (predicate(element)) result = element
    }
    if (result is NotFound) throw NoSuchElementException("Sequence contains no element matching the predicate.")
    return result // Automatic smart cast to T.
}
 */


