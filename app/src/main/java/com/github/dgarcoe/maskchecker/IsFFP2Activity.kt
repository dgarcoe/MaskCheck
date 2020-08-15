package com.github.dgarcoe.maskchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.system.exitProcess

class IsFFP2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_ffp2)

        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            this@IsFFP2Activity.finish()
            finishAffinity()
            exitProcess(0)
        }

    }
}
