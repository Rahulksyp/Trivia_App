package com.example.triviaapp.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.model.CricketerName
import com.example.triviaapp.utils.Constants

class CricketerAdapter(var contex:Context,var list:ArrayList<CricketerName>):RecyclerView.Adapter<CricketerAdapter.ViewHolder>() {

    var singleSelect:Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cricket_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sharedPreferences: SharedPreferences = contex.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE)

        var obj = list.get(position)
        holder.cricketerCheckbox.text = obj.cricketerName

        holder.cricketerCheckbox.isChecked = position==singleSelect
        holder.cricketerCheckbox.setOnClickListener(View.OnClickListener {
            if (position == singleSelect){
                holder.cricketerCheckbox.isChecked=false

            }else {
                singleSelect = position
                notifyDataSetChanged()
            }

        })

        holder.cricketerCheckbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {compoundButton, b ->
            if(b){
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putString(Constants.CRICKETER_NAME,list.get(position).cricketerName).apply()

            }
        })
    }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
//        var cricketerName = itemView.findViewById(R.id.cricketerName) as TextView
        var cricketerCheckbox = itemView.findViewById(R.id.cricketCheckbox) as CheckBox
    }
}