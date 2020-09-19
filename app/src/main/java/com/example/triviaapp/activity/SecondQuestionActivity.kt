package com.example.triviaapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.adapter.CricketerAdapter
import com.example.triviaapp.model.CricketerName

private lateinit var questionTwo: TextView
private lateinit var questionNextBtn: Button
private lateinit var cricketerRv: RecyclerView

class SecondQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_question)

        questionTwo = findViewById(
            R.id.questionTwoTv
        )
        questionNextBtn = findViewById(
            R.id.questionNextBtn
        )
        cricketerRv = findViewById(
            R.id.cricketerRecyclerView
        )

        cricketerRv.layoutManager = LinearLayoutManager(this)

        val cricketer = ArrayList<CricketerName>()
        cricketer.add(CricketerName("Sachin Tendulkar"))
        cricketer.add(CricketerName("Virat Kolli"))
        cricketer.add(CricketerName("Adam Gilchirst"))
        cricketer.add(CricketerName("Jacques Kallis"))

        val adapter = CricketerAdapter(this,cricketer)
        cricketerRv.adapter = adapter
        questionNextBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this,ThirdQuestionActivity::class.java)
            startActivity(intent)
        })


    }

}