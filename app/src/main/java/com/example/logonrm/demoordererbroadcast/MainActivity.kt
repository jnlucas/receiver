package com.example.logonrm.demoordererbroadcast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var btEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btEnviar = findViewById(R.id.btEnviar)

        btEnviar.setOnClickListener({
            sendBroadcast(Intent("MeuOrderedBroadcast"))
        })
    }
}
