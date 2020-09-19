package com.example.triviaapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.adapter.FlagAdapter
import com.example.triviaapp.model.FlagColor

class ThirdQuestionActivity : AppCompatActivity() {
    private lateinit var questionThird: TextView
    private lateinit var questionNextBtn: Button
    private lateinit var flagRv: RecyclerView
    private lateinit var selectAll:CheckBox
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_question)

        questionThird = findViewById(R.id.questionThirdTv)
        questionNextBtn = findViewById(R.id.questionNextBtn)
        flagRv = findViewById(R.id.flagRecyclerView)
        selectAll = findViewById(R.id.selectAll)



        val colorList = ArrayList<FlagColor>()
        colorList.add(FlagColor("White"))
        colorList.add(FlagColor("Yellow"))
        colorList.add(FlagColor("Orange"))
        colorList.add(FlagColor("Green"))
        val adapter = FlagAdapter(this,colorList)
        flagRv.layoutManager = LinearLayoutManager(this)
        flagRv.adapter = adapter

        selectAll.setOnClickListener(View.OnClickListener {
            if (selectAll.isChecked){
                adapter.selectAll()
            }else{
                adapter.unselectall()
            }
        })

        questionNextBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, SummaryActivity::class.java)
            startActivity(intent)
        })


    }

}