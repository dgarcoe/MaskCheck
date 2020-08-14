package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox

class CheckListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list)

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val checkBoxBrandValue = findViewById<CheckBox>(R.id.brandCheckBox).isChecked
            val checkBoxCELogoValue = findViewById<CheckBox>(R.id.ceLogoCheckBox).isChecked
            val checkBoxFFP2Value = findViewById<CheckBox>(R.id.FFP2CheckBox).isChecked
            val checkBoxENValue = findViewById<CheckBox>(R.id.ENCheckBox).isChecked

            var intent: Intent

            if (checkBoxBrandValue && checkBoxCELogoValue && checkBoxFFP2Value && checkBoxENValue) {
                intent = Intent(this, CheckList2Activity::class.java)
            } else {
                intent = Intent(this, NotFFP2Activity::class.java)
            }

            startActivity(intent)
        }

    }
}
