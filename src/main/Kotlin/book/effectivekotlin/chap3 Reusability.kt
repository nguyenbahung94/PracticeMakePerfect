package book.effectivekotlin

/*
Don’t Repeat Yourself rule
Do not repeat common algorithms
example:
override fun saveCallResult(item:SourceResponse){
val sourceEntries = item.sources.map(::sourceToEntry)
db.insertSources(sourceEntries)

private fun sourceToEntry(source:Source)=SourceEntity() .apply {
        id = source.id
        category = source.category
        country = source.country
        description = source.description
}
////
Implementing your own utils
It is bad practice to have duplicate functions achieving the same results.
Each function needs to be tested, remembered and maintained, and so should be considered as a cost.
 We should be aware not to define functions we don’t need, therefore,
 we should first search for an existing function before implementing our own.
 Do not repeat common algorithms. First, it is likely that there is a stdlib function that you can use instead
 ///
 Use property delegation to extract common property patterns
 example using the Delegation pattern
 interface IHardwareMonitor {

    fun observeBatteryChanges()

    fun observeNetworkChanges()

}
class HardwareMonitorImpl : IHardwareMonitor {

    override fun observeBatteryChanges() {
        TODO("Not yet implemented")
    }

    override fun observeNetworkChanges() {
        TODO("Not yet implemented")
    }

}
interface ITrackingService {

    fun trackUserAction(action: String)

}
class UserTrackingImpl : ITrackingService {

    override fun trackUserAction(action: String) {
        TODO("Not yet implemented")
    }

}
in activity
class MainActivity : ComponentActivity(),
    IHardwareMonitor by HardwareMonitorImpl(), // delegation
    ITrackingService by UserTrackingImpl() { // delegation
override fun onCreate(savedInstanceState: Bundle?) {
    observeBatteryChanges()
    observeNetworkChanges()
   }
override fun onPause() {
    super.onPause()
    trackUserAction("Activity paused")
   }
}
///
Use generics when implementing common algorithms
* */