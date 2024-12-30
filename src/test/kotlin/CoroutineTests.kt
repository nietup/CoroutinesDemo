import kotlin.test.Test
import kotlinx.coroutines.*

class CoroutineTests {
    @Test
    fun `run blocking is the entry point for coroutines`() {
        runBlocking {
            println("hello")
        }
    }

    @Test
    fun `run blocking is blocking`() {
        println("hello")

        runBlocking {
            delay(3000)
        }

        println("world")
    }

    @Test
    fun `launch runs concurrently`() {
        runBlocking {
            println("hello")

            launch {
                delay(3000)
                println("netcompany")
            }

            println("world")
        }
    }

    @Test
    fun `exercise 1 - what will happen`() {
        runBlocking {
            println("hello")

            launch {
                delay(3000)
                println("netcompany")
            }

            println("world")
        }

        println("we are commited")
    }

    @Test
    fun `launch returns a job`() {
        runBlocking {
            println("hello")

            val job = launch {
                delay(3000)
                println("netcompany")
            }

            println(job.isActive)
        }
    }

    @Test
    fun `join waits for the coroutine to finish`() {
        runBlocking {
            println("hello")

            val job = launch {
                delay(3000)
                println("netcompany")
            }

            job.join()
            println("world")
        }
    }

    @Test
    fun `exercise 2 - what will happen`() {
        runBlocking {
            println("hello")

            launch {
                delay(3000)
                println("netcompany")
            }

            launch {
                delay(1500)
                println("oslo kommune")
                throw NullPointerException("cdcds")
            }

            println("world")
        }
    }

    @Test
    fun `async is used when a method returns something`() {
        runBlocking {
            val meaningOfLife = async {
                delay(3000)
                42
            }

            val okRemainingBudget = async {
                delay(1500)
                -100
            }

            println("computing result...")
            val sum = meaningOfLife.await() + okRemainingBudget.await()
            println(sum)
        }
    }

    private suspend fun susFunction1() {
        delay(3000)
        println("kinda sus")
    }

    @Test
    fun `suspend functions need to be run from coroutines context`() {
        runBlocking {
            susFunction1()
        }
    }
}