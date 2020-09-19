package com.example.triviaapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.triviaapp.R
import com.example.triviaapp.helper.DatabaseHandler
import com.example.triviaapp.model.DataModelClass
import com.example.triviaapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class SummaryActivity : AppCompatActivity() {
    private lateinit var usename:TextView
    private lateinit var cricketrName:TextView
    private lateinit var selectedFlag:TextView
    private lateinit var finshBtn:Button
    private lateinit var historyBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        usename = findViewById(R.id.helloTv)
        cricketrName = findViewById(R.id.cricketrAns)
        selectedFlag = findViewById(R.id.selectFlag)

        finshBtn = findViewById(R.id.btnFinish)
        historyBtn = findViewById(R.id.btnHistory)


        var username = sharedPreferences.getString(Constants.USERNAME,"") as String
        var color=sharedPreferences.getString(Constants.FLAG_COLOR,"") as String
        var cricker = sharedPreferences.getString(Constants.CRICKETER_NAME,"")as String
        var removeSingle : String
        var removeBoth:String
        removeSingle = getArrayList(sharedPreferences,Constants.FLAG_COLOR).toString().replace("[","")
        removeBoth = removeSingle.replace("]","")


        usename.text="Hello "+username
        cricketrName.text=getString(R.string.questionSecond)+"\n"+"Answer: "+cricker
        selectedFlag.text=getString(R.string.questionThird)+"\n"+"Answer: "+removeBoth

        val insert:DatabaseHandler= DatabaseHandler(this)
        insert.insertData(DataModelClass(username,cricker,removeBoth))


        finshBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        })

        historyBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        })



    }

    fun getArrayList(sharedPreferences: SharedPreferences,key: String?):ArrayList<String?>? {
        val gson = Gson()
        val json: String? = sharedPreferences.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
        return gson.fromJson(json, type)
    }

}