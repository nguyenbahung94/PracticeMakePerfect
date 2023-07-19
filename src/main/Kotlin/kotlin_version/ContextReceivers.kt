package kotlin_version

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.swing.text.View
// Context receivers were added in Kotlin 1.6.20 and do not work in earlier versions. What is more, to enable this experimental feature in that version, one needs to add the "-Xcontext-receivers" compiler argument.
interface Foo {
    fun foo() {
        print("Foo")
    }
}
interface Boo {
    fun boo() {
        println("Boo")
    }
}
context(Foo, Boo)
fun callFooBoo() {
    foo()
    boo()
}

context(Foo, Boo)
fun callFooBoo2() {
    callFooBoo()
}

class FooBoo : Foo, Boo {
    fun call() {
        callFooBoo()
    }
}

fun main() {
  /*  with(Foo()) {
        with(Boo()) {
            callFooBoo() // FooBoo
            callFooBoo2() // FooBoo
        }
    }
    with(Boo()) {
        with(Foo()) {
            callFooBoo() // FooBoo
            callFooBoo2() // FooBoo
        }
    }*/
    //////////////
    val fooBoo = FooBoo()
    fooBoo.call() // FooBoo
}



////////////////// before use
fun <T> Flow<T>.launchIn(scope: CoroutineScope): Job =
    scope.launch { collect() }

suspend fun main3(): Unit = coroutineScope {
    flowOf(1, 2, 3)
        .onEach { print(it) }
        .launchIn(this)
}
//////////////////////////// after use
context(CoroutineScope)
fun <T> Flow<T>.launchFlow(): Job =
    this@CoroutineScope.launch { collect() }

suspend fun main2(): Unit = coroutineScope {
    flowOf(1, 2, 3)
        .onEach { print(it) }
        .launchFlow()
}
///////////////////////////////
/*
context(View)
val Float.dp get() = this * resources.displayMetrics.density

context(View)
val Int.dp get() = this.toFloat().dp
*/
///////////////////////////
class ApplicationConfig(
    val name: String,
) {
    fun start() {
        print("Start application")
    }
}

context(ApplicationConfig)
class ApplicationControl(
    val applicationName: String = this@ApplicationConfig.name
) {
    fun start() {
        print("Using control: ")
        this@ApplicationConfig.start()
    }
}

fun main4() {
    with(ApplicationConfig("AppName")) {
        val control = ApplicationControl()
        println(control.applicationName) // AppName
        control.start() // Using control: Start application
    }
}


