package com.example.triviaapp.adapter

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.model.FlagColor
import com.example.triviaapp.utils.Constants
import com.google.gson.Gson

class FlagAdapter(var context: Context,var list:ArrayList<FlagColor>):RecyclerView.Adapter<FlagAdapter.ViewHolder>() {

    var isSelectedAll = false
    lateinit var allSelectedList:ArrayList<String>
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.flag_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        allSelectedList = ArrayList()

        var obj = list.get(position)
        holder.cricketerCheckbox.text = obj.color
        if (!isSelectedAll){
            holder.cricketerCheckbox.isChecked=false
        }else{
            holder.cricketerCheckbox.isChecked=true
            for (d in list){
                allSelectedList.add(d.color)
                saveArrayList(allSelectedList,Constants.FLAG_COLOR)
            }

        }

    }


    fun saveArrayList(list: ArrayList<String>?, key: String?) {
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun selectAll() {
        isSelectedAll = true
        notifyDataSetChanged()
    }

    fun unselectall() {
        isSelectedAll = false
        notifyDataSetChanged()
    }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        var cricketerCheckbox = itemView.findViewById(R.id.cricketCheckbox) as CheckBox
    }
}