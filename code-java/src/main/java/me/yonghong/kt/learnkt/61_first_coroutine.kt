import kotlinx.coroutines.*


@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) {        // Executes in main thread

    println("Main program starts: ${Thread.currentThread().name}")

    GlobalScope.launch {    // creates a background coroutine that runs on a background thread
        println("Fake work starts: ${Thread.currentThread().name}")
        sleep()    // Pretend doing some work... may be file upload
        println("Fake work finished: ${Thread.currentThread().name}")
    }

    // Blocks the current main thread & wait for coroutine to finish (practically not a right way to wait)
    println("Main program continue: ${Thread.currentThread().name}")
    Thread.sleep(2000)
    println("Main program ends: ${Thread.currentThread().name}")
}

suspend fun sleep() {
    withContext(Dispatchers.IO) {
        println("Fake io starts: ${Thread.currentThread().name}")
        Thread.sleep(1000)
    }
}