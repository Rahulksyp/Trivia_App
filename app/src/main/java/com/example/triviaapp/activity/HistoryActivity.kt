package com.example.triviaapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.adapter.CricketerAdapter
import com.example.triviaapp.adapter.HistoryAdapter
import com.example.triviaapp.helper.DatabaseHandler
import com.example.triviaapp.model.CricketerName
import com.example.triviaapp.model.DataModelClass
import com.example.triviaapp.model.History

class HistoryActivity : AppCompatActivity() {
    private lateinit var hisRv:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        hisRv = findViewById(R.id.hisRv)
        hisRv.layoutManager = LinearLayoutManager(this)

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val emp: List<DataModelClass> = databaseHandler.viewData()
        val history = ArrayList<History>()

        for (data in emp){
            history.add(History(data.userName,data.cricktrerName,data.flagColorName))
        }
        val adapter = HistoryAdapter(this,history)
        hisRv.adapter = adapter

    }
}