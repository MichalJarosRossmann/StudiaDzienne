package pl.studia.studiadzienne.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class TestWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        setForegroundAsync(ForegroundInfo(1000,prepareNotification()))
        Log.i("TestWorker","start")
        val doSMTH = doSMTH()
        Log.i("TestWorker","end")
        if (doSMTH==3)
            return Result.success()
        else
            return Result.retry()
    }

    private fun doSMTH():Int{

        for (i in 0.. 3){
            Thread.sleep(1000)
            Log.i("TestWorker","doSMTH $i")
        }

        return Random.nextInt(3)
    }


    //tworzenie notyfikacji która będzie pokazywać się w momencie pobierania danych
    fun prepareNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            prepareNotificationChannel()
        }

        val notification = NotificationCompat.Builder(applicationContext,"CHANNEL_ID")
            .setContentTitle("title")
            .setTicker("title")
            .setContentText("progress")
            .setSmallIcon(android.R.drawable.ic_delete)
            .setOngoing(true)
            // Add the cancel action to the notification which can
            // be used to cancel the worker
            .build()

        return notification
    }

    //wymagane do uruchamienia notyfikacji
    @RequiresApi(Build.VERSION_CODES.O)
    fun prepareNotificationChannel(){

        val channel = NotificationChannel("CHANNEL_ID", "PennSkanvTicChannel", NotificationManager.IMPORTANCE_MIN)
        channel.description = "PennSkanvTic channel for foreground service notification"

        val notificationManager = context.getSystemService<NotificationManager>(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}