import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {    // Creates a blocking coroutine that executes in current thread (main)

    println("Main program starts: ${Thread.currentThread().name}")  // main thread

    val deferredJob: Deferred<Unit> = async {  // Thread: main
        println("Fake work starts: ${Thread.currentThread().name}")     // Thread: main
        delay(1000)   // Coroutine is suspended but Thread: main is free (not blocked)
        println("Fake work finished: ${Thread.currentThread().name}") // Thread: main
        15
    }

    deferredJob.await()  // main thread: wait for coroutine to finish and return data

    println("Main program ends: ${Thread.currentThread().name}")    // main thread
}

suspend fun myOwnSuspendingFunc() {
    delay(1000)     // do something
}