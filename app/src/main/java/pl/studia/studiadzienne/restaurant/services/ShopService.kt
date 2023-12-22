package pl.studia.studiadzienne.restaurant.services

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class ShopService:IntentService("") {


    override fun onHandleIntent(p0: Intent?) {

        startForeground(100,prepareNotification())
        Log.i("ShopService","startService")
        val doSMTH = doSMTH()
        Log.i("ShopService","end $doSMTH")

        stopForeground(STOP_FOREGROUND_REMOVE)

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

        val notificationManager = getSystemService<NotificationManager>(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ShopService","onDestroy")
    }

    private fun doSMTH():Int{

        for (i in 0.. 20){
            Thread.sleep(1000)
            Log.i("ShopService","doSMTH $i")
        }

        return 3
    }
}