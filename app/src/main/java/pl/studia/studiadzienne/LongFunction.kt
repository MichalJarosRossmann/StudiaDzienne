package pl.studia.studiadzienne

import kotlin.random.Random

class LongFunction() {

    fun doSomething():String{

        Thread.sleep(5000)
        return "text ${Random.nextInt()}"
    }
}