package com.example.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    private val channelID = "com.example.notificationdemo.channel1"
    private val notificationManager:NotificationManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID,"DemoChannel","this is demo")
       val button =  findViewById<Button>(R.id.button2)
        button.setOnClickListener{
         displayNotification()
        }

    }
    private fun displayNotification(){
      val notificationId = 45
        val notification = NotificationCompat.Builder(this@MainActivity,channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a Demo Notification")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        notificationManager?.notify(notificationId,notification)
    }
    private fun createNotificationChannel(id:String,name:String,channelDescription:String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
         val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }

    }    }