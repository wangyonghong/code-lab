import kotlinx.coroutines.*

fun main() = runBlocking {    // Creates a blocking coroutine that executes in current thread (main)

    println("Main program starts: ${Thread.currentThread().name}")  // main thread

    val msgOne: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageFive() }
    val msgTwo: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageSix() }
    println("The entire message is: ${msgOne.await() + msgTwo.await()}")

    println("Main program ends: ${Thread.currentThread().name}")    // main thread
}

suspend fun getMessageFive(): String {
    delay(1000L)    // pretend to do some work
    println("After working in getMessageOne()")
    return "Hello "
}

suspend fun getMessageSix(): String {
    delay(1000L)    // pretend to do some work
    println("After working in getMessageTwo()")
    return "World!"
}