package com.example.triviaapp.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.model.CricketerName
import com.example.triviaapp.model.DataModelClass
import com.example.triviaapp.model.FlagColor
import com.example.triviaapp.model.History
import com.example.triviaapp.utils.Constants

class HistoryAdapter(var context: Context, var list:ArrayList<History>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hist_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var obj = list.get(position)
        holder.usernmae.text = obj.name
        holder.criName.text = obj.cricketer
        holder.flagColorName.text = obj.color


    }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        var usernmae = itemView.findViewById(R.id.userName) as TextView
        var criName = itemView.findViewById(R.id.criName) as TextView
        var flagColorName = itemView.findViewById(R.id.flagColorName) as TextView
    }
}