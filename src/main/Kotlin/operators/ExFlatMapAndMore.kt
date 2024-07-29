package operators

import io.reactivex.rxjava3.core.Observable
import java.util.stream.Stream


// flatmap functions
//switchMap is a powerful operator for scenarios where you need to ensure that only the latest emitted items from inner observables are processed,
// effectively canceling any previous operations that are no longer relevant
fun main() {


    println("combine concatMap and concatWith.\n\n")
    val listUSerIdObservable = fetchListUserIds()
    listUSerIdObservable
        .concatMap { userIds ->
            Observable.fromIterable(userIds)
                .concatMap { userId ->
                    fetchUserDetails(userId)
                        .concatMap { user ->
                            fetchUserActivityReturnString(user.id)
                        }
                }
        }
        .concatWith(fetchSystemNotifications())
        .subscribe(
            { value ->
                println("Received: $value")
            },
            { error ->
                println("Error: $error")
            },
            {
                println("Completed")
            }
        )




    println("ConcatWith Operator\n\n")
    val userIdsObservable = fetchUserIdsTypeString()
    val notificationsObservable = fetchSystemNotifications()
    // Concatenate the two observables
    userIdsObservable.concatWith(notificationsObservable)
        .subscribe(
            { value ->
                println("Received: $value")
            },
            { error ->
                println("Error: $error")
            },
            {
                println("Completed")
            }
        )



///////////////////////////////////////////
    print("\n\nFlatMap and ConcatMap Operators\n\n")
    fetchListUserIds()
        .flatMap {  userIds ->
            Observable.fromIterable(userIds)
        }.concatMap {  userId ->
            fetchUserDetails(userId)
        }.concatMap { userDetails ->
            fetchUserActivity(userDetails.id)
        }.subscribe(
            { activity ->
                println("Fetched user activity: $activity")
            }
            ,{ error ->
                println("Error fetching user activity: $error")

            }
        )
//// 3 flatmap functions////////////////////////////////
    print("\n\n3 flatMap functions\n\n")
    fetchListUserIds()
        .flatMap { userIds ->
            Observable.fromIterable(userIds)
        }
        .flatMap { userId ->
            println("user id = $userId")
           fetchUserDetails(userId)
        }
        .flatMap { user ->
            fetchUserActivity(user.id)
        }
        .subscribe(
            { activity ->
                println("Fetched user activity: $activity")

            }
            ,{ error ->
                println("Error fetching user activity: $error")

            }
        )

/////////////////////////observable to flatMap //////////////////////////
    println("\n\nobservable to flatMap\n\n")
    // Subscribe to the flattened observable to get user details
    flattenedUserDetails.subscribe { user ->
        println("Fetched user details: $user")
    }

    userDetails.subscribe { userObservable ->
            println("Fetched user by 2 subscribe details: ${userObservable.blockingFirst()}")
    }

}


fun fetchSystemNotifications(): Observable<String> {
    return Observable.just(
        "System update available",
        "New message received"
    )
}

fun fetchUserActivity(userId: Int): Observable<Activity> {
    return Observable.just(Activity(userId, "Activity for user $userId"))
}

fun fetchUserActivityReturnString(userId: Int): Observable<String> {
    return Observable.just("Activity 1 for user $userId", "Activity 2 for user $userId")
}

// Simulated functions to fetch data asynchronously
fun fetchListUserIds(): Observable<List<Int>> {
    return Observable.just(listOf(1, 2, 3))
}

fun fetchUserIdsTypeString(): Observable<String> {
    return Observable.just("1", "2", "3")
}


/////////////////////////observable to flatMap //////////////////////////
val userIds: Observable<Int> = Observable.just(1, 2, 3)



// Flattened observable: Observable<User>
val flattenedUserDetails: Observable<User> = userIds.flatMap { userId ->
    fetchUserDetails(userId)
}



// Higher-order observable: Observable<Observable<User>>
val userDetails: Observable<Observable<User>> = userIds.map { userId ->
    fetchUserDetails(userId) // returns Observable<User>
}


fun fetchUserDetails(userId: Int): Observable<User> {
    // Simulate an asynchronous network call to fetch user details
    return Observable.just(User(userId, "User $userId"))
}

data class User(val id: Int, val name: String)
data class Activity(val userId: Int, val activity: String)


