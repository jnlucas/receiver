package com.example.logonrm.smsbroadcastreceiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class SMSActivity : AppCompatActivity() {

    lateinit var mReceiver: BroadcastReceiver

    lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        tvMessage = findViewById(R.id.tvMessage)


        if (intent != null) {
            val i = intent
            val remetente = i.getStringExtra("remetente")
            val mensagem = i.getStringExtra("mensagem")
            tvMessage!!.text = if (remetente == null && mensagem == null) "" else remetente + " : " + mensagem
        }
        requestSmsPermission()



    }

    private fun requestSmsPermission() {
        val permission = Manifest.permission.RECEIVE_SMS
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permission_list = arrayOfNulls<String>(1)
            permission_list[0] = permission
            ActivityCompat.requestPermissions(this, permission_list, 1)
        }
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()

        intentFilter.addAction("SMSRECEBIDO")

        mReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                //extract our message from intent
                Log.i("TEXTE","CHEGUEIIII")

                Log.i("ACTION",intent?.action)

                val remetente = intent.getStringExtra("remetente")
                val mensagem = intent.getStringExtra("mensagem")
                tvMessage!!.text = remetente + " : " + mensagem

            }
        }
        

        //registering our receiver
        registerReceiver(mReceiver, intentFilter)
     }

    override fun onPause() {
        super.onPause()
        //unregister our receiver
        unregisterReceiver(this.mReceiver)
    }
}