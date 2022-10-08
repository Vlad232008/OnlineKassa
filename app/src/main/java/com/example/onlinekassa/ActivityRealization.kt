package com.example.onlinekassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_realization.*
import kotlinx.android.synthetic.main.activity_realization.Fexcstr

class ActivityRealization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realization)
        btnCreateCheck.setOnClickListener {
            if (edEmail.text.toString() == "") {
                Fexcstr.text = "Введите E-Mail"
                return@setOnClickListener
            }
            if (edSum.text.toString() == "") {
                Fexcstr.text = "Введите сумму чека"
                return@setOnClickListener
            }

        }
    }
}