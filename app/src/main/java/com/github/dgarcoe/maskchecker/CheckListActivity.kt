package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class CheckListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list)

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {

            var counter = 0

            val checkBoxBrandValue = findViewById<CheckBox>(R.id.brandCheckBox)

            if (checkBoxBrandValue.isChecked) {
                counter++
            }
            val checkBoxCELogoValue = findViewById<CheckBox>(R.id.ceLogoCheckBox)

            if (checkBoxCELogoValue.isChecked) {
                counter++
            }

            val checkBoxFFP2Value = findViewById<CheckBox>(R.id.FFP2CheckBox)

            if (checkBoxFFP2Value.isChecked) {
                counter++
            }

            val checkBoxENValue = findViewById<CheckBox>(R.id.ENCheckBox)

            if (checkBoxENValue.isChecked) {
                counter++
            }

            var intent: Intent

            if (counter==4) {
                intent = Intent(this, CheckList2Activity::class.java)
            } else {
                intent = Intent(this, FalseFFP2Activity::class.java)
            }

            startActivity(intent)
        }

    }
}
