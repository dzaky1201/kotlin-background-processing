package com.dzaky.coroutine

import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.system.measureTimeMillis

class FutureTest {

    val executorService = Executors.newFixedThreadPool(10)

    fun getFoo(): Int {
        Thread.sleep(5000)
        return 10
    }

    fun getBar(): Int {
        Thread.sleep(5000)
        return 10
    }

    @Test
    // hasil akan didapatkan setelah 10 detik
    // karena akan di eksekusi satu persatu sampai salah satu fungsi selesai
    internal fun testNonParallel() {
        val time = measureTimeMillis {
            val foo = getFoo()
            val bar = getBar()
            val result = foo + bar
            println("Total : $result")
        }
        println("Total Time : $time")
    }

    @Test
    // hasil akan didapatkan setelah 5 detik
    // fungsi ini berjalan dengan parallel, sehingga akan berjalan secara bersamaan
    internal fun testFuture() {
        val time = measureTimeMillis {
            // callable berfungsi ketika sebuah thread ingin mengembalikan sebuah value
            // pada contoh dibawah ini value yang dikembalikan adalah integer
            // method submit berfungsi untuk untuk meng-eksekusi callable
            // hasil dari sebuah submit ada Future<>
            // future merupakan return value untuk eksekuasi callable
            val foo: Future<Int> = executorService.submit(Callable { getFoo() })
            val bar: Future<Int> = executorService.submit(Callable { getBar() })

            val result = foo.get() + bar.get()
            println("Total : $result")
        }

        println("Total Time : $time")

        /**
         * output :
         * Total : 20
           Total Time : 5007

         total akan didapatkan dalam waktu 5detik, hal ini didapatkan dari waktu maksimum
         pada case ini kebetulan dua buah functionnya 5 detik
         */
    }
}