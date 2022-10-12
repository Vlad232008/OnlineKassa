package com.example.onlinekassa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    private val file: String = "phone"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fexcstr.text = "Войдите под своим номером телефона"
        readJson()
        btnlogout.setOnClickListener{
           inputEmployer()
        }
    }

    private fun inputEmployer(){
        if (phone.length() != 11) {
            Fexcstr.text = "Телефон не верен, должно быть 11 символов"
            return
        }
        saveJson()
        val input = Intent(this, ActivityRealization::class.java)
        input.putExtra("phone", phone.text.toString())
        startActivity(input)
    }
    private fun saveJson(){
        try {
            // отрываем поток для записи
            val bw = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput(file, MODE_PRIVATE)
                )
            )
            // пишем данные
            bw.write(phone.text.toString())
            // закрываем поток
            bw.close()
            Log.d("MyLog", "Файл записан")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readJson(){
        try {
            // открываем поток для чтения
            val br = BufferedReader(
                InputStreamReader(
                    openFileInput(file)
                )
            )
            var str = ""
            str = br.readLine()
            // читаем содержимое
            if (str != null) {
                Log.d("MyLog", "Файл пуст")
            } else {
                val input = Intent(this, ActivityRealization::class.java)
                input.putExtra("phone", str.toString())
                startActivity(input)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}