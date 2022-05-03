package com.dzaky.coroutine

import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Executors

class ExecutorServiceTest {

    @Test
    internal fun testSingleThreadPool() {
        // hanya menggunakan 1 thread
        val executorService = Executors.newSingleThreadExecutor()
        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }

            // runnable akan ditampung semua ke dalam executors secara langsung (masuk antrian) dan akan di proses satu persatu
            // kenapa satu persatu ? hal tersebut karena kita hanya menggunakan 1 thread
            executorService.execute(runnable)

        }

        println("Menunggu")
        Thread.sleep(11_000)
        println("SELESAI")

        /**
         * output :
         * Menunggu
        Done 0 pool-1-thread-1 Tue May 03 21:09:19 WIB 2022
        Done 1 pool-1-thread-1 Tue May 03 21:09:20 WIB 2022
        Done 2 pool-1-thread-1 Tue May 03 21:09:21 WIB 2022
        Done 3 pool-1-thread-1 Tue May 03 21:09:22 WIB 2022
        Done 4 pool-1-thread-1 Tue May 03 21:09:23 WIB 2022
        Done 5 pool-1-thread-1 Tue May 03 21:09:24 WIB 2022
        Done 6 pool-1-thread-1 Tue May 03 21:09:25 WIB 2022
        Done 7 pool-1-thread-1 Tue May 03 21:09:26 WIB 2022
        Done 8 pool-1-thread-1 Tue May 03 21:09:27 WIB 2022
        Done 9 pool-1-thread-1 Tue May 03 21:09:28 WIB 2022
        SELESAI
         */

    }

    @Test
    internal fun testFixedThreadPool() {
        // menentukan threadnya 3
        val executorService = Executors.newFixedThreadPool(3)
        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }

            // runnable akan ditampung semua ke dalam executors secara langsung (masuk antrian)
            // jika menggunakan fixed thread, yang artinya menentukan jumlah threadnya
            // maka akan dieksekusi sesuai dengan jumlah threadnya
            // pada contoh kali ini thread yang dibuat 3, maka runnable akan di eksekusi 3 sekaligus sampai selesai
            // output bisa dilihat di bawah ini
            executorService.execute(runnable)

        }

        println("Menunggu")
        Thread.sleep(11_000)
        println("SELESAI")

        /**
         * output :
         *Menunggu
        Done 2 pool-1-thread-3 Tue May 03 21:13:25 WIB 2022
        Done 1 pool-1-thread-2 Tue May 03 21:13:25 WIB 2022
        Done 0 pool-1-thread-1 Tue May 03 21:13:25 WIB 2022

        Done 4 pool-1-thread-2 Tue May 03 21:13:26 WIB 2022
        Done 5 pool-1-thread-1 Tue May 03 21:13:26 WIB 2022
        Done 3 pool-1-thread-3 Tue May 03 21:13:26 WIB 2022

        Done 6 pool-1-thread-2 Tue May 03 21:13:27 WIB 2022
        Done 7 pool-1-thread-1 Tue May 03 21:13:27 WIB 2022
        Done 8 pool-1-thread-3 Tue May 03 21:13:27 WIB 2022

        Done 9 pool-1-thread-2 Tue May 03 21:13:28 WIB 2022
        SELESAI
         */

    }

    @Test
    internal fun testCachedThreadPool() {
        // jumlah thread tergantung dari proses yang dilakukan
        // jika ada 10, maka akan dibuat 10 thread
        // peringatan ! -> thread ini tidak disarankan untuk dipakai"
        // hal tersebut akan menyebabkan memori kita habis
        val executorService = Executors.newCachedThreadPool()
        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }

            // runnable akan ditampung semua ke dalam executors secara langsung (masuk antrian)
            // jika menggunakan cached thread, maka thread akan dibuat sesuai dengan jumlah runnablenya
            // dioutput kode program ini terdapat 10 thread yang dibuat
            executorService.execute(runnable)

        }

        println("Menunggu")
        Thread.sleep(11_000)
        println("SELESAI")

        /**
         * output :
         *Menunggu
        Done 7 pool-1-thread-8 Tue May 03 21:22:03 WIB 2022
        Done 0 pool-1-thread-1 Tue May 03 21:22:03 WIB 2022
        Done 4 pool-1-thread-5 Tue May 03 21:22:03 WIB 2022
        Done 1 pool-1-thread-2 Tue May 03 21:22:03 WIB 2022
        Done 5 pool-1-thread-6 Tue May 03 21:22:03 WIB 2022
        Done 2 pool-1-thread-3 Tue May 03 21:22:03 WIB 2022
        Done 3 pool-1-thread-4 Tue May 03 21:22:03 WIB 2022
        Done 9 pool-1-thread-10 Tue May 03 21:22:03 WIB 2022
        Done 8 pool-1-thread-9 Tue May 03 21:22:03 WIB 2022
        Done 6 pool-1-thread-7 Tue May 03 21:22:03 WIB 2022
        SELESAI
         */

    }
}