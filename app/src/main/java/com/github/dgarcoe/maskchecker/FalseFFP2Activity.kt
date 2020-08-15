package com.github.dgarcoe.maskchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.system.exitProcess

class FalseFFP2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_false_ffp2)

        val exitButton = findViewById<Button>(R.id.exitButtonFalseFFP2)
        exitButton.setOnClickListener {
            this@FalseFFP2Activity.finish()
            finishAffinity()
            exitProcess(0)
        }
    }
}
