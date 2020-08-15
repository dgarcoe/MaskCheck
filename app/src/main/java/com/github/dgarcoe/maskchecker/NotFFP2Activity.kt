package com.github.dgarcoe.maskchecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class NotFFP2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_ffp2)

        val textViewOtherStandard = findViewById<TextView>(R.id.textViewOtherStandard)
        textViewOtherStandard.setText(intent.getStringExtra("otherStandard"))

        val exitButton = findViewById<Button>(R.id.exitButtonNotFFP2)
        exitButton.setOnClickListener {
            this@NotFFP2Activity.finish()
            finishAffinity()
            exitProcess(0)
        }
    }
}
