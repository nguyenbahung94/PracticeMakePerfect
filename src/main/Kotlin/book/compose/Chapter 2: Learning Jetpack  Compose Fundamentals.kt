package book.compose

/*
* In short, by using annotations, you can add behavior to classes and generate useful code, without writing a lot of boilerplates
* Only other composable functions can call it
• The composable can only be invoked from a compose scope.
* the CompositionContext, which is a reference to the parent composition. CompositionContext is used to coordinate scheduling of composition updates in a composition tree.
* The parent of the root composition is a Recomposer which determines the thread where recomposition happens — one of the most important features of Jetpack
Compose.
* In simple terms, recomposition is an event that asks the app to re-draw the current UI with new values. Recomposition happens every time a value such as state
changes.
* remember compose : In Jetpack Compose, remember is a utility function used for retaining state across recompositions of a Composable function.
*  It allows you to keep track of mutable state within a Composable function without having to manage the state yourself.
* This concept is that we call Positional Memoization and this is the concept that Compose is built around, from the ground up.
* explain ::
 remember is something that knows how to appeal to the slot table. remember looks at items and stores the list and query in the slot table. The filter computation then runs and remember stores the result before passing it back.
The second time the function executes, remember looks at the new values being passed in and compares them with the old values. If neither of them has changed, then the filter operation is skipped and the previous result returned. That’s positional memoization.
*
* */
