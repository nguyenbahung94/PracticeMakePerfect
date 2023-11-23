package book.kotlin_corountine
/*
How does suspension work?
Suspending functions are the hallmark of Kotlin coroutines.
The suspension capability is the single most essential feature upon which all other Kotlin Coroutines concepts are built.
That is why our goal in this chapter is to forge a solid understanding of how it works.
Suspending a coroutine means stopping it in the middle.
It is similar to stopping a video game: you save at a checkpoint, turn off the game,
and both you and your computer can focus on doing different things. Then, when you would like to continue some time later,
you turn on the game again, resume from the saved checkpoint, and thus you can play from where you previously left off.
This is an analogy to coroutines. When they are suspended, they return a Continuation.
It is like a save in a game: we can use it to continue from the point where we stopped.
Notice that this is very different from a thread, which cannot be saved, only blocked.
A coroutine is much more powerful. When suspended, it does not consume any resources.
A coroutine can be resumed on a different thread, and (at least in theory) a continuation can be serialized, deserialized and then resumed.

* */