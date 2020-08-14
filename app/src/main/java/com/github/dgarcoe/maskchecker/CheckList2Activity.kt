package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox

class CheckList2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list2)

        val nextButton = findViewById<Button>(R.id.nextButton2)
        nextButton.setOnClickListener {

            val checkboxNoneAboveValue = findViewById<CheckBox>(R.id.noneAboveCheckBox).isChecked

            var intent: Intent

            if (checkboxNoneAboveValue) {
                intent = Intent(this, IsFFP2Activity::class.java)
            } else {
                intent = Intent(this, NotFFP2Activity::class.java)
            }

            startActivity(intent)
        }
    }
}
