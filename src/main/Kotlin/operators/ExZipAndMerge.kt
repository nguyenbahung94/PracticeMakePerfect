package operators

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun main() {

    //The merge operator combines multiple observables into one by merging their emissions. It emits items from both (or all) sources as they arrive, without any specific order.
    //
    //Characteristics of merge:
    //Concurrent Emissions: Emits items from all sources concurrently as they are emitted.
    //Order: There is no guarantee of order; items are emitted as soon as they are available.
    //Error Propagation: If any of the merged observables emits an error, merge propagates that error and stops emitting further items.
    val observable1 = Observable.interval(1, TimeUnit.SECONDS).map { "Observable 1: $it" }
    val observable2 = Observable.interval(2, TimeUnit.SECONDS).map { "Observable 2: $it" }

    Observable.merge(observable1, observable2)
        .subscribe { println(it) }

    Thread.sleep(10000) // Keep the application running for a while to see the output
    ////


    val observable3 = Observable.interval(1, TimeUnit.SECONDS).map { "observable 3: $it" }
    val observable4 = Observable.interval(3, TimeUnit.SECONDS).map { "Observable 4: $it" }
    //Characteristics of zip:
    //Pairing by Index: Combines items from each source by their index.
    //Synchronization: Waits for each source to emit an item before combining.
    //Order: The emissions are combined in the order they are emitted from each source.
    //Limited by Shortest Source: Emits items only as long as the source with the fewest emissions continues to emit.
    Observable.zip(observable3, observable4) { item1, item2 -> "$item1 - $item2" }
        .subscribe { println(it) }

    Thread.sleep(20000) // Keep the application running for a while to see the output

}