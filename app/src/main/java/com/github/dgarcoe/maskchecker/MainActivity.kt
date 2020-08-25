package com.github.dgarcoe.maskchecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener{
            val intent = Intent(this, FirstCheckActivity::class.java)
            startActivity(intent)
        }

        val referencesButton = findViewById<Button>(R.id.referencesButton)
        referencesButton.setOnClickListener {
            val intent = Intent(this, ReferencesActivity::class.java)
            startActivity(intent)
        }
    }
}
