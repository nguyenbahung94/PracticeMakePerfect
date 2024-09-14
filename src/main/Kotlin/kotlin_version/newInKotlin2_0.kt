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
    val kitty = Cat()
    petAnimal(kitty)
}


////////////////////////////////
// more notes
/*
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


