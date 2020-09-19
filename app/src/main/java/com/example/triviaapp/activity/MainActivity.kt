package com.example.triviaapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaapp.R
import com.example.triviaapp.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var questionTv: TextView;
    private lateinit var ansET: EditText
    private lateinit var nextBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)


        questionTv = findViewById(R.id.questionOneTv)
        ansET = findViewById(R.id.ansOneET)
        nextBtn = findViewById(R.id.questionNextBtn)

        nextBtn.setOnClickListener(View.OnClickListener {
            if (ansET.text.toString().trim().length > 0) {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(Constants.USERNAME, ansET.text.toString()).apply()
                var intent = Intent(this, SecondQuestionActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(this,"Enter text first",Toast.LENGTH_SHORT).show()
            }
        })


    }
}