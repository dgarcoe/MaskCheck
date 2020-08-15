package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NotFFP2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_ffp2)

        val nextButton = findViewById<Button>(R.id.nextButton3)
        nextButton.setOnClickListener {
            val intent = Intent(this, CheckOtherStandardsActivity::class.java)
            startActivity(intent)
        }
    }
}
