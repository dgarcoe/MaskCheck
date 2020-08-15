package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

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

                var otherStandard = ""

                val checkboxG2626 = findViewById<CheckBox>(R.id.gb2626CheckBox)
                val checkboxKN95 = findViewById<CheckBox>(R.id.kn95CheckBox)
                val checkboxNIOSH = findViewById<CheckBox>(R.id.nioshCheckBox)
                val checkboxN95 = findViewById<CheckBox>(R.id.n95CheckBox)
                val checkboxFDA = findViewById<CheckBox>(R.id.fdaCheckBox)
                val checkboxCE1282 = findViewById<CheckBox>(R.id.ce1282CheckBox)

                val isG2626Condition = (checkboxG2626.isChecked || checkboxKN95.isChecked) &&
                        (!checkboxNIOSH.isChecked && !checkboxN95.isChecked && !checkboxFDA.isChecked && !checkboxCE1282.isChecked)

                val isNIOSHCondition = (checkboxNIOSH.isChecked || checkboxN95.isChecked) &&
                        (!checkboxG2626.isChecked && !checkboxKN95.isChecked && !checkboxFDA.isChecked && !checkboxCE1282.isChecked)

                if (isG2626Condition) {
                    otherStandard = getString(R.string.g2626)
                }

                if (isNIOSHCondition) {
                    otherStandard = getString(R.string.niosh)
                }

                if (otherStandard.compareTo("")==0) {
                    intent = Intent(this, FalseFFP2Activity::class.java)
                } else {
                    intent = Intent(this, NotFFP2Activity::class.java)
                    intent.putExtra("otherStandard",otherStandard)
                }
            }

            startActivity(intent)
        }
    }
}
