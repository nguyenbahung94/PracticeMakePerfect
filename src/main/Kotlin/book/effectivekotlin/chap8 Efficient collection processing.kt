package book.effectivekotlin

/* Prefer Sequence for big collections with more than one processing step
Sequences do not create collections at every processing step
--Limit the number of operations
class Student(val name: String?)
// Works
fun List<Student>.getNames(): List<String> =
this .map { it.name }
.filter { it != null }
.map { it!! }
// Better
fun List<Student>.getNames(): List<String> =
this .map { it.name }
.filterNotNull()
// Best
fun List<Student>.getNames(): List<String> =
this .mapNotNull { it.name }
--Consider Arrays with primitives for performance-critical processing
primitives and arrays with primitives can be used as an optimization in performance-critical parts of your code.
List or Set should be preferred over Array. Though if you hold big collections of primitives,
using Array might significantly improve your performance and memory use.
--Consider using mutable collections
Adding to mutable collections is generally faster, but immutable col- lections give us more control over how they are changed

* */