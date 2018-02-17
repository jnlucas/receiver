package com.example.logonrm.demoordererbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var btEnviar: Button
    lateinit var usbReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btEnviar = findViewById(R.id.btEnviar)

        btEnviar.setOnClickListener({
            sendBroadcast(Intent("MeuOrderedBroadcast"))
        })


        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        val usbReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent?.action.equals(Intent.ACTION_POWER_CONNECTED)){
                    tvUsbStatus.text = "cabo conectado"
                }else{
                    tvUsbStatus.text = "cabo desconectado"
                }
            }
        }
        registerReceiver(usbReceiver,filter);


    }
}
