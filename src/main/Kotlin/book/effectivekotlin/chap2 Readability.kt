package book.effectivekotlin

//Readability
/*Knowing that it should be clear that we should code with readability in mind.
The function name should always be coherent with its behavior
 Avoid cases where operator meaning is unclear
 Clarify it by using a regular function with a descriptive name instead.
 If you wish to have a more operator-like syntax, then use the infix modifier or a top-level function.
 Specify the variable type when it is not clear
 Do not change scope receiver just because you can. It might be confusing to have too many receivers all giving us methods we can use.
 Explicit argument or reference is generally better
 Properties should represent state, not behavior
 example code:

val Context.preferences:SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

val Context.inflater:LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

val Context.notificationManager:NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE)
as NotificationManager
//properties represent accessors, not fields.

//DON’T DO THIS!

val Tree<Int>.sum:Int
   get() = when (this) {
            is Leaf -> value
             is Node -> left.sum + right.sum

 ////
 A simple rule of thumb is that a property describes and sets state, while a function describes behavior.
 Consider naming arguments / Named arguments are not only useful when we need to skip some default values.
 They are important information for developers reading our code, and they can improve the safety of our code.
 Respect coding conventions
 Every project should look like it was written by a single person, not a group of people fighting with each other.

* */



fun main() {
}