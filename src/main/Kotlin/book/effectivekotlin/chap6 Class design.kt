package book.effectivekotlin

/*
* Composition is more secure - We do not depend on how a class is implemented, but only on its externally observable behavior.
* Composition is more flexible - We can only extend a single class, while we can compose many.
* Use the data modifier to represent a bundle of data
* Use function types instead of interfaces to pass operations and actions
* SAM’s (Single-Abstract Method)
* should use this replace create interface for handle onlick
* If you don’t have a good reason to define an interface, prefer using function types.
* They are well supported and are used frequently by Kotlin developers.
* class CalendarView {
var onDateClicked: ((date: Date) -> Unit)? = null
var onPageChanged: ((date: Date) -> Unit)? = null
*
}
We define hashCode in Kotlin practically only when we define custom equals.
* When we use the data modifier, it generates both equals and a consistent hashCode.
*  When you do not have a custom equals method, do not define a custom hashCode unless
* you are sure you know what you are doing and you have a good reason.
* The biggest difference between members and extensions in terms of use is that extensions need to be imported separately
* Therefore we should not use extensions for elements that are designed for inheritance.
 In Kotlin, you define extension functions on types, not on specific classes.
 * This means that extension functions can be added to any type, including both classes and interfaces.
 * when you define extension functions or extension properties, they are not listed as members in the class reference.
 * This means that when you look at the class documentation or use auto-completion features, extension functions or properties won't be explicitly listed as part of the class's API.
 * To summarize it, extensions give us more freedom and flexibility. Although they do not support inheritance, annotation processing
 -- Avoid member extensions
 * When we define an extension function to some class, it is not added to this class as a member.
 *  An extension function is just a different kind of function that we call on the first argument that is there, called a receiver
 * Under the hood, extension functions are compiled to normal functions, and the receiver is placed as the first parameter.
 * Part 3: Efficiency

* */