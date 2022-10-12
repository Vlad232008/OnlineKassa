package com.example.onlinekassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_realization.*
import kotlinx.android.synthetic.main.activity_realization.Fexcstr
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.util.*

class ActivityRealization : AppCompatActivity() {
    private val URL_ATOL = "http://192.168.9.125:16732/api/v2/requests"
    private var json = ""
    private val okHttpClient = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realization)
        btnCreateCheck.setOnClickListener {
            if (edEmail.text.toString() == "") {
                Fexcstr.text = "Введите E-Mail/Номер телефона"
                return@setOnClickListener
            }
            if (edSum.text.toString() == "") {
                Fexcstr.text = "Введите сумму чека"
                return@setOnClickListener
            }
            test()
        }
    }
    private fun openKassSmena() {
        val UUID: String = UUID.randomUUID().toString()
        val dataSend: String = "{\n" +
                "  \"uuid\": \"$UUID\",\n" +
                "  \"request\": [\n" +
                "    {\n" +
                "      \"type\": \"openShift\",\n" +
                "      \"operator\": {\n" +
                "        \"name\": \"Удут В.А.\",\n" +
                "        \"vatin\": \"\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        val textQuery:RequestBody = dataSend.toRequestBody()
        val request = Request.Builder()
            .url(URL_ATOL)
            .method("POST", textQuery)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                json = response.body.string()
            }
            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }

    private fun checkPrixoda(){
        val UUID: String = UUID.randomUUID().toString()
        val dataSend: String = "{\n" +
                "  \"uuid\": \"$UUID\",\n" +
                "  \"request\": [\n" +
                "    {\n" +
                "      \"type\": \"sell\",\n" +
                "      \"taxationType\": \"osn\",\n" +
                "      \"electronically\": true,\n" +
                "      \"operator\": {\n" +
                "        \"name\": \"Удут В.А.\",\n" +
                "        \"vatin\": \"\"\n" +
                "      },\n" +
                "      \"clientInfo\": {\n" +
                "        \"emailOrPhone\": \"${edEmail.text}\"\n" +
                "      },\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"type\": \"position\",\n" +
                "          \"name\": \"Транспортная услуга\",\n" +
                "          \"price\": ${edSum.text},\n" +
                "          \"quantity\": 1,\n" +
                "          \"amount\": ${edSum.text},\n" +
                "          \"infoDiscountAmount\": 0,\n" +
                "          \"department\": 1,\n" +
                "          \"measurementUnit\": \"шт\",\n" +
                "          \"paymentMethod\": \"fullPayment\",\n" +
                "          \"paymentObject\": \"service\",\n" +
                "          \"tax\": {\n" +
                "            \"type\": \"vat20\"\n" +
                "          }\n" +
                "        },\n" +
                "      ],\n" +
                "      \"payments\": [\n" +
                "        {\n" +
                "          \"type\": \"cash\",\n" +
                "          \"sum\": ${edSum.text}\n" +
                "        }\n" +
                "      ],\n" +
                "      \"total\": ${edSum.text}\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        val textQuery:RequestBody = dataSend.toRequestBody()
        val request = Request.Builder()
            .url(URL_ATOL)
            .method("POST", textQuery)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                json = response.body.string()
            }
            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }

    private fun test(){
        val UUID: String = UUID.randomUUID().toString()
        val dataSend = "{\n" +
                " \"uuid\": \"$UUID\",\n" +
                " \"request\": [\n" +
                "    {\n" +
                "     \"type\": \"reportX\",\n" +
                "     \"operator\": {\n" +
                "       \"name\": \"Иванов\",\n" +
                "       \"vatin\": \"123654789507\"\n" +
                "     }\n" +
                "   },\n" +
                "   {\"type\": \"nonFiscal\",\n" +
                "     \"items\": [\n" +
                "         {\n" +
                "           \"type\": \"text\",\n" +
                "     \"text\": \"Тестовый текст для проверки\"\n" +
                "      }\n" +
                "     ]\n" +
                "   },\n" +
                "    {\n" +
                "     \"type\": \"reportOfdExchangeStatus\",\n" +
                "     \"operator\": {\n" +
                "       \"name\": \"${edEmail.text}\",\n" +
                "       \"vatin\": \"\"\n" +
                "     }\n" +
                "   }    \n" +
                " ]\n" +
                "}"
        val textQuery:RequestBody = dataSend.toRequestBody()
        val request = Request.Builder()
            .addHeader("deviceID", "ATOL_1")
            .addHeader("Content-Type", "application/json")
            .url(URL_ATOL)
            .method("POST", textQuery)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                json = response.body.string()
            }
            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }
}