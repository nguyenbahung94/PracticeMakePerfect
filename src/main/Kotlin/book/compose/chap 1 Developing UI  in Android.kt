package book.compose
/*
Jetpack Compose, the modern Android UI toolkit, was officially announced by Google in July 2019.

As of my last knowledge update in January 2022, Jetpack Compose was still in the beta stage, and Google was actively collecting feedback and making enhancements. The stable release had not been officially declared at that time.
* In Android, you build your UI as a tree-based hierarchy of layouts and widgets. In
code, layouts are represented by ViewGroup objects. They are containers controlling
the position and behavior of their children on the screen.
* widgets are represented by View objects. They display individual
UI components like buttons and text boxes
*  ViewGroups can contain other ViewGroups and Views.
If you’re familiar with computer science structures, you’ll recognize ViewGroups are
like nodes of the tree structure, where each View is a leaf.
-------
Declarative programming is a programming paradigm where you don’t focus on
describing how a program should operate, but what the program should accomplish.
For example, you need to describe how your UI should show a hidden Button,
rather than how it should hide a Button
---Compose is designed in a way where your UI is a representation of your data.
Meaning your UI components — in this case, composables — aren’t responsible for
managing state. They represent your state.
Recomposition is one of the most important concepts in Jetpack Compose. It’s the
mechanism used by compose to update the UI based on state changes.
Recomposition allows any composable function to be re-invoked at any time to rerender the component based on new data.
Whenever state changes, Compose will re-invoke all the composables that depend
on that state and update your UI.
just be aware that this concept means you don’t
have to manually update your UI when the state changes, as you had to do with the
old Android UI toolkit.
-----------Jetpack Compose’s Tech Stack checkout img.png image to know more
Two parts categorize the different components that make up Jetpack Compose: the
development host and the device host.
At the bottom, you have a Kotlin compiler. Jetpack Compose is written in Kotlin and
uses a lot of Kotlin features, which makes it so flexible and easy to use. You’ve seen
how Compose uses trailing lambdas to make the code more readable and intuitive
On top of that, you have a Compose Compiler Plugin. Even though you use
@Composable as an annotation, Compose doesn’t use the annotation processor. This
plugin works at the type system level and also at the code generation level to change
the types of your composable functions.
If you’re not familiar with annotation processors, or APTs, they are a special
system within the build process that analyze specific annotations and generate code
based on them.
This is a great thing because you can use the generated code instead of writing it
yourself, but sometimes it’s terrible as it greatly increases the build time for your
project. But because Compose doesn’t use APTs, it doesn’t slow down your builds!
On top of that, you have Android Studio, which includes Compose-specific tools,
simplifying the work you do with Compose.
-----Device Host
At the bottom, there’s a Compose Runtime. At its core, the Compose logic doesn’t
know anything about Android or UIs. It only knows how to work with tree structures
to emit specific items.
Compose UI Core. It handles input management, measurement, drawing, layout etc.
These two layers support the widgets the next layer provides — Compose UI
foundation. It contains basic building blocks like Text, Row, Column and default interactions
Finally, there’s Compose UI Material, an implementation of the Material Design system. It provides Material components out of the box, making it easy to use
Material Design in your app.
----------------Key Points
• View.java’s size makes the old Android UI toolkit hard to maintain and scale.
• Creating custom views is hard and requires too much code.
• Unlike imperative programming, declarative programming simplifies code and
makes it easier to understand.
• In the old Android UI toolkit, it’s not clear what the source of truth is, who owns it
and who updates it.
• In Jetpack Compose, you use composables to build your UI.
• Composables are just functions annotated with @Composable. • Jetpack Compose is written in Kotlin and allows you to use all the Kotlin features.
• In Jetpack Compose, your UI is a function of data. • You use setContent { } as the entry point to display your composables.
• In Compose you control where to draw the line of separation of concerns between
your business logic and UI.
• Jetpack Compose favors composition over inheritance. • In Jetpack Compose, you use function parameters to pass down the data and
callbacks to propagate events up. This is called Unidirectional data flow
• Jetpack Compose uses recomposition to re-invoke composables when the state
changes.
• Jetpack Compose doesn’t use the annotation processor, but rather a Compose
Compiler Plugin that changes the type of composable functions.
* */