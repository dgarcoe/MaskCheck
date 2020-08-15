package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class FirstCheckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_check)

        val nextButton = findViewById<Button>(R.id.nextButtonToChecklist)
        nextButton.setOnClickListener {
            val yesIsChecked = findViewById<RadioButton>(R.id.radioButtonYes).isChecked
            val noIsChecked = findViewById<RadioButton>(R.id.radioButtonNo).isChecked

            var intent: Intent

            if (!yesIsChecked && !noIsChecked) {
                    Toast.makeText(applicationContext,R.string.check_one_option,Toast.LENGTH_SHORT).show()
            }

            if (yesIsChecked) {
               intent = Intent(this, CheckListActivity::class.java)
                startActivity(intent)
            } else if (noIsChecked) {
                intent = Intent(this, FalseFFP2Activity::class.java)
                startActivity(intent)
            }

        }


    }
}
